package com.onlineshop.service;

import java.io.IOException;

import javax.xml.ws.Endpoint;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
//This class starts server for restful service  
public class ServerStarter {
	//path where the service is available 
    static final String BASE_URI_REST = "http://localhost:8080/rest/";
	static final String BASE_URI_SOAP = "http://localhost:8090/soap/services";
    public static void main(String[] args) {
        try {
        	//instantiate singleton class, server instantiates a object of the singleton class for which only one object can be instantiated
        	//all the other objects in server are instantiated by singleton class and bound to it, so that when a service is called it can get
        	//handle of those uniquely instantiated objects like store, customer list etc. from singleton object and refer to them for operation
        	Singleton singleton = Singleton.getInstance( );
        	//instantiate REST server
        	HttpServer server = HttpServerFactory.create(BASE_URI_REST);
            server.start();
            //instantiate SOAP server
            Endpoint.publish(BASE_URI_SOAP, new Services());
            
            System.out.println("Press Enter to stop the server. ");
            System.in.read();
            server.stop(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
