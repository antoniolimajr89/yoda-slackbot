package br.com.caelum.yodaslackbot.caelumweb;

import br.com.caelum.yodaslackbot.slackbot.Room;
import org.springframework.util.StringUtils;

public class ImportedRoomsDto {

    private String numeroDaSala;
    private String cursoAtual;
    private String periodoAtual;

    public String getNumeroDaSala() {
        return numeroDaSala;
    }

    public String getCursoAtual() {
        return cursoAtual;
    }

    public String getPeriodoAtual() {
        return periodoAtual;
    }

    public Room toModel() {
        return new Room(trataNumeroDaSala(this.numeroDaSala), this.periodoAtual, this.cursoAtual);
    }

    private String trataNumeroDaSala(String numeroDaSala) {
        if (StringUtils.startsWithIgnoreCase(numeroDaSala, "Sala ")) {
            return this.numeroDaSala = numeroDaSala.substring(5);
        } else if (numeroDaSala.contains("Auditório") || numeroDaSala.contains("22")) {
            return this.numeroDaSala = "22";
        } else {
            return this.numeroDaSala = numeroDaSala;
        }
    }

}