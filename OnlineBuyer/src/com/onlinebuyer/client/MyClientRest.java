package com.onlinebuyer.client;

import java.io.IOException;
import java.io.StringReader;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
//This is the client class to get response for Restful services   
public class MyClientRest {
	
    static final String REST_URI = "http://localhost:8080/rest/";
    static final String SMARTPHONE_LIST_PATH = "services/smartphonelist";
    static final String HOMETHEATER_LIST_PATH = "services/hometheaterlist";
    static final String LAPTOP_LIST_PATH = "services/laptoplist";
    static final String VIEW_CART_PATH = "services/viewcart";
    static final String ADD_ITEM_TO_CART_PATH = "services/additemtocart";
    static final String REMOVE_FROM_CART_PATH = "services/removefromcart";
    static final String BUY_ITEMS_PATH = "services/buyitems";
    WebResource service; 
    WebResource addService;
    WebResource subService;
    WebResource SMPLService;
    WebResource HTLService;
    WebResource LPLService;
    WebResource viewCartService;
    WebResource addToCartService;
    WebResource removeFromCartService;
    WebResource buyItemsService;
    Document dom,domCL,domARB;
    private String myName;
    private Map<String,String> mapHash;
    private ArrayList<String> cartList;
    private ArrayList<String> messageList;
    private String totalItem="0";
    private String totalPrice="0";
    
    public MyClientRest(String myName){
    	
    	this.myName=myName;
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource(REST_URI);
        
    }
    public static void main(String[] args) {
       
 //       MyClientRest myclient1 = new MyClientRest("Dibyo1");
 //       MyClientRest myclient2 = new MyClientRest("Dibyo2");
     }
	//This mathod parses xml response from web service additemtocart,removefromcart,buyitems. message format:
	//<result>{<messagetouser>XXXX</messagetouser>}*<totalitem>XX</totalitem></result>
	private void parseUserActionResponse(String xml){
		messageList = new ArrayList<String>();
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			domARB = db.parse(new InputSource(new StringReader(xml)));
			domARB.getDocumentElement().normalize();
			NodeList nList = domARB.getElementsByTagName("messagetouser");
			messageList.clear();
			//put all message from server in a list
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		        	Element eElement = (Element) nNode; 
		        	messageList.add(eElement.getTextContent());
		        }
		    }
		    //catch total items returned from server
		    totalItem=domARB.getElementsByTagName("totalitem").item(0).getTextContent();
		    
		}catch(ParserConfigurationException pce) {			pce.printStackTrace();
		}catch(SAXException se) {							se.printStackTrace();
		}catch(IOException ioe) {							ioe.printStackTrace();
		}catch (Exception e){								e.printStackTrace();
		}
	}
	
    //This mathod parses xml response from web service viewcart,message format:
	//<result><cart>
	//	{<item><type>XXXXXX</type><make>XXXXXX</make><price>XXXXXX</price><model>XXXXXX</model><id>XXXXXX</id></item>}*
	//	 <totalcost>XXXXXX.X</totalcost><totalitem>X</totalitem>
	//</cart></result>
	//Note: if cart empty -> <result><cart><totalcost>0</totalcost><totalitem>0</totalitem></cart></result>
	private void parseCartList(String xml){
		cartList = new ArrayList<String>();
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			domCL = db.parse(new InputSource(new StringReader(xml)));
			domCL.getDocumentElement().normalize();
			
			NodeList nList = domCL.getElementsByTagName("item");
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		        	Element eElement = (Element) nNode; 
		        	//put cart items in list
		        	String accum = ""+eElement.getElementsByTagName("type").item(0).getTextContent() + " / " +
		        			eElement.getElementsByTagName("make").item(0).getTextContent() + " / " +
		        			eElement.getElementsByTagName("model").item(0).getTextContent()+ " / " +
		        			eElement.getElementsByTagName("price").item(0).getTextContent(); 
		        	cartList.add(accum);
		         }
		    }
		    //catch total price and item returned from server
		    totalPrice=domCL.getElementsByTagName("totalcost").item(0).getTextContent();
		    totalItem=domCL.getElementsByTagName("totalitem").item(0).getTextContent();
		    
		}catch(ParserConfigurationException pce) {			pce.printStackTrace();
		}catch(SAXException se) {							se.printStackTrace();
		}catch(IOException ioe) {							ioe.printStackTrace();
		}catch (Exception e){								e.printStackTrace();
		}
	}
    //This mathod parses xml response from web service viewcart,message format:
	//<result><itemnumber>
	//	{<item><type>XXXXXX</type><make>XXXXXX</make><price>XXXXXX</price><model>XXXXXX</model><id>XXXXXX</id></item>}*
	//</itemnumber></result>
	private void parseItemList(String xml){
    	List<String> list1 = new ArrayList<String>();
    	List<String> list2 = new ArrayList<String>();
    	mapHash = new HashMap<String,String>();
    	mapHash.clear();
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse(new InputSource(new StringReader(xml)));
			dom.getDocumentElement().normalize();
			NodeList nList = dom.getElementsByTagName("itemnumber");
			for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		        	Element eElement = (Element) nNode;
		        	//put the items in cart in 2 list
		        	String accum=eElement.getElementsByTagName("type").item(0).getTextContent()+"#"+
		        			eElement.getElementsByTagName("make").item(0).getTextContent()+"#"+	
		        			eElement.getElementsByTagName("price").item(0).getTextContent()+"#"+
		        			eElement.getElementsByTagName("model").item(0).getTextContent();
		        	list1.add(accum); 
		        	list2.add(accum);
		         }
		    }
			//count the number of unique items in cart
			Iterator<String> it1 = list1.iterator();
			while(it1.hasNext()){
				int count=0;
				String str=it1.next();
				Iterator<String> it2 = list2.iterator();
				while(it2.hasNext()){
					if(str.equals(it2.next())){
						count=count+1;
					}
				}
				mapHash.put(str, ""+count); 
			}
		}catch(ParserConfigurationException pce) {			pce.printStackTrace();
		}catch(SAXException se) {							se.printStackTrace();
		}catch(IOException ioe) {							ioe.printStackTrace();
		}catch (Exception e){								e.printStackTrace();
		}
		
	}
	//This method adds item to cart after creating service path and calling to the specific service
    //it gets total item count after adding item from cart
    public void addToCart(String itemType,String itemMake,String itemModel,String userId){
        //System.out.println("Add Response: " + getResponse(addToCartService));
    	addToCartService = service.path(ADD_ITEM_TO_CART_PATH).path(itemType + "/" + itemMake+ "/" +itemModel+ "/" +userId);
    	parseUserActionResponse(getOutputAsXML(addToCartService));
    }
	//This method removes item from cart after creating service path and calling to the specific service
	//it gets total item count after removeing item from cart
    public void removeFromCart(String itemType,String itemMake,String itemModel,String userId){
    	removeFromCartService = service.path(REMOVE_FROM_CART_PATH).path(itemType + "/" + itemMake+ "/" +itemModel+ "/" +userId);
    	parseUserActionResponse(getOutputAsXML(removeFromCartService));
    }
	//This method views cart after creating service path and calling to the specific service
    //it gets list of items in XML format and parses xml by calling parseCartList
    public void viewCart(String userId){
    	viewCartService = service.path(VIEW_CART_PATH).path(userId);
    	parseCartList(getOutputAsXML(viewCartService));
    }
    //This method buys all item in cart after creating service path and calling to the specific service
    //and parses the returned message for each item (bought sucess fully or not) and total item count which is 0 to update cart
    public void buyItems(String userId){
    	buyItemsService = service.path(BUY_ITEMS_PATH).path(userId);
    	parseUserActionResponse(getOutputAsXML(buyItemsService));
    }
    //This method gets the list of smart phones in xml format and parses the XML by calling parseItemList
    public void viewSmartPhoneList(){
    	SMPLService = service.path(SMARTPHONE_LIST_PATH);
        parseItemList(getOutputAsXML(SMPLService));
    }
  //This method gets the list of home theaters in xml format and parses the XML by calling parseItemList
    public void viewHomeTheaterList(){
    	HTLService = service.path(HOMETHEATER_LIST_PATH);
        parseItemList(getOutputAsXML(HTLService));
    }
  //This method gets the list of laptops in xml format and parses the XML by calling parseItemList
    public void viewLaptopist(){
    	LPLService = service.path(LAPTOP_LIST_PATH);
        parseItemList(getOutputAsXML(LPLService));
    }
  //This method gets the response from web services in textformat
    private static String getResponse(WebResource service) {
        return service.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class).toString();
    }
  //This method gets the output from web services in xmlformat   
    private static String getOutputAsXML(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(String.class);
    }
  //This method gets the output from web services in textformat   
    private static String getOutputAsText(WebResource service) {
        return service.accept(MediaType.TEXT_PLAIN).get(String.class);
    }
    //getters
    public Map<String,String> getItemList(){ return mapHash;}
    public ArrayList<String>getCartList(){return cartList;}
    public String getMyName(){return this.myName;}
    public String getTotalItem(){ return this.totalItem;}
    public String getTotalPrice(){ return this.totalPrice;}
    public ArrayList<String> getMessageList(){return this.messageList;}
}