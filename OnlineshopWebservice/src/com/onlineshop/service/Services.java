package com.onlineshop.service;

import java.util.HashSet;
import java.util.Map;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


//This class is the combined implementation for Restful & SOAP class which provide web services to REST and SOAP type of connections
//for SOAP
@WebService(endpointInterface = "com.onlineshop.service.SOAPserverInterface")
//for REST
@Path("/services")
public class Services implements SOAPserverInterface{
	private static Store storeHandle;
	private static Customer customerHandle;
	private static HashSet<Item> itemsHandle;
	public Services(){
		//Get handle of store from Singleton 
		storeHandle=Singleton.getStoreHandle();
	}
    //this method will add a item to user cart and after finishing return number of items in cart and a message
	// returned xml format:<result><messagetouser>XXXX</messagetouser><totalitem>XX</totalitem></result>
    @GET
    @Path("/additemtocart/{itemtype}/{itemmake}/{itemmodel}/{userid}")
    @Produces(MediaType.TEXT_XML)
    @Consumes(MediaType.TEXT_PLAIN)
    public String additemtocart(@PathParam("itemtype") String itemType, @PathParam("itemmake") String itemMake,
    							@PathParam("itemmodel") String itemModel, @PathParam("userid") String userID) {
 
    	int itembefore=0,itemafter=0;
    	String messageToUser="";
    	String userResponse="<?xml version='1.0' encoding='UTF-8'?>"+"<result>"+"<messagetouser>";

    	customerHandle=Singleton.getCustomerhandle(userID);								//get customer handle
    	itemsHandle = Singleton.getItemHandle(itemType, itemMake, itemModel);			//get item handle in store
    	if(itemsHandle.isEmpty()){											//if item not found in store return message
    		messageToUser="item not found in store while customer trying to add item in cart";
    	}
    	else{																//else take count of items beforeadding, add item to cart
    		itembefore = customerHandle.getshoppingCart().gettotalItem();				
    		itemafter  = customerHandle.addItemToMyCart(itemsHandle);

    		if(itembefore==itemafter){								//if item not added because all items of same type is already in user cart
        		messageToUser="Already this item is added in cart, there are no more item of same type in store";
        	}
        	else{													//else item added sucessfully
        		messageToUser="One item added to cart";
        	}
    	}
    	
    	return userResponse+messageToUser+"</messagetouser><totalitem>"+String.valueOf(itemafter)+"</totalitem></result>";
    }
    //this method will remove a item from user cart and return number of item in cart and message
    // returned xml format:<result><messagetouser>XXXX</messagetouser><totalitem>XX</totalitem></result>
    @GET
    @Path("/removefromcart/{itemtype}/{itemmake}/{itemmodel}/{userid}")
    @Produces(MediaType.TEXT_XML)
    @Consumes(MediaType.TEXT_PLAIN)
    public String removefromcart(@PathParam("itemtype") String itemType, @PathParam("itemmake") String itemMake,
    							@PathParam("itemmodel") String itemModel, @PathParam("userid") String userID) {

    	int itemafter=0;
    	String messageToUser="Item removed from cart";
    	String userResponse="<?xml version='1.0' encoding='UTF-8'?>"+"<result>"+"<messagetouser>";

    	customerHandle=Singleton.getCustomerhandle(userID);								//get customer handle
    	itemafter=customerHandle.removeItemFromMyCart(itemType,itemMake,itemModel);		//remove item customer cart

    	return userResponse+messageToUser+"</messagetouser><totalitem>"+String.valueOf(itemafter)+"</totalitem></result>";
    }

    //This method will return list of smart phone in XML format
    @GET
    @Path("/smartphonelist")
    @Produces(MediaType.TEXT_XML)
    public String smartphonelist() {
		
        return "<?xml version='1.0' encoding='UTF-8'?>"+storeHandle.showItemList("SmartPhone");
    }
  //This method will return list of home theater in XML format
    @GET
    @Path("/hometheaterlist")
    @Produces(MediaType.TEXT_XML)
    public String hometheaterlist() {
		
        return "<?xml version='1.0' encoding='UTF-8'?>"+storeHandle.showItemList("HomeTheater");
    }
    //This method will return list of Laptop in XML format
    @GET
    @Path("/laptoplist")
    @Produces(MediaType.TEXT_XML)
    public String laptoplist() {
	
        return "<?xml version='1.0' encoding='UTF-8'?>"+storeHandle.showItemList("Laptop");
    }
    //This method will return list of items in cart,total items and total price in XML format
    @GET
    @Path("/viewcart/{userid}")
    @Produces(MediaType.TEXT_XML)
    public String viewcart(@PathParam("userid") String userID) {
    
    	customerHandle=Singleton.getCustomerhandle(userID);
        return "<?xml version='1.0'  encoding='UTF-8'?>" + "<result>" +  customerHandle.viewMyCart() + "</result>";

    }
    //this method will remove all items from user cart and delete items from store  
    //and return success or not success(if item bought by another user and no more same type of item available in store) 
    //message for each item and make total item in cart as 0 by returning 0
    //message format:	<result>{<messagetouser>XXX</messagetouser>}*<totalitem>0</totalitem></result>
    @GET
    @Path("/buyitems/{userid}")
    @Produces(MediaType.TEXT_XML)
    public String buyitems(@PathParam("userid") String userID) {
    	
    	String userResponse="<?xml version='1.0' encoding='UTF-8'?>" + "<result>";
    	String messageToUser="";
    	String sellResponse="";
    	int totalitems=0;
    	//get the list of items from customer cart and call sellItem to remove item from store
    	//if item is already sold to another customer send message accordingly
    	//call buyItems to remove all items from customer cart
    	customerHandle=Singleton.getCustomerhandle(userID);

    	if(!customerHandle.getshoppingCart().getitemsInCart().isEmpty()){		//if cart is not empty take items from customer cart and sell
		for(Map.Entry<String, Item> entry : customerHandle.getshoppingCart().getitemsInCart().entrySet()){

			sellResponse = storeHandle.sellItem(entry.getValue().getItem_Name(),entry.getValue().getItem_Make(),entry.getValue().getItem_Model());

			if(sellResponse.equals("Item not in Store")){
 				messageToUser="<messagetouser>This item already sold out, no more stock: "+
							  entry.getValue().getItem_Name()+" "+entry.getValue().getItem_Make()+" "+entry.getValue().getItem_Model()+
							  "</messagetouser>";
			}
			else{
				messageToUser="<messagetouser>Thanks! Item ready for despatch from store: "+
						      entry.getValue().getItem_Name()+" "+entry.getValue().getItem_Make()+" "+entry.getValue().getItem_Model()+
						      "</messagetouser>";
			}
			userResponse = userResponse + messageToUser; 
		}
		totalitems=customerHandle.buyItems();				//remove all item from customer cart
    	}
    	else{																	//else send empty cart message
    		messageToUser="<messagetouser>"+"Cart is empty, please add items in cart"+"</messagetouser>";
    		userResponse = userResponse + messageToUser;
    	}
    	return userResponse + "<totalitem>" + totalitems +"</totalitem>"+ "</result>";
    }
}
