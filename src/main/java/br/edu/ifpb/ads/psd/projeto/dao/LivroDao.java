package br.edu.ifpb.ads.psd.projeto.dao;


import br.edu.ifpb.ads.psd.projeto.conexao.ClasseConexao;
import br.edu.ifpb.ads.psd.projeto.conexao.ConnectionFactory;
import br.edu.ifpb.ads.psd.projeto.entidades.Livro;
import br.edu.ifpb.ads.psd.projeto.interfaces.LivroDaoIF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDao implements LivroDaoIF {
    ConnectionFactory factory = new ConnectionFactory();
    ClasseConexao conexao = new ClasseConexao();
    Connection con;
    PreparedStatement pstm;
    
    public LivroDao() {
        try {
            con = factory.getConnection();
        } catch (Exception e) {
        }
    }
    public void inserir(Livro l) throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "insert into Livro(isbn, titulo, anoPublicacao, editora, fotoCapa, tema, autores) values (?,?,?,?,?,?,?)";
            String a = l.getAutores();
            pstm = con.prepareStatement(SQL);
            pstm.setString(1, l.getIsbn());
            pstm.setString(2, l.getTitulo());
            pstm.setInt(3, l.getAnoPublicacao());
            pstm.setString(4, l.getEditora());
            pstm.setString(5, l.getFotoCapa());
            pstm.setString(6, l.getTema());
            pstm.setString(7, a);
            
            pstm.executeUpdate();

        } finally {
            conexao.liberar();
        }
    }

    public void remover(Livro l) throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "delete from Livro where titulo=?";

            pstm = con.prepareStatement(SQL);
            pstm.setString(1, l.getTitulo());

            pstm.executeUpdate();
        } finally {
            conexao.liberar();
        }
    }

    public void atualizar(Livro l) throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "update Livro set titulo = ?, anoPublicacao = ?, editora = ?, fotoCapa = ?, tema = ?, autores = ? where isbn=?";
            
            pstm = con.prepareStatement(SQL);
            pstm.setString(1, l.getTitulo());
            pstm.setInt(2, l.getAnoPublicacao());
            pstm.setString(3, l.getEditora());
            pstm.setString(4, l.getFotoCapa());
            pstm.setString(5, l.getTema());
            pstm.setString(6, l.getIsbn());
            pstm.setString(7, l.getAutores());

            pstm.executeUpdate();
        } catch(Exception E) { 
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
    }

//    public Livro pesquisar(String titulo) throws SQLException {
//        try {
//            conexao.abrir();
//            
//            String SQL = "select * from Livro where titulo ilike '%"+ titulo +"%'";
//            
//            pstm = con.prepareStatement(SQL);
//            
//            ResultSet result = pstm.executeQuery();
//            
//            Livro livro = new Livro();
//            
//            while(result.next()){
//                livro.setIsbn(result.getString("isbn"));
//                livro.setTitulo(result.getString("titulo"));
//                livro.setAnoPublicacao(result.getInt("anoPublicacao"));
//                livro.setEditora(result.getString("editora"));
//                livro.setFotoCapa(result.getString("fotoCapa"));
//                livro.setTema(result.getString("tema"));
//                livro.setAutores(result.getString("autores"));
//            }
//            return livro;
//            
//        } catch(Exception E) { 
//            E.printStackTrace();
//        } finally {
//            conexao.liberar();
//        }
//        return null;
//    }
    
    public Livro pesquisar(String isbn) throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "select * from Livro where isbn = '"+ isbn +"'";
            
            pstm = con.prepareStatement(SQL);
            
            ResultSet result = pstm.executeQuery();
            
            Livro livro = new Livro();
            
            while(result.next()){
                livro.setIsbn(result.getString("isbn"));
                livro.setTitulo(result.getString("titulo"));
                livro.setAnoPublicacao(result.getInt("anoPublicacao"));
                livro.setEditora(result.getString("editora"));
                livro.setFotoCapa(result.getString("fotoCapa"));
                livro.setTema(result.getString("tema"));
                livro.setAutores(result.getString("autores"));
            }
            return livro;
            
        } catch(Exception E) { 
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
        return null;
    }
    
    
    public List<Livro> listar() throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "select * from livro";
            
            pstm = con.prepareStatement(SQL);
            
            ResultSet result = pstm.executeQuery();
            
            List<Livro> livros = new ArrayList<Livro>();
            
            while(result.next()){
                Livro livro = new Livro();
                livro.setIsbn(result.getString("isbn"));
                livro.setTitulo(result.getString("titulo"));
                livro.setAnoPublicacao(result.getInt("anoPublicacao"));
                livro.setEditora(result.getString("editora"));
                livro.setFotoCapa(result.getString("fotoCapa"));
                livro.setTema(result.getString("tema"));
                livro.setAutores(result.getString("autores"));              
                livros.add(livro);
            }
            return livros.isEmpty() ? null : livros;
        } catch(Exception E) { 
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
        return null;
    }
    
    public List<Livro> ListarpesquisaLivro(String titulo) throws SQLException {
        try {
            conexao.abrir();
            
            String SQL = "titulo, foto from Livro where titulo ilike '%"+ titulo +"%'";
            
            pstm = con.prepareStatement(SQL);
            
            ResultSet result = pstm.executeQuery();
            
            List<Livro> livros = new ArrayList<Livro>();
            
            while(result.next()){
                Livro livro = new Livro();
                livro.setIsbn(result.getString("isbn"));
                livro.setTitulo(result.getString("titulo"));
                livro.setAnoPublicacao(result.getInt("anoPublicacao"));
                livro.setEditora(result.getString("editora"));
                livro.setFotoCapa(result.getString("fotoCapa"));
                livro.setTema(result.getString("tema"));
                livro.setAutores(result.getString("autores"));
                livros.add(livro);
            }
            return livros.isEmpty() ? null : livros;
        } catch(Exception E) { 
            E.printStackTrace();
        } finally {
            conexao.liberar();
        }
        return null;
    }
    
}