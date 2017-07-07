package com.onlinebuyer.client;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

import javax.swing.*;

//This class is for REST client screen
public class RESTclientScreen {
   private JFrame frame;  
   private JLabel header;
  // private JLabel statusLabel;
   private JPanel control;
  // private JLabel msglabel;
   JPanel panelProduct,panelSP,panelHT,panelLT,usercart;
   JPanel paneltop,panelbottom,panelCenter;
   DefaultComboBoxModel panelcomboName,panelcomboNameVC;
   JComboBox listCombo,listComboVC;
   JScrollPane scrollPane,SPScrollPane,scrollPaneVC,scrollPaneTA;
   JButton show,view,remove,buy;

    //label and buttons for card layout 1
    JLabel JLabel211,JLabel212,JLabel213,JLabel214,JLabel311,JLabel312,JLabel313,JLabel314,JLabel411,JLabel412,JLabel413,JLabel414;
    JLabel JLabel511,JLabel512,JLabel513,JLabel514,JLabel611,JLabel612,JLabel613,JLabel614,JLabel711,JLabel712,JLabel713,JLabel714;
    JLabel JLabel811,JLabel812,JLabel813,JLabel814,JLabel911,JLabel912,JLabel913,JLabel914,JLabel1011,JLabel1012,JLabel1013,JLabel1014;
    JLabel JLabel1111,JLabel1112,JLabel1113,JLabel1114;
    JButton JButton21,JButton31,JButton41,JButton51,JButton61,JButton71,JButton81,JButton91,JButton101,JButton111;
    //label and buttons for card layout 2
    JLabel JLabel221,JLabel222,JLabel223,JLabel224,JLabel321,JLabel322,JLabel323,JLabel324,JLabel421,JLabel422,JLabel423,JLabel424;
    JLabel JLabel521,JLabel522,JLabel523,JLabel524,JLabel621,JLabel622,JLabel623,JLabel624,JLabel721,JLabel722,JLabel723,JLabel724;
    JLabel JLabel821,JLabel822,JLabel823,JLabel824,JLabel921,JLabel922,JLabel923,JLabel924,JLabel1021,JLabel1022,JLabel1023,JLabel1024;
    JLabel JLabel1121,JLabel1122,JLabel1123,JLabel1124;
    JButton JButton22,JButton32,JButton42,JButton52,JButton62,JButton72,JButton82,JButton92,JButton102,JButton112;
    //label and buttons for card layout 3
    JLabel JLabel231,JLabel232,JLabel233,JLabel234,JLabel331,JLabel332,JLabel333,JLabel334,JLabel431,JLabel432,JLabel433,JLabel434;
    JLabel JLabel531,JLabel532,JLabel533,JLabel534,JLabel631,JLabel632,JLabel633,JLabel634,JLabel731,JLabel732,JLabel733,JLabel734;
    JLabel JLabel831,JLabel832,JLabel833,JLabel834,JLabel931,JLabel932,JLabel933,JLabel934,JLabel1031,JLabel1032,JLabel1033,JLabel1034;
    JLabel JLabel1131,JLabel1132,JLabel1133,JLabel1134;
    JButton JButton23,JButton33,JButton43,JButton53,JButton63,JButton73,JButton83,JButton93,JButton103,JButton113;
    
    String maker21,maker31,maker41,maker51,maker61,maker71,maker81,maker91,maker101,maker111;
    String maker22,maker32,maker42,maker52,maker62,maker72,maker82,maker92,maker102,maker112;
    String maker23,maker33,maker43,maker53,maker63,maker73,maker83,maker93,maker103,maker113;
    
    MyClientRest myclient1;
    private Map<String,String> itemList;
    private ArrayList<String> cartList;
    JLabel ShowCartAllTimeLabel,ShowCartAllTime,ShowTotalPriceLabel,ShowTotalPrice,showtagname;
    JButton refresh;
    JTextArea  msg;
    ArrayList<String> messgList;
   public RESTclientScreen(){
	   createGUI();
	   //creating a instance for user, this user name will be used to log in server as user and all other client request will be
	   //resolved against the user name
	   myclient1 = new MyClientRest("RestClient");
	   //holding Items list of items user wants to view
	   itemList = new HashMap<String,String>(); 
   }
   
   /* TK1 Ex2 */
   private void createGUI(){
	   frame = new JFrame("Rest Client");
	   frame.setSize(700,700);	   
	   frame.setLayout(null);
	   frame.setResizable(false);
     
	   frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
	        System.exit(0);
         }        
      });    
     //code for internal elements for Panels in screen
      addPanelTop();
      addShowProductsPanel();
      addPanelbottom();
      
      frame.add(paneltop);
      frame.add(panelProduct);
      frame.add(panelbottom);
      frame.setVisible(true);  

   }
   //bottom pannel for displaying message
   private void addPanelbottom(){
	   panelbottom = new JPanel(new FlowLayout()); 
	   panelbottom.setBackground(Color.CYAN);
	   panelbottom.setBounds(0, 460, 695, 200);
	   //panelbottom.setSize(100, 100);
	   messgList =new ArrayList<String>();
	   msg= new JTextArea(16,58);
	   msg.setText("");
	   msg.setEditable(false);
	   msg.setBackground(Color.CYAN);
	   
	   scrollPaneTA = new JScrollPane(msg);
	   //scrollPaneTA.setBounds(0, 0, 60, 20);
	   //scrollPaneTA.setBounds(0, 0, 695, 20);
	   scrollPaneTA.setPreferredSize(new Dimension(690, 180));
	   scrollPaneTA.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	   panelbottom.add(scrollPaneTA);
   }
   //middle pannel for view product list,view cart but etc
   private void addShowProductsPanel(){
	   
 	   panelProduct = new JPanel();
	   panelProduct.setBackground(Color.RED);
	   CardLayout layout = new CardLayout();
	   layout.setHgap(10);
	   layout.setVgap(10);
	   panelProduct.setLayout(layout);        
	   panelProduct.setBounds(0, 60, 695, 380);
	   panelProduct.setVisible(true);

      GridLayout bpgl = new GridLayout(10,0);      
      bpgl.setHgap(8);
      bpgl.setVgap(8);
      //pannel for smart phone
      panelSP = new JPanel();
      panelSP.setBackground(Color.PINK);
      panelSP.setLayout(null); 
      setPanelSP();
      //pannel for home theater
      panelHT = new JPanel();
      panelHT.setBackground(Color.GREEN);
      panelHT.setLayout(null);
      setPanelHT();
      //pannel for laptop      
      panelLT = new JPanel();
      panelLT.setBackground(Color.YELLOW);
      panelLT.setLayout(null);
      setPanelLT();
      //pannel for user cart
      usercart = new JPanel();    
      usercart.setBackground(Color.ORANGE);
      usercart.setLayout(null);
      setPanelUserCart();
      
      panelProduct.add("Smart Phones", panelSP);
      panelProduct.add("Home Theater", panelHT);
      panelProduct.add("Laptop", panelLT);
      panelProduct.add("ViewCart", usercart);
      //method for all buttons and their functions
      addButtonListenes();
   }
   //method to set components in user cart pannel
   private void setPanelUserCart(){
	   
	   showtagname = new JLabel("List of Items in Cart:");
	   showtagname.setBounds(0, 10, 200, 20);
	   panelcomboNameVC = new DefaultComboBoxModel();
	   panelcomboNameVC.addElement("No Items in Cart");
	   listComboVC = new JComboBox(panelcomboNameVC);    
	   listComboVC.setSelectedIndex(0);

	   scrollPaneVC = new JScrollPane(listComboVC);
	   scrollPaneVC.setBounds(0, 50, 675, 30);
	   scrollPaneVC.setBackground(Color.CYAN);
	   //remove items button
	   remove = new JButton("Remove Item");
	   remove.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            if (listComboVC.getSelectedIndex() != -1) {  
  	               //call remove function and remove items from combo box also
    			   String[] s = ((String) listComboVC.getItemAt(listComboVC.getSelectedIndex())).split("/"); 
    			   myclient1.removeFromCart(s[0].trim(),s[1].trim(),s[2].trim(),myclient1.getMyName());
    			   //remove items from viewList
    			   listComboVC.removeItem(listComboVC.getItemAt(listComboVC.getSelectedIndex()));
    			   ShowCartAllTime.setText(myclient1.getTotalItem());
    			   //show message after add action
    		       messgList = myclient1.getMessageList();
    		       Iterator<String> it = messgList.iterator();
    		       msg.setText("");
    		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
	            }              
	         }
	      });
	   remove.setBounds(200, 330, 120, 20);
	   //buy items button
	   buy = new JButton("Buy All Items");
	   buy.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) { 
	             //call buy function and remove all items from cart
	        	 myclient1.buyItems(myclient1.getMyName());
	        	 //remove all items from viewList
	        	 listComboVC.removeAllItems();
	        	 panelcomboNameVC.addElement("No Items in Cart");
	        	 ShowCartAllTime.setText(myclient1.getTotalItem());
	        	 ShowTotalPrice.setText("0");
	        	 //show message against buy action
	        	 messgList = myclient1.getMessageList();
			       Iterator<String> it = messgList.iterator();
			       msg.setText("");
			       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
	         }
	   }); 
	   buy.setBounds(350, 330, 150, 20);
	   ShowTotalPriceLabel = new JLabel("Total Price: ");
	   ShowTotalPriceLabel.setBounds(300, 10, 100, 20);
	   ShowTotalPrice      = new JLabel("0");
	   ShowTotalPrice.setBounds(410, 10, 80, 20);
	   //refersh button to refresh total price
	   refresh = new JButton("Refresh");
	   refresh.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) { 
	             //call vire cart method
	        	 setViewCartList();
	         }
	   });  
	   refresh.setBounds(510, 10, 85, 20);
	   usercart.add(showtagname);
	   usercart.add(scrollPaneVC);
	   usercart.add(remove);
	   usercart.add(buy);
	   usercart.add(ShowTotalPriceLabel);
	   usercart.add(ShowTotalPrice);
	   usercart.add(refresh);
   }
   //method to set all buttons
   private void addButtonListenes(){

	   JButton21.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   //call methid to add item in user cart in server side
			   myclient1.addToCart(maker21,JLabel211.getText(),JLabel213.getText(),myclient1.getMyName());
			   //show item count 
		       ShowCartAllTime.setText(myclient1.getTotalItem());	
		       //show reply message agter adding item
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton31.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker31,JLabel311.getText(),JLabel313.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());	
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton41.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker41,JLabel411.getText(),JLabel413.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton51.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker51,JLabel511.getText(),JLabel513.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton61.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker61,JLabel611.getText(),JLabel613.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton71.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker71,JLabel711.getText(),JLabel713.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }

		   } });
	   JButton81.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker81,JLabel811.getText(),JLabel813.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton91.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker91,JLabel911.getText(),JLabel913.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton101.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker101,JLabel1011.getText(),JLabel1013.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton111.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker111,JLabel1111.getText(),JLabel1113.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton22.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker22,JLabel221.getText(),JLabel223.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton32.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker32,JLabel321.getText(),JLabel323.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton42.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker42,JLabel421.getText(),JLabel423.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton52.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker52,JLabel521.getText(),JLabel523.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton62.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker62,JLabel621.getText(),JLabel623.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton72.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker72,JLabel721.getText(),JLabel723.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton82.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker82,JLabel821.getText(),JLabel823.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton92.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker92,JLabel921.getText(),JLabel923.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton102.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker102,JLabel1021.getText(),JLabel1023.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton112.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker112,JLabel1121.getText(),JLabel1123.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton23.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker23,JLabel231.getText(),JLabel233.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton33.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker33,JLabel331.getText(),JLabel333.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton43.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker43,JLabel431.getText(),JLabel433.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton53.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker53,JLabel531.getText(),JLabel533.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton63.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker63,JLabel631.getText(),JLabel633.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton73.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker73,JLabel731.getText(),JLabel733.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton83.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker83,JLabel831.getText(),JLabel833.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton93.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker93,JLabel931.getText(),JLabel933.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton103.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker103,JLabel1031.getText(),JLabel1033.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
	   JButton113.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			   myclient1.addToCart(maker113,JLabel1131.getText(),JLabel1133.getText(),myclient1.getMyName());
			   ShowCartAllTime.setText(myclient1.getTotalItem());
		       messgList = myclient1.getMessageList();
		       Iterator<String> it = messgList.iterator();
		       msg.setText("");
		       while(it.hasNext()){ msg.append("\n "+it.next().toString());     }
		   } });
   }
   //set components for laptop pannel
   private void setPanelLT(){

		      JPanel panrow11 = new JPanel(new GridLayout(0,5));
		      JPanel panrow21 = new JPanel(new GridLayout(0,5));
		      JPanel panrow31 = new JPanel(new GridLayout(0,5));
		      JPanel panrow41 = new JPanel(new GridLayout(0,5));
		      JPanel panrow51 = new JPanel(new GridLayout(0,5));
		      JPanel panrow61 = new JPanel(new GridLayout(0,5));
		      JPanel panrow71 = new JPanel(new GridLayout(0,5));
		      JPanel panrow81 = new JPanel(new GridLayout(0,5));
		      JPanel panrow91 = new JPanel(new GridLayout(0,5));
		      JPanel panrow101 = new JPanel(new GridLayout(0,5));
		      JPanel panrow111 = new JPanel(new GridLayout(0,5));
		      
		      JLabel231 = new JLabel("");JLabel232 = new JLabel("");JLabel233 = new JLabel("");JLabel234 = new JLabel("");
		      JLabel331 = new JLabel("");JLabel332 = new JLabel("");JLabel333 = new JLabel("");JLabel334 = new JLabel("");
		      JLabel431 = new JLabel("");JLabel432 = new JLabel("");JLabel433 = new JLabel("");JLabel434 = new JLabel("");	
		      JLabel531 = new JLabel("");JLabel532 = new JLabel("");JLabel533 = new JLabel("");JLabel534 = new JLabel("");
		      JLabel631 = new JLabel("");JLabel632 = new JLabel("");JLabel633 = new JLabel("");JLabel634 = new JLabel("");
		      JLabel731 = new JLabel("");JLabel732 = new JLabel("");JLabel733 = new JLabel("");JLabel734 = new JLabel("");
		      JLabel831 = new JLabel("");JLabel832 = new JLabel("");JLabel833 = new JLabel("");JLabel834 = new JLabel("");
		      JLabel931 = new JLabel("");JLabel932 = new JLabel("");JLabel933 = new JLabel("");JLabel934 = new JLabel("");
		      JLabel1031 = new JLabel("");JLabel1032 = new JLabel("");JLabel1033 = new JLabel("");JLabel1034 = new JLabel("");
		      JLabel1131 = new JLabel("");JLabel1132 = new JLabel("");JLabel1133 = new JLabel("");JLabel1134 = new JLabel("");

		      JButton23=new JButton("Add To Cart");JButton23.setVisible(false);
		      JButton33=new JButton("Add To Cart");JButton33.setVisible(false);
		      JButton43=new JButton("Add To Cart");JButton43.setVisible(false);
		      JButton53=new JButton("Add To Cart");JButton53.setVisible(false);
		      JButton63=new JButton("Add To Cart");JButton63.setVisible(false);
		      JButton73=new JButton("Add To Cart");JButton73.setVisible(false);
		      JButton83=new JButton("Add To Cart");JButton83.setVisible(false);
		      JButton93=new JButton("Add To Cart");JButton93.setVisible(false);
		      JButton103=new JButton("Add To Cart");JButton103.setVisible(false);
		      JButton113=new JButton("Add To Cart");JButton113.setVisible(false);
		      //top row of list
		      panrow11.add(new JLabel(" Brand "));
		      panrow11.add(new JLabel(" Price  "));
		      panrow11.add(new JLabel("    Model "));
		      panrow11.add(new JLabel("In stock "));
		      panrow11.add(new JLabel("                                        "));

		      panrow21.add(JLabel231);panrow21.add(JLabel232);panrow21.add(JLabel233);panrow21.add(JLabel234);panrow21.add(JButton23);
		      panrow31.add(JLabel331);panrow31.add(JLabel332);panrow31.add(JLabel333);panrow31.add(JLabel334);panrow31.add(JButton33);
		      panrow41.add(JLabel431);panrow41.add(JLabel432);panrow41.add(JLabel433);panrow41.add(JLabel434);panrow41.add(JButton43);
		      panrow51.add(JLabel531);panrow51.add(JLabel532);panrow51.add(JLabel533);panrow51.add(JLabel534);panrow51.add(JButton53);
		      panrow61.add(JLabel631);panrow61.add(JLabel632);panrow61.add(JLabel633);panrow61.add(JLabel634);panrow61.add(JButton63);
		      panrow71.add(JLabel731);panrow71.add(JLabel732);panrow71.add(JLabel733);panrow71.add(JLabel734);panrow71.add(JButton73);
		      panrow81.add(JLabel831);panrow81.add(JLabel832);panrow81.add(JLabel833);panrow81.add(JLabel834);panrow81.add(JButton83);
		      panrow91.add(JLabel931);panrow91.add(JLabel932);panrow91.add(JLabel933);panrow91.add(JLabel934);panrow91.add(JButton93);
		      panrow101.add(JLabel1031);panrow101.add(JLabel1032);panrow101.add(JLabel1033);panrow101.add(JLabel1034);panrow101.add(JButton103);
		      panrow111.add(JLabel1131);panrow111.add(JLabel1132);panrow111.add(JLabel1133);panrow111.add(JLabel1134);panrow111.add(JButton113);

		      panrow11.setBounds(0, 0, 700, 50);
		      panrow21.setBounds(0, 51, 700, 30);
		      panrow31.setBounds(0, 82, 700, 30);
		      panrow41.setBounds(0, 113, 700, 30);
		      panrow51.setBounds(0, 144, 700, 30);
		      panrow61.setBounds(0, 175, 700, 30);
		      panrow71.setBounds(0, 206, 700, 30);
		      panrow81.setBounds(0, 237, 700, 30);
		      panrow91.setBounds(0, 268, 700, 30);
		      panrow101.setBounds(0, 299, 700, 30);
		      panrow111.setBounds(0, 330, 700, 30);
		      
		      panelLT.add(panrow11);
		      panelLT.add(panrow21);
		      panelLT.add(panrow31);
		      panelLT.add(panrow41);
		      panelLT.add(panrow51);
		      panelLT.add(panrow61);
		      panelLT.add(panrow71);
		      panelLT.add(panrow81);
		      panelLT.add(panrow91);
		      panelLT.add(panrow101);
		      panelLT.add(panrow111);


   }
  //set components for home theater pannel
   private void setPanelHT(){
	      JPanel panrow11 = new JPanel(new GridLayout(0,5));
	      JPanel panrow21 = new JPanel(new GridLayout(0,5));
	      JPanel panrow31 = new JPanel(new GridLayout(0,5));
	      JPanel panrow41 = new JPanel(new GridLayout(0,5));
	      JPanel panrow51 = new JPanel(new GridLayout(0,5));
	      JPanel panrow61 = new JPanel(new GridLayout(0,5));
	      JPanel panrow71 = new JPanel(new GridLayout(0,5));
	      JPanel panrow81 = new JPanel(new GridLayout(0,5));
	      JPanel panrow91 = new JPanel(new GridLayout(0,5));
	      JPanel panrow101 = new JPanel(new GridLayout(0,5));
	      JPanel panrow111 = new JPanel(new GridLayout(0,5));
	      
	      JLabel221 = new JLabel("");JLabel222 = new JLabel("");JLabel223 = new JLabel("");JLabel224 = new JLabel("");
	      JLabel321 = new JLabel("");JLabel322 = new JLabel("");JLabel323 = new JLabel("");JLabel324 = new JLabel("");
	      JLabel421 = new JLabel("");JLabel422 = new JLabel("");JLabel423 = new JLabel("");JLabel424 = new JLabel("");	
	      JLabel521 = new JLabel("");JLabel522 = new JLabel("");JLabel523 = new JLabel("");JLabel524 = new JLabel("");
	      JLabel621 = new JLabel("");JLabel622 = new JLabel("");JLabel623 = new JLabel("");JLabel624 = new JLabel("");
	      JLabel721 = new JLabel("");JLabel722 = new JLabel("");JLabel723 = new JLabel("");JLabel724 = new JLabel("");
	      JLabel821 = new JLabel("");JLabel822 = new JLabel("");JLabel823 = new JLabel("");JLabel824 = new JLabel("");
	      JLabel921 = new JLabel("");JLabel922 = new JLabel("");JLabel923 = new JLabel("");JLabel924 = new JLabel("");
	      JLabel1021 = new JLabel("");JLabel1022 = new JLabel("");JLabel1023 = new JLabel("");JLabel1024 = new JLabel("");
	      JLabel1121 = new JLabel("");JLabel1122 = new JLabel("");JLabel1123 = new JLabel("");JLabel1124 = new JLabel("");

	      JButton22=new JButton("Add To Cart");JButton22.setVisible(false);
	      JButton32=new JButton("Add To Cart");JButton32.setVisible(false);
	      JButton42=new JButton("Add To Cart");JButton42.setVisible(false);
	      JButton52=new JButton("Add To Cart");JButton52.setVisible(false);
	      JButton62=new JButton("Add To Cart");JButton62.setVisible(false);
	      JButton72=new JButton("Add To Cart");JButton72.setVisible(false);
	      JButton82=new JButton("Add To Cart");JButton82.setVisible(false);
	      JButton92=new JButton("Add To Cart");JButton92.setVisible(false);
	      JButton102=new JButton("Add To Cart");JButton102.setVisible(false);
	      JButton112=new JButton("Add To Cart");JButton112.setVisible(false);
	      //top row in pannel
	      panrow11.add(new JLabel(" Brand "));
	      panrow11.add(new JLabel("        Price  "));
	      panrow11.add(new JLabel("    Model "));
	      panrow11.add(new JLabel("In stock "));
	      panrow11.add(new JLabel("                      "));

	      panrow21.add(JLabel221);panrow21.add(JLabel222);panrow21.add(JLabel223);panrow21.add(JLabel224);panrow21.add(JButton22);
	      panrow31.add(JLabel321);panrow31.add(JLabel322);panrow31.add(JLabel323);panrow31.add(JLabel324);panrow31.add(JButton32);
	      panrow41.add(JLabel421);panrow41.add(JLabel422);panrow41.add(JLabel423);panrow41.add(JLabel424);panrow41.add(JButton42);
	      panrow51.add(JLabel521);panrow51.add(JLabel522);panrow51.add(JLabel523);panrow51.add(JLabel524);panrow51.add(JButton52);
	      panrow61.add(JLabel621);panrow61.add(JLabel622);panrow61.add(JLabel623);panrow61.add(JLabel624);panrow61.add(JButton62);
	      panrow71.add(JLabel721);panrow71.add(JLabel722);panrow71.add(JLabel723);panrow71.add(JLabel724);panrow71.add(JButton72);
	      panrow81.add(JLabel821);panrow81.add(JLabel822);panrow81.add(JLabel823);panrow81.add(JLabel824);panrow81.add(JButton82);
	      panrow91.add(JLabel921);panrow91.add(JLabel922);panrow91.add(JLabel923);panrow91.add(JLabel924);panrow91.add(JButton92);
	      panrow101.add(JLabel1021);panrow101.add(JLabel1022);panrow101.add(JLabel1023);panrow101.add(JLabel1024);panrow101.add(JButton102);
	      panrow111.add(JLabel1121);panrow111.add(JLabel1122);panrow111.add(JLabel1123);panrow111.add(JLabel1124);panrow111.add(JButton112);

	      panrow11.setBounds(0, 0, 700, 50);
	      panrow21.setBounds(0, 51, 700, 30);
	      panrow31.setBounds(0, 82, 700, 30);
	      panrow41.setBounds(0, 113, 700, 30);
	      panrow51.setBounds(0, 144, 700, 30);
	      panrow61.setBounds(0, 175, 700, 30);
	      panrow71.setBounds(0, 206, 700, 30);
	      panrow81.setBounds(0, 237, 700, 30);
	      panrow91.setBounds(0, 268, 700, 30);
	      panrow101.setBounds(0, 299, 700, 30);
	      panrow111.setBounds(0, 330, 700, 30);
	      
	      panelHT.add(panrow11);
	      panelHT.add(panrow21);
	      panelHT.add(panrow31);
	      panelHT.add(panrow41);
	      panelHT.add(panrow51);
	      panelHT.add(panrow61);
	      panelHT.add(panrow71);
	      panelHT.add(panrow81);
	      panelHT.add(panrow91);
	      panelHT.add(panrow101);
	      panelHT.add(panrow111);
	      

   }
   //set components for smartphone pannel
   private void setPanelSP(){
	     
	      JPanel panrow11 = new JPanel(new GridLayout(0,5));
	      JPanel panrow21 = new JPanel(new GridLayout(0,5));
	      JPanel panrow31 = new JPanel(new GridLayout(0,5));
	      JPanel panrow41 = new JPanel(new GridLayout(0,5));
	      JPanel panrow51 = new JPanel(new GridLayout(0,5));
	      JPanel panrow61 = new JPanel(new GridLayout(0,5));
	      JPanel panrow71 = new JPanel(new GridLayout(0,5));
	      JPanel panrow81 = new JPanel(new GridLayout(0,5));
	      JPanel panrow91 = new JPanel(new GridLayout(0,5));
	      JPanel panrow101 = new JPanel(new GridLayout(0,5));
	      JPanel panrow111 = new JPanel(new GridLayout(0,5));
	      
	      JLabel211 = new JLabel("");JLabel212 = new JLabel("");JLabel213 = new JLabel("");JLabel214 = new JLabel("");
	      JLabel311 = new JLabel("");JLabel312 = new JLabel("");JLabel313 = new JLabel("");JLabel314 = new JLabel("");
	      JLabel411 = new JLabel("");JLabel412 = new JLabel("");JLabel413 = new JLabel("");JLabel414 = new JLabel("");	
	      JLabel511 = new JLabel("");JLabel512 = new JLabel("");JLabel513 = new JLabel("");JLabel514 = new JLabel("");
	      JLabel611 = new JLabel("");JLabel612 = new JLabel("");JLabel613 = new JLabel("");JLabel614 = new JLabel("");
	      JLabel711 = new JLabel("");JLabel712 = new JLabel("");JLabel713 = new JLabel("");JLabel714 = new JLabel("");
	      JLabel811 = new JLabel("");JLabel812 = new JLabel("");JLabel813 = new JLabel("");JLabel814 = new JLabel("");
	      JLabel911 = new JLabel("");JLabel912 = new JLabel("");JLabel913 = new JLabel("");JLabel914 = new JLabel("");
	      JLabel1011 = new JLabel("");JLabel1012 = new JLabel("");JLabel1013 = new JLabel("");JLabel1014 = new JLabel("");
	      JLabel1111 = new JLabel("");JLabel1112 = new JLabel("");JLabel1113 = new JLabel("");JLabel1114 = new JLabel("");

	      JButton21=new JButton("Add To Cart");JButton21.setVisible(false);
	      JButton31=new JButton("Add To Cart");JButton31.setVisible(false);
	      JButton41=new JButton("Add To Cart");JButton41.setVisible(false);
	      JButton51=new JButton("Add To Cart");JButton51.setVisible(false);
	      JButton61=new JButton("Add To Cart");JButton61.setVisible(false);
	      JButton71=new JButton("Add To Cart");JButton71.setVisible(false);
	      JButton81=new JButton("Add To Cart");JButton81.setVisible(false);
	      JButton91=new JButton("Add To Cart");JButton91.setVisible(false);
	      JButton101=new JButton("Add To Cart");JButton101.setVisible(false);
	      JButton111=new JButton("Add To Cart");JButton111.setVisible(false);
	      
	      panrow11.add(new JLabel(" Brand "));
	      panrow11.add(new JLabel("        Price  "));
	      panrow11.add(new JLabel("    Model "));
	      panrow11.add(new JLabel("In stock "));
	      panrow11.add(new JLabel("                      "));

	      panrow21.add(JLabel211);panrow21.add(JLabel212);panrow21.add(JLabel213);panrow21.add(JLabel214);panrow21.add(JButton21);
	      panrow31.add(JLabel311);panrow31.add(JLabel312);panrow31.add(JLabel313);panrow31.add(JLabel314);panrow31.add(JButton31);
	      panrow41.add(JLabel411);panrow41.add(JLabel412);panrow41.add(JLabel413);panrow41.add(JLabel414);panrow41.add(JButton41);
	      panrow51.add(JLabel511);panrow51.add(JLabel512);panrow51.add(JLabel513);panrow51.add(JLabel514);panrow51.add(JButton51);
	      panrow61.add(JLabel611);panrow61.add(JLabel612);panrow61.add(JLabel613);panrow61.add(JLabel614);panrow61.add(JButton61);
	      panrow71.add(JLabel711);panrow71.add(JLabel712);panrow71.add(JLabel713);panrow71.add(JLabel714);panrow71.add(JButton71);
	      panrow81.add(JLabel811);panrow81.add(JLabel812);panrow81.add(JLabel813);panrow81.add(JLabel814);panrow81.add(JButton81);
	      panrow91.add(JLabel911);panrow91.add(JLabel912);panrow91.add(JLabel913);panrow91.add(JLabel914);panrow91.add(JButton91);
	      panrow101.add(JLabel1011);panrow101.add(JLabel1012);panrow101.add(JLabel1013);panrow101.add(JLabel1014);panrow101.add(JButton101);
	      panrow111.add(JLabel1111);panrow111.add(JLabel1112);panrow111.add(JLabel1113);panrow111.add(JLabel1114);panrow111.add(JButton111);

	      panrow11.setBounds(0, 0, 700, 50);
	      panrow21.setBounds(0, 51, 700, 30);
	      panrow31.setBounds(0, 82, 700, 30);
	      panrow41.setBounds(0, 113, 700, 30);
	      panrow51.setBounds(0, 144, 700, 30);
	      panrow61.setBounds(0, 175, 700, 30);
	      panrow71.setBounds(0, 206, 700, 30);
	      panrow81.setBounds(0, 237, 700, 30);
	      panrow91.setBounds(0, 268, 700, 30);
	      panrow101.setBounds(0, 299, 700, 30);
	      panrow111.setBounds(0, 330, 700, 30);
	      
	      panelSP.add(panrow11);
	      panelSP.add(panrow21);
	      panelSP.add(panrow31);
	      panelSP.add(panrow41);
	      panelSP.add(panrow51);
	      panelSP.add(panrow61);
	      panelSP.add(panrow71);
	      panelSP.add(panrow81);
	      panelSP.add(panrow91);
	      panelSP.add(panrow101);
	      panelSP.add(panrow111);
	      
   }
   //set top panel in frame to select pnnels for viewing cart, and differnt pproduct list
   private void addPanelTop(){
	      paneltop = new JPanel(new FlowLayout());
	      paneltop.setBounds(0, 0, 700, 50);
		  panelcomboName = new DefaultComboBoxModel();
			 
		  panelcomboName.addElement("Smart Phones");
		  panelcomboName.addElement("Home Theater");
		  panelcomboName.addElement("Laptop");

		  listCombo = new JComboBox(panelcomboName);    
	      listCombo.setSelectedIndex(0);

	      scrollPane = new JScrollPane(listCombo);    
	      //Show items button
	      show = new JButton("Show List");
	      show.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (listCombo.getSelectedIndex() != -1) {  
	               CardLayout cardLayout = (CardLayout)(panelProduct.getLayout());
	               setListValueInRow();
	               cardLayout.show(panelProduct, (String)listCombo.getItemAt(listCombo.getSelectedIndex()));
	            }              
	         }
	      });
	    //view cart button
	      view = new JButton("View Cart");
	      view.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) { 
	        	 CardLayout cardLayout = (CardLayout)(panelProduct.getLayout());
	        	 setViewCartList();
	        	 cardLayout.show(panelProduct, "ViewCart"); 
	        	 ShowCartAllTime.setText(myclient1.getTotalItem());
	         }
	      }); 

	      ShowCartAllTimeLabel = new JLabel("                            Items in Cart:     ");
	      ShowCartAllTime      = new JLabel("0");
	      ShowCartAllTime.setForeground(Color.RED);
	      paneltop.add(scrollPane);
	      paneltop.add(show);
	      paneltop.add(view);
	      paneltop.add(ShowCartAllTimeLabel);
	      paneltop.add(ShowCartAllTime);
	      paneltop.setVisible(true);

}
//method to show user cart pannel
   private void setViewCartList(){
	   myclient1.viewCart(myclient1.getMyName());
	   //fetch items from cart and show in pannel
	   cartList=myclient1.getCartList();
	   Iterator it = cartList.iterator();
	   if(!cartList.isEmpty()){
		   panelcomboNameVC.removeAllElements();
		   while(it.hasNext()){
			   panelcomboNameVC.addElement(it.next());
		   }
	   }
	   ShowTotalPrice.setText(myclient1.getTotalPrice());
   }
   //method to disply products in respective product pannels
   private void setListValueInRow(){
	   //smart phone 
       if(listCombo.getSelectedIndex()==0){
    	   myclient1.viewSmartPhoneList();
    	   itemList.clear();
           itemList=myclient1.getItemList();
     	   int printcount=0;
     	   JLabel211.setText("");JLabel212.setText("");JLabel213.setText("");JLabel214.setText("");JLabel311.setText("");JLabel312.setText("");
     	   JLabel313.setText("");JLabel314.setText("");JLabel411.setText("");JLabel412.setText("");JLabel413.setText("");JLabel414.setText("");
     	   JLabel511.setText("");JLabel512.setText("");JLabel513.setText("");JLabel514.setText("");JLabel611.setText("");JLabel612.setText("");
     	   JLabel613.setText("");JLabel614.setText("");JLabel711.setText("");JLabel712.setText("");JLabel713.setText("");JLabel714.setText("");
     	   JLabel811.setText("");JLabel812.setText("");JLabel813.setText("");JLabel814.setText("");JLabel911.setText("");JLabel912.setText("");
     	   JLabel913.setText("");JLabel914.setText("");JLabel1011.setText("");JLabel1012.setText("");JLabel1013.setText("");JLabel1014.setText("");
     	   JLabel1111.setText("");JLabel1112.setText("");JLabel1113.setText("");JLabel1114.setText("");

     	   JButton21.setVisible(false);JButton31.setVisible(false);JButton41.setVisible(false);JButton51.setVisible(false);JButton61.setVisible(false);
     	  JButton71.setVisible(false);JButton81.setVisible(false);JButton91.setVisible(false);JButton101.setVisible(false);JButton111.setVisible(false);
     	  		//loop through the list of items in store and display
    		   for(Map.Entry<String, String> entry: itemList.entrySet()){
    			   printcount=printcount+1;
    			   String[] s = entry.getKey().split("#");
    			   //System.out.println(entry.getKey()+"  "+entry.getValue()+" : "+s[1]);
    			   if(printcount==1){
    				   maker21=s[0].trim();
    				   JLabel211.setText(s[1].trim());
    				   JLabel212.setText(s[2].trim());
    				   JLabel213.setText(s[3].trim());
    				   JLabel214.setText(entry.getValue().trim());
    				   JButton21.setVisible(true);
    			   }
    			   else if(printcount==2){
    				   maker31=s[0].trim();
    				   JLabel311.setText(s[1].trim());
    				   JLabel312.setText(s[2].trim());
    				   JLabel313.setText(s[3].trim());
    				   JLabel314.setText(entry.getValue().trim());
    				   JButton31.setVisible(true);
    			   }
    			   else if(printcount==3){
    				   maker41=s[0].trim();
    				   JLabel411.setText(s[1].trim());
    				   JLabel412.setText(s[2].trim());
    				   JLabel413.setText(s[3].trim());
    				   JLabel414.setText(entry.getValue().trim());
    				   JButton41.setVisible(true);
    			   }
    			   else if(printcount==4){
    				   maker51=s[0].trim();
    				   JLabel511.setText(s[1].trim());
    				   JLabel512.setText(s[2].trim());
    				   JLabel513.setText(s[3].trim());
    				   JLabel514.setText(entry.getValue().trim());
    				   JButton51.setVisible(true);
    			   }
    			   else if(printcount==5){
    				   maker61=s[0].trim();
    				   JLabel611.setText(s[1].trim());
    				   JLabel612.setText(s[2].trim());
    				   JLabel613.setText(s[3].trim());
    				   JLabel614.setText(entry.getValue().trim());
    				   JButton61.setVisible(true);
    			   }
    			   else if(printcount==6){
    				   maker71=s[0].trim();
    				   JLabel711.setText(s[1].trim());
    				   JLabel712.setText(s[2].trim());
    				   JLabel713.setText(s[3].trim());
    				   JLabel714.setText(entry.getValue().trim());
    				   JButton71.setVisible(true);
    			   }
    			   else if(printcount==7){
    				   maker81=s[0].trim();
    				   JLabel811.setText(s[1].trim());
    				   JLabel812.setText(s[2].trim());
    				   JLabel813.setText(s[3].trim());
    				   JLabel814.setText(entry.getValue().trim());
    				   JButton81.setVisible(true);
    			   }
    			   else if(printcount==8){
    				   maker91=s[0].trim();
    				   JLabel911.setText(s[1].trim());
    				   JLabel912.setText(s[2].trim());
    				   JLabel913.setText(s[3].trim());
    				   JLabel914.setText(entry.getValue().trim());
    				   JButton91.setVisible(true);
    			   }
    			   else if(printcount==9){
    				   maker101=s[0].trim();
    				   JLabel1011.setText(s[1].trim());
    				   JLabel1012.setText(s[2].trim());
    				   JLabel1013.setText(s[3].trim());
    				   JLabel1014.setText(entry.getValue().trim());
    				   JButton101.setVisible(true);
    			   }
    			   else if(printcount==10){
    				   maker111=s[0].trim();
    				   JLabel1111.setText(s[1].trim());
    				   JLabel1112.setText(s[2].trim());
    				   JLabel1113.setText(s[3].trim());
    				   JLabel1114.setText(entry.getValue().trim());
    				   JButton111.setVisible(true);
    			   }

    		   }
       }
       //home theater 
       else if(listCombo.getSelectedIndex()==1){
    	   myclient1.viewHomeTheaterList();
    	   itemList.clear();
           itemList=myclient1.getItemList();
     	   int printcount=0;
     	   JLabel221.setText("");JLabel222.setText("");JLabel223.setText("");JLabel224.setText("");JLabel321.setText("");JLabel322.setText("");
     	   JLabel323.setText("");JLabel324.setText("");JLabel421.setText("");JLabel422.setText("");JLabel423.setText("");JLabel424.setText("");
     	   JLabel521.setText("");JLabel522.setText("");JLabel523.setText("");JLabel524.setText("");JLabel621.setText("");JLabel622.setText("");
     	   JLabel623.setText("");JLabel624.setText("");JLabel721.setText("");JLabel722.setText("");JLabel723.setText("");JLabel724.setText("");
     	   JLabel821.setText("");JLabel822.setText("");JLabel823.setText("");JLabel824.setText("");JLabel921.setText("");JLabel922.setText("");
     	   JLabel923.setText("");JLabel924.setText("");JLabel1021.setText("");JLabel1022.setText("");JLabel1023.setText("");JLabel1024.setText("");
     	   JLabel1121.setText("");JLabel1122.setText("");JLabel1123.setText("");JLabel1124.setText("");
     	   JButton22.setVisible(false);JButton32.setVisible(false);JButton42.setVisible(false);JButton52.setVisible(false);JButton62.setVisible(false);
      	   JButton72.setVisible(false);JButton82.setVisible(false);JButton92.setVisible(false);JButton102.setVisible(false);JButton112.setVisible(false);

    		   for(Map.Entry<String, String> entry: itemList.entrySet()){
    			   printcount=printcount+1;
    			   String[] s = entry.getKey().split("#");
    			   //System.out.println(entry.getKey()+"  "+entry.getValue()+" : "+s[1]);
    			   if(printcount==1){
    				   maker22=s[0].trim();
    				   JLabel221.setText(s[1].trim());
    				   JLabel222.setText(s[2].trim());
    				   JLabel223.setText(s[3].trim());
    				   JLabel224.setText(entry.getValue().trim());
    				   JButton22.setVisible(true);
    			   }
    			   else if(printcount==2){
    				   maker32=s[0].trim();
    				   JLabel321.setText(s[1].trim());
    				   JLabel322.setText(s[2].trim());
    				   JLabel323.setText(s[3].trim());
    				   JLabel324.setText(entry.getValue().trim());
    				   JButton32.setVisible(true);
    			   }
    			   else if(printcount==3){
    				   maker42=s[0].trim();
    				   JLabel421.setText(s[1].trim());
    				   JLabel422.setText(s[2].trim());
    				   JLabel423.setText(s[3].trim());
    				   JLabel424.setText(entry.getValue().trim());
    				   JButton42.setVisible(true);
    			   }
    			   else if(printcount==4){
    				   maker52=s[0].trim();
    				   JLabel521.setText(s[1].trim());
    				   JLabel522.setText(s[2].trim());
    				   JLabel523.setText(s[3].trim());
    				   JLabel524.setText(entry.getValue().trim());
    				   JButton52.setVisible(true);
    			   }
    			   else if(printcount==5){
    				   maker62=s[0].trim();
    				   JLabel621.setText(s[1].trim());
    				   JLabel622.setText(s[2].trim());
    				   JLabel623.setText(s[3].trim());
    				   JLabel624.setText(entry.getValue().trim());
    				   JButton62.setVisible(true);
    			   }
    			   else if(printcount==6){
    				   maker72=s[0].trim();
    				   JLabel721.setText(s[1].trim());
    				   JLabel722.setText(s[2].trim());
    				   JLabel723.setText(s[3].trim());
    				   JLabel724.setText(entry.getValue().trim());
    				   JButton72.setVisible(true);
    			   }
    			   else if(printcount==7){
    				   maker82=s[0].trim();
    				   JLabel821.setText(s[1].trim());
    				   JLabel822.setText(s[2].trim());
    				   JLabel823.setText(s[3].trim());
    				   JLabel824.setText(entry.getValue().trim());
    				   JButton82.setVisible(true);
    			   }
    			   else if(printcount==8){
    				   maker92=s[0].trim();
    				   JLabel921.setText(s[1].trim());
    				   JLabel922.setText(s[2].trim());
    				   JLabel923.setText(s[3].trim());
    				   JLabel924.setText(entry.getValue().trim());
    				   JButton92.setVisible(true);
    			   }
    			   else if(printcount==9){
    				   maker102=s[0].trim(); 
    				   JLabel1021.setText(s[1].trim());
    				   JLabel1022.setText(s[2].trim());
    				   JLabel1023.setText(s[3].trim());
    				   JLabel1024.setText(entry.getValue().trim());
    				   JButton102.setVisible(true);
    			   }
    			   else if(printcount==10){
    				   maker112=s[0].trim();
    				   JLabel1121.setText(s[1].trim());
    				   JLabel1122.setText(s[2].trim());
    				   JLabel1123.setText(s[3].trim());
    				   JLabel1124.setText(entry.getValue().trim());
    				   JButton112.setVisible(true);
    			   }

    		   }
       }
       //laptop
       else if(listCombo.getSelectedIndex()==2){
    	   myclient1.viewLaptopist();
    	   itemList.clear();
           itemList=myclient1.getItemList();
     	   int printcount=0;
     	   JLabel231.setText("");JLabel232.setText("");JLabel233.setText("");JLabel234.setText("");JLabel331.setText("");JLabel332.setText("");
     	   JLabel333.setText("");JLabel334.setText("");JLabel431.setText("");JLabel432.setText("");JLabel433.setText("");JLabel434.setText("");
     	   JLabel531.setText("");JLabel532.setText("");JLabel533.setText("");JLabel534.setText("");JLabel631.setText("");JLabel632.setText("");
     	   JLabel633.setText("");JLabel634.setText("");JLabel731.setText("");JLabel732.setText("");JLabel733.setText("");JLabel734.setText("");
     	   JLabel831.setText("");JLabel832.setText("");JLabel833.setText("");JLabel834.setText("");JLabel931.setText("");JLabel932.setText("");
     	   JLabel933.setText("");JLabel934.setText("");JLabel1031.setText("");JLabel1032.setText("");JLabel1033.setText("");JLabel1034.setText("");
     	   JLabel1131.setText("");JLabel1132.setText("");JLabel1133.setText("");JLabel1134.setText("");
     	   JButton23.setVisible(false);JButton33.setVisible(false);JButton43.setVisible(false);JButton53.setVisible(false);JButton63.setVisible(false);
       	   JButton73.setVisible(false);JButton83.setVisible(false);JButton93.setVisible(false);JButton103.setVisible(false);JButton113.setVisible(false);

    		   for(Map.Entry<String, String> entry: itemList.entrySet()){
    			   printcount=printcount+1;
    			   String[] s = entry.getKey().split("#");
    			   //System.out.println(entry.getKey()+"  "+entry.getValue()+" : "+s[1]);
    			   if(printcount==1){
    				   maker23=s[0].trim();
    				   JLabel231.setText(s[1].trim());
    				   JLabel232.setText(s[2].trim());
    				   JLabel233.setText(s[3].trim());
    				   JLabel234.setText(entry.getValue().trim());
    				   JButton23.setVisible(true);
    			   }
    			   else if(printcount==2){
    				   maker33=s[0].trim();
    				   JLabel331.setText(s[1].trim());
    				   JLabel332.setText(s[2].trim());
    				   JLabel333.setText(s[3].trim());
    				   JLabel334.setText(entry.getValue().trim());
    				   JButton33.setVisible(true);
    			   }
    			   else if(printcount==3){
    				   maker43=s[0].trim();
    				   JLabel431.setText(s[1].trim());
    				   JLabel432.setText(s[2].trim());
    				   JLabel433.setText(s[3].trim());
    				   JLabel434.setText(entry.getValue().trim());
    				   JButton43.setVisible(true);
    			   }
    			   else if(printcount==4){
    				   maker53=s[0].trim();
    				   JLabel531.setText(s[1].trim());
    				   JLabel532.setText(s[2].trim());
    				   JLabel533.setText(s[3].trim());
    				   JLabel534.setText(entry.getValue().trim());
    				   JButton53.setVisible(true);
    			   }
    			   else if(printcount==5){
    				   maker63=s[0].trim();
    				   JLabel631.setText(s[1].trim());
    				   JLabel632.setText(s[2].trim());
    				   JLabel633.setText(s[3].trim());
    				   JLabel634.setText(entry.getValue().trim());
    				   JButton63.setVisible(true);
    			   }
    			   else if(printcount==6){
    				   maker73=s[0].trim();
    				   JLabel731.setText(s[1].trim());
    				   JLabel732.setText(s[2].trim());
    				   JLabel733.setText(s[3].trim());
    				   JLabel734.setText(entry.getValue().trim());
    				   JButton73.setVisible(true);
    			   }
    			   else if(printcount==7){
    				   maker83=s[0].trim();
    				   JLabel831.setText(s[1].trim());
    				   JLabel832.setText(s[2].trim());
    				   JLabel833.setText(s[3].trim());
    				   JLabel834.setText(entry.getValue().trim());
    				   JButton83.setVisible(true);
    			   }
    			   else if(printcount==8){
    				   maker93=s[0].trim();
    				   JLabel931.setText(s[1].trim());
    				   JLabel932.setText(s[2].trim());
    				   JLabel933.setText(s[3].trim());
    				   JLabel934.setText(entry.getValue().trim());
    				   JButton93.setVisible(true);
    			   }
    			   else if(printcount==9){
    				   maker103=s[0].trim();
    				   JLabel1031.setText(s[1].trim());
    				   JLabel1032.setText(s[2].trim());
    				   JLabel1033.setText(s[3].trim());
    				   JLabel1034.setText(entry.getValue().trim());
    				   JButton103.setVisible(true);
    			   }
    			   else if(printcount==10){
    				   maker113=s[0].trim();
    				   JLabel1131.setText(s[1].trim());
    				   JLabel1132.setText(s[2].trim());
    				   JLabel1133.setText(s[3].trim());
    				   JLabel1134.setText(entry.getValue().trim());
    				   JButton113.setVisible(true);
    			   }

    		   }
       }
   }
   //main method
   public static void main(String[] args){
	   RESTclientScreen startup = new RESTclientScreen();
      
   }
}

