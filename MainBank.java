import java.util.Scanner;

public class MainBank {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        int choice;

        bank.loadAccountsFromCSV("accounts.csv");

        do{
            System.out.println("\n --- BANK MENU ---");
            System.out.println("1. New Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Find Account");
            System.out.println("5. Total Deposits");
            System.out.println("6. Account Below Limit");
            System.out.println("7. Largest Account");
            System.out.println("0. Exit Menu");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Acc ID: ");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Owner Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Initial Balance: ");
                    double balance = scanner.nextDouble();
                    boolean created = bank.createAccount(num, name, balance);
                    System.out.println(created ? "Account successfully created!" : "Error: Limit overflow");
                    break;
            
                case 2:
                    System.out.print("Acc ID: ");
                    int accNum = scanner.nextInt();
                    BankAccount acc = bank.findAccountByNumber(accNum);
                    if ( acc != null) {
                        System.out.print("Deposit amount: ");
                        double dep = scanner.nextDouble();
                        if (acc.deposit(dep)) {
                            System.out.println("Deposit successfull.");
                        }else {
                            System.out.println("Invalid amount.");
                        }
                    }else {
                        System.out.println("Account not found");
                    }
                    break;

                case 3:
                    System.out.print("Acc ID: ");
                    int accNum2 = scanner.nextInt();
                    BankAccount acc2 = bank.findAccountByNumber(accNum2);
                    if (acc2 != null) {
                        System.out.print("Withdraw amount: ");
                        double with = scanner.nextDouble();
                        if (acc2.withdraw(with)) {
                            System.out.println("Successfull withdraw.");
                        }else {
                            System.out.println("Low balance!");
                        }
                    }else {
                        System.out.println("Account not found");
                    }
                    break;
                case 4:
                    System.out.print("Acc Id: ");
                    int search = scanner.nextInt();
                    BankAccount found = bank.findAccountByNumber(search);
                    if (found != null) {
                        found.displayInfo();
                    }else {
                        System.out.println("Account does not exist.");
                    }
                    break;

                case 5:
                    System.out.println("Total balance: " + bank.getTotalDeposit());
                    break;

                case 6:
                    System.out.print("Limit: ");
                    double lim = scanner.nextDouble();
                    int below = bank.countAccountsBelowLimit(lim);
                    System.out.println("Accounts with balance < " + lim + ": " + below);
                    break;

                case 7:
                    BankAccount maxAcc = bank.getMaxBalanceAccount();
                    if (maxAcc != null) {
                        System.out.println("Account with largest balance: ");
                        maxAcc.displayInfo();
                    }else {
                        System.out.println("No accounts");
                    }
                    break;

                case 0:
                    System.out.println("Exit menu.");
                    break;

                default:
                    System.out.println("Invalid choice.");


            }

        } while (choice != 0);
        bank.saveAccountsToCSV("accounts.csv");
        scanner.close();
    }
    
}
