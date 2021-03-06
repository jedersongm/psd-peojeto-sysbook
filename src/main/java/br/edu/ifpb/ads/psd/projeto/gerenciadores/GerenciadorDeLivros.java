package br.edu.ifpb.ads.psd.projeto.gerenciadores;

import br.edu.ifpb.ads.psd.projeto.entidades.Livro;
import br.edu.ifpb.ads.psd.projeto.fabricas.DaoFactory;
import br.edu.ifpb.ads.psd.projeto.fabricas.DaoFactoryIF;
import br.edu.ifpb.ads.psd.projeto.interfaces.LivroDaoIF;
import java.sql.SQLException;
import java.util.List;

public class GerenciadorDeLivros {
    
    public void adicionarLivros(String isbn, String titulo, Integer anoPublicacao, String editora, String fotoCapa, String tema, String autores) throws SQLException{
        Livro novoLivro = new Livro();
        
        novoLivro.setIsbn(isbn);
        novoLivro.setTitulo(titulo);
        novoLivro.setAnoPublicacao(anoPublicacao);
        novoLivro.setEditora(editora);
        novoLivro.setFotoCapa(fotoCapa);
        novoLivro.setTema(tema);
        novoLivro.setAutores(autores);
        
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        LivroDaoIF livroDao = fabrica.criaLivroDao();
        livroDao.inserir(novoLivro);
    }
    
    public void removerLivro(Livro livro) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        LivroDaoIF livroDao = fabrica.criaLivroDao();
        livroDao.remover(livro);
    }
    
    public void atualizaLivro(Livro livro) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        LivroDaoIF livroDao = fabrica.criaLivroDao();
        livroDao.atualizar(livro);
    }
    
    public Livro pesquisarLivro(String isbn) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        LivroDaoIF livroDao = fabrica.criaLivroDao();
        return livroDao.pesquisar(isbn);
    }
    
    public List<Livro> listarLivros() throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        LivroDaoIF livroDao = fabrica.criaLivroDao();
        return livroDao.listar();
    }
    
    public List<Livro> listarPesquisaLivros(String titulo) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        LivroDaoIF livroDao = fabrica.criaLivroDao();
        return livroDao.ListarpesquisaLivro(titulo);
    }
    
}