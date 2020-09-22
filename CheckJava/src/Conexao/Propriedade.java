package Conexao;

public class Propriedade {
    private String valor;
    private String tipo;

    public Propriedade(String tipo, String valor) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }
}