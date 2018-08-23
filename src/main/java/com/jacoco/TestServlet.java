package com.jacoco;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TestServlet extends HttpServlet {
	
	Map<String, String> usermap = new HashMap<String, String>();
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String name, password, opertype;
        name = request.getParameter("name");
        password = request.getParameter("password");
        opertype = request.getParameter("opertype");
        
        if (opertype==null) {
        	out.println("opertype is null");
        }
        else {
            if(opertype.equals("add")) {
            	useradd(name, password, out);
            }
            else if (opertype.equals("mod")) {
            	usermod(name, password, out);
            }
            else if (opertype.equals("del")) {
            	userdel(name, out);
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
    
    protected void useradd(String name, String password, PrintWriter out) {
    	if (name==null || password==null) {
    		out.println("name or password is null");
    	}
    	else if (usermap.containsKey(name)) {
    		out.println("user exists, add failed");
    	}
    	else {
    		usermap.put(name, password);
    		out.println("add success");
    	}
    }
    
    protected void usermod(String name, String password, PrintWriter out) {
    	if (name==null || password==null) {
    		out.println("name or password is null");
    	}
    	else if (usermap.containsKey(name)) {
    		usermap.put(name, password);
    		out.println("modify success");
    	}
    	else {
    		out.println("user not exist");
    	}
    }
    
    protected void userdel(String name, PrintWriter out) {
    	if (name==null) {
    		usermap.clear();
    		out.println("delete all users success");
    	}
    	else if (usermap.containsKey(name)) {
        	usermap.remove(name);
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
