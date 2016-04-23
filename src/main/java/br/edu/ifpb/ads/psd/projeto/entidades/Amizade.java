package br.edu.ifpb.ads.psd.projeto.entidades;
   
import br.edu.ifpb.ads.psd.projeto.converterInformacao.ConverterData;
import java.text.ParseException;

public class Amizade {
        private String emailUsuario;
        private String emailAmigo;
        private boolean isAmigo;
        private String since;
        

    public Amizade() throws ParseException{
        this.isAmigo = false;
        this.since = ConverterData.getDateTime();
    }

    public Amizade(String emailUsuario, String emailAmigo, boolean isAmigo) throws ParseException {
        this.emailUsuario = emailUsuario;
        this.emailAmigo = emailAmigo;
        this.isAmigo = isAmigo;
        this.since = ConverterData.getDateTime();
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getEmailAmigo() {
        return emailAmigo;
    }

    public void setEmailAmigo(String emailAmigo) {
        this.emailAmigo = emailAmigo;
    }

    public boolean isAmigo() {
        return isAmigo;
    }

    public void setAmigo(boolean isAmigo) {
        this.isAmigo = isAmigo;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    
}

