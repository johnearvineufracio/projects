package com.techexam.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techexam.domainmodel.product.VeganChocolate;
import com.techexam.services.VendingMachineServices;
import com.techexam.services.VendingMachineServicesImpl;

public class VendingMachineDisplayImpl implements VendingMachineDisplay {

	private VendingMachineServices service = new VendingMachineServicesImpl();

	public void displayStartOrder() {
		System.out.println("VEGAN CHOCOLATE VENDING MACHINE");
		System.out.println("Please enter Amount to pay, one coin at a time");

		Scanner scanner = new Scanner(System.in);

		List<String> enteredCoins = new ArrayList<String>();

		boolean stopInsertingCoins = false;

		while (!stopInsertingCoins) {

			boolean isDenomValid = false;
			while (!isDenomValid) {
				System.out.print("Enter Coin: AUD ");
				String currentAmountEntered = scanner.next();

				isDenomValid = service.validateDenomination(currentAmountEntered);
				if (isDenomValid) {
					enteredCoins.add(currentAmountEntered);
				} else {
					System.out.println("Invalid denomination entered. Returning AUD: " + currentAmountEntered);
					break;
				}
			}

			boolean isAnswerValid = false;

			while (!isAnswerValid) {
				System.out.print("Add more coins?(Y/N):");
				String choice = scanner.next();

				if (choice.equalsIgnoreCase("n")) {
					stopInsertingCoins = true;
					isAnswerValid = true;
				} else if (choice.equalsIgnoreCase("y")) {
					isAnswerValid = true;
				} else {
					System.out.println("Invalid answer");
				}

			}
		}
		System.out.println("Coins Entered:" + enteredCoins.toString());

		BigDecimal totalAmountEntered = service.getTotalCoinsEntered(enteredCoins);
		System.out.println("Total Amount Entered: AUD " + totalAmountEntered);

		displayChocolateChoices(totalAmountEntered, scanner, enteredCoins);

		scanner.close();

	}

	private void displayChocolateChoices(BigDecimal totalAmountEntered, Scanner scanner, List<String> enteredCoins) {
		List<VeganChocolate> veganChocolates = service.getAvailableChocolateForAmountEntered(totalAmountEntered);

		if (!veganChocolates.isEmpty()) {

			System.out.println("Select a Chocolate from the Choices Below:");
			System.out.println("Description          Amount");

			for (int i = 0; i < veganChocolates.size(); i++) {

				System.out.println(i+1 + ".   " + veganChocolates.get(i).getDescription() + "         AUD:"
						+ veganChocolates.get(i).getValue());
			}
			
			selectChocolateAndDispense(scanner, veganChocolates, totalAmountEntered);
		} else {
			System.out.println("Insert more coins to buy a chocolate");
			System.out.println("Returning entered amount of: AUD" + totalAmountEntered.toString());
			for (String enteredCoin : enteredCoins) {
				if (Long.valueOf(enteredCoin) < 1)
					System.out.println("Returning " + enteredCoin + "c");
				else {
					System.out.println("Returning $" + enteredCoin);
				}
			}

			System.out.print("Would you like to start over? (Y/N)");

			String choice = scanner.next();

			boolean isAnswerValid = false;

			while (!isAnswerValid) {
				if (choice.equalsIgnoreCase("n")) {
					isAnswerValid = true;
					clearScreen();
				} else if (choice.equalsIgnoreCase("y")) {
					isAnswerValid = true;
					clearScreen();
					displayStartOrder();
				} else {
					System.out.println("Invalid answer");
				}
			}
		}
	}
	
	private void selectChocolateAndDispense(Scanner scanner, List<VeganChocolate> veganChocolates,
			BigDecimal totalAmount) {
		System.out.print("Select your chocolate (enter the number):");
		int choice = scanner.nextInt();

		VeganChocolate chosenChocolate = veganChocolates.get(choice-1);

		System.out.println("Dispensing " + chosenChocolate.getDescription());

		BigDecimal change = service.calculateChange(totalAmount, chosenChocolate.getValue());

		if (change.compareTo(BigDecimal.ZERO) != 0) {
			System.out.println("Dispensing Change of AUD "+change.toString());
		}
		
		promptBuyAgain(scanner);
	}
	
	private void promptBuyAgain(Scanner scanner){
		System.out.print("Would you like to buy another one? (Y/N): ");
		String choice=scanner.next();
		boolean isAnswerValid = false;

		while (!isAnswerValid) {
			if (choice.equalsIgnoreCase("n")) {
				isAnswerValid = true;
				System.exit(0);
			} else if (choice.equalsIgnoreCase("y")) {
				isAnswerValid = true;
				clearScreen();
				displayStartOrder();
			} else {
				System.out.println("Invalid answer");
			}
		}
	}

	private final static void clearScreen() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
		}
	}
}
