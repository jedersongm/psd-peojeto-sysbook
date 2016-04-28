/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.dao;

import br.edu.ifpb.ads.psd.projeto.conexao.ClasseConexao;
import br.edu.ifpb.ads.psd.projeto.conexao.ConnectionFactory;
import br.edu.ifpb.ads.psd.projeto.interfaces.RecomendaLivroDaoIF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jederson
 */
public class RecomendaLivroDao implements RecomendaLivroDaoIF{
    
    ConnectionFactory factory = new ConnectionFactory();
    ClasseConexao conexao = new ClasseConexao();
    Connection con;
    PreparedStatement pstm;

    public RecomendaLivroDao() {
        try {
            con = factory.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean adicionarRelação(String emailUsuario, String emailDestino, String isbn) throws SQLException {
        
        try{
            conexao.abrir();
            
            String sql = "INSERT INTO recomendalivro(emailUsuario, emailDestino, isbnLivro, visualized) VALUES(?,?,?,?)";
            
            pstm = con.prepareStatement(sql);
            pstm.setString(0, emailUsuario);
            pstm.setString(1, emailDestino);
            pstm.setString(2, isbn);
            pstm.setBoolean(3, false);
            return pstm.executeUpdate() > 0;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexao.liberar();
        }
        return false;
    }

    @Override
    public void responder(String email, String isbn) throws SQLException {
        try{
            conexao.abrir();
            
            String sql = "UPDATE recomendalivro SET visualized = true WHERE  emailDestino = '"+email+"' AND isbnLivro = '"+isbn+"'";
            
            pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexao.liberar();
        }
    }

    @Override
    public List<String> listarLivrosRecomendados(String email) throws SQLException {
        try{
            conexao.abrir();
            
            String sql = "SELECT isbnLivro FROM recomendalivro WHERE emailDestino =' "+email+"' AND visualized = false";
            
            pstm = con.prepareStatement(sql);
            
            List<String> livros = new ArrayList<>();
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                livros.add(rs.getString("isbnLivro"));
            }
            return livros.isEmpty()? null : livros;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexao.liberar();
        }
        return null;
    }

    
    
    
}
