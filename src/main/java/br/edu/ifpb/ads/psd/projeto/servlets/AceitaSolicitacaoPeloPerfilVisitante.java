/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.servlets;

import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeAmizade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author jederson
 */

public class AceitaSolicitacaoPeloPerfilVisitante extends HttpServlet {

    GerenciadorDeAmizade amizadeGer = new GerenciadorDeAmizade();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        boolean resposta = Boolean.parseBoolean(request.getParameter("resposta"));
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        Usuario visitante = (Usuario) request.getSession().getAttribute("visitante");
        
        try{
        if(resposta){           
            amizadeGer.aceitaAmizade(user.getEmail(), visitante.getEmail());            
        }else{
            amizadeGer.recusaAmizade(user.getEmail(),visitante.getEmail());           
        }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        request.getRequestDispatcher("user.jsp").forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
