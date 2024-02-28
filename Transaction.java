import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Comparable<Transaction>, Serializable {

    private static int next = 1;
    int transactionNo;
    Account acc;
    LocalDate date;
    char operation;
    double amount;

    public Transaction(Account acc, LocalDate date, char operation, double amount) {
        this.acc = acc;
        this.date = date;
        this.operation = operation;
        this.amount = amount;
        transactionNo = next++;
    }

    //compare the transaction number between the transaction and the serializable
    @Override
    public int compareTo(Transaction o) {
        return this.transactionNo - o.transactionNo;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionNo=" + transactionNo +
                ", acc=" + acc +
                ", date=" + date +
                ", operation=" + operation +
                ", amount=" + amount +
                '}';
    }

    public int getTransactionNo() {
        return transactionNo;
    }

    public Account getAcc() {
        return acc;
    }

    public LocalDate getDate() {
        return date;
    }

    public char getOperation() {
        return operation;
    }

    public double getAmount() {
        return amount;
    }
}
