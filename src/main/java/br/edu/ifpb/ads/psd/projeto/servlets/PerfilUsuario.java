/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.servlets;

import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Reginaldo
 */
@WebServlet(name = "PerfilUsuario", urlPatterns = {"/PerfilUsuario"})
public class PerfilUsuario extends HttpServlet {

    private GerenciadorDeUsuario usuGer = new GerenciadorDeUsuario();
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

     @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        UsuarioDao dao = new UsuarioDao();
       request.setCharacterEncoding("UTF-8");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/mostarUsuario.jsp");
        Usuario usuario = new Usuario();
         try {
             String email = request.getParameter("email");
             usuario = usuGer.getUsuario(email);
         } catch (SQLException ex) {
             Logger.getLogger(ListarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
         }
        request.setAttribute("perfil", usuario);

        dispatcher.forward(request, response);

    }
    

}
