package com.appspot.commentator.server;

import java.io.IOException;

import org.jsoup.Jsoup;

import com.appspot.commentator.client.controller.MainService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MainServiceImpl extends RemoteServiceServlet implements
    MainService {

	@Override
	public String getHtml(String url) {
		try {
			return Jsoup.connect(url).get().html();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
