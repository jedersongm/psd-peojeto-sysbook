/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.servlets;

import br.edu.ifpb.ads.psd.projeto.entidades.Livro;
import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeLivros;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorRecomendaLivro;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jederson
 */
public class AbrirLivroRecomendado extends HttpServlet {
    
    GerenciadorDeLivros livroGer = new GerenciadorDeLivros();
    GerenciadorRecomendaLivro rlGer = new GerenciadorRecomendaLivro();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        String livro = request.getParameter("isbn");
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookRecomendado.jsp");
        try{
            Livro l = livroGer.pesquisarLivro(livro);
            request.setAttribute("livroRecomendado",l);
            rlGer.responder(user.getEmail(), l.getIsbn());
            dispatcher.forward(request, response);
        }catch(Exception ex){
            ex.printStackTrace();
        }
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
