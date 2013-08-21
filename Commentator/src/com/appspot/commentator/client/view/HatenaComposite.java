package com.appspot.commentator.client.view;

import com.appspot.commentator.client.controller.HatenaPlace;
import com.appspot.commentator.client.model.GoogleFeedsJson;
import com.appspot.commentator.client.model.GoogleFeedsJson.EntryJson;
import com.appspot.commentator.client.model.HatenaData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class HatenaComposite extends Composite {
    private static HatenaCompositeUiBinder uiBinder = GWT
            .create(HatenaCompositeUiBinder.class);
    @UiField
    VerticalPanel hatenaEntryVerticalPanel;

    interface HatenaCompositeUiBinder extends UiBinder<Widget, HatenaComposite> {
    }

    public HatenaComposite(final PlaceController placeController) {
        initWidget(uiBinder.createAndBindUi(this));
        HatenaData hatena = new HatenaData();
        hatena.requestGoogleFeed(new AsyncCallback<GoogleFeedsJson>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(GoogleFeedsJson result) {
                JsArray<EntryJson> entryArray = result.getResponseData().getFeed().getEntries();
                for (int i = 0; i < entryArray.length(); i++) {
                    final EntryJson entry = entryArray.get(i);
                    Anchor anchor = new Anchor(entry.getTitle());
                    anchor.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                            placeController.goTo(new HatenaPlace(entry.getLink()));
                        }
                    });
                    hatenaEntryVerticalPanel.add(anchor);
                }
            }
        });
    }

}
