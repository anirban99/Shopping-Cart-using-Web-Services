package com.onlineshop.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
 
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface SOAPserverInterface {

	@WebMethod String additemtocart(String itemType,String itemMake,String itemModel,String userID);
	@WebMethod String removefromcart(String itemType,String itemMake,String itemModel,String userID);
	@WebMethod String smartphonelist();
	@WebMethod String hometheaterlist();
	@WebMethod String laptoplist();
	@WebMethod String viewcart(String userID);
	@WebMethod String buyitems(String userID);
}
