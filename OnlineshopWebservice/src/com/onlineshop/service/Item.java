package com.onlineshop.service;
//This class represents Item objets
 class Item {

	private String Item_ID;
	private String Item_Name;
	private double Item_Price;
	private String Item_Make;
	private String Item_Model;

	public Item(){}
	public Item(String ID,String name,double price,String make,String model) {
		Item_ID    = ID;
		Item_Name  = name;
		Item_Price = price;
		Item_Make  = make;
		Item_Model  = model;
	}
	//setters
	public void setItem_ID(String ID){			Item_ID = ID;		}
	public void setItem_Name(String name){		Item_Name = name;	}
	public void setItem_Price(double price){	Item_Price = price;	}
	public void setItem_Make(String make){		Item_Make = make;	}
	public void setItem_Model(String model){	Item_Model = model;	}
	//getters
	public String getItem_ID(){ 				return Item_ID;		}
	public String getItem_Name(){				return Item_Name;	}
	public double getItem_Price(){				return Item_Price;	}
	public String getItem_Make(){				return Item_Make;	}
	public String getItem_Model(){				return Item_Model;	}
}
