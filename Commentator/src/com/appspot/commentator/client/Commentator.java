package com.appspot.commentator.client;

import com.appspot.commentator.client.view.MainComposite;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Commentator implements EntryPoint {
	@Override
	public void onModuleLoad() {
		RootPanel.get().add(new MainComposite());
	}
}
