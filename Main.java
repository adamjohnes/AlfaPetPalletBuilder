import java.util.*;
import java.io.*;

public class Main{

	static ArrayList<Order> orderList = new ArrayList<Order>();

	public static void main(String[] args){
		orderList = returnOrders("routingStatusV3.csv");

		/**for (Order order : orderList){
			System.out.println(order);
		}**/

		Pallet palletObj = new Pallet();
		ArrayList<Pallet> palletOrders = new ArrayList<Pallet>();
		//orderList = startObj.sortedList(orderList);

		palletOrders = palletObj.returnPallets(orderList);

		outputToFile(palletOrders);

		for (Pallet pallet : palletOrders){
			System.out.print(pallet.getPallet());
			System.out.println();
		}
	}

	public static ArrayList<Order> returnOrders(String fileName){

		ArrayList<Order> orderList = new ArrayList<Order>();

		try{
			Scanner reader = new Scanner(new File(fileName));
			reader.nextLine();

			while (reader.hasNext()){
				Order orderString = new Order();
				String splitString = reader.nextLine();
				String[] stringArray = splitString.split(",", 25);
				
				orderString.setOrder(Long.parseLong(stringArray[0]), Integer.parseInt(stringArray[1]), 
					Integer.parseInt(stringArray[2]), stringArray[3], stringArray[4], stringArray[5], 
					stringArray[6], stringArray[7], stringArray[8], stringArray[9], stringArray[10], 
					stringArray[11], stringArray[12], Integer.parseInt(stringArray[13]), stringArray[14], 
					Integer.parseInt(stringArray[15]), Integer.parseInt(stringArray[16]), Integer.parseInt(stringArray[17]), 
					Integer.parseInt(stringArray[18]), stringArray[19], Integer.parseInt(stringArray[20]), 
					Integer.parseInt(stringArray[21]), stringArray[22], stringArray[23], Integer.parseInt(stringArray[24]));

				orderList.add(orderString);
			}
		}
		catch (FileNotFoundException er){
			System.out.print("Invalid file name error. Please ensure the file is closed.");
			System.exit(1);
		}

		return orderList;
	}

	public static void outputToFile(ArrayList<Pallet> pallets){

		String fileOutName = "Programmed Shipping Order " + pallets.get(0).palletOrders.get(0).confirmDate + ".csv";
		fileOutName = fileOutName.replaceAll("/", "-");

		try (PrintWriter writeFile = new PrintWriter(new File(fileOutName))){

			StringBuilder sBuilder = new StringBuilder();
			String message = readPallets(pallets);
			sBuilder.append(message);
			sBuilder.append("\n");
			writeFile.write(sBuilder.toString());
			writeFile.close();

		} catch (FileNotFoundException e){
			System.out.println("Error creating file. Please ensure the file is closed.");
			System.exit(1);
		}
	}

	public static String readPallets(ArrayList<Pallet> pallets){

		int lines = 4;
		sortOrders(pallets);
		String message = "\t\t--------------------------------ALFAPET ORDER LAYOUT SHEET--------------------------------\nDate: " + pallets.get(0).palletOrders.get(0).confirmDate + "\n" + "Load ID: " 
		+ pallets.get(0).palletOrders.get(0).loadID + "\n\n";

		for (int i = 0; i < pallets.size(); i++){
			System.out.println(lines);
			if (lines <= 40 && lines >= 37)
			{
				message = message + "\n\n\n";
				lines = lines + 3;
			}
			if (lines <= 80 && lines >= 77)
			{
				message = message + "\n\n\n";
				lines = lines + 3;
			}

		 	message = message + "Pallet #" + (i + 1) + "  --------------" + "----  Cases " + " ---  PO Destination # " 
		 	+ " ---  Layers --- Destination Address" + "\n" + pallets.get(i).getPallet() + "\n\n";
		 	lines = lines + 2 + pallets.get(i).numPallets;
		}
		return message;
	}

	public static void sortOrders(ArrayList<Pallet> pallets){

		for (Pallet pallet : pallets){
			pallet.palletOrders = pallet.sortedList(pallet.palletOrders);
		}
	}
}