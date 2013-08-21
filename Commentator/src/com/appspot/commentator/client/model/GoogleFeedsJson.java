package com.appspot.commentator.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;

public class GoogleFeedsJson extends JavaScriptObject {
    protected GoogleFeedsJson() {};
    
    public final native ResponseDataJson getResponseData() /*-{ return this.responseData; }-*/;
    public final native int getResponseStatus() /*-{ return this.responseStatus; }-*/;
    
    public static class ResponseDataJson extends JavaScriptObject {
        protected ResponseDataJson() {};
        public final native FeedJson getFeed() /*-{ return this.feed; }-*/;
        public final native String getXmlString() /*-{ return this.xmlString; }-*/;
    }
    
    public static class FeedJson extends JavaScriptObject {
        protected FeedJson() {};
        
        public final native String getTitle() /*-{ return this.title; }-*/;
        public final native String getLink() /*-{ return this.link; }-*/;
        public final native String getDescription() /*-{ return this.description; }-*/;
        public final native String getAuthor() /*-{ return this.author; }-*/;
        public final native JsArray<EntryJson> getEntries() /*-{ return this.entries; }-*/;
    }
    
    public static class EntryJson extends JavaScriptObject {
        protected EntryJson() {};
        
        public final native String getTitle() /*-{ return this.title; }-*/;
        public final native String getLink() /*-{ return this.link; }-*/;
        public final native String getContent() /*-{ return this.content; }-*/;
        public final native String getContentSnippet() /*-{ return this.contentSnippet; }-*/;
        public final native String getPublishedDate() /*-{ return this.publishedDate; }-*/;
        public final native JsArrayString getCategories() /*-{ return this.categories; }-*/;
    }
}
