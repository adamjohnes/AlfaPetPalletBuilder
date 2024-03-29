import java.util.*;
import java.math.*;

public class Pallet{

	ArrayList<Order> palletOrders = new ArrayList<Order>();
	int numLayers, numPallets;

	public Pallet(){
		numLayers = 0;
		numPallets = 0;
	}

	public boolean have40()
	{
		for (Order order : palletOrders)
		{
			if (order.getCases() == 40)
			{
				return true;
			}
		}

		return false;
	}

	public ArrayList<Order> sortedList(ArrayList<Order> orderList){
		
		int min = Integer.MAX_VALUE;
		int size = orderList.size();
		Order minOrder = new Order();
		ArrayList<Order> orders = new ArrayList<Order>();

		for (int i = 0; i < size; i++){
			for (Order order : orderList){
				if (order.getCases() == 40)
				{
					minOrder = order;
					break;
				}
				minOrder = (order.getCases() <= min) ? order : minOrder;
				min = minOrder.getCases();
			}

			orderList.remove(minOrder);
			orders.add(minOrder);
			min = 1000;
		}
		return orders;
	}

	public ArrayList<Pallet> returnPallets(ArrayList<Order> orderList){

		ArrayList<Pallet> finalList = new ArrayList<Pallet>();

		finalList = matchPOs(orderList);
		finalList = matchPallets(finalList, orderList);
		finalList = finishPallets(finalList, orderList);
		return finalList;
	}

	public ArrayList<Pallet> matchPOs(ArrayList<Order> orderList){

		int poNum = 0;
		Order orderSame = new Order();
		ArrayList<Order> matchingPOs = new ArrayList<Order>();
		ArrayList<Pallet> finalizedPOs = new ArrayList<Pallet>();
		HashMap<Order, Integer> map = new HashMap<Order, Integer>();

		for (Order order : orderList){
			if (!map.containsValue(order.getPODestination())){
				map.put(order, order.getPODestination());
				orderSame = order;
			}

			else if (map.containsValue(order.getPODestination())){
				Order matchOrder = returnKey(map, order.getPODestination());
				if (matchOrder.getPONumber() != order.getPONumber()){
					matchingPOs.add(orderSame);
					matchingPOs.add(order);
				}
			} 
		}

		if (matchingPOs.size() > 0){
			poNum = matchingPOs.get(0).getPODestination();
			for (Order order : matchingPOs){
				Pallet newPallet = new Pallet();
				if (poNum == order.getPODestination()){
					for (Order order1 : matchingPOs){
						if (order1.getPODestination() == poNum && order1.used == false && newPallet.numLayers + order1.getLayers() <= 6){
							newPallet.palletOrders.add(order1);
							newPallet.numLayers = newPallet.numLayers + order1.getLayers();
							newPallet.numPallets = newPallet.numPallets + 1;
							order1.used = true;
						} 
					}
				}
				if (newPallet.numLayers > 0){
					finalizedPOs.add(newPallet);
				}
				poNum = order.getPODestination();
			}
		}

		return finalizedPOs;
	}

	public ArrayList<Pallet> matchPallets(ArrayList<Pallet> finalizedPallets, ArrayList<Order> orderList){

		int emergencyExit = 0;
		int freeze = 0;

		if (finalizedPallets.size() > 0){
			for (Pallet pallet : finalizedPallets){
				while (pallet.numLayers < 6 && emergencyExit <= 5){
					switch (pallet.numLayers){
							case 2:
								for (Order order : orderList){
									if (pallet.numLayers == 6){ break; }
									if (order.getLayers() == 4 && order.getLayers() + pallet.numLayers <= 6 && pallet.numPallets <= 4 
										&& order.used == false){
										pallet.palletOrders.add(order);
										order.used = true;
										pallet.numLayers = pallet.numLayers + order.getLayers();
										pallet.numPallets += 1;
									}
								}

								for (Order order : orderList){
									if (pallet.numLayers == 6){ break; }
									if (order.getLayers() == 3 && order.getLayers() + pallet.numLayers <= 6 && pallet.numPallets <= 4 
										&& order.used == false){
										pallet.palletOrders.add(order);
										order.used = true;
										pallet.numLayers = pallet.numLayers + order.getLayers();
										pallet.numPallets += 1;
									}
								}

								for (Order order : orderList){
									if (pallet.numLayers == 6){ break; }
									if (order.getLayers() == 2 && order.getLayers() + pallet.numLayers <= 6 && pallet.numPallets <= 4 
										&& order.used == false){
										pallet.palletOrders.add(order);
										order.used = true;
										pallet.numLayers = pallet.numLayers + order.getLayers();
										pallet.numPallets += 1;
									}
								}

								for (Order order : orderList){
									if (pallet.numLayers == 6){ break; }
									if (order.getLayers() == 1 && order.getLayers() + pallet.numLayers <= 6 && pallet.numPallets <= 4 
										&& order.used == false){
										pallet.palletOrders.add(order);
										order.used = true;
										pallet.numLayers = pallet.numLayers + order.getLayers();
										pallet.numPallets += 1;
									}
								}
								emergencyExit++;
								break;
							case 3:
								for (Order order : orderList){
									if (order.getLayers() == 3 && order.getLayers() + pallet.numLayers <= 6 && pallet.numPallets <= 4 
										&& order.used == false){
										pallet.palletOrders.add(order);
										order.used = true;
										pallet.numLayers = pallet.numLayers + order.getLayers();
										pallet.numPallets += 1;
									}
									if (order.getLayers() == 2 && order.getLayers() + pallet.numLayers <= 6 && pallet.numPallets <= 4 
										&& order.used == false){
										pallet.palletOrders.add(order);
										order.used = true;
										pallet.numLayers = pallet.numLayers + order.getLayers();
										pallet.numPallets += 1;
									}
									if (order.getLayers() == 1 && order.getLayers() + pallet.numLayers <= 6 && pallet.numPallets <= 4 
										&& order.used == false){
										pallet.palletOrders.add(order);
										order.used = true;
										pallet.numLayers = pallet.numLayers + order.getLayers();
										pallet.numPallets += 1;
									}
								}
								emergencyExit++;
								break;
							case 4:
								for (Order order : orderList){
									if (order.getLayers() == 2 && order.getLayers() + pallet.numLayers <= 6 && pallet.numPallets <= 4 
										&& order.used == false){
										if (pallet.have40() && order.getCases() == 40)
											{
												freeze = freeze + 1;
												if (freeze > 30)
												{
													pallet.palletOrders.add(order);
													order.used = true;
													pallet.numLayers = pallet.numLayers + order.getLayers();
													pallet.numPallets++;
													freeze = 0;
												}
												else
												{
													continue;
												}
											}
										pallet.palletOrders.add(order);
										order.used = true;
										pallet.numLayers = pallet.numLayers + order.getLayers();
										pallet.numPallets += 1;
									}
									if (order.getLayers() == 1 && order.getLayers() + pallet.numLayers <= 6 && pallet.numPallets <= 4 
										&& order.used == false){
										pallet.palletOrders.add(order);
										order.used = true;
										pallet.numLayers = pallet.numLayers + order.getLayers();
										pallet.numPallets += 1;
									} 
								}
								emergencyExit++;
								break;
							case 5:
								for (Order order : orderList){
									if (order.getLayers() == 1 && order.getLayers() + pallet.numLayers <= 6 && pallet.numPallets <= 4 
										&& order.used == false){
										pallet.palletOrders.add(order);
										order.used = true;
										pallet.numLayers = pallet.numLayers + order.getLayers();
										pallet.numPallets += 1;
									}
								}
								emergencyExit++;
								break;
							default:
								emergencyExit++;
								break;
					}
				}
				emergencyExit = 0;
			}
		}
		emergencyExit = 0;
		return finalizedPallets;
	}
		
	public ArrayList<Pallet> finishPallets(ArrayList<Pallet> finalizedPallets, ArrayList<Order> orderList){

		int freeze = 0;
		int emergencyExit = 0;
		boolean anyRemaining = true;

		while (anyRemaining){
			for (Order order : orderList){
				if (order.getLayers() == 6 && order.used == false){
					Pallet newPallet = new Pallet();
					newPallet.palletOrders.add(order);
					order.used = true;
					newPallet.numLayers = 6;
					newPallet.numPallets++;
					finalizedPallets.add(newPallet);
				}
				else if (order.getLayers() == 5 && order.used == false){
					Pallet newPallet = new Pallet();
					newPallet.palletOrders.add(order);
					order.used = true;
					newPallet.numLayers = 5;
					newPallet.numPallets++;
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 1 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					finalizedPallets.add(newPallet);
				}
				else if (order.getLayers() == 4 && order.used == false){
					Pallet newPallet = new Pallet();
					newPallet.palletOrders.add(order);
					order.used = true;
					newPallet.numLayers = 4;
					newPallet.numPallets++;
					for (Order order40: orderList)
						{
							if (order40.getLayers() == 2 && order40.getLayers() + newPallet.numLayers <= 6 && newPallet.numPallets <= 4 
							&& order40.used == false && order40.getCases() == 40 && !newPallet.have40())
							{
								newPallet.palletOrders.add(order40);
								order40.used = true;
								newPallet.numLayers = newPallet.numLayers + order40.getLayers();
								newPallet.numPallets++;
							}
						}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 2 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 1 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					finalizedPallets.add(newPallet);
				}
				else if (order.getLayers() == 3 && order.used == false){
					Pallet newPallet = new Pallet();
					newPallet.palletOrders.add(order);
					order.used = true;
					newPallet.numLayers = 3;
					newPallet.numPallets++;
					for (Order order40: orderList)
						{
							if (order40.getLayers() == 2 && order40.getLayers() + newPallet.numLayers <= 6 && newPallet.numPallets <= 4 
							&& order40.used == false && order40.getCases() == 40 && !newPallet.have40())
							{
								newPallet.palletOrders.add(order40);
								order40.used = true;
								newPallet.numLayers = newPallet.numLayers + order40.getLayers();
								newPallet.numPallets++;
							}
						}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 3 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 2 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 1 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					finalizedPallets.add(newPallet);
				}
				else if (order.getLayers() == 2 && order.used == false){
					Pallet newPallet = new Pallet();
					newPallet.palletOrders.add(order);
					order.used = true;
					newPallet.numLayers = 2;
					newPallet.numPallets++;
					for (Order order40: orderList)
						{
							if (order40.getLayers() == 2 && order40.getLayers() + newPallet.numLayers <= 6 && newPallet.numPallets <= 4 
							&& order40.used == false && order40.getCases() == 40 && !newPallet.have40())
							{
								newPallet.palletOrders.add(order40);
								order40.used = true;
								newPallet.numLayers = newPallet.numLayers + order40.getLayers();
								newPallet.numPallets++;
							}
						}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 4 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 3 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 2 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							if (newPallet.have40() && orderList.get(i).getCases() == 40)
							{
								freeze = freeze + 1;
								if (freeze > 30)
								{
									newPallet.palletOrders.add(orderList.get(i));
									orderList.get(i).used = true;
									newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
									newPallet.numPallets++;
									freeze = 0;
								}
								else
								{
									continue;
								}
							}
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 1 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					finalizedPallets.add(newPallet);
				}
				else if (order.getLayers() == 1 && order.used == false){
					Pallet newPallet = new Pallet();
					newPallet.palletOrders.add(order);
					order.used = true;
					newPallet.numLayers = 1;
					newPallet.numPallets++;
					for (Order order40: orderList)
						{
							if (order40.getLayers() == 2 && order40.getLayers() + newPallet.numLayers <= 6 && newPallet.numPallets <= 4 
							&& order40.used == false && order40.getCases() == 40 && !newPallet.have40())
							{
								newPallet.palletOrders.add(order40);
								order40.used = true;
								newPallet.numLayers = newPallet.numLayers + order40.getLayers();
								newPallet.numPallets++;
							}
						}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 5 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 4 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 3 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					for (int i = 0; i < orderList.size(); i++){	
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 2 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							if (newPallet.have40() && orderList.get(i).getCases() == 40)
							{
								freeze = freeze + 1;
								if (freeze > 30)
								{
									newPallet.palletOrders.add(orderList.get(i));
									orderList.get(i).used = true;
									newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
									newPallet.numPallets++;
									freeze = 0;
								}
								else
								{
									continue;
								}
							}
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					for (int i = 0; i < orderList.size(); i++){
						if (orderList.get(i).used == false && orderList.get(i).getLayers() == 1 && orderList.get(i).getLayers() 
							+ newPallet.numLayers <= 6 && newPallet.numPallets <= 3){
							newPallet.palletOrders.add(orderList.get(i));
							orderList.get(i).used = true;
							newPallet.numLayers = newPallet.numLayers + orderList.get(i).getLayers();
							newPallet.numPallets++;
						}
					}
					finalizedPallets.add(newPallet);
				}
				emergencyExit++;
				if (emergencyExit >= 100){
					System.out.println("Stuck in an infinite loop. Exiting the program.");
					System.exit(1);
				}
			}

			for (Order order : orderList){
				if (order.used){ 
					anyRemaining = false;
				}
				else{
					anyRemaining = true;
					break;
				}
			}

		}
		return finalizedPallets;
	}

	public Order returnKey(HashMap<Order, Integer> map, int poNum){
		for (Order order : map.keySet()){
			if (order.getPODestination() == poNum){
				return order;
			}
		}
		return null;
	}

	public String getPallet(){
		String message = "";
			for (Order order: palletOrders){
				message = message + "\t\t\t\t\t\t\t\t\t" + "Order: " + order + "\n"; //calls the order class toString()
			}
		return message;
	}

	public void resetPallet(){
		this.numLayers = 0;
		this.numPallets = 0;
		this.palletOrders.clear();
	}
}