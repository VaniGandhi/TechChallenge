package com.techchallenge.model;

import android.provider.MediaStore;

import java.util.ArrayList;

public class InviteModel {

    public ArrayList<Profile> profiles;

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }

    public  class  Profile{
        public GeneralInformation general_information;
        public  ArrayList<Photos> photos;

        public GeneralInformation getGeneral_information() {
            return general_information;
        }

        public void setGeneral_information(GeneralInformation general_information) {
            this.general_information = general_information;
        }

        public ArrayList<Photos> getPhotos() {
            return photos;
        }

        public void setPhotos(ArrayList<Photos> photos) {
            this.photos = photos;
        }
    }

    public  class  GeneralInformation{
        String first_name=null;
        int age=0;

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public  class  Photos{
        public  String photo=null;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
