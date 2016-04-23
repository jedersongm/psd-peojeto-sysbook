/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.servlets;

import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeAmizade;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeUsuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jederson
 */


public class AbrirUsuario extends HttpServlet {

    private GerenciadorDeUsuario usuarioGer = new GerenciadorDeUsuario();
    private GerenciadorDeAmizade amizadeGer = new GerenciadorDeAmizade();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Usuario visitante;
        try {
            Usuario user = (Usuario) request.getSession().getAttribute("usuario");
            String emailUser = user.getEmail();
            if(id != null){
                visitante = usuarioGer.getUsuario(id);
            }else{
                visitante = (Usuario) request.getSession().getAttribute("visitante");
            }            
            Boolean isAmigo = amizadeGer.isAmigo(emailUser,visitante.getEmail());
            if(isAmigo){
                request.setAttribute("status", "amigo");
            }else{
                boolean isPendente = amizadeGer.isPendente(emailUser, visitante.getEmail());
                if(isPendente){
                    request.setAttribute("status", "pendente");
                }else{
                    isPendente = amizadeGer.isPendente(visitante.getEmail(),emailUser);
                    if(isPendente){
                        request.setAttribute("status", "responder");
                    }else{
                        request.setAttribute("status", "nada");
                    }
                }
            }
            request.getSession().setAttribute("visitante", visitante);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
