package com.motion.takku.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String id;
    private String name;
    private String profile_image;
    private int jumlah_tak;
    private String status;
    private String username;
    private int tak_target;

    public User() {

    }

    public User(String profile_image, String id, String name, int jumlah_tak, String status, String username, int tak_target) {
        this.id = id;
        this.name = name;
        this.profile_image = profile_image;
        this.jumlah_tak = jumlah_tak;
        this.status = status;
        this.username = username;
        this.tak_target = tak_target;
    }

    public User(String id, String name, int jumlah_tak, String status, String username, int tak_target) {
        this.id = id;
        this.name = name;
        this.jumlah_tak = jumlah_tak;
        this.status = status;
        this.username = username;
        this.tak_target = tak_target;
    }

    public User(String profile_image, String name, int jumlah_tak, String status) {
        this.name = name;
        this.profile_image = profile_image;
        this.jumlah_tak = jumlah_tak;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTak_target() {
        return tak_target;
    }

    public void setTak_target(int tak_target) {
        this.tak_target = tak_target;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.profile_image);
        dest.writeInt(this.jumlah_tak);
        dest.writeString(this.status);
        dest.writeString(this.username);
        dest.writeInt(this.tak_target);
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.profile_image = in.readString();
        this.jumlah_tak = in.readInt();
        this.status = in.readString();
        this.username = in.readString();
        this.tak_target = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
