//classe principal

package model;

public class BancoMalvader {
    private String nome;

    public BancoMalvader(String nome) {
        this.nome = nome;
    }

    // inicia o sistema com o menu de login
    public void iniciarSistema() {
        
    }

    public static void main(String[] args) {
        BancoMalvader banco = new BancoMalvader("Banco Malvader");
        banco.iniciarSistema();
    }
}
