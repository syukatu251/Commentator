package com.appspot.commentator.client.view;

import com.appspot.commentator.client.model.HatenaData;
import com.appspot.commentator.client.model.HatenaJson;
import com.appspot.commentator.client.model.HatenaJson.BookmarkJson;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;

public class CenterComposite extends Composite {

	private static CenterCompositeUiBinder uiBinder = GWT
			.create(CenterCompositeUiBinder.class);
	@UiField AbsolutePanel commentPanel;
	@UiField Frame webpageFrame;

	interface CenterCompositeUiBinder extends UiBinder<Widget, CenterComposite> {
	}

	public CenterComposite() {
		initWidget(uiBinder.createAndBindUi(this));
		commentPanel.getElement().getStyle().setOverflow(Overflow.VISIBLE);
	}

    public void move(final String url) {
        webpageFrame.setUrl(GWT.getHostPageBaseURL() + "ProxyServlet?url=" + url);
        webpageFrame.addLoadHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
                IFrameElement frameElement = IFrameElement.as(webpageFrame.getElement());
                Document contentDocument = frameElement.getContentDocument();
                int width = contentDocument.getScrollWidth();
                int height = contentDocument.getScrollHeight();
                webpageFrame.setPixelSize(width + 20, height + 100);
            }
        });
        
        HatenaData hatenaData = new HatenaData();
        hatenaData.requestBookmark(url, new AsyncCallback<HatenaJson>() {
            
            @Override
            public void onSuccess(HatenaJson result) {
                JsArray<BookmarkJson> bookmarkArray = result.getBookmarks();
                for (int i = 0; i < bookmarkArray.length(); i++) {
                    BookmarkJson bookmark = bookmarkArray.get(i);
                    if (!bookmark.getComment().isEmpty()) {
                        commentPanel.add(
                                new CommentComposite(bookmark.getComment()), 
                                Random.nextInt(webpageFrame.getOffsetWidth() / 2),
                                Random.nextInt(webpageFrame.getOffsetHeight()));
                    }
                }
            }
            
            @Override
            public void onFailure(Throwable caught) {
                // TODO 自動生成されたメソッド・スタブ
                
            }
        });
    }
}
