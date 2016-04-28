/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dijalma
 */
public interface ParticipaGrupoDaoIF{
    
    
    public boolean adicionaRelacaoAdmin(String emailUsuario, int idGrupo)throws SQLException ;
    
    public boolean adicionaRelacao(String emailUsuario, int idGrupo)throws SQLException ;
    
    public List<Integer> retornaGrupos(String emailUsuario)throws SQLException ;
    
    public boolean isParticipa(String emailUsuario, int idGrupo)throws SQLException ;
    
    public List<Integer> retornaUsuariosDeUmGrupo(int idGrupo)throws SQLException ;
}
