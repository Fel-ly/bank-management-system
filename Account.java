import java.util.Date;

public class Account implements Comparable{

    //declare variables
    static int nextAccNo = 100;
    int accountNo;
    String owner;
    City city;
    char gender;
    double balance;
    Date dateOpened;

    //creating the account (empty) constructor
    public Account(){

    }

    //full parameter constructor
    public Account(String owner, City city, char gender) {
        accountNo = nextAccNo;
        nextAccNo +=3;
        this.owner = owner;
        this.city = city;
        this.gender = gender;

        balance = 0.0;
        dateOpened = null; // System.current date();
    }

    public Account(int accountNo, String owner, City city, char gender, double balance) {
        this.accountNo = accountNo;
        this.owner = owner;
        this.city = city;
        this.gender = gender;
        setBalance(balance);
    }

    //creating the mutators(setter)
    public void setBalance(double b) {
        balance = b > 0.0 ? b: 0.0;
    }

    @Override
    public String toString() {
        return accountNo + " " + owner + " " + city.cityName +  " " + gender +" " + balance;
    }

    //comparing the owner account with the acc we have passed in
    @Override
    public int compareTo(Object o) {
        return this.owner.compareTo(((Account) o) .owner);
    }

    public void deposit (double amount) {
        if (amount > 0) {
            setBalance(balance + amount);
        }
    }

    public double withdraw(double amount) {
        if (amount > 0) {
            if (amount < balance) {
                setBalance(balance - amount);
            }
            else {
                amount = balance;
                setBalance(0.0);
            }
            return amount;
        }
        return 0.0;
    }
}
