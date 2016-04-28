/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.gerenciadores;


import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.fabricas.DaoFactory;
import br.edu.ifpb.ads.psd.projeto.fabricas.DaoFactoryIF;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.runtime.Debug.id;
import br.edu.ifpb.ads.psd.projeto.interfaces.ParticipaGrupoDaoIF;

/**
 *
 * @author dijalma
 */
public class GerenciadorParticipaGrupo {
    
    
    public boolean adicionaRelacaoAdmin(String emailUsuario, int idGrupo) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        ParticipaGrupoDaoIF participaGrupo = fabrica.criaParticipaGrupoDAO();
        return participaGrupo.adicionaRelacaoAdmin(emailUsuario, idGrupo); 
        
    }
    
    public boolean adicionaRelacao(String emailUsuario, int idGrupo) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        ParticipaGrupoDaoIF participaGrupo = fabrica.criaParticipaGrupoDAO();
        return participaGrupo.adicionaRelacao(emailUsuario, idGrupo); 
    }
    
    public List<Integer> retornaGrupos(String emailUsuario) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        ParticipaGrupoDaoIF participaGrupo = fabrica.criaParticipaGrupoDAO();
        return participaGrupo.retornaGrupos(emailUsuario);
    }
    
    public boolean isParticipa(String emailUsuario, int idGrupo) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        ParticipaGrupoDaoIF participaGrupo = fabrica.criaParticipaGrupoDAO();
        return participaGrupo.isParticipa(emailUsuario, idGrupo);
    }
    
    public List<Usuario> retornaUsuariosDeUmGrupo(int idGrupo) throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        ParticipaGrupoDaoIF participaGrupo = fabrica.criaParticipaGrupoDAO();
        
        for(Integer i: participaGrupo.retornaUsuariosDeUmGrupo(idGrupo)){
            usuarios.add(new GerenciadorDeUsuario().getUsuario(i));
        }
        
        return usuarios;
    }
}
