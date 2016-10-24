import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.*;

public class Demo1 {
	
	private static int compute(int number){
		Random gamblingMachine = new Random();
		int generatedNumber = (gamblingMachine.nextInt(65536)-32768);
		number += generatedNumber;
		
		return number;
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Rules: Five rounds. You vs. your computer. Each round you can take a gamble to change your score. At the end of 5 rounds, the player with the most score wins.\nGood luck!\n ");
		TimeUnit.SECONDS.sleep(1);
		Random pcNumber = new Random();
		System.out.println("Please enter the user");
		Scanner scan1 = new Scanner(System.in);
		String firstInput = scan1.nextLine();
		System.out.println("Then the user is " + firstInput);
		int value = pcNumber.nextInt(1000);
		System.out.println("User " + firstInput+ " has the value " + value);
		HashMap playerHash = new HashMap();
		playerHash.put(firstInput, value);
		int pcsNum = pcNumber.nextInt(1000);
		int pcValue = pcsNum;
		playerHash.put("PC", pcValue);
		
		for(int i = 0; i < 5; i++){
			System.out.println("Current Score\n" + playerHash.toString());
			System.out.println("Does user " + firstInput + " wish to gamble?");
			Scanner scan2 = new Scanner(System.in);
			String gambleInput = scan2.nextLine();
			if(gambleInput.equals("yes")){
				int newValue = compute(value);
				if (newValue > value){
					System.out.println("Gamble paid off! New value for " + firstInput + " is " +newValue);
				}
				else{
					System.out.println("Unlucky! New value for " + firstInput + " is " + newValue);
				}
				playerHash.replace(firstInput, newValue);	
			}
			else{
				System.out.println( firstInput + " sticks with the given value!");
			}
			
			
			System.out.println("PC's Turn!");
			TimeUnit.SECONDS.sleep(2);
			System.out.println("PC has the value " + (int) playerHash.get("PC"));
			TimeUnit.SECONDS.sleep(2);
			int gambleChance = pcNumber.nextInt(2);
			int reconsideration;
			if ((int) playerHash.get("PC") < (int) playerHash.get(firstInput)){
			    reconsideration = pcNumber.nextInt(11);
				if (reconsideration >= 4){
					gambleChance = 0;
				}
				if((int) playerHash.get(firstInput) - (int) playerHash.get("PC") > 10000 ){
					gambleChance = 0;
				}
			}
			else{
				reconsideration = pcNumber.nextInt(11);
				if(reconsideration >= 4){
					gambleChance = 1;
				}
			}
			if(gambleChance == 0){
				System.out.println("The computer takes a gamble!");
				TimeUnit.SECONDS.sleep(2);
				int newPCValue = compute(pcValue);
				playerHash.replace("PC", newPCValue);
				if (newPCValue > pcValue){
					System.out.println("Gamble paid off! New value for the computer is " +newPCValue);
					TimeUnit.SECONDS.sleep(2);
				}
				else{
					System.out.println("Unlucky! New value for PC is " + newPCValue);
					TimeUnit.SECONDS.sleep(2);
				}
			}
			else{
				System.out.println("The computer decides to stick with its value!");
				TimeUnit.SECONDS.sleep(2);
			}
		}
		if((int) playerHash.get(firstInput) > (int) playerHash.get("PC")){
			System.out.println("User " + firstInput + " wins!");
		}
		else{
			System.out.println("The computer wins");
		}
		System.out.println("Final Score:" + playerHash.toString());
	}

}
