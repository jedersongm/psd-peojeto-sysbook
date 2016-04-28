/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.dao;

import br.edu.ifpb.ads.psd.projeto.conexao.ClasseConexao;
import br.edu.ifpb.ads.psd.projeto.conexao.ConnectionFactory;
import br.edu.ifpb.ads.psd.projeto.interfaces.ParticipaGrupoDaoIF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author reginaldo
 */
public class ParticipaGrupoDao implements ParticipaGrupoDaoIF{

    ConnectionFactory factory = new ConnectionFactory();
    ClasseConexao conexao = new ClasseConexao();
    Connection con;
    PreparedStatement pstm;
    
    public ParticipaGrupoDao() {
        try {
            con = factory.getConnection();
        } catch (Exception e) {
        }
    }
    @Override
    public boolean adicionaRelacao(String emailUsuario, int idGrupo) throws SQLException {
        boolean result = false;
        
        String sql = "insert into participagrupo (emailusuario, idgrupo, useradmin) values (?, ?, ?)";
        
        try {
            conexao.abrir();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, emailUsuario);
            stmt.setInt(2, idGrupo);
            stmt.setBoolean(3, false);
            stmt.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            conexao.liberar();
        }
        
        return result;
    }

    @Override
    public boolean adicionaRelacaoAdmin(String emailUsuario, int idGrupo) throws SQLException {
        boolean result = false;
      
        
        String sql = "insert into participagrupo (emailusuario, idgrupo, useradmin) values (?, ?, ?)";
        
        try {
            conexao.abrir();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, emailUsuario);
            stmt.setInt(2, idGrupo);
            stmt.setBoolean(3, true);
            stmt.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conexao.liberar();
        }
        
        return result;
    }
    
    @Override
    public List<Integer> retornaGrupos(String emailUsuario) throws SQLException{
        List<Integer> idGrupos = new ArrayList<>();
        
        
        String sql = "select idgrupo from participagrupo where emailusuario = "+emailUsuario+"";
        
        try {
            conexao.abrir();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            
            while(rs.next()){
                idGrupos.add(rs.getInt("idgrupo"));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conexao.liberar();
        }
        
        return idGrupos;
    }
    
    @Override
    public boolean isParticipa(String emailUsuario, int idGrupo) throws SQLException{
        boolean result = false;
        
        
        
            conexao.abrir();
            String sql = "select * from participaGrupo where emailusuario = ? and idGrupo = ?";
        try {
            conexao.abrir();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, emailUsuario);
            pstm.setInt(2, idGrupo);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                result = true;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            conexao.liberar();
        }
        
        return result;
    }
    
    @Override
    public List<String> retornaUsuariosDeUmGrupo(int idGrupo) throws SQLException{
        List<String> emailDeUsuarios = new ArrayList<>();
        
       
        try{
            conexao.abrir();
            String sql = "select emailusuario from participagrupo where idGrupo = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idGrupo);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                emailDeUsuarios.add(rs.getString("emailusuario"));
            }
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            conexao.liberar();
        }
        
        
        return emailDeUsuarios;
    }
}
