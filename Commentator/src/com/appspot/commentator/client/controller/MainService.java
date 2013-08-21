package com.appspot.commentator.client.controller;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("MainService")
public interface MainService extends RemoteService {
    String getHtml(String url);
}
