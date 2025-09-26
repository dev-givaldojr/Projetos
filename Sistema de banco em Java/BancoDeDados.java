import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    private final List<Conta> contas = new ArrayList<>();
    private int proximoNumero = 1001;

    public Conta criarContaCorrente(String titular, double saldoInicial, double limiteChequeEspecial) {
        ContaCorrente c = new ContaCorrente(gerarNumero(), titular, saldoInicial, limiteChequeEspecial);
        contas.add(c);
        return c;
    }

    public Conta criarContaPoupanca(String titular, double saldoInicial, double taxaJurosAnualPercent) {
        ContaPoupanca c = new ContaPoupanca(gerarNumero(), titular, saldoInicial, taxaJurosAnualPercent);
        contas.add(c);
        return c;
    }

    private int gerarNumero() {
        return proximoNumero++;
    }

    public Conta buscarPorNumero(int numero) {
        for (Conta c : contas) {
            if (c.getNumero() == numero) return c;
        }
        return null;
    }

    public List<Conta> listarContas() {
        return new ArrayList<>(contas);
    }

    public boolean transferirInterno(int numeroOrigem, int numeroDestino, double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor da transferência deve ser positivo.");
        Conta origem = buscarPorNumero(numeroOrigem);
        Conta destino = buscarPorNumero(numeroDestino);
        if (origem == null || destino == null) return false;

        boolean sacou = origem.sacar(valor);
        if (!sacou) return false;

        destino.depositar(valor);
        return true;
    }

    public boolean transferirExterno(int numeroOrigem, String identificadorExterno, double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor da transferência deve ser positivo.");
        Conta origem = buscarPorNumero(numeroOrigem);
        if (origem == null) return false;

        boolean sacou = origem.sacar(valor);
        if (!sacou) return false;

        System.out.printf("Transferência externa realizada: Origem=%d, DestinoExterno=%s, Valor=R$ %.2f%n",
                numeroOrigem, identificadorExterno, valor);
        return true;
    }

    public void aplicarTaxasEmTodasContas() {
        for (Conta c : contas) {
            c.aplicarTaxa();
        }
    }
}
