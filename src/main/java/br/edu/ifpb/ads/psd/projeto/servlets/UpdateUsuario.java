package br.edu.ifpb.ads.psd.projeto.servlets;


import br.edu.ifpb.ads.psd.projeto.converterInformacao.ConverterData;
import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeUsuario;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorImagem;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.websocket.Session;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpdateUsuario extends HttpServlet {

    private GerenciadorDeUsuario usuarioGer = new GerenciadorDeUsuario();
    ConverterData converter = new ConverterData();
    public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> itens;
                itens = (ArrayList<FileItem>) upload.parseRequest(request);
                Usuario u = (Usuario) request.getSession().getAttribute("usuario");

                String email = itens.get(4).getString("UTF-8");
                String nome = itens.get(1).getString("UTF-8");
                String apelido = itens.get(2).getString("UTF-8");
                String senha = itens.get(5).getString("UTF-8");
                String cidade = itens.get(7).getString("UTF-8");
                String estado = itens.get(6).getString("UTF-8");
               
                u.setEmail(email);
                u.setNome(nome);
                u.setApelido(apelido);
                u.setSenha(senha);
                u.setCidade(cidade);
                u.setEstado(estado);
                
                String realPath = getServletContext().getRealPath("/imagensPerfil");
                String nomeImagem = nome;
                String foto;
                if (itens.get(0).getString().equals("")) {
                    foto = "images/usuario.png";
                } else {
                    new GerenciadorImagem().inserirImagemPerfil(itens.get(0), realPath, nomeImagem);
                    foto = "imagensPerfil/" + nomeImagem + ".jpg";
                }
                u.setFoto(foto);
                String dataNascimento = itens.get(3).getString("UTF-8");
                u.setDataNascimento(converter.stringParaDate(dataNascimento));                
//                Usuario user = new Usuario(email, nome, apelido, senha, cidade, estado, foto, converter.stringParaDate(dataNascimento));
                usuarioGer.atualizaUsuario(u);
                request.getSession().setAttribute("usuario", u);

            } catch (SQLException ex) {
            Logger.getLogger(CadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileUploadException ex) {
            Logger.getLogger(CadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
