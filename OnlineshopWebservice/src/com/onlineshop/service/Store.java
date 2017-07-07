package com.onlineshop.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
//This item represents item object
public class Store {

	private Item newitem1,newitem2,newitem3,newitem4,newitem5,newitem6,newitem7,newitem8,newitem9,newitem10;
	private Item newitem11,newitem12,newitem13,newitem14,newitem15,newitem16,newitem17,newitem18,newitem19,newitem20;
	private Item newitem21,newitem22,newitem23,newitem24,newitem25,newitem26,newitem27,newitem28,newitem29,newitem30;
	//maps to hold 3 types of items in store
    private Map<String,Item> smartPhoneList;
    private Map<String,Item> homeTheaterList;
    private Map<String,Item> laptopList;
 
	public Store() {

		smartPhoneList  = new HashMap<String,Item>();
		homeTheaterList  = new HashMap<String,Item>();
		laptopList  = new HashMap<String,Item>();
	}
	//This method removes Item from store, 
	//if item is not in store returns "Item not in Store" if item sucessfully removed returns "Item removed from Store" 
	private String removeItem(String itemType,String itemID){
		String response="";
		if(itemType.equals("SmartPhone")){
				if(smartPhoneList.containsKey(itemID)){
						smartPhoneList.remove(itemID);	response="Item removed from Store";	}
				else {									response="Item not in Store";		}
		}
		else if(itemType.equals("HomeTheater")){
				if(homeTheaterList.containsKey(itemID)){
						homeTheaterList.remove(itemID);	response="Item removed from Store";	}
			else {										response="Item not in Store";		}
		}
		else if(itemType.equals("Laptop")){
				if(laptopList.containsKey(itemID)){
						laptopList.remove(itemID);		response="Item removed from Store";	}
				else {									response="Item not in Store";}
		}
		return response;
	}
	//This method checks if item is in store, if yes remove from store and mark it sold
	//if item can be sold returns "Item removed from Store" else returns "Item not in Store"
	public synchronized String sellItem(String itemType, String itemMake,String itemModel){
		String sellResponse="Item not in Store";
		if(itemType.equals("SmartPhone")){					//search smartphone list if list not empty and call removeItem to remove from store
			if(!smartPhoneList.isEmpty()){
				for(Map.Entry<String, Item> entry : smartPhoneList.entrySet()){

					if((entry.getValue().getItem_Name().equals(itemType)) &&
					   (entry.getValue().getItem_Make().equals(itemMake)) &&	
					   (entry.getValue().getItem_Model().equals(itemModel)) ){
	
						sellResponse = removeItem(itemType,entry.getKey());
						break;
					}
				}
			}
		}
		else if(itemType.equals("HomeTheater")){			//search hometheater list if list not empty and call removeItem to remove from store
			if(!homeTheaterList.isEmpty()){
				for(Map.Entry<String, Item> entry : homeTheaterList.entrySet()){

					if((entry.getValue().getItem_Name().equals(itemType)) &&
					   (entry.getValue().getItem_Make().equals(itemMake)) &&	
					   (entry.getValue().getItem_Model().equals(itemModel)) ){
							
						sellResponse = removeItem(itemType,entry.getKey());
						break;
					}
				}
			}
		}
		else if(itemType.equals("Laptop")){					//search laptop list if list not empty and call removeItem to remove from store
			if(!laptopList.isEmpty()){
				for(Map.Entry<String, Item> entry : laptopList.entrySet()){
					
					if((entry.getValue().getItem_Name().equals(itemType)) &&
					   (entry.getValue().getItem_Make().equals(itemMake)) &&	
					   (entry.getValue().getItem_Model().equals(itemModel)) ){
							
						sellResponse = removeItem(itemType,entry.getKey());
						break;
					}
				}
			}
		}
		return sellResponse;
	}
	//This method finds a tuple (item type,item make,item model) in store and returns set of items along with item ID
	//it seraches corresponding item list and addds matching item in set which will be returned  else returns empty set
	public HashSet<Item> findIteminStore(String itemType,String itemMake,String itemModel){
		
		HashSet<Item> returnItems = new HashSet<Item>();

		if(itemType.equals("SmartPhone")){
			if(!smartPhoneList.isEmpty()){
				for(Map.Entry<String, Item> entry : smartPhoneList.entrySet()){

					if((entry.getValue().getItem_Make().equals(itemMake)) &&
					   (entry.getValue().getItem_Model().equals(itemModel)))	{
							returnItems.add(entry.getValue());
					}
				}
			}
		}
		else if(itemType.equals("HomeTheater")){
			if(!homeTheaterList.isEmpty()){
				for(Map.Entry<String, Item> entry : homeTheaterList.entrySet()){

					if((entry.getValue().getItem_Make().equals(itemMake)) &&
					   (entry.getValue().getItem_Model().equals(itemModel)))	{
							returnItems.add(entry.getValue());
					}
				}
			}
		}
		else if(itemType.equals("Laptop")){
			if(!laptopList.isEmpty()){
				for(Map.Entry<String, Item> entry : laptopList.entrySet()){

					if((entry.getValue().getItem_Make().equals(itemMake)) &&
					   (entry.getValue().getItem_Model().equals(itemModel)))	{
							returnItems.add(entry.getValue());
					}
				}
			}
		}

		return returnItems;
	}
	//This method returns list of items in store for specific type in XML format
	//if there is no item in list then returns "No Items in List"
	public String showItemList(String itemType){
		String Value="";
		String type="";
		String make="";
		String price="";
		String model="";
		String id="";
		
		if(itemType.equals("SmartPhone")){
			if(!smartPhoneList.isEmpty()){
				for(Map.Entry<String, Item> entry : smartPhoneList.entrySet()){

					type ="		<type> "+entry.getValue().getItem_Name()+" </type>";
					make ="		<make> "+entry.getValue().getItem_Make()+" </make>";
					price ="	<price> "+entry.getValue().getItem_Price()+" </price>";
					model ="	<model> "+entry.getValue().getItem_Model()+" </model>";
					id ="		<id> "+entry.getValue().getItem_ID()+" </id>";
					
					Value= Value + "<itemnumber>" + type + make + price + model + id +"</itemnumber>";
				}
			}
			else{
					Value= "No Items in List";
			}
		}
		else if(itemType.equals("HomeTheater")){
			if(!homeTheaterList.isEmpty()){
				for(Map.Entry<String, Item> entry : homeTheaterList.entrySet()){

					type ="<type> "+entry.getValue().getItem_Name()+" </type>";
					make ="<make> "+entry.getValue().getItem_Make()+" </make>";
					price ="<price> "+entry.getValue().getItem_Price()+" </price>";
					model ="<model> "+entry.getValue().getItem_Model()+" </model>";
					id ="<id> "+entry.getValue().getItem_ID()+" </id>";
					
					Value= Value + "<itemnumber>" + type + make + price + model + id +"</itemnumber>";
				}
			}
			else{
					Value= "No Items in List";
			}
		}
		else if(itemType.equals("Laptop")){
			if(!laptopList.isEmpty()){
				for(Map.Entry<String, Item> entry : laptopList.entrySet()){
					
					type ="		<type> "+entry.getValue().getItem_Name()+" </type>";
					make ="		<make> "+entry.getValue().getItem_Make()+" </make>";
					price ="	<price> "+entry.getValue().getItem_Price()+" </price>";
					model ="	<model> "+entry.getValue().getItem_Model()+" </model>";
					id ="		<id> "+entry.getValue().getItem_ID()+" </id>";
					
					Value= Value + "<itemnumber>" + type + make + price + model + id +"</itemnumber>";
				}
			}
			else{
					Value= "No Items in List";
			}
		}

		return "<result>" +  Value + "</result>";
	}
	//This method creates items
	public void createItem(){
		newitem1 = new Item("S00001","SmartPhone",10000,"Samsung","Galaxy A5");
		newitem2 = new Item("S00002","SmartPhone",10000,"Samsung","Galaxy A5");
		newitem3 = new Item("S00003","SmartPhone",15000,"Samsung","Galaxy Note 4");
		newitem4 = new Item("S00004","SmartPhone",15000,"Samsung","Galaxy Note 4");
		newitem5 = new Item("S00005","SmartPhone",10000,"O2","XDA Seera");
		newitem6 = new Item("S00006","SmartPhone",9000,"O2","XDA Nova");
		newitem7 = new Item("S00007","SmartPhone",8000,"Sony","Xperia Z3 Tablet");
		newitem8 = new Item("S00008","SmartPhone",15000,"Sony","Xperia C3 Dual");
		newitem9 = new Item("S00009","SmartPhone",20000,"Sony","Xperia M2 Dual");
		newitem10 = new Item("S00010","SmartPhone",14000,"Nokia","Lumia830");

		newitem11 = new Item("T00001","HomeTheater",50000,"Samsung","HT F5550K 5");
		newitem12 = new Item("T00002","HomeTheater",50000,"Samsung","HT F5550K 5");
		newitem13 = new Item("T00003","HomeTheater",60000,"Samsung","HT F5551 5");
		newitem14 = new Item("T00004","HomeTheater",60000,"Samsung","HT F5551 5");
		newitem15 = new Item("T00005","HomeTheater",100000,"Samsung","HT H7750WM 3D Blue Ray");
		newitem16 = new Item("T00006","HomeTheater",55000,"Sony","BDV E3100 5");
		newitem17 = new Item("T00007","HomeTheater",55000,"Sony","BDV E3100 5");
		newitem18 = new Item("T00008","HomeTheater",60000,"Sony","HT M5");
		newitem19 = new Item("T00009","HomeTheater",60000,"Sony","HT M5");
		newitem20 = new Item("T00010","HomeTheater",110000,"Sony","Shake 6D Mini Hi Fi");

		newitem21 = new Item("L00001","Laptop",30000,"Samsung","NP100E5E-14'Scr-300GB-2GB-i3");
		newitem22 = new Item("L00002","Laptop",29000,"Samsung","NP200E5E-14'Scr-500GB-2GB-i5");
		newitem23 = new Item("L00003","Laptop",31000,"Samsung","NP300E5E-15'Scr-500GB-2GB-i5");
		newitem24 = new Item("L00004","Laptop",30000,"HP","Pavilion-15'Scr-500GB-1GB-i3");
		newitem25 = new Item("L00005","Laptop",31000,"HP","Notebook-15'Scr-1TB-2GB-i3");
		newitem26 = new Item("L00006","Laptop",55000,"Apple","Macbook Pro-15'Scr-500GB-2GB-i7");
		newitem27 = new Item("L00007","Laptop",55000,"Apple","Macbook Pro-15'Scr-500GB-2GB-i7");
		newitem28 = new Item("L00008","Laptop",55000,"Apple","Macbook Pro-15'Scr-500GB-2GB-i7");
		newitem29 = new Item("L00009","Laptop",35000,"Lenovo","Notebook 16'Scr-500GB-2GB-i3");
		newitem30 = new Item("L00010","Laptop",40000,"Lenovo","Ideapad 16'Scr-500GB-2GB-i5");
 
	}
	//This method add items to store
	public void addItemToList(){
		smartPhoneList.put(newitem1.getItem_ID(), newitem1);
		smartPhoneList.put(newitem2.getItem_ID(), newitem2);
		smartPhoneList.put(newitem3.getItem_ID(), newitem3);
		smartPhoneList.put(newitem4.getItem_ID(), newitem4);
		smartPhoneList.put(newitem5.getItem_ID(), newitem5);
		smartPhoneList.put(newitem6.getItem_ID(), newitem6);
		smartPhoneList.put(newitem7.getItem_ID(), newitem7);
		smartPhoneList.put(newitem8.getItem_ID(), newitem8);
		smartPhoneList.put(newitem9.getItem_ID(), newitem9);
		smartPhoneList.put(newitem10.getItem_ID(), newitem10);
		homeTheaterList.put(newitem11.getItem_ID(), newitem11);
		homeTheaterList.put(newitem12.getItem_ID(), newitem12);
		homeTheaterList.put(newitem13.getItem_ID(), newitem13);
		homeTheaterList.put(newitem14.getItem_ID(), newitem14);
		homeTheaterList.put(newitem15.getItem_ID(), newitem15);
		homeTheaterList.put(newitem16.getItem_ID(), newitem16);
		homeTheaterList.put(newitem17.getItem_ID(), newitem17);
		homeTheaterList.put(newitem18.getItem_ID(), newitem18);
		homeTheaterList.put(newitem19.getItem_ID(), newitem19);
		homeTheaterList.put(newitem20.getItem_ID(), newitem20);
 		laptopList.put(newitem21.getItem_ID(), newitem21);
 		laptopList.put(newitem22.getItem_ID(), newitem22);
 		laptopList.put(newitem23.getItem_ID(), newitem23);
 		laptopList.put(newitem24.getItem_ID(), newitem24);
 		laptopList.put(newitem25.getItem_ID(), newitem25);
 		laptopList.put(newitem26.getItem_ID(), newitem26);
 		laptopList.put(newitem27.getItem_ID(), newitem27);
 		laptopList.put(newitem28.getItem_ID(), newitem28);
		laptopList.put(newitem29.getItem_ID(), newitem29);
		laptopList.put(newitem30.getItem_ID(), newitem30);
		
	}

}
