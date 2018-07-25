package com.migutest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {
	
	Map<String, String> usermap = new HashMap<String, String>();
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username, password, opertype;
        username = request.getParameter("username");
        password = request.getParameter("password");
        opertype = request.getParameter("opertype");
        
        if (opertype==null) {
        	out.println("opertype is null");
        }
        else {
            if(opertype.equals("add")) {
            	useradd(username, password, out);
            }
            else if (opertype.equals("mod")) {
            	usermod(username, password, out);
            }
            else if (opertype.equals("del")) {
            	userdel(username, out);
            }
            else if (opertype.equals("query")) {
            	userquery(out);
            }
            else {
            	out.println("opertype unrecognized");
            }
        }
        
        out.close();
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request,response);
	}
    
    protected void useradd(String username, String password, PrintWriter out) {
    	if (username==null || password==null) {
    		out.println("username or password is null");
    	}
    	else if (usermap.containsKey(username)) {
    		out.println("user exists, add failed");
    	}
    	else {
    		usermap.put(username, password);
    		out.println("add success");
    	}
    }
    
    protected void usermod(String username, String password, PrintWriter out) {
    	if (username==null || password==null) {
    		out.println("username or password is null");
    	}
    	else if (usermap.containsKey(username)) {
    		usermap.put(username, password);
    		out.println("modify success");
    	}
    	else {
    		out.println("user not exist");
    	}
    }
    
    protected void userdel(String username, PrintWriter out) {
    	if (username==null) {
    		usermap.clear();
    		out.println("delete all users success");
    	}
    	else if (usermap.containsKey(username)) {
        	usermap.remove(username);
        	out.println("delete success");
    	}
    	else {
    		out.println("user not exist");
    	}
    }

    protected void userquery(PrintWriter out) {
    	out.println(usermap.keySet());
    	out.println("query success");
    }
    
}
