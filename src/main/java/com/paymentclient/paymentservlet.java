package com.paymentclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/paymentservlet")
public class paymentservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	payment paymentObj = new payment();
       
    
    public paymentservlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = paymentObj.insertItem(request.getParameter("billno"),
				request.getParameter("name"),
				request.getParameter("amount"),
				request.getParameter("contact"));

        response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
	    {
			Map<String, String> map = new HashMap<String, String>();
			try
			{
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
						scanner.useDelimiter("\\A").next() : "";
				scanner.close();
		 
				String[] params = queryString.split("&");
				for (String param : params)
				{ 
					String[] p = param.split("=");
					map.put(p[0], p[1]);
			    }
			 }
					
			 catch (Exception e)
		     {
			 }
			 
			return map;
		}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		String output = paymentObj.updateItem(paras.get("hidIDSave").toString(),
										   paras.get("billno").toString(),
										   paras.get("name").toString(),
										   paras.get("amount").toString(),
										   paras.get("contact").toString());
			
		response.getWriter().write(output);
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = paymentObj.deleteItem(paras.get("Id").toString());
		response.getWriter().write(output);
		
	}

}
