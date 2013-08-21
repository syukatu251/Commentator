package com.appspot.commentator.client.controller;

import com.appspot.commentator.client.view.CenterComposite;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class CenterActivity extends AbstractActivity {
    private String token;

    public CenterActivity(HatenaPlace place) {
        super();
        this.token = place.getToken();
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        final CenterComposite composite = new CenterComposite();
        panel.setWidget(composite);
        
        composite.move(token);
    }
    

}
