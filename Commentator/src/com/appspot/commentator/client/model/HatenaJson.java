package com.appspot.commentator.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;

public class HatenaJson extends JavaScriptObject {
    protected HatenaJson() {};
    
 // ブックマークしている合計ユーザ数
    public final native String getCount() /*-{ return this.count; }-*/;
    
    // ブックマークされているURL
    public final native String getUrl() /*-{ return this.url; }-*/;
    
    // タイトル
    public final native String getTitle() /*-{ return this.title; }-*/;
    
    // エントリーID
    public final native String getEid() /*-{ return this.eid; }-*/;
    
    // はてなブックマークエントリーページのURL
    public final native String getEntryUrl() /*-{ return this.entry_url; }-*/;
    
    // スクリーンショット画像のURL
    public final native String getScreenShot() /*-{ return this.screenshot; }-*/;
    
    // bookmarks配列を取得
    public final native JsArray<BookmarkJson> getBookmarks() /*-{ return this.bookmarks; }-*/;
    
    public static class BookmarkJson extends JavaScriptObject {
        
        protected BookmarkJson() {
        }
        
        // コメント
        public final native String getComment() /*-{ return this.comment; }-*/;
        
        // ブックマークした時刻
        public final native String getTimestamp() /*-{ return this.timestamp; }-*/;
        
        // ブックマークしたユーザ名
        public final native String getUser() /*-{ return this.user; }-*/;
        
        // タグの配列
        public final native JsArrayString getTags() /*-{ return this.tags; }-*/;
    }
}
