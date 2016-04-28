/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.gerenciadores;

import br.edu.ifpb.ads.psd.projeto.fabricas.DaoFactory;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jederson
 */
public class GerenciadorRecomendaLivro {
    
    public boolean adicionarRelação(String emailUsuario, String emailDestino, String isbn) throws SQLException{
       return DaoFactory.creatFactory().criaRecomendaLivroDao().adicionarRelação(emailUsuario, emailDestino, isbn);        
    }
    
    public void responder(String email,String isbn) throws SQLException{
        DaoFactory.creatFactory().criaRecomendaLivroDao().responder(email, isbn);
    }
    
    public List<String> listarLivrosRecomendados(String email) throws SQLException{
        return DaoFactory.creatFactory().criaRecomendaLivroDao().listarLivrosRecomendados(email);
    }
}
