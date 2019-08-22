package com.motion.takku.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailTAK implements Parcelable {
    private String namaAcara;
    private String penyelenggara;
    private String tanggal;
    private int jumlahTak;
    private int imgSertifikat;

    public DetailTAK(String namaAcara, String penyelenggara, String tanggal, int jumlahTak, int imgSertifikat) {
        this.namaAcara = namaAcara;
        this.penyelenggara = penyelenggara;
        this.tanggal = tanggal;
        this.jumlahTak = jumlahTak;
        this.imgSertifikat = imgSertifikat;
    }

    public DetailTAK(String namaAcara, String penyelenggara, String tanggal, int jumlahTak) {
        this.namaAcara = namaAcara;
        this.penyelenggara = penyelenggara;
        this.tanggal = tanggal;
        this.jumlahTak = jumlahTak;
    }

    public String getNamaAcara() {
        return namaAcara;
    }

    public void setNamaAcara(String namaAcara) {
        this.namaAcara = namaAcara;
    }

    public String getPenyelenggara() {
        return penyelenggara;
    }

    public void setPenyelenggara(String penyelenggara) {
        this.penyelenggara = penyelenggara;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getJumlahTak() {
        return jumlahTak;
    }

    public void setJumlahTak(int jumlahTak) {
        this.jumlahTak = jumlahTak;
    }

    public int getImgSertifikat() {
        return imgSertifikat;
    }

    public void setImgSertifikat(int imgSertifikat) {
        this.imgSertifikat = imgSertifikat;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.namaAcara);
        dest.writeString(this.penyelenggara);
        dest.writeString(this.tanggal);
        dest.writeInt(this.jumlahTak);
        dest.writeInt(this.imgSertifikat);
    }

    protected DetailTAK(Parcel in) {
        this.namaAcara = in.readString();
        this.penyelenggara = in.readString();
        this.tanggal = in.readString();
        this.jumlahTak = in.readInt();
        this.imgSertifikat = in.readInt();
    }

    public static final Parcelable.Creator<DetailTAK> CREATOR = new Parcelable.Creator<DetailTAK>() {
        @Override
        public DetailTAK createFromParcel(Parcel source) {
            return new DetailTAK(source);
        }

        @Override
        public DetailTAK[] newArray(int size) {
            return new DetailTAK[size];
        }
    };
}
