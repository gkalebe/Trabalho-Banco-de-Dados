package model;

import java.time.LocalDateTime;
import java.util.List;

public class Relatorio {
    private String tipo;
    private final LocalDateTime dataGeracao;
    private List<String> dados;

    public Relatorio(String tipo, List<String> dados) {
        this.tipo = tipo;
        this.dataGeracao = LocalDateTime.now();
        this.dados = dados;
    }

    public void gerarRelatorioGeral() {
        System.out.println("Tipo de Relatório: " + tipo);
        System.out.println("Data de Geração: " + dataGeracao);
        System.out.println("Dados:");
        for (String dado : dados) {
            System.out.println("- " + dado);
        }
    }

    public void exportarParaExcel() {
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public List<String> getDados() {
        return dados;
    }

    public void setDados(List<String> dados) {
        this.dados = dados;
    }

}
