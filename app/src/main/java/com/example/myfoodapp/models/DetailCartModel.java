package com.example.myfoodapp.models;

public class DetailCartModel {
    int id_donhang;
    String tenthucan;
    String hoten;
    String sdt;
    String diachi;
    Float tongtien;
    String ngaydat;
    String phuongthuc;
    String Email;
    int trangthai;

    public DetailCartModel() {
    }

    public DetailCartModel(int id_donhang, String tenthucan, String hoten, String sdt, String diachi, Float tongtien, String ngaydat, String phuongthuc, String email, int trangthai) {
        this.id_donhang = id_donhang;
        this.tenthucan = tenthucan;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.tongtien = tongtien;
        this.ngaydat = ngaydat;
        this.phuongthuc = phuongthuc;
        Email = email;
        this.trangthai = trangthai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getId_donhang() {
        return id_donhang;
    }

    public void setId_donhang(int id_donhang) {
        this.id_donhang = id_donhang;
    }

    public String getTenthucan() {
        return tenthucan;
    }

    public void setTenthucan(String tenthucan) {
        this.tenthucan = tenthucan;
    }

    public String getHoten() {
        return hoten;
    }

    public String getPhuongthuc() {
        return phuongthuc;
    }

    public void setPhuongthuc(String phuongthuc) {
        this.phuongthuc = phuongthuc;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Float getTongtien() {
        return tongtien;
    }

    public void setTongtien(Float tongtien) {
        this.tongtien = tongtien;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }
}
