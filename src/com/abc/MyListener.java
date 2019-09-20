package com.abc;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyListener
 *
 */
@WebListener
public class MyListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    //public void contextDestroyed(ServletContextEvent sce)  { 
    	
         // TODO Auto-generated method stub
    

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	
    	try {
    	    Class.forName("com.mysql.jdbc.Driver");
    	    Connection con = DriverManager.getConnection(
    	    "jdbc:mysql://192.168.34.18:3306/mytable?useSSl=false&serverTimezone=CST","root","12345678");
    	    ServletContext ctx = sce.getServletContext();
    	    ctx.setAttribute("mycon",con);
    	}catch(Exception e ) {e.printStackTrace();}
    }
    public void contextDestroyed(ServletContextEvent arg0) {}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
         // TODO Auto-generated method stub
    }
	

