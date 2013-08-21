package com.appspot.commentator.server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



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
            connection.setRequestProperty(name, req.getHeader(name));
        }
        
        connection.connect();
        
/*        for (Entry<String, List<String>> headerEntry : connection.getHeaderFields().entrySet()) {
            resp.setHeader(headerEntry.getKey(), StringUtils.join(headerEntry.getValue(), ","));
        }
        
        PrintWriter printWriter = resp.getWriter();
        InputStream inputStream = connection.getInputStream();
        
        IOUtils.copy(inputStream, printWriter);
        
        inputStream.close();
        printWriter.close();*/
    }
}
