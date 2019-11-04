package com.example.onpvd;

public class Tacgia {
    private int maTG;
    private String tenTG, diaChi, email;

    public Tacgia(int maTG, String tenTG, String diaChi, String email) {
        this.maTG = maTG;
        this.tenTG = tenTG;
        this.diaChi = diaChi;
        this.email = email;
    }

    public int getMaTG() {
        return maTG;
    }

    public void setMaTG(int maTG) {
        this.maTG = maTG;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
