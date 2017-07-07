# Shopping-Cart-using-Web-Services

Shopping Cart using Web Services application, in Distributed Systems Project in Winter 2014-15 semester at Technische Universität Darmstadt.

Group Members

1. Dibyojyoti Sanyal (https://github.com/Dibyojyoti) (@Dibyojyoti)
2. Anirban Chatterjee (https://github.com/anirban99) (@anirban99)
3. Pranay Sarkar (https://github.com/pranay22) (@pranay22)

This is a Web Services application built using SOAP and REST-ful services, which follows the fundamental functionality of a shopping cart system.

The server provides the following functionality:
The server manages the shopping cart for every client. Each shopping cart is stored using a unique ID for each client. No complex session management is needed for the clients.
The server provides Information about at least three different products (“Name”, “Price”, “Available Amount”)
The server manages the available amounts for each product

Each client provides the following functionality:
The client provides a user interface, which shows the available products with prices and the available amount for each product
The user is able to buy products. The user can send his order using his shopping cart.
The user gets a return message, if a product is not available, because another order might be received beforehand.

Steps to run:

1. Run the Build.xml from OnlineshopWebervice folder which will start both the REST and SOAP servers

2. Run the Build.xml from OnlineBuyer folder which will start one REST client and one SOAP client

3. The Rest client will automatically create a REST client customer with name as 'RestClient' and the SOAP client will automatically create a SOAP client customer with name as 'SOAPClient'. It will be listed in server side first when time the customers will add any item in their own cart.

4. Now you can perform following activities 
	i. select any option among Smart phone, home heater or Laptop and view the items which are available in on-line store using 'show List' button.
	ii. view the cart to see which all items are added already in cart.
	iii. inside cart there are two buttons one to remove items from cart and one to buy all items in cart
	iv. select any item in the drop down and click 'Remove Item' button to remove a item.
	v. click 'Buy All Items' to buy items.

Note: If a item is added to cart but not bought it will keeps on showing in Show List.
      If a item is already bought by second user and the first user added that item in his cart, he will
      get notification that item not in store after he buys all items in his cart using 'Buy All Items'.

Note:
1. ANT script should be run only via root superuser. As the normal 'TK1' user do not have any write access to the Eclipse workspace and thus the ANT script, if run via this user will fail.