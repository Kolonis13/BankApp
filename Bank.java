import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Bank {
    private BankAccount[] accounts;
    private int accountCount;

    public Bank() {
        accounts = new BankAccount[1000]; //Μέγιστος αριθμός λογαριασμών
        accountCount = 0;
    }

    //Δημιουργία λογαριασμού
    public boolean createAccount(int accountNumber, String owner, double initialBalance) {
        if (accountCount >= 1000) return false;

        //Έλεγχος αν υπάρχει ίδιος λογαριασμός
        if (findAccountByNumber(accountNumber) != null) return false;

        accounts[accountCount++] = new BankAccount(accountNumber, owner, initialBalance);
        return true;
    }

    //Σύνολο καταθέσεων
    public double getTotalDeposit(){
        double total = 0;
        for (int i = 0; i < accountCount; i++){
            total += accounts[i].getBalance();
        }
        return total;
    }

    //Πληθος λογαριασμών σύμφωνα με το δωθέν όριο
    public int countAccountsBelowLimit(double limit) {
        int count = 0;
        for (int i = 0; i < accountCount; i++) {
            if ( accounts[i].getBalance() < limit) {
                count++;
            }
        }
        return count;
    }

    //Λογαριασμός με το μεγαλύτερο υπόλοιπο
    public BankAccount getMaxBalanceAccount() {
        if(accountCount == 0) return null; 

        BankAccount max = accounts[0];
        for (int i = 1; i < accountCount; i++) {
            if (accounts[i].getBalance() > max.getBalance()){
                max = accounts[i];
            }
        }
        return max;
    }

    //Εύρεση λογαριασμού με βάση αριθμό
    public BankAccount findAccountByNumber(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    public void saveAccountsToCSV(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for(int i = 0; i < accountCount; i++) {
                BankAccount acc = accounts[i];
                writer.write(acc.getAccountNumber() + "," + acc.getOwner() + "," + acc.getBalance());
                writer.newLine();

            }
        } catch (IOException e) {
            System.out.println("Error!: " + e.getMessage());

        }
    }

    public void loadAccountsFromCSV(String filename) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    int accountNumber = Integer.parseInt(parts[0]);
                    String owner = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    createAccount(accountNumber, owner, balance);
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found. Creating file...");
        }catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
    }
}
