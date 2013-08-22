package com.appspot.commentator.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;



public class ProxyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("url").isEmpty()) {
            return;
        }
        URL url = new URL(req.getParameter("url"));
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        
        for (Enumeration<?> iterator = req.getHeaderNames(); iterator.hasMoreElements();) {
        	
            String name = (String) iterator.nextElement();
            if (!name.equals("Host")) {
            	connection.setRequestProperty(name, req.getHeader(name));
            }
        }
        
        connection.connect();
        
        resp.setContentType(connection.getHeaderField("content-type"));
        
        Document document = null;
        try (
        		StringWriter stringWriter = new StringWriter();
        		InputStream inputStream = connection.getInputStream();) {
        	IOUtils.copy(inputStream, stringWriter);
        	document = Jsoup.parse(stringWriter.toString());
        }
        
        try {
			resolve(document, req.getParameter("url"), "src");
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
        
        try {
			resolve(document, req.getParameter("url"), "href");
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
       
        
        PrintWriter printWriter = resp.getWriter();
        
        printWriter.write(document.html());
    }
    
    private void resolve(Document document, String url, String attr) throws URISyntaxException {
    	URI uri = new URI(url);
		for (Iterator<Element> iterator = 
        		document.getElementsByAttributeValueMatching(attr, Pattern.compile("[^^(//|http|https|#)]\\w+"))
        		.iterator(); iterator.hasNext();) {
        	Element element = iterator.next();
			element.attr(attr, uri.resolve(element.attr(attr)).toString());
		}
    }
}
