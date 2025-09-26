public class ContaCorrente extends Conta {
    private final double limiteChequeEspecial;
    private final double taxaManutencao = 5.00;

    public ContaCorrente(int numero, String titular, double saldoInicial, double limiteChequeEspecial) {
        super(numero, titular, saldoInicial);
        this.limiteChequeEspecial = Math.max(0, limiteChequeEspecial);
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor do saque deve ser positivo.");
        double permitido = getSaldo() + limiteChequeEspecial;
        if (valor <= permitido) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public void aplicarTaxa() {
        saldo -= taxaManutencao;
    }

    @Override
    public String toString() {
        return String.format("%s (Corrente) - Limite: R$ %.2f", super.toString(), limiteChequeEspecial);
    }
}