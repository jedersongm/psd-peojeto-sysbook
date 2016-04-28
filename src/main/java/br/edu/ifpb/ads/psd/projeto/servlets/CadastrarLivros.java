package br.edu.ifpb.ads.psd.projeto.servlets;


import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeLivros;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorImagem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class CadastrarLivros extends HttpServlet {

  private GerenciadorDeLivros livroGer = new GerenciadorDeLivros();

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> itens;
                itens = (ArrayList<FileItem>) upload.parseRequest(request);

                String isbn = itens.get(1).getString("UTF-8");
                String titulo = itens.get(2).getString("UTF-8");
                String editora = itens.get(4).getString("UTF-8");
                String tema = itens.get(6).getString("UTF-8");
                Integer anopublicacao = Integer.parseInt(itens.get(3).getString("UTF-8"));
                String autores = itens.get(5).getString("UTF-8");
                
                String realPath = getServletContext().getRealPath("/imagensLivros");
                String nomeImagem = titulo;
                String foto;
                if (itens.get(4).getString().equals("")) {
                    foto = "images/book.ico";
                } else {
                    new GerenciadorImagem().inserirImagemPerfil(itens.get(0), realPath, nomeImagem);
                    foto = "imagensLivros/" + nomeImagem + ".jpg";
                }
                
//                String dataNascimento = itens.get(3).getString("UTF-8");                
//                Usuario user = new Usuario();
//                user = usuarioGer.pesquisarUsuarioEmail(email);
//                if(user.getEmail() != null){
//                    request.setAttribute("mensagem", "Email já cadastrado!");
//                    request.getRequestDispatcher("cadastrarUsuario.jsp").forward(request, response);
//                }else{
                    livroGer.adicionarLivros(isbn, titulo, anopublicacao, editora, foto, tema, autores);
//                }

      } catch (SQLException ex) {
          Logger.getLogger(CadastrarLivros.class.getName()).log(Level.SEVERE, null, ex);
      }     catch (FileUploadException ex) {
                Logger.getLogger(CadastrarLivros.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
            request.getRequestDispatcher("ListarLivros").forward(request, response);
    }
}


   
    