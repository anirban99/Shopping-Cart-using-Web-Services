package com.onlineshop.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
//This class instantiates all persistent objects that are needed in serverside and provide handles to Services class
//Only one instance can be created of this class and that can be referenced from other class 
public class Singleton {

	private static Singleton singleton = new Singleton( );

	   private static Customer  cust;
	   private static Map<String,Customer> customerList;
	   private static Store store;
	   private static HashSet<Item> items;

	   /*private Constructor prevents any other class from instantiating*/
	   private Singleton(){ 
			customerList = new HashMap<String,Customer>();
			store = new Store();
			store.createItem();
			store.addItemToList();
	   }
	   /* Static 'instance' method */
	   public static Singleton getInstance( ) {
	      return singleton;
	   }
	   /* Other methods protected by singleton-ness */
	   //This method finds a customer in list of active customers and returns a handle
	   protected static Customer getCustomerhandle(String userID){
	    	 boolean customerFound=false;
	    	 //find customer in list of customers if list is not empty and return if found
	    	 if(!customerList.isEmpty()){
	 			for(Map.Entry<String, Customer> entry : customerList.entrySet()){
	        		if(entry.getValue().getcustomerID().equals(userID)){
	        			 customerFound=true;
	        			 cust=entry.getValue();
	        			 break;
	        		 }
	    	   	 } 
	    	 }//if not found create customer and add to list
	    	 if(customerFound==false){
	    		 cust=new Customer(userID);
	    		 customerList.put(userID,cust);	    	 
	    	 }
	    	 //customerList.put(userID,new Customer(userID));
	    	 /*for(Map.Entry<String, Customer> entry : customerList.entrySet()){
	    		 System.out.println("Customers: "+entry.getKey()+" "+entry.getValue().getcustomerID());
	    	 }*/
	    	 return cust;
	   }
	   //This method returns handle of store
	   protected static Store getStoreHandle(){
		   return store;
	   }
	   //This method returns handle of set of items in store that matches (item type, item make, item model)
	   protected static HashSet<Item> getItemHandle(String itemType, String itemMake, String itemModel){
		   items = store.findIteminStore(itemType, itemMake, itemModel);
		   return items;
	   }
	}
