package com.migutest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ZooServlet extends HttpServlet {
	
	Map<String, String> classmap = new HashMap<String, String>();
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String name, count, opertype;
        name = request.getParameter("name");
        count = request.getParameter("count");
        opertype = request.getParameter("opertype");
        
        if (opertype==null) {
        	out.println("opertype is null");
        }
        else {
            if(opertype.equals("add")) {
            	classadd(name, count, out);
            }
            else if (opertype.equals("mod")) {
            	classmod(name, count, out);
            }
            else if (opertype.equals("del")) {
            	classdel(name, out);
            }
            else if (opertype.equals("query")) {
            	classquery(out);
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
    
    protected void classadd(String name, String count, PrintWriter out) {
    	if (name==null || count==null) {
    		out.println("name or count is null");
    	}
    	else if (classmap.containsKey(name)) {
    		out.println("name exists, add failed");
    	}
    	else {
    		classmap.put(name, count);
    		out.println("add success");
    	}
    }
    
    protected void classmod(String name, String count, PrintWriter out) {
    	if (name==null || count==null) {
    		out.println("name or count is null");
    	}
    	else if (classmap.containsKey(name)) {
    		classmap.put(name, count);
    		out.println("modify success");
    	}
    	else {
    		out.println("name not exist");
    	}
    }
    
    protected void classdel(String name, PrintWriter out) {
    	if (name==null) {
    		classmap.clear();
    		out.println("delete all classes success");
    	}
    	else if (classmap.containsKey(name)) {
        	classmap.remove(name);
        	out.println("delete success");
    	}
    	else {
    		out.println("name not exist");
    	}
    }

    protected void classquery(PrintWriter out) {
    	out.println(classmap.keySet());
    	out.println("query success");
    }
    
}
