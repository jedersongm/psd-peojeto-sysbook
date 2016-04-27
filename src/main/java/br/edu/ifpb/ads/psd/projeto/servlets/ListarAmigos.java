/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.servlets;

import br.edu.ifpb.ads.psd.projeto.entidades.Amizade;
import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeAmizade;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jederson
 */
public class ListarAmigos extends HttpServlet {
    GerenciadorDeAmizade amizadeGer = new GerenciadorDeAmizade();
    GerenciadorDeUsuario userGer = new GerenciadorDeUsuario();
    
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

       request.setCharacterEncoding("UTF-8");
       Usuario user = (Usuario) request.getSession().getAttribute("usuario");
               
        List listaDeAmigos = new ArrayList();
        try {
            List<Amizade> l = amizadeGer.listarAmizade(user.getEmail());
            if(l != null){
                for (Amizade amizade : l) {
                    Usuario u = userGer.getUsuario(amizade.getEmailAmigo());
                    listaDeAmigos.add(u);
                }
            }else{
                listaDeAmigos = null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
 
        request.setAttribute("usuarios", listaDeAmigos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listarUsuarios.jsp");
        dispatcher.forward(request, response);

    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
