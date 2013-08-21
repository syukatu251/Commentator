package com.appspot.commentator.client.view;

import com.appspot.commentator.client.controller.AppActivityMapper;
import com.appspot.commentator.client.controller.AppPlaceHistoryMapper;
import com.appspot.commentator.client.controller.HatenaPlace;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class MainComposite extends Composite {

	private static MainCompositeUiBinder uiBinder = GWT
			.create(MainCompositeUiBinder.class);
	@UiField SimplePanel titlePanel;
	@UiField SimplePanel centerPanel;
	@UiField SimplePanel hatenaPanel;

	interface MainCompositeUiBinder extends UiBinder<Widget, MainComposite> {
	}

	public MainComposite() {
		initWidget(uiBinder.createAndBindUi(this));
		
		final EventBus eventBus = new SimpleEventBus();
        final PlaceController placeController = new PlaceController(eventBus);
        
        hatenaPanel.setWidget(new HatenaComposite(placeController));
        
        ActivityMapper activityMapper = new AppActivityMapper();
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(centerPanel);
        
        AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, new HatenaPlace(""));
        
        historyHandler.handleCurrentHistory();
	}

}
