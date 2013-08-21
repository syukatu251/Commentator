package com.appspot.commentator.client.controller;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MainServiceAsync {
void getHtml(String url, AsyncCallback<String> callback);
}
