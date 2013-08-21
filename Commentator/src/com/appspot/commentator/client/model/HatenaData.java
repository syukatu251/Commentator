package com.appspot.commentator.client.model;

import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class HatenaData {
    private static String googleFeedJsonpUrl = 
            "http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=20&q=http://feeds.feedburner.com/hatena/b/hotentry";
    private static String hatenaJsonpUrl = "http://b.hatena.ne.jp/entry/json/?url=";
    public void requestGoogleFeed(AsyncCallback<GoogleFeedsJson> callback) {
        JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
        jsonp.requestObject(googleFeedJsonpUrl, callback);
    }
    
    public void requestBookmark(String url, AsyncCallback<HatenaJson> callback) {
        JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
        jsonp.requestObject(hatenaJsonpUrl + url, callback);
    }
}
