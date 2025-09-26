public class ContaPoupanca extends Conta {
    private final double taxaJurosAnualPercent;

    public ContaPoupanca(int numero, String titular, double saldoInicial, double taxaJurosAnualPercent) {
        super(numero, titular, saldoInicial);
        this.taxaJurosAnualPercent = Math.max(0, taxaJurosAnualPercent);
    }

    public double getTaxaJurosAnualPercent() {
        return taxaJurosAnualPercent;
    }

    @Override
    public void aplicarTaxa() {
        double jurosMensal = (taxaJurosAnualPercent / 100.0) / 12.0;
        saldo += saldo * jurosMensal;
    }

    @Override
    public String toString() {
        return String.format("%s (Poupança) - Juros anuais: %.2f%%", super.toString(), taxaJurosAnualPercent);
    }
}
