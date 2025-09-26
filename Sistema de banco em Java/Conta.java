public abstract class Conta {
    private final int numero;
    private final String titular;
    protected double saldo;

    public Conta(int numero, String titular, double saldoInicial) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor do depósito deve ser positivo.");
        saldo += valor;
    }

    public boolean sacar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor do saque deve ser positivo.");
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public abstract void aplicarTaxa();

    @Override
    public String toString() {
        return String.format("[%d] %s - Saldo: R$ %.2f", numero, titular, saldo);
    }
}
