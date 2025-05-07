public class BankAccount {
    private int accountNumber;
    private String owner;
    private double balance;


    public BankAccount(int accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public String getOwner() {
        return owner;
    }
    
    public double getBalance(){
        return balance;
    }

    // Κατάθεση ποσού 
    public boolean deposit(double amount){
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    //Ανάληψη ποσού αν υπάρχει επαρκές υπόλοιπο
    public boolean withdraw(double amount){
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    //Στοιχεία λογαριασμού
    public void displayInfo(){
        System.out.println("Acc. ID: " + accountNumber + " Owner: " + owner + " Balance: " + balance);
    }
}
