import javax.swing.*;
import java.util.*;

public class a {
    private String accountId;
    private String accountPin;
    private boolean isLoggedIn;
    private List<String> transactionHistory;
    private double accountBalance;

    public a() {
        this.accountId = "191111989";
        this.accountPin = "2002";
        this.isLoggedIn = false;
        this.transactionHistory = new ArrayList<>();
        this.accountBalance = 1000.0;
    }

    public void start() {
        JOptionPane.showMessageDialog(null, "Welcome to the ATM Service!");

        authenticateUser();
        if (isLoggedIn) {
            showMenu();
            performOperations();
        }

        JOptionPane.showMessageDialog(null, "Thank you for using the ATM Service! Visit Again and wish you a Good Day!!!");
    }

    private void authenticateUser() {
        String enteredAccountId = JOptionPane.showInputDialog(null, "Enter Account ID: ");
        String enteredAccountPin = JOptionPane.showInputDialog(null, "Enter Account PIN: ");

        if (enteredAccountId != null && enteredAccountPin != null &&
                enteredAccountId.equals(accountId) && enteredAccountPin.equals(accountPin)) {
            JOptionPane.showMessageDialog(null, "Login Successful!");
            isLoggedIn = true;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Account ID or PIN. Login Failed!");
        }
    }

    private void performOperations() {
        int choice;
        do {
            String choiceString = JOptionPane.showInputDialog(null, getMenuText());

            try {
                choice = Integer.parseInt(choiceString);

                switch (choice) {
                    case 1:
                        displayTransactionHistory();
                        break;
                    case 2:
                        performWithdrawal();
                        break;
                    case 3:
                        performDeposit();
                        break;
                    case 4:
                        performTransfer();
                        break;
                    case 5:
                        changePIN();
                        break;
                    case 6:
                        checkBalance();
                        break;
                    case 7:
                        printPassbook();
                        break;
                    case 8:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid choice! Please enter a number.");
                choice = -1;
            }
        } while (choice != 8);
    }

    private String getMenuText() {
        return "ATM Menu:\n" +
                "1. View Transaction History\n" +
                "2. Withdraw Funds\n" +
                "3. Deposit Funds\n" +
                "4. Transfer Funds\n" +
                "5. Change PIN\n" +
                "6. Check Balance\n" +
                "7. Print Passbook\n" +
                "8. Quit\n" +
                "Enter your choice:";
    }

    private void showMenu() {
        JOptionPane.showMessageDialog(null, getMenuText());
    }

    private void displayTransactionHistory() {
        StringBuilder message = new StringBuilder("Transaction History:\n");
        if (transactionHistory.isEmpty()) {
            message.append("No transactions available.");
        } else {
            for (String transaction : transactionHistory) {
                message.append(transaction).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    private void performWithdrawal() {
        String amountString = JOptionPane.showInputDialog(null, "Enter withdrawal amount:");
        try {
            double amount = Double.parseDouble(amountString);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Withdrawal failed!");
            } else if (amount > accountBalance) {
                JOptionPane.showMessageDialog(null, "Insufficient balance. Withdrawal failed!");
            } else {
                accountBalance -= amount;
                transactionHistory.add("Withdrawal: Rs. " + amount);
                JOptionPane.showMessageDialog(null, "Withdrawal of Rs. " + amount + " successful.\nUpdated account balance: Rs. " + accountBalance);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
        }
    }

    private void performDeposit() {
        String amountString = JOptionPane.showInputDialog(null, "Enter deposit amount:");
        try {
            double amount = Double.parseDouble(amountString);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Deposit failed!");
            } else {
                accountBalance += amount;
                transactionHistory.add("Deposit: Rs. " + amount);
                JOptionPane.showMessageDialog(null, "Deposit of Rs. " + amount + " successful.\nUpdated account balance: Rs. " + accountBalance);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
        }
    }

    private void performTransfer() {
        String recipientAccountId = JOptionPane.showInputDialog(null, "Enter recipient's account number:");
        String amountString = JOptionPane.showInputDialog(null, "Enter transfer amount:");
        try {
            double amount = Double.parseDouble(amountString);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Transfer failed!");
            } else if (amount > accountBalance) {
                JOptionPane.showMessageDialog(null, "Insufficient balance. Transfer failed!");
            } else {
                accountBalance -= amount;
                transactionHistory.add("Transfer: Rs. " + amount + " to account " + recipientAccountId);
                JOptionPane.showMessageDialog(null, "Transfer of Rs. " + amount + " to account " + recipientAccountId + " successful.\nUpdated account balance: Rs. " + accountBalance);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
        }
    }

    private void changePIN() {
        String newPin = JOptionPane.showInputDialog(null, "Enter new PIN: ");
        if (newPin != null) {
            accountPin = newPin;
            JOptionPane.showMessageDialog(null, "PIN changed successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid PIN. PIN change failed!");
        }
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(null, "Your current balance is: Rs. " + accountBalance);
    }

    private void printPassbook() {
        StringBuilder passbook = new StringBuilder("Passbook:\n");
        passbook.append("Account ID: ").append(accountId).append("\n");
        passbook.append("Transactions:\n");
        for (String transaction : transactionHistory) {
            passbook.append(transaction).append("\n");
        }
        passbook.append("Current Balance: Rs. ").append(accountBalance);
        JOptionPane.showMessageDialog(null, passbook.toString());
    }

    public static void main(String[] args) {
        a atm = new a();
        atm.start();
    }
}
