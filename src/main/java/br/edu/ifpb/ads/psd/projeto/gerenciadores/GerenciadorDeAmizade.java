package br.edu.ifpb.ads.psd.projeto.gerenciadores;

import br.edu.ifpb.ads.psd.projeto.converterInformacao.ConverterData;
import br.edu.ifpb.ads.psd.projeto.entidades.Amizade;
import br.edu.ifpb.ads.psd.projeto.entidades.Usuario;
import br.edu.ifpb.ads.psd.projeto.fabricas.DaoFactory;
import br.edu.ifpb.ads.psd.projeto.fabricas.DaoFactoryIF;
import br.edu.ifpb.ads.psd.projeto.interfaces.AmizadeDaoIF;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class GerenciadorDeAmizade {
    
    public void enviarConviteAmizade(String  emailUsuario, String emailAmigo) throws SQLException, ParseException{
       
        Amizade novaAmizade = new Amizade();            
        novaAmizade.setEmailUsuario(emailUsuario);
        novaAmizade.setEmailAmigo(emailAmigo);        
        DaoFactory.creatFactory().criaAmizadeDao().inserir(novaAmizade);    
              
    }
    
    public void removerAmizade(Amizade a) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        AmizadeDaoIF amizadeDao = fabrica.criaAmizadeDao();
        amizadeDao.remover(a);
    }
    
    public void recusaAmizade(String emailUsuario, String emailSolicitante) throws SQLException{
        Amizade a = pesquisarAmizade(emailSolicitante, emailUsuario);
        removerAmizade(a);
    }
    
    public boolean aceitaAmizade(String emailUsuario, String emailSolicitante) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        AmizadeDaoIF amizadeDao = fabrica.criaAmizadeDao();
        Amizade a = amizadeDao.pesquisar(emailSolicitante, emailUsuario);
        a.setSince(ConverterData.getDateTime());
        return amizadeDao.aceita(a);
    }
    
    public Amizade pesquisarAmizade(String emailUsuario, String emailAmigo) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        AmizadeDaoIF amizadeDao = fabrica.criaAmizadeDao();
        return amizadeDao.pesquisar(emailUsuario, emailAmigo);
    }
    
    public boolean isAmigo(String emailUsuario, String emailAmigo) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        AmizadeDaoIF amizadeDao = fabrica.criaAmizadeDao();
        return amizadeDao.isAmigo(emailUsuario, emailAmigo);
    }
    
    public boolean isPendente(String emailUsuario, String emailAmigo) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        AmizadeDaoIF amizadeDao = fabrica.criaAmizadeDao();
        return amizadeDao.isPendente(emailUsuario, emailAmigo);
    }
    public ArrayList<Amizade> listarAmizade(String emailUsuario) throws SQLException{
        DaoFactoryIF fabrica = DaoFactory.creatFactory();
        AmizadeDaoIF amizadeDao = fabrica.criaAmizadeDao();
        return amizadeDao.listar(emailUsuario);
    }
    
    public ArrayList<Amizade> listarSolicitações(String emailUsuario) throws SQLException{
        return DaoFactory.creatFactory().criaAmizadeDao().listarSolicitacoes(emailUsuario);
    }
}
