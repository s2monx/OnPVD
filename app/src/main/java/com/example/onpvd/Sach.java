package com.example.onpvd;

public class Sach {
    private int maS, maTG;
    private String tuaS;

    public Sach(int maS, int maTG, String tuaS) {
        this.maS = maS;
        this.maTG = maTG;
        this.tuaS = tuaS;
    }

    public int getMaS() {
        return maS;
    }

    public void setMaS(int maS) {
        this.maS = maS;
    }

    public int getMaTG() {
        return maTG;
    }

    public void setMaTG(int maTG) {
        this.maTG = maTG;
    }

    public String getTuaS() {
        return tuaS;
    }

    public void setTuaS(String tuaS) {
        this.tuaS = tuaS;
    }
}
