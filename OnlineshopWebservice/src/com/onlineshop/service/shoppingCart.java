package com.onlineshop.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
//This class represents shopping cart object
public class shoppingCart {

	private Map<String,Item> itemsInCart;					//item list
	private int totalItem=0;								//total items in cart
	private double totalPrice=0;							//total price of items in cart
	
	public shoppingCart() {

		itemsInCart = new HashMap<String,Item>(); 
	}
	//This method add item in cart, increases total item count in cart and total price of cart
	//receives the list items (along with item ID) available in store which matches the customer selection (item type,item make,item model)
	//returns total item in cart so that client page can show updated item number in cart 
	public int addToCart(HashSet<Item> itemToCart){
		 /*for (Item temp : itemToCart) {
 			  System.out.println("all items sent "+ temp.getItem_ID());
		   }*/
		//if user cart is not empty, search item set received: 
		//		if there is any item in set (with unique itemID) which already in user cart, add item with another itemID from set (if exist)
		//		if all the items in set is in user cart no items will be added and total item count will be same, which this method will return   
		if(!itemsInCart.isEmpty()){
				for (Item temp : itemToCart) {
 					//System.out.println("itemToCart "+temp.getItem_ID());
					if(!itemsInCart.containsKey(temp.getItem_ID())){
						itemsInCart.put(temp.getItem_ID(),temp);
						totalPrice = totalPrice + temp.getItem_Price();
						totalItem = totalItem + 1;
						break;
					}
				}
		} //if user cart is empty take the first item from list and add to cart
		else{
		Iterator<Item> it = itemToCart.iterator();	
		Item i = it.next();
		itemsInCart.put(i.getItem_ID(),i);
		totalPrice = totalPrice + i.getItem_Price();
		totalItem = totalItem + 1;
		}

		return totalItem;
	}
	//This method removes item from cart which matches(item type,item make,item model) tuple and decreases item count and total price
	//receives (item type,item make,item model) user want to remove
	//returns total item in cart after removal
	public int removeFromCart(String itemType,String itemMake,String itemModel){
		//if cart empty just for assurance set item count and price to 0
 		if(itemsInCart.isEmpty()){
			totalItem=0;
			totalPrice=0;
		}//else get the matching itemID of the item from cart and remove
		else{
			for(Map.Entry<String, Item> entry : itemsInCart.entrySet()){
 
				if((entry.getValue().getItem_Name().equals(itemType)) &&
				   (entry.getValue().getItem_Make().equals(itemMake)) &&
				   (entry.getValue().getItem_Model().equals(itemModel))){
						itemsInCart.remove(entry.getValue().getItem_ID());
						totalItem = totalItem - 1;
						totalPrice = totalPrice - entry.getValue().getItem_Price();
						break;
				}
			}	
		}
		return totalItem;
	}
	//This method removes all items from user cart and resets total item count and total price
	public int removeallItems(){
	
		if(!itemsInCart.isEmpty()){
			itemsInCart.clear();
			totalItem = 0;
			totalPrice = 0;
		}
		return totalItem;
	}
	//*This method will return all the items in cart in below format*/
	//<cart>
	//	{<item><type>  XXXXXX </type><make>  XXXXXX </make><price> XXXXXX </price><model> XXXXXX </model><id>    XXXXXX </id></item>}*
	//	 <totalcost>XXXXXX.X</totalcost><totalitem>X</totalitem>
	//</cart>
	//Note: if cart empty -> <cart><totalcost>0</totalcost><totalitem>0</totalitem></cart>
	public String viewCart(){
		String viewCartResult="<cart>";
		String type="";
		String make="";
		String price="";
		String model="";
		String id="";
		
		if(!itemsInCart.isEmpty()){
			
			for(Map.Entry<String, Item> entry : itemsInCart.entrySet()){

				type ="		<type> "+entry.getValue().getItem_Name()+" </type>";
				make ="		<make> "+entry.getValue().getItem_Make()+" </make>";
				price ="	<price> "+entry.getValue().getItem_Price()+" </price>";
				model ="	<model> "+entry.getValue().getItem_Model()+" </model>";
				id ="		<id> "+entry.getValue().getItem_ID()+" </id>";
				
				viewCartResult= viewCartResult +"<item>"+ type + make + price + model + id +"</item>";
			}
			viewCartResult= viewCartResult +"<totalcost>"+totalPrice+"</totalcost><totalitem>"+totalItem+"</totalitem></cart>";
		}
		else{
			viewCartResult= viewCartResult+"<totalcost>0</totalcost><totalitem>0</totalitem></cart>";
		}

		return viewCartResult;
	}
	//getters
	public int gettotalItem(){					return this.totalItem;	}
	public double gettotaPrice(){				return this.totalPrice;	}
	public Map<String,Item> getitemsInCart(){ 	return this.itemsInCart;}

}
