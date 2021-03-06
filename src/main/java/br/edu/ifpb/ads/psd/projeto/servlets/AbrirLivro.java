/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.servlets;


import br.edu.ifpb.ads.psd.projeto.entidades.Livro;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeLivros;
import java.io.IOException;import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Reginaldo
 */
public class AbrirLivro extends HttpServlet {
    
    private GerenciadorDeLivros livroGer = new GerenciadorDeLivros();

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    
        request.setCharacterEncoding("UTF-8");

        String livro = request.getParameter("isbn");
        RequestDispatcher dispatcher = request.getRequestDispatcher("book.jsp");
        try{
            Livro l = livroGer.pesquisarLivro(livro);
            request.setAttribute("livro",l);
            dispatcher.forward(request, response);
        }catch(Exception ex){
            ex.printStackTrace();
        }

   }
}
