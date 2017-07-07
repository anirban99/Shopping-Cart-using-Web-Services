package com.onlineshop.service;

import java.util.HashSet;
import java.util.List;
//This Class represents customer object
public class Customer {
	private String customerID;
	private String customerName="";
	private String customerAddress="";
	private shoppingCart   customerCart;		//shopping cart for each customer
	
	public Customer(){}
	public Customer(String customerID) {
		this.customerID = customerID;
		setcustomerCart();						//Initialize customers cart
	}
	//This method adds item to customer's cart
	public int addItemToMyCart(HashSet<Item> items){		return customerCart.addToCart(items);		}
	//This method displays customer's cart
	public String viewMyCart(){								return customerCart.viewCart();				}
	//This method removes item from customer's cart
	public int removeItemFromMyCart(String itemType,String itemMake,String itemModel){
		return customerCart.removeFromCart(itemType,itemMake,itemModel);	
	}
	//This method buys items in customer's cart
	public int buyItems(){									return customerCart.removeallItems();		}
	//setters
	public void setcustomerID(String customerID){			this.customerID = customerID;				}
	public void setcustomerName(String customerName){		this.customerName = customerName;			}
	public void setcustomerAddress(String customerAddress){	this.customerAddress = customerAddress;		}
	public void setcustomerCart(){							this.customerCart = new shoppingCart();		}
	//getters
	public String getcustomerID(){							return this.customerID;						}
	public String getcustomerName(){						return this.customerName;					}
	public String getcustomerAddress(){						return this.customerAddress;				}
	public shoppingCart getshoppingCart(){					return this.customerCart;					}

}
