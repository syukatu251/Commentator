package com.appspot.commentator.client.controller;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof HatenaPlace) {
            return new CenterActivity((HatenaPlace) place);
        }
        return null;
    }

}
