import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Gachapon {
	
	int numGach;
	double totalNX, totalUSD;
	static ArrayList<String> gachaPool;
	Random r;
	
	//Constructor 
	public Gachapon(String fileName) throws FileNotFoundException {
		gachaPool = new ArrayList<String>();
		readTheFile(fileName); //Forest_Ranger_Bag_Gachapon.txt
		r = new Random();
		int numGach = 0; 
		double totalNX = 0, totalUSD = 0;
	}
	
	//Checks if the item is in the gachapool or not.
	public boolean isInList(String item){
		return gachaPool.contains(item);
	}
	
	//Continually gaches until it hits target item from the list.
	public void searchTheGach(String itemName){
		int counter = 0; 
		String foundGacha = "";
		while(!(foundGacha.equals(itemName))){
			//Retry and add to counter
			foundGacha = gachaPool.get(r.nextInt(gachaPool.size()));
			counter++;
			System.out.println((counter) + ". Obtained [" + foundGacha.substring(0,foundGacha.length()) + "]"); 
		}
		
		double NX = counter*1500;
		System.out.println("Found your item in " + counter +" tries and " + "spent a total of " + NX +" NX! or $" + (NX/1000) + " USD");
		numGach += counter;
		totalNX += NX;
		totalUSD += NX/1000;
		
	}
	
	//Searches for multiple specific items in the gachapool
	public void searchMultipleGach(ArrayList<String> list) {
		int counter = 0; 
		int numItems = list.size();
		int foundCounter = 0;
		
		//System.out.println(list);
		
		ArrayList <String> savedOutput = new ArrayList<String>();
		boolean [] isAlreadyFound = new boolean[numItems];
		
		String foundGacha = "";
		
		while(foundCounter != numItems){
			//Retry and add to counter
			foundGacha = gachaPool.get(r.nextInt(gachaPool.size())); //Random Gacha Item
			counter++;
			
			for(int i = 0; i< list.size(); i++){
				if(foundGacha.equals(list.get(i))){
					if(isAlreadyFound[i] == false){ //Dictionary to check if the current gacha item has already been found.
						foundCounter++;
						savedOutput.add("Obtained [" + foundGacha.substring(0,foundGacha.length()) + "] in " + counter + " tries.");
						isAlreadyFound[i] = true;
					}
					else {//We have a duplicate find.
						savedOutput.add("Obtained [" + foundGacha.substring(0,foundGacha.length()) + "] in " + counter + " tries. (Duplicate Find!)");
					}
				}
			}
			
			System.out.println((counter) + ". Obtained [" + foundGacha.substring(0,foundGacha.length()) + "]"); 
		}
		
		double NX = counter*1500;
		System.out.println("\n\nFound your items in " + counter +" tries and " + "spent a total of " + NX +" NX! or $" + (NX/1000) + " USD");
		for(int i = 0; i<savedOutput.size(); i++){
			System.out.println(savedOutput.get(i));
		}
		numGach += counter;
		totalNX += NX;
		totalUSD += NX/1000;
		
		
	}
	
	//Prints output of a specified amount of gachas 
	public void bulkGach(int amountGach) {
		//int min = 0; int max = gachaPool.size(); 
		int counter = 0; 
		Random r = new Random();
		for(int i = 0; i < amountGach; i++){
			String foundGacha = gachaPool.get(r.nextInt(gachaPool.size()));
			counter++;
			System.out.println((counter) + ". Obtained [" + foundGacha + "]"); 
		}
		double NX = counter*1500;
		System.out.println("\nYou spent " + (NX) +" NX on " + counter + " gachas.");
		numGach += counter;
		totalNX += NX;
		totalUSD += NX/1000;
	}
	
	public void printCurrentTotal(){ 
		System.out.println("Your current expenditure comes at $" + totalUSD + " USD or " + totalNX + " NX for a total of " + numGach + " gachapons.");
	}

	public ArrayList<String> getList(){
		return gachaPool;
	}

	//Reads the file and populates the gachaPool.
	private static void readTheFile(String nameOfTheFile) throws FileNotFoundException {
		File fileName = new File(nameOfTheFile);
		Scanner scan = new Scanner(fileName).useDelimiter("%\\t|\\n");
		
		while(scan.hasNext()){
			double numItem = scan.nextDouble() * 10; //0.10% percent chance is read as 0.10, not 0.001. We multiply by 10 to get it to a whole number out of 1000
			//System.out.println("NUMITEM =  " + numItem);
			String ItemToAdd = scan.next();
			//System.out.println("ItemToAdd = " + ItemToAdd);
			for(int i = 0; i<numItem; i++){
				gachaPool.add(ItemToAdd.substring(0,ItemToAdd.length()-1)); 
			}
		}
		//System.out.println("GachaPool Size = " + gachaPool.size() + "\n\n");
		//System.out.println(gachaPool);
		scan.close();
	}



	

}
