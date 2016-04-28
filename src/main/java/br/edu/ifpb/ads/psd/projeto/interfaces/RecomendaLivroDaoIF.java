/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jederson
 */
public interface RecomendaLivroDaoIF {
    
    public boolean adicionarRelação(String emailUsuario, String emailDestino, String isbn) throws SQLException;
    
    public void responder(String email,String isbn) throws SQLException;
    
    public List<String> listarLivrosRecomendados(String email) throws SQLException;
}
