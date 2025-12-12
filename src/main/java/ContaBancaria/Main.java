package ContaBancaria;

public class Main {
    public static void main(String[] args) {
        ContaBanco c1 = new ContaBanco();

        c1.abrirConta(1234, "CP", "Lais Christiny Berndt");
        c1.estadoAtual();
        c1.depositar(100);
        c1.sacar(182);
        c1.pagarAnuidade();
        c1.fecharConta();
        c1.estadoAtual();
    }
}
