import java.util.Scanner;

class BankAccount {
    private double balance;

    // Constructor to initialize balance
    public BankAccount(double initialBalance) {
        if (initialBalance > 0.0) {
            this.balance = initialBalance;
        }
    }

    // Method to deposit amount
    public void deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
            System.out.println("Deposit successful. Your new balance is: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw amount
    public void withdraw(double amount) {
        if (amount > 0.0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Your new balance is: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    // Method to check the balance
    public double checkBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;

    // Constructor to set the bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to show the ATM interface
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\nATM Interface");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.println("Your current balance is: $" + account.checkBalance());
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        scanner.close();
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        // Create a bank account with an initial balance of $1000
        BankAccount account = new BankAccount(1000.0);

        // Create an ATM object with the bank account
        ATM atm = new ATM(account);

        // Start the ATM interface
        atm.start();
    }
}
