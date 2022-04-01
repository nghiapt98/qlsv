package com.example.assignment;

public class Sinhvien {
    private int id;
    private String hoten;
    private int ngaythang;
    private String diachi;

    public Sinhvien(int id, String hoten, int ngaythang, String diachi) {
        this.id = id;
        this.hoten = hoten;
        this.ngaythang = ngaythang;
        this.diachi = diachi;
    }

    public Sinhvien() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getNgaythang() {
        return ngaythang;
    }

    public void setNgaythang(int ngaythang) {
        this.ngaythang = ngaythang;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
