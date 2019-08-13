package com.motion.takku.Model;

public class DetailTAK {
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
}
