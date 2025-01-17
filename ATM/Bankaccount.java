//importing modules for their access
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

//This is the class for the bank account. 
public class Bankaccount {

	private int customerNumber;
	private int pinNumber;
	private double checkingBalance = 0;
	private double savingBalance = 0;

	Scanner input = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

	//Constructor (Which we refactored)
	public Bankaccount(int customerNumber, int pinNumber,double checkingBalance, double savingBalance) {
		this.customerNumber = customerNumber;
		this.pinNumber = pinNumber;
		this.checkingBalance = checkingBalance;
		this.savingBalance = savingBalance;
	}

	public int setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
		return customerNumber;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public int setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
		return pinNumber;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public double getCheckingBalance() {
		return checkingBalance;
	}

	public double getSavingBalance() {
		return savingBalance;
	}
    //This is the calculation of checking account balance
	public double calculateCheckingWithdraw(double amount) {
		checkingBalance = checkingBalance - amount;
		return checkingBalance;
	}
    //This is the calculation of saving account balance
	public double calculateSavingWithdraw(double amount) {
		savingBalance = savingBalance - amount;
		return savingBalance;
	}
	//This is the calculation of checking account after depositing money
	public double calculateCheckingDeposit(double amount) {
		checkingBalance = checkingBalance + amount;
		return checkingBalance;
	}
	//This is the calculation of saving account after depositing money
	public double calculateSavingDeposit(double amount) {
		savingBalance = savingBalance + amount;
		return savingBalance;
	}
	//This is the calculation of amount after transfering the money from checking to saving
	public void calculateCheckTransfer(double amount) {
		checkingBalance = checkingBalance - amount;
		savingBalance = savingBalance + amount;
	}
	//This is the calculation of amount after transfering the money from saving to checking
	public void calculateSavingTransfer(double amount) {
		savingBalance = savingBalance - amount;
		checkingBalance = checkingBalance + amount;
	}
	//This is the error handling
	public void getCheckingWithdrawInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Checkings Bankaccount Balance: " + moneyFormat.format(checkingBalance) + "\nAmount you want to withdraw from Checkings Bankaccount: ");
				double amount = input.nextDouble();
				if ((checkingBalance - amount) >= 0 && amount >= 0) {			//Big O is O(N)
					calculateCheckingWithdraw(amount);
					System.out.println("\nCurrent Checkings Bankaccount Balance: " + moneyFormat.format(checkingBalance));
					end = true;
				} else {
					System.out.println("\nBalance Cannot be Negative.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInsufficient Balance.");
				input.next();
			}
		}
	}
	//This is the error handling
	public void getsavingWithdrawInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Savings Bankaccount Balance: " + moneyFormat.format(savingBalance) + "\nAmount you want to withdraw from Savings Bankaccount: ");
				double amount = input.nextDouble();
				if ((savingBalance - amount) >= 0 && amount >= 0) {				//Big O is O(N)
					calculateSavingWithdraw(amount);
					System.out.println("\nCurrent Savings Bankaccount Balance: " + moneyFormat.format(savingBalance));
					end = true;
				} else {
					System.out.println("\nBalance Cannot Be Negative.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInsufficient Balance.");
				input.next();
			}
		}
	}
	//This is the error handling
	public void getCheckingDepositInput() { 
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Checkings Bankaccount Balance: " + moneyFormat.format(checkingBalance)+"\nAmount you want to deposit from Checkings Bankaccount: ");
				double amount = input.nextDouble();
				if ((checkingBalance + amount) >= 0 && amount >= 0) {               //Big O is O(N)
					calculateCheckingDeposit(amount);
					System.out.println("\nCurrent Checkings Bankaccount Balance: " + moneyFormat.format(checkingBalance));
					end = true;
				} else {
					System.out.println("\nInsuffient Balance.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}

	public void getSavingDepositInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Savings Bankaccount Balance: " + moneyFormat.format(savingBalance) + "\nAmount you want to deposit into your Savings Bankaccount: ");
				double amount = input.nextDouble();

				if ((savingBalance + amount) >= 0 && amount >= 0) {                 //Big O is O(N)
					calculateSavingDeposit(amount);
					System.out.println("\nCurrent Savings Bankaccount Balance: " + moneyFormat.format(savingBalance));
					end = true;
				} else {
					System.out.println("\nInsufficient Balance.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}

	public void getTransferInput(String accType) {
		boolean end = false;
		while (!end) {
			try {
				if (accType.equals("Checkings")) {
					System.out.println("\nSelect an Bankaccount you wish to tranfers funds to:" + "\n1. Savings" + "\n2. Exit" + "\nChoice: ");
					int choice = input.nextInt();
					switch (choice) {
					case 1:
						System.out.println("\nCurrent Checkings Bankaccount Balance: " + moneyFormat.format(checkingBalance) + "\nAmount you want to deposit into your Savings Bankaccount: ");
						double amount = input.nextDouble();
						if ((savingBalance + amount) >= 0 && (checkingBalance - amount) >= 0 && amount >= 0) {calculateCheckTransfer(amount); System.out.println("\nCurrent Savings Bankaccount Balance: " + moneyFormat.format(savingBalance)); end = true; }else {System.out.println("\nBalance Cannot Be Negative.");}
						break;
					case 2:
						return;
					default:
						System.out.println("\nInvalid Choice.");
						break;
					}
				} else if (accType.equals("Savings")) {
					System.out.println("\nSelect an Bankaccount you wish to tranfers funds to: " + "\n1. Checkings" + "\n2. Exit" + "\nChoice: ");
					int choice = input.nextInt();
					switch (choice) {
					case 1:
						System.out.println("\nCurrent Savings Bankaccount Balance: " + moneyFormat.format(savingBalance)+ "\nAmount you want to deposit into your savings Bankaccount: ");
						double amount = input.nextDouble();
						if ((checkingBalance + amount) >= 0 && (savingBalance - amount) >= 0 && amount >= 0) {calculateSavingTransfer(amount); System.out.println("\nCurrent checkings Bankaccount balance: " + moneyFormat.format(checkingBalance) + "\nCurrent savings Bankaccount balance: " + moneyFormat.format(savingBalance));end = true;} else{System.out.println("\nBalance Cannot Be Negative.");}
						break;
					case 2:
						return;
					default:
						System.out.println("\nInvalid Choice.");
						break;
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}
}
