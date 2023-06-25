package banco;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Banco implements OperacoesBancarias {
    static Scanner input = new Scanner(System.in);

    static ArrayList<Conta> abrircontasBancarias;

    public static void main(String[] args) {
        abrircontasBancarias = new ArrayList<Conta>();
        funcoesDoBanco();
    }
    //criando um tipo de interface para as opçoes do banco //
    public static void funcoesDoBanco(){

        System.out.println("------------------------------------------------------");
        System.out.println("--------- Bem-vindos à Agência Digital House ---------");
        System.out.println("***** Selecione a operação que deseja realizar *****");
        System.out.println("------------------------------------------------------");
        System.out.println("|    Opção 1 - Criar Conta     |");
        System.out.println("|    Opção 2 - Depositar       |");
        System.out.println("|    Opção 3 - Sacar           |");
        System.out.println("|    Opção 4 - Transferir      |");
        System.out.println("|    Opção 5 - Lista           |");
        System.out.println("------------------------------------------------------");


        int funcoes = input.nextInt();

        switch (funcoes){
            case 1:
                criarConta();
                break;
            case 2:
                fazerDeposito();
                break;
            case 3:
                fazerSaque();
                break;
            case 4:
                fazerTransferencia();
                break;
            case 5:
                mostrarLista();
                break;

            case 6:
                System.out.println("Obrigado por usar nossa agência!");
                System.exit(0);
            default:
                System.out.println("Opção inválida!");
                funcoesDoBanco();
        }
    }



    public static void criarConta(){

        System.out.println("\nNome");
        String nome = input.next();

        System.out.println("\nCPF");
        String cpf = input.next();

        System.out.println("\nEmail");
        String email = input.next();


        Cliente cliente = new Cliente(nome,cpf,email);

        Conta conta = new Conta(cliente) {
            @Override
            public void encnontrarConta(int numeroDaConta) {

            }
        };

        abrircontasBancarias.add(conta);

        System.out.println("Conta " +nome+ " Criada com Sucesso");

        funcoesDoBanco();
    }

    //  para a realização da transferencia //
    private static Conta encontarConta(int numeroConta){
        Conta conta = null;
        try {

            if (abrircontasBancarias.size() > 0) {
                for (Conta c : abrircontasBancarias) {
                    if (c.getNumeroDaConta() == numeroConta) ;
                    conta = c;
                }
            } else {
                throw new ContaNaoEncontradaException("Conta não encontrada!");
            }
        }catch (ContaNaoEncontradaException e){
            System.out.println("Erro:" + e.getMessage());
        }catch (Exception e){
            System.out.println("Erro desconhecido: " + e.getMessage());
        }
        return conta;
    }

    public static void fazerDeposito(){
        System.out.println("Numero da conta: ");
        int numeroConta = input.nextInt();

        try {


            Conta conta = encontarConta(numeroConta);

            if (conta != null) {
                System.out.println("Qual valor do deposito ? ");
                Double valorDepositor = input.nextDouble();
                conta.depositar(valorDepositor);
                System.out.println("Deposito realizado com sucesso!");
            } else {
                throw new ContaNaoEncontradaException("Conta não encontrada!");
            }
        }catch (ContaNaoEncontradaException e){
            System.out.println("Erro:" + e.getMessage());
        }catch (Exception e){
            System.out.println("Erro desconhecido: " + e.getMessage());
        }
        funcoesDoBanco();
    }

    public static void fazerSaque(){
        System.out.println("Numero da conta:");
        int numeroConta = input.nextInt();

        Conta conta = encontarConta(numeroConta);

        if (conta != null){
            System.out.println("Valor do saque? ");
            Double valorSaque = input.nextDouble();
            conta.sacar(valorSaque);
            System.out.println("Saque realizado com sucesso!");
        }else {
            System.out.println("Conta"+numeroConta+"Não encontrada!" );
        }
        funcoesDoBanco();
    }


    public static void fazerTransferencia(){
        System.out.println("Numero da conta para transferência");
        int numeroContaParaTransferencia = input.nextInt();

        Conta contaRementente = encontarConta(numeroContaParaTransferencia);

        if (contaRementente != null){
            System.out.println("Numero da conta do destinatário: ");
            int numeroContaDestinatario = input.nextInt();

            Conta contaDestinatario = encontarConta(numeroContaDestinatario);
            if (contaDestinatario != null){
                System.out.println("valor da transferência: ");
                Double valor = input.nextDouble();

                contaRementente.transferir(contaDestinatario,valor);
            }
        }funcoesDoBanco();
    }
    public static void mostrarLista() {
        if (abrircontasBancarias.size() > 0) {
            for (Conta conta : abrircontasBancarias) {
                System.out.println(conta);
            }
        } else {
            System.out.println("Não há contas cadastradas");
        }
    }
}
//exercução pelo cmd //



