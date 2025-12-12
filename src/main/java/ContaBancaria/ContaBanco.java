package ContaBancaria;


public class ContaBanco {


    //ATRIBUTOS
    private int numConta;
    private String tipo;
    private String proprietario;
    private double saldo;
    private boolean status;


    //MÉTODO CONSTRUTOR
    public ContaBanco() {
        this.setSaldo(0.0);
        this.setStatus(false);
    }

    //MÉTODOS ESPECIAIS - GETTER E SETTER
    public int getNumConta() {
        return numConta;
    }
    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProprietario() {
        return proprietario;
    }
    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public double getSaldo() {
        return saldo; //só vai pegar/ver um valor
    }
    //setter privado pois o saldo deve ser alterado apenas pelas transações inerentes a ele
    private void setSaldo(double saldo) {
        this.saldo = saldo; //vai criar/entregar um valor
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    //MÉTODOS PERSONALIZADOS
    public void estadoAtual() {
        System.out.println("----------------------------------------------");
        System.out.println("\nNúmero da Conta: " + this.getNumConta());
        System.out.println("Tipo: " + this.getTipo());
        System.out.println("Proprietário: " + this.getProprietario());
        System.out.println("Saldo Atual: R$ " + this.getSaldo());
        System.out.println("Status: " + (this.isStatus() ? "Aberta" : "Fechada"));
    }

    public void abrirConta(int numConta, String tipo, String proprietario){
        this.setNumConta(numConta);
        this.setTipo(tipo);
        this.setProprietario(proprietario);
        this.setStatus(true); //onde abre a conta

        //regras de negócio: bônus de abertura conforme o tipo da conta
        if (tipo.equals("CC")) { //verifica se é conta corrente
            this.setSaldo(this.getSaldo() + 50);
            System.out.println("\nConta Corrente aberta com sucesso. Bônus de R$50,00!");
        } else if (tipo.equals("CP")) { //o || significa "ou"
            this.setSaldo(this.getSaldo() + 100);
            System.out.println("\nConta Poupança aberta com sucesso. Bônus de R$100,00!");
        } else { //se não for nenhum dos dois
            System.out.println("\nTipo de conta inválida!");
            this.setStatus(false);
        }
    }

    //regra de negócio: saldo deve estar zerado
    public void fecharConta(){
        if (this.getSaldo() > 0) {
            System.out.println("\nERRO! Saldo de R$ " + this.getSaldo() + " disponível. Saque esse valor para fechar a conta.");
        } else if (this.getSaldo() < 0) {
            System.out.println("\nERRO! Saldo negativo de R$ " + this.getSaldo() + ". Pague esse valor para fechar a conta.");
            //professor vai pedir pra alguém fazer mais um else if
        } else {
            this.setStatus(false);
            System.out.println("\nConta de " + this.getProprietario() + " fechada com sucesso!");
        }
    }

    //regra de negócio: conta precisa estar aberta
    public void depositar(double valor){
        if (this.isStatus()) { //premissa, conta como verdadeiro automaticamente
            this.setSaldo(this.getSaldo() + valor);
            System.out.println("\nDepósito de R$ " + valor + " realizado com sucesso!");
        } else {
            System.out.println("\nERRO! Conta fechada, abra a conta para depositar um valor.");
        }
    }

    //regra de negócio: conta deve estar aberta e ter dinheiro na conta
    public void sacar(double valor){
        if (!this.isStatus()) { //a ! representa negação
            System.out.println("\nERRO! A conta está fechada, não foi possível sacar.");
            return;
        }
        if (this.getSaldo() >= valor) {
            this.setSaldo(this.getSaldo() - valor);
            System.out.println("\nSaque de R$ " + valor + " realizado om sucesso!");
        } else {
            System.out.println("\nERRO! Saldo insuficiente para saque. Saldo disponível: R$ " + this.getSaldo());
        }
    }

    public void pagarAnuidade(){
        int valor = 0; //valor da anuidade
        if (this.getTipo().equals("CC")) { //vai chamar essa variável de outro lugar
            valor = 12; //anuidade da CC
        } else if (this.getTipo().equals("CP")) {
            valor = 18; //anuidade da CP
        }
        if (this.isStatus()) {
            if (this.getSaldo() >= valor) {
                this.setSaldo(this.getSaldo() - valor);
                System.out.println("\nAnuidade de R$ " + valor + " debitada com sucesso!");
            } else {
                System.out.println("\nSaldo insuficiente para pagar a anuidade.");
            }
        } else {
            System.out.println("\nImpossível pagar anuidade em conta fechada.");
        }
    }
} //fim da classe