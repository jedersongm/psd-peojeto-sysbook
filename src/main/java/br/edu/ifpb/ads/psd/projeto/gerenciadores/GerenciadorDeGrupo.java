package br.edu.ifpb.ads.psd.projeto.gerenciadores;

import br.edu.ifpb.ads.psd.projeto.dao.ParticipaGrupoDao;
import br.edu.ifpb.ads.psd.projeto.entidades.Grupo;
import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.fabricas.DaoFactory;
import br.edu.ifpb.ads.psd.projeto.fabricas.DaoFactoryIF;
import br.edu.ifpb.ads.psd.projeto.interfaces.GrupoDaoIF;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifpb.ads.psd.projeto.interfaces.ParticipaGrupoDaoIF;

public class GerenciadorDeGrupo {
    
    public void adicionarGrupo(String email, String nome, String descricao) throws SQLException{
        Grupo novoGrupo = new Grupo();
        novoGrupo.setEmailUsuario(email);
        novoGrupo.setNome(nome);
        novoGrupo.setDescricao(descricao);
        
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        GrupoDaoIF gruDao = fabrica.criaGrupoDao();
        gruDao.inserir(novoGrupo);
    }
    
    public boolean adicionaRelacao(String emailUsuario, int idGrupo) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        ParticipaGrupoDao gruDao = fabrica.criaParticipaGrupoDAO();
        return gruDao.adicionaRelacao(emailUsuario, idGrupo);
    }
    
    public boolean adicionaRelacaoAdmin(String emailUsuario, int idGrupo) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        ParticipaGrupoDao gruDao = fabrica.criaParticipaGrupoDAO();
        return gruDao.adicionaRelacaoAdmin(emailUsuario, idGrupo);
    }
    
    public void removerGrupo(Grupo grupo) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        GrupoDaoIF gruDao = fabrica.criaGrupoDao();
        gruDao.remover(grupo);
    }
    
    public void atualizaGrupo(Grupo grupo) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        GrupoDaoIF gruDao = fabrica.criaGrupoDao();
        gruDao.atualizar(grupo);
    }
    
    public Grupo pesquisarGrupo(String nome) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        GrupoDaoIF gruDao = fabrica.criaGrupoDao();
        return gruDao.pesquisar(nome);
    }
    
    public List<Grupo> pesquisarGrupos(String email) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        GrupoDaoIF gruDao = fabrica.criaGrupoDao();
        return gruDao.pesquisarGrupos(email);
    }
    
    public int retornaMaximo() throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        GrupoDaoIF gruDao = fabrica.criaGrupoDao();
        return gruDao.retornaMaximo();
    }
    
    public List<Grupo> retornaGruposDoUsuario(String emailUsuario) throws SQLException {
        List<Grupo> grupos = new ArrayList<>();
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        GrupoDaoIF gruDao = fabrica.criaGrupoDao();
        ParticipaGrupoDaoIF participaGrupo = fabrica.criaParticipaGrupoDAO();
        for (Integer id : participaGrupo.retornaGrupos(emailUsuario)) {
            grupos.add((Grupo) gruDao.pesquisarGrupos(emailUsuario));
        }
        
        return grupos;
    }
}
