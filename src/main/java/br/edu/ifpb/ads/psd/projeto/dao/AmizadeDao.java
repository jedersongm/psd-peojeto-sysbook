package br.edu.ifpb.ads.psd.projeto.dao;

import br.edu.ifpb.ads.psd.projeto.conexao.ClasseConexao;
import br.edu.ifpb.ads.psd.projeto.conexao.ConnectionFactory;
import br.edu.ifpb.ads.psd.projeto.entidades.Amizade;
import br.edu.ifpb.ads.psd.projeto.interfaces.AmizadeDaoIF;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AmizadeDao implements AmizadeDaoIF {

    ConnectionFactory factory = new ConnectionFactory();
    ClasseConexao conexao = new ClasseConexao();
    Connection con;
    PreparedStatement pstm;

    public AmizadeDao() {
        try {
            con = factory.getConnection();
        } catch (Exception e) {
        }
    }
    
    @Override
    public void inserir(Amizade amizade) throws SQLException {

        try {
            conexao.abrir();

            String SQL = "INSERT INTO Amizade(emailUsuario, emailAmigo, isAmigo, since) VALUES (?,?,?,?)";
                        
            pstm = con.prepareStatement(SQL);
            pstm.setString(1, amizade.getEmailUsuario());
            pstm.setString(2, amizade.getEmailAmigo());
            pstm.setBoolean(3, amizade.isAmigo());
            pstm.setString(4, amizade.getSince());

            pstm.executeUpdate();

        } finally {
            conexao.liberar();
        }
    }

    @Override
    public void remover(Amizade amizade) throws SQLException {
        try {
            conexao.abrir();

            String SQL = "DELETE FROM Amizade WHERE emailUsuario= ? AND emailAmigo= ?";

            pstm = con.prepareStatement(SQL);
            pstm.setString(1, amizade.getEmailUsuario());
            pstm.setString(2, amizade.getEmailAmigo());

            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } finally {
            conexao.liberar();
        }
    }

    @Override
    public void atualizar(Amizade amizade) throws SQLException {

        try {
            conexao.abrir();

            String SQL = "UPDATE Amizade SET isAmigo= ? since = ? WHERE emailUsuario=? AND emailAmigo=?";

            pstm = con.prepareStatement(SQL);
            pstm.setBoolean(1, amizade.isAmigo());
            pstm.setString(2, amizade.getSince());
            pstm.setString(3, amizade.getEmailUsuario());
            pstm.setString(4, amizade.getEmailAmigo());
            pstm.executeUpdate();
        } catch (Exception E) {
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
    }
    
    @Override
    public boolean aceita(Amizade amizade) throws SQLException{
        boolean result = true;
        result &= acceptFriend(amizade);
        result &=  insertNewFriend(amizade);
        return result;
    }
    
    private boolean acceptFriend(Amizade amizade) throws SQLException{
        try{
            conexao.abrir();
            String SQL = "UPDATE Amizade SET isAmigo = ?  WHERE emailUsuario = ? AND emailAmigo = ?";
            pstm = con.prepareStatement(SQL);
            pstm.setBoolean(1, true);
            //pstm.setString(2, amizade.getSince());
            pstm.setString(2, amizade.getEmailUsuario());
            pstm.setString(3, amizade.getEmailAmigo());
            if(pstm.executeUpdate() > 0) return true;          
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexao.liberar();
        }
        return false;
    }
    
    private boolean insertNewFriend(Amizade amizade) throws SQLException{
        try{
            conexao.abrir();
            
            String sql = "INSERT INTO Amizade(isAmigo, since, emailAmigo, emailUsuario) VALUES (?,?,?,?)";
            
            pstm = con.prepareStatement(sql);
            pstm.setBoolean(1, true);
            pstm.setString(2, amizade.getSince());
            pstm.setString(3, amizade.getEmailUsuario());
            pstm.setString(4, amizade.getEmailAmigo());
            if(pstm.executeUpdate() > 0) return true; 
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexao.liberar();
        }
        return false;
    }
    @Override
    public boolean isAmigo(String emailUsuario, String emailAmigo) throws SQLException {
        try{
            conexao.abrir();
            
            String sql = "SELECT * FROM Amizade WHERE emailUsuario = ? AND emailAmigo = ? AND isAmigo = ?";
            
            pstm = con.prepareStatement(sql);
            pstm.setString(1, emailUsuario);
            pstm.setString(2, emailAmigo);
            pstm.setBoolean(3, true);
            
            ResultSet rs = pstm.executeQuery();
            
            return rs.next();
        }catch(SQLException  ex){
            ex.printStackTrace();
        }finally{
            conexao.liberar();                   
        }
        return false;
    }
    
    @Override
    public boolean isPendente(String emailUsuario, String emailAmigo) throws SQLException {
        try{
            conexao.abrir();
            
            String sql = "SELECT * FROM Amizade WHERE emailUsuario = ? AND emailAmigo = ? AND isAmigo = false";
            
            pstm = con.prepareStatement(sql);
            pstm.setString(1, emailUsuario);
            pstm.setString(2, emailAmigo);                     
            
            ResultSet rs = pstm.executeQuery();
            
            return rs.next();
        }catch(SQLException  ex){
            ex.printStackTrace();
        }finally{
            conexao.liberar();                   
        }
        return false;
    }
    
    @Override
    public Amizade pesquisar(String emailUsuario, String emailAmigo) throws SQLException {
        try {
            conexao.abrir();

            String SQL = "SELECT * FROM Amizade WHERE emailUsuario = ? AND emailAmigo = ?";

            pstm = con.prepareStatement(SQL);
            pstm.setString(1, emailUsuario);
            pstm.setString(2, emailAmigo);            

            ResultSet result = pstm.executeQuery();

            Amizade amizade = new Amizade();

            while (result.next()) {
                amizade.setEmailUsuario(result.getString("emailUsuario"));
                amizade.setEmailAmigo(result.getString("emailAmigo"));
                amizade.setSince(result.getString("since"));
                amizade.setAmigo(result.getBoolean("isAmigo"));
            }
            return amizade;

        } catch (Exception E) {
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
        return null;
    }

    @Override
    public ArrayList<Amizade> listar(String email) throws SQLException {
        try {
            conexao.abrir();

            String SQL = "SELECT * FROM Amizade WHERE emailUsuario = ? OR emailAmigo = ? AND isAmigo = ?";

            pstm = con.prepareStatement(SQL);
            pstm.setString(1, email);
            pstm.setString(2, email);
            pstm.setBoolean(3, true);

            ResultSet result = pstm.executeQuery();

            ArrayList<Amizade> listaAmizades = new ArrayList<Amizade>();

            while (result.next()) {
                Amizade amizade = new Amizade();

                amizade.setEmailUsuario(result.getString("emailUsuario"));
                amizade.setEmailAmigo(result.getString("emailAmigo"));
                amizade.setSince(result.getString("since"));
                amizade.setAmigo(result.getBoolean("isAmigo"));
                
                listaAmizades.add(amizade);
            }
            return listaAmizades.isEmpty()? null: listaAmizades;

        } catch (Exception E) {
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
        return null;
    }
    
    @Override
    public ArrayList<Amizade> listarSolicitacoes(String email) throws SQLException{
        
        try{
            conexao.abrir();
            
            String sql = "SELECT * FROM Amizade WHERE emailAmigo = ? AND isAmigo ? ORDER BY since DESC";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setBoolean(2, false);
            
            ResultSet result = pstm.executeQuery();
            
            ArrayList<Amizade> solicitações = new ArrayList<>();
            
            while (result.next()) {                
                Amizade amizade = new Amizade();

                amizade.setEmailUsuario(result.getString("emailUsuario"));
                amizade.setEmailAmigo(result.getString("emailAmigo"));
                amizade.setSince(result.getString("since"));
                amizade.setAmigo(result.getBoolean("isAmigo"));
                
                solicitações.add(amizade);
            }
            return (solicitações.isEmpty()) ? null : solicitações;
        }catch(SQLException | ParseException ex){
            ex.printStackTrace();
        }finally{
            conexao.liberar();
        }
        
        return null;
    }
}
