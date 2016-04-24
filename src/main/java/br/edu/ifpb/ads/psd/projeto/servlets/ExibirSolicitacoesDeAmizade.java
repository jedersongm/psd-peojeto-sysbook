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
import com.sun.jmx.remote.internal.ArrayQueue;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jederson
 */
public class ExibirSolicitacoesDeAmizade extends HttpServlet {

    GerenciadorDeAmizade amizadeGer = new GerenciadorDeAmizade();
    GerenciadorDeUsuario userGer = new GerenciadorDeUsuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        List usuarios = new ArrayList();
        try {
            List<Amizade> solicitações = amizadeGer.listarSolicitações(user.getEmail());
            if(solicitações != null){
              for (Amizade a : solicitações) {
                  Usuario solicitante = userGer.getUsuario(a.getEmailUsuario());
                  usuarios.add(solicitante);
              }
            } else usuarios = null;
            request.setAttribute("solicitantes", usuarios);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        request.getRequestDispatcher("solicitacoesAmizade.jsp").forward(request, response);
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
