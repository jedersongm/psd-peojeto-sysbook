package br.edu.ifpb.ads.psd.projeto.dao;

import br.edu.ifpb.ads.psd.projeto.conexao.ClasseConexao;
import br.edu.ifpb.ads.psd.projeto.conexao.ConnectionFactory;
import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.interfaces.UsuarioDaoIF;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuarioDao implements UsuarioDaoIF {
    ConnectionFactory factory = new ConnectionFactory();
    ClasseConexao conexao = new ClasseConexao();
    Connection con;
    PreparedStatement pstm;
    
    public UsuarioDao() {
        try {
            con = factory.getConnection();
        } catch (Exception e) {
        }
    }
    
    public void inserir(Usuario usuario) throws SQLException {
//        Date dataNascimento = (Date) usuario.getDataNascimento();
//        java.sql.Date dataConvertida = new java.sql.Date(dataNascimento.getTime());
       try{ 
        conexao.abrir();
        String SQL = "insert into usuario(email, nome, apelido, senha, cidade, estado,foto, datanascimento, tipo) values (?,?,?,?,?,?,?,?,?)";
        pstm = con.prepareStatement(SQL);
        pstm.setString(1, usuario.getEmail());
        pstm.setString(2, usuario.getNome());
        pstm.setString(3, usuario.getApelido());
        pstm.setString(4, usuario.getSenha());
        pstm.setString(5, usuario.getCidade());
        pstm.setString(6, usuario.getEstado());
        pstm.setString(7, usuario.getFoto());
        pstm.setDate(8, (java.sql.Date) usuario.getDataNascimento());
        pstm.setBoolean(9, usuario.getTipo());
        pstm.executeUpdate();
    }catch(SQLException e){
           System.out.println(e);
    }
       finally {
            conexao.liberar();
        }
    }

    public void remover(String email) throws SQLException{
        try {
            conexao.abrir();
            
            String SQL = "delete from usuario where email=?";

            pstm = con.prepareStatement(SQL);
            pstm.setString(1, email);

            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } finally {
            conexao.liberar();
        }
    }

    public void atualizar(Usuario usuario) throws SQLException{
//        Date dataNascimento = usuario.getDataNascimento();
//        java.sql.Date dataConvertida = new java.sql.Date(dataNascimento.getTime());
        
        try {
            conexao.abrir();
            
            String SQL = "update Usuario set nome=?, apelido=?, senha=?, cidade=?, estado=?,"
                    + "dataNascimento=?, foto=?, tipo=? where email=?";
            
            pstm = con.prepareStatement(SQL);            
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getApelido());
            pstm.setString(3, usuario.getSenha());
            pstm.setString(4, usuario.getCidade());
            pstm.setString(5, usuario.getEstado());
            pstm.setDate(6, (java.sql.Date) usuario.getDataNascimento());
            pstm.setString(7, usuario.getFoto());
            pstm.setBoolean(8, usuario.getTipo());
            pstm.setString(9, usuario.getEmail());
            pstm.executeUpdate();
        
        } catch(Exception E) { 
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
    }

    public List<Usuario> pesquisar(String nome) throws SQLException {
         List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            conexao.abrir();
            
            String SQL = "SELECT * FROM Usuario WHERE nome ILIKE '%"+ nome +"%'";
            
            pstm = con.prepareStatement(SQL);
            
            ResultSet result = pstm.executeQuery();            
            
            while(result.next()){
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setEmail(result.getString("email"));
                usuario.setNome(result.getString("nome"));
                usuario.setApelido(result.getString("apelido"));
                usuario.setSenha(result.getString("senha"));
                usuario.setCidade(result.getString("cidade"));
                usuario.setEstado(result.getString("estado"));
                usuario.setDataNascimento(result.getDate("dataNascimento"));
                usuario.setFoto(result.getString("foto"));
                usuario.setTipo(result.getBoolean("tipo"));
                usuarios.add(usuario);
            }
            return usuarios;
            
        } catch(Exception E) { 
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
        return null;
    }
    
    public boolean logar(String email, String senha) throws SQLException {

        try {
            conexao.abrir();
       
            String SQL = "SELECT * FROM usuario WHERE email = ?";
            pstm = con.prepareStatement(SQL);
            pstm.setString(1, email);
            
            ResultSet result = pstm.executeQuery();
            
            Usuario usuario = new Usuario();
            
            while(result.next()){
                usuario.setId(result.getInt("id"));
                usuario.setEmail(result.getString("email"));
                usuario.setNome(result.getString("nome"));
                usuario.setApelido(result.getString("apelido"));
                usuario.setSenha(result.getString("senha"));
                usuario.setCidade(result.getString("cidade"));
                usuario.setEstado(result.getString("estado"));
                usuario.setDataNascimento(result.getDate("dataNascimento"));
                usuario.setFoto(result.getString("foto"));
                usuario.setTipo(result.getBoolean("tipo"));
            }
            
            if (usuario.getEmail()!= null && usuario.getSenha() != null){
                if (usuario.getEmail().equalsIgnoreCase(email) 
                        && usuario.getSenha().equalsIgnoreCase(senha)){
                    return true;
                }
                else 
                    return false;
            }
            else
                return false;
        } catch(Exception E) { 
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
        return false;
    }
    
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            conexao.abrir();
            
            String sql = "SELECT * FROM usuario";
            
            pstm = con.prepareStatement(sql);
            
            ResultSet result = pstm.executeQuery();
  
            while(result.next()){
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setNome(result.getString("nome"));
                usuario.setApelido(result.getString("apelido"));
                usuario.setEmail(result.getString("email"));
                usuario.setCidade(result.getString("cidade"));
                usuario.setEstado(result.getString("estado"));
                usuario.setDataNascimento(result.getDate("datanascimento"));
                usuario.setFoto(result.getString("foto"));
                usuario.setTipo(result.getBoolean("tipo"));
                
                usuarios.add(usuario);
            }
            return usuarios;
        } catch(Exception E) { 
            JOptionPane.showMessageDialog(null, E);
        } finally {
            conexao.liberar();
        }
        return null;
    }
    
    public void atualizarImagemPerfil(String path, int idUsuario) throws SQLException {
        try {
            conexao.abrir();
            String sql = "UPDATE usuario SET foto=? WHERE id=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, path);
            stat.setInt(2, idUsuario);
            stat.executeUpdate();
        }catch(Exception E) { 
            E.printStackTrace();
        }
        finally {
            conexao.liberar();
        }
    }
    
    public Usuario getUsuario(String email) throws SQLException {
        try {
            conexao.abrir();
            String sql = "SELECT * FROM usuario WHERE email=?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, email);
            ResultSet result = pstm.executeQuery();
            
            Usuario usuario = new Usuario();
            if (result.next()) {
                usuario.setId(result.getInt("id"));
                usuario.setEmail(result.getString("email"));
                usuario.setNome(result.getString("nome"));
                usuario.setApelido(result.getString("apelido"));
                usuario.setSenha(result.getString("senha"));
                usuario.setCidade(result.getString("cidade"));
                usuario.setEstado(result.getString("estado"));
                usuario.setDataNascimento(result.getDate("dataNascimento"));
                usuario.setFoto(result.getString("foto"));
                usuario.setTipo(result.getBoolean("tipo"));
            }
                return usuario;
              } catch(Exception E) { 
                    E.printStackTrace();
               } finally {
            conexao.liberar();
        }
        return null;    
    }

       
    
    
    public Usuario getUsuario(Integer id) throws SQLException {
        try {
            conexao.abrir();
            String sql = "SELECT * FROM usuario WHERE id=?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();
            
            Usuario usuario = new Usuario();
            if (result.next()) {
                
                usuario.setId(result.getInt("id"));
                usuario.setEmail(result.getString("email"));
                usuario.setNome(result.getString("nome"));
                usuario.setApelido(result.getString("apelido"));
                usuario.setSenha(result.getString("senha"));
                usuario.setCidade(result.getString("cidade"));
                usuario.setEstado(result.getString("estado"));
                usuario.setDataNascimento(result.getDate("dataNascimento"));
                usuario.setFoto(result.getString("foto"));
                usuario.setTipo(result.getBoolean("tipo"));
            }
                return usuario;
              } catch(Exception E) { 
                    E.printStackTrace();
               } finally {
            conexao.liberar();
        }
        return null;
       
    }
    
   

    @Override
    public boolean verificarExistenciaEmail(String email) throws SQLException {
        try {
            conexao.abrir();
            String sql = "{?=call existenciaEmail(?)}";
            boolean retorno;
            try (CallableStatement stat = con.prepareCall(sql)) {
                stat.registerOutParameter(1, java.sql.Types.BOOLEAN);
                stat.setString(2, email);
                stat.execute();
                retorno = stat.getBoolean(1);
            }
            return retorno;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    
}
