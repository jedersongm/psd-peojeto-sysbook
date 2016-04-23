package br.edu.ifpb.ads.psd.projeto.dao;


import br.edu.ifpb.ads.psd.projeto.conexao.ClasseConexao;
import br.edu.ifpb.ads.psd.projeto.conexao.ConnectionFactory;
import br.edu.ifpb.ads.psd.projeto.entidades.Grupo;
import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.interfaces.GrupoDaoIF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GrupoDao implements GrupoDaoIF{
    ConnectionFactory factory = new ConnectionFactory();
    ClasseConexao conexao = new ClasseConexao();
    Connection con;
    PreparedStatement pstm;
    
    public GrupoDao() {
        try {
            con = factory.getConnection();
        } catch (Exception e) {
        }
    }
    
    public void inserir(Grupo grupo) throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "insert into Grupo(emailUsuario, nome, descricao) values (?,?,?)";

            pstm = con.prepareStatement(SQL);
            pstm.setString(1, grupo.getEmailUsuario());
            pstm.setString(2, grupo.getNome());
            pstm.setString(3, grupo.getDescricao());

            pstm.executeUpdate();

        } finally {
            conexao.liberar();
        }
    }

    public void remover(Grupo g) throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "delete from grupo where nome=?";

            pstm = con.prepareStatement(SQL);
            pstm.setString(1, g.getNome());

            pstm.executeUpdate();
        } finally {
            conexao.liberar();
        }
    }

    public void atualizar(Grupo g) throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "update Grupo set nome=?, descricao=? where id=?";
            
            pstm = con.prepareStatement(SQL);
            pstm.setString(1, g.getNome());
            pstm.setString(2, g.getDescricao());
            pstm.setInt(3, g.getId());

            pstm.executeUpdate();
        } catch(Exception E) { 
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
    }

    public Grupo pesquisar(String nome) throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "select * from grupo where nome ilike '%"+ nome +"%'";
            
            pstm = con.prepareStatement(SQL);
            
            ResultSet result = pstm.executeQuery();
            
            Grupo grupo = new Grupo();
            
            while(result.next()){
                grupo.setId(result.getInt("id"));
                grupo.setNome(result.getString("nome"));
                grupo.setDescricao(result.getString("descricao"));
            }
            return grupo;
            
        } catch(Exception E) { 
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
        return null;
    }
    
//    public List<Grupo> pesquisarGrupos(String nome) throws SQLException {
//        try {
//            conexao.abrir();
//            
//            String SQL = "select id, nome, descricao from Grupo where nome ilike '%"+ nome +"%'";
//            
//            pstm = con.prepareStatement(SQL);
//            
//            ResultSet result = pstm.executeQuery();
//            
//            List<Grupo> grupos = new ArrayList<Grupo>();
//            
//            while(result.next()){
//                Grupo grupo = new Grupo();
//                grupo.setId(result.getInt("id"));
//                grupo.setNome(result.getString("nome"));
//                grupo.setDescricao(result.getString("descricao"));
//                
//                grupos.add(grupo);
//            }
//            return grupos.isEmpty() ? null : grupos;
//        } catch(Exception E) { 
//            E.printStackTrace();
//        } finally {
//            conexao.liberar();
//        }
//        return null;
//    }
    
    @Override
    public List<Grupo> pesquisarGrupos(String email) throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "select id, nome, descricao from Grupo where emailusuario = ?";
            
            pstm = con.prepareStatement(SQL);
            pstm.setString(1, email);
            ResultSet result = pstm.executeQuery();
            
            List<Grupo> grupos = new ArrayList<Grupo>();
            
            while(result.next()){
                Grupo grupo = new Grupo();
                grupo.setId(result.getInt("id"));
                grupo.setNome(result.getString("nome"));
                grupo.setDescricao(result.getString("descricao"));
                
                grupos.add(grupo);
            }
            return grupos.isEmpty() ? null : grupos;
        } catch(Exception E) { 
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
        return null;
    }
    
    public int retornaMaximo() throws SQLException {
        int i = 0;
        String sql = "select max(id) from grupo";

        try {
            conexao.abrir();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conexao.liberar();
        }

        return i;
    }

    @Override
    public List<Grupo> pesquisarGrupos(Integer id) throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "select id, nome, descricao from Grupo where id = ?";
            
            pstm = con.prepareStatement(SQL);
            
            ResultSet result = pstm.executeQuery();
            
            List<Grupo> grupos = new ArrayList<Grupo>();
            
            while(result.next()){
                Grupo grupo = new Grupo();
                grupo.setId(result.getInt("id"));
                grupo.setEmailUsuario("emailusuario");
                grupo.setNome(result.getString("nome"));
                grupo.setDescricao(result.getString("descricao"));
                
                grupos.add(grupo);
            }
            return grupos.isEmpty() ? null : grupos;
        } catch(Exception E) { 
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
        return null;
    }
    
}