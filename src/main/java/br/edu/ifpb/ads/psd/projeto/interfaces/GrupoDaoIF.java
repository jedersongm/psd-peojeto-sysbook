package br.edu.ifpb.ads.psd.projeto.interfaces;


import br.edu.ifpb.ads.psd.projeto.entidades.Grupo;
import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface GrupoDaoIF {
    
    public void inserir(Grupo g)throws SQLException;
    
    public void remover(Grupo g)throws SQLException;
    
    public void atualizar(Grupo g)throws SQLException;
    
    public Grupo pesquisar(String nome)throws SQLException;
    
    public List<Grupo> pesquisarGrupos(Integer id) throws SQLException;
    
    public List<Grupo> pesquisarGrupos(String email) throws SQLException;
    
    public int retornaMaximo() throws SQLException;
}
