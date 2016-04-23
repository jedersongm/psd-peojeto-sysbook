package br.edu.ifpb.ads.psd.projeto.servlets;

import br.edu.ifpb.ads.psd.projeto.entidades.Grupo;
import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeGrupo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControleCadastroGrupo", urlPatterns = {"/ControleCadastroGrupo"})
public class ControleCadastroGrupo extends HttpServlet {

    GerenciadorDeGrupo grupoGer = new GerenciadorDeGrupo();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String nomeDoGrupo = req.getParameter("groupName");
            String descricao = req.getParameter("groupDescription");
            
            String emailUsuario = ((Usuario) req.getSession().getAttribute("usuario")).getEmail();
            int IdUsuario = ((Usuario) req.getSession().getAttribute("usuario")).getId();
            
            
            grupoGer.adicionarGrupo(emailUsuario, nomeDoGrupo, descricao);
            grupoGer.adicionaRelacaoAdmin(emailUsuario, grupoGer.retornaMaximo());
                        
            List<Grupo> grupos = grupoGer.retornaGruposDoUsuario(emailUsuario);
            if (!grupos.isEmpty()) {
                req.getSession().setAttribute("grupos", grupos);
            }else{
                req.getSession().setAttribute("grupos",null);
            }
            
            req.getRequestDispatcher("visualizaGrupos.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ControleCadastroGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
