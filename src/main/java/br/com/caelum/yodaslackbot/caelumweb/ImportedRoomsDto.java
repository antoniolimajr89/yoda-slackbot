package br.com.caelum.yodaslackbot.caelumweb;

import br.com.caelum.yodaslackbot.model.Room;
import org.springframework.util.StringUtils;

public class ImportedRoomsDto {

    private String numeroSala;
    private String cursoAtual;
    private String periodoAtual;

    public String getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(String numeroSala) {

        if (StringUtils.startsWithIgnoreCase(numeroSala, "Sala ")) {
            this.numeroSala = numeroSala.substring(5);
        } else if (numeroSala.contains("Auditório") || numeroSala.contains("22")) {
            this.numeroSala = "22";
        } else {
            this.numeroSala = numeroSala;
        }
    }

    public String getCursoAtual() {
        return cursoAtual;
    }

    public void setCursoAtual(String cursoAtual) {
        this.cursoAtual = cursoAtual;
    }

    public String getPeriodoAtual() {
        return periodoAtual;
    }

    public void setPeriodoAtual(String periodoAtual) {
        this.periodoAtual = periodoAtual;
    }

    public Room toModel() {
        return new Room(this.numeroSala, this.periodoAtual, this.cursoAtual);
    }
}

