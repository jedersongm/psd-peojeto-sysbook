package br.edu.ifpb.ads.psd.projeto.servlets;


import br.edu.ifpb.ads.psd.projeto.entidades.Grupo;
import br.edu.ifpb.ads.psd.projeto.entidades.Livro;
import br.edu.ifpb.ads.psd.projeto.entidades.Topico;
import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeGrupo;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeLivros;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeTopico;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorParticipaGrupo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControleVerGrupo extends HttpServlet{
    GerenciadorDeGrupo grupoGer = new GerenciadorDeGrupo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);  
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            String emailUser = ((Usuario) req.getSession().getAttribute("usuario")).getEmail();
            List<Grupo> grupos = grupoGer.pesquisarGrupos(emailUser);
            req.getSession().setAttribute("grupos", grupos);
            
            String nomeGrupo = req.getParameter("nome");
            Grupo g = new GerenciadorDeGrupo().pesquisarGrupo(nomeGrupo);
            int idGrupo = g.getId();

            List<Usuario> usuariosDoGrupo = new GerenciadorParticipaGrupo().retornaUsuariosDeUmGrupo(idGrupo);
            
            List<Livro> listaLivros = new GerenciadorDeLivros().listarLivros();
            List<String> nomeLivros = new ArrayList();
            for(int i=0;i<listaLivros.size();i++){
                nomeLivros.add(listaLivros.get(i).getTitulo());   
            }
            boolean p = new GerenciadorParticipaGrupo().isParticipa(emailUser, idGrupo);
            req.getSession().setAttribute("grupo", g);
            req.setAttribute("nomeDosLivros", nomeLivros);
            req.getSession().setAttribute("usuariosDoGrupo", usuariosDoGrupo);
            req.getSession().setAttribute("participa", p);
            req.getRequestDispatcher("verGrupo.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ControleVerGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
