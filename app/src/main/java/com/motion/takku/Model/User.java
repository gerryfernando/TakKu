package com.motion.takku.Model;

public class User {

    private String name;
    private int profile_image;
    private int rank_logo_image;
    private int jumlah_tak;
    private String status;
/**
    private String username;
    private String password;
    private String email;
    private int tak_target;
**/


    public User(String name, int profile_image, int rank_logo_image, int jumlah_tak, String status) {
        this.name = name;
        this.profile_image = profile_image;
        this.rank_logo_image = rank_logo_image;
        this.jumlah_tak = jumlah_tak;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(int profile_image) {
        this.profile_image = profile_image;
    }

    public int getRank_logo_image() {
        return rank_logo_image;
    }

    public void setRank_logo_image(int rank_logo_image) {
        this.rank_logo_image = rank_logo_image;
    }

    public int getJumlah_tak() {
        return jumlah_tak;
    }

    public void setJumlah_tak(int jumlah_tak) {
        this.jumlah_tak = jumlah_tak;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
