/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.psd.projeto.conexao;

import br.edu.ifpb.ads.psd.projeto.converterInformacao.ConverterData;
import br.edu.ifpb.ads.psd.projeto.dao.UsuarioDao;
import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.fabricas.DaoFactory;
import br.edu.ifpb.ads.psd.projeto.gerenciadores.GerenciadorDeUsuario;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jederson
 */
public class Main{
   
    public static void main(String[] args) throws SQLException {
     
        String email = "ludgero@gmail.com";
        String nome = "Ludgero";
        String apelido = "Francois";
        String senha = "123";
        String cidade = "Cajazeiras";
        String estado = "PB";
        String fotoCapa = "/sd";
        Date dataNascimento;
        try {
            dataNascimento = ConverterData.stringParaDate("17/04/1994");
             GerenciadorDeUsuario userGer = new GerenciadorDeUsuario();
        
            userGer.adicionarUsuario(email, nome, apelido, senha, cidade, estado, fotoCapa, dataNascimento);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       
        
    }
}
