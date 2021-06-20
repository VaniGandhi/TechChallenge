package com.techchallenge.model;

public class ProfileResponseModel {
    public  InviteModel invites;
    public  LikeResponseModel likes;

    public InviteModel getInvites() {
        return invites;
    }

    public void setInvites(InviteModel invites) {
        this.invites = invites;
    }

    public LikeResponseModel getLikes() {
        return likes;
    }

    public void setLikes(LikeResponseModel likes) {
        this.likes = likes;
    }
}
