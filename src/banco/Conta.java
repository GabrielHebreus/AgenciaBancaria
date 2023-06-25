package banco;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.text.NumberFormat;

// toda conta inicia com saldo em zero//
public abstract class Conta {
    protected static int contadorDeConta = 1;
    private int numeroDaConta;
    private Double saldo = 0.0;
    public Cliente cliente;



    public Conta(Cliente cliente){
        this.numeroDaConta = contadorDeConta;
        this.cliente = cliente;
        contadorDeConta += 1;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String toString(){
        return "\nNúmero da conta:" + this.getNumeroDaConta() +
                "\nNome:" + this.cliente.getNome() +
                "\nCPF:" + this.cliente.getCpf() +
                "\nEmail:" + this.cliente.getEmail() +
                "\nSaldo:" + converterEmReal.doubleToString(this.getSaldo()) +
                "\n";
    }

    public void depositar(Double valor){
        if(valor > 0){
            setSaldo(getSaldo() + valor);
            System.out.println("Depósito realizado com sucesso!");
        }else{
            System.out.println("Não foi possivel realizar o deposito!");
        }
    }

    public void sacar(Double valor){
        if(valor > 0 && this.getSaldo() >= valor){
            setSaldo(getSaldo() - valor);
            System.out.println("Saque realizado com sucesso!");
        }else {
            System.out.println("Saldo incompativel com o valor solicitado" + valor + "diferente do valor solicitado" + getSaldo());
        }
    }
    public void transferir(Conta contaParaDepositar, Double valor){
        if(valor > 0 && this.getSaldo() >= valor){
            setSaldo(getSaldo() - valor);

            contaParaDepositar.saldo = contaParaDepositar.getSaldo() + valor;
            System.out.println("Transferência realizada com sucesso!");
        }else {
            System.out.println("Não foi possivel realizar a transferência!");
        }
    }

  //Converte int em real, é uma mascara//
    public class converterEmReal{

        static NumberFormat formatandoValores = new DecimalFormat("R$ #,##0.00");

        public static String doubleToString(Double valor) {
            return formatandoValores.format(valor);
        }
    }
    public abstract void encnontrarConta(int numeroDaConta);
}
