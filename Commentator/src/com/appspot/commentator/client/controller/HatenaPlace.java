package com.appspot.commentator.client.controller;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class HatenaPlace extends Place {
    private String token;

    public HatenaPlace(String token) {
        super();
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<HatenaPlace> {
        @Override
        public String getToken(HatenaPlace place) {
            return place.getToken();
        }

        @Override
        public HatenaPlace getPlace(String token) {
            return new HatenaPlace(token);
        }
    }
}
