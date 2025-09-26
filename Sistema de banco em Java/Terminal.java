import java.util.List;
import java.util.Scanner;

public class Terminal {
    private static final BancoDeDados banco = new BancoDeDados();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean rodando = true;
        while (rodando) {
            imprimirMenu();
            int opc = lerInt("Escolha uma opção: ");
            switch (opc) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    listarContas();
                    break;
                case 3:
                    realizarTransferencia();
                    break;
                case 4:
                    realizarTransferenciaExterna();
                    break;
                case 5:
                    banco.aplicarTaxasEmTodasContas();
                    System.out.println("Taxas/aplicação de juros aplicadas a todas as contas.");
                    break;
                case 0:
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
            System.out.println();
        }
        System.out.println("Encerrando terminal. Até logo!");
        sc.close();
    }

    private static void imprimirMenu() {
        System.out.println("====== Banco Federal - Terminal v4.0 ======");
        System.out.println("1. Criar nova conta (Corrente / Poupança)");
        System.out.println("2. Listar todas as contas");
        System.out.println("3. Transferência interna");
        System.out.println("4. Transferência externa (PIX/TED)");
        System.out.println("5. Aplicar taxas / juros em todas as contas");
        System.out.println("0. Sair");
    }

    private static void criarConta() {
        System.out.println("-- Criar Conta --");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        int tipo = lerInt("Escolha o tipo de conta: ");
        sc.nextLine();
        String titular = lerString("Nome do titular: ");
        double saldoInicial = lerDouble("Saldo inicial: ");

        if (tipo == 1) {
            double limite = lerDouble("Limite do cheque especial: ");
            Conta c = banco.criarContaCorrente(titular, saldoInicial, limite);
            System.out.println("Conta corrente criada: " + c.getNumero());
        } else if (tipo == 2) {
            double juros = lerDouble("Taxa de juros anual (%) (ex: 4.5): ");
            Conta c = banco.criarContaPoupanca(titular, saldoInicial, juros);
            System.out.println("Conta poupança criada: " + c.getNumero());
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void listarContas() {
        System.out.println("-- Lista de Contas --");
        List<Conta> contas = banco.listarContas();
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        for (Conta c : contas) {
            System.out.println(c.toString());
        }
    }

    private static void realizarTransferencia() {
        System.out.println("-- Transferência Interna --");
        int origem = lerInt("Número da conta origem: ");
        int destino = lerInt("Número da conta destino: ");
        double valor = lerDouble("Valor a transferir: ");
        boolean ok = banco.transferirInterno(origem, destino, valor);
        if (ok) System.out.println("Transferência interna concluída com sucesso.");
        else System.out.println("Falha na transferência (contas inexistentes ou saldo insuficiente).");
    }

    private static void realizarTransferenciaExterna() {
        System.out.println("-- Transferência Externa (PIX/TED) --");
        int origem = lerInt("Número da conta origem: ");
        sc.nextLine();
        String idExterno = lerString("Identificador externo (chave PIX ou banco/conta): ");
        double valor = lerDouble("Valor a transferir: ");
        boolean ok = banco.transferirExterno(origem, idExterno, valor);
        if (ok) System.out.println("Transferência externa concluída (simulada).");
        else System.out.println("Falha na transferência externa (conta inexistente ou saldo insuficiente).");
    }

    private static int lerInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Entrada inválida. " + prompt);
        }
        return sc.nextInt();
    }

    private static double lerDouble(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            sc.next();
            System.out.print("Entrada inválida. " + prompt);
        }
        return sc.nextDouble();
    }

    private static String lerString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
