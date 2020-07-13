import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[]args) throws FileNotFoundException{
		
		System.out.println("What is the name of the input file?");
		Scanner kb = new Scanner(System.in);
		String fileName = kb.nextLine();
		
		//Gachapon g1 = new Gachapon(fileName);
		Gachapon g1 = new Gachapon("Forest_Ranger_Bag_Gachapon.txt"); 
		//Gachapon g1 = new Gachapon("Battle_Wear_Box_Gachapon.txt"); 
		
		//From here ask the user if they want a specefic item or if they want to open a gachapon
		OptionsMenu(kb,g1);
	}

	private static void OptionsMenu(Scanner kb, Gachapon g1 ) {
		
		System.out.println("\n\n---------------------Options-------------------");
		System.out.println("1. I would like to gach for a specific item.");
		System.out.println("2. Just gach!");
		System.out.println("3. I would like to gach for multiple specefic items.");
		System.out.println("4. Exit");
		
		g1.printCurrentTotal();
		int choice = kb.nextInt();
		kb.nextLine();
		switch(choice) {
			case 1:
				System.out.print("Please input item name as it appears on the official gacha page.");
				String item = kb.nextLine();
				if(g1.isInList(item)){
					g1.searchTheGach(item);
				}
				else {
					System.out.println("This item is not part of the gacha pool, please try again.");
				}
				
				while(true){
					System.out.println("Would you like to retry with that same list? (Y/N)");
					String input = kb.nextLine();
					if(input.equals("YES")||input.equals("Y")||input.equals("y")||input.equals("yes")){
						g1.searchTheGach(item);
					}
					else{
						break;						
					}
				}

				OptionsMenu(kb,g1);
				break;
			
			case 2: //Just gach! IN BULK
				System.out.print("How many would you like to gach?");
				int amountGach = kb.nextInt();
				g1.bulkGach(amountGach);
				OptionsMenu(kb,g1);
				break;
				
			case 3: //I would like to gach for multiple specefic items.
				ArrayList<String>list = new ArrayList<String>();
				System.out.print("Please input item names one at a time as it appears on the official gacha page. Input 'END' to indicate you're finished.");
				String items  = "";
				
				while(items != "END"){
					items = kb.nextLine();
					if(g1.isInList(items)){
						list.add(items);
					}
					else if(items.equals("END")||items.equals("end")||items.equals("stop")){
						break;
					}
					else {
						System.out.println("This item is not part of the gacha pool, please try again.");
						System.out.println("If you are trying to finish the list, please input 'End'");
					}
				}
				
				g1.searchMultipleGach(list);
				
				while(true){
					System.out.println("Would you like to retry with that same list? (Y/N)");
					String input = kb.nextLine();
					if(input.equals("YES")||input.equals("Y")||input.equals("y")||input.equals("yes")){
						g1.searchMultipleGach(list);
					}
					else{
						break;						
					}
				}
				
				OptionsMenu(kb,g1);
				break;
				
			case 4:
				return;
				
			default:
				OptionsMenu(kb,g1);
				break;
		}
	}
}
