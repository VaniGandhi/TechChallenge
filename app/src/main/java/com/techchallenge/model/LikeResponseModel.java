package com.techchallenge.model;

import java.util.ArrayList;

public class LikeResponseModel {

    public ArrayList<Profiles> profiles;

    public ArrayList<Profiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<Profiles> profiles) {
        this.profiles = profiles;
    }

    public class Profiles {
        String first_name = null;
        String avatar = null;

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
