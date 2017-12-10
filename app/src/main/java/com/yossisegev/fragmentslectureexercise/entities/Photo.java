package com.yossisegev.fragmentslectureexercise.entities;

/**
 * Created by Yossi Segev on 10/12/2017.
 */

public class Photo {

    private int id;
    private PhotoUrls urls;

    public int getId() {
        return id;
    }

    public PhotoUrls getUrls() {
        return urls;
    }

    public static class PhotoUrls {
        private String thumb;
        private String small;
        private String regular;

        public String getThumb() {
            return thumb;
        }

        public String getSmall() {
            return small;
        }

        public String getRegular() {
            return regular;
        }
    }
}
