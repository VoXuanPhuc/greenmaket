package com.app.laptrinhdidong;

public class danhmucClass {
    private String traiCayTuoi;
    private String rauTuoi;
    private String thitSach;
    private String Ca;

    public danhmucClass(String traiCayTuoi, String rauTuoi, String thitSach, String ca) {

        this.traiCayTuoi = traiCayTuoi;
        this.rauTuoi = rauTuoi;
        this.thitSach = thitSach;
        this.Ca = ca;
    }

    public String getTraiCayTuoi() {
        return traiCayTuoi;
    }

    public void setTraiCayTuoi(String traiCayTuoi) {
        this.traiCayTuoi = traiCayTuoi;
    }

    public String getRauTuoi() {
        return rauTuoi;
    }

    public void setRauTuoi(String rauTuoi) {
        this.rauTuoi = rauTuoi;
    }

    public String getThitSach() {
        return thitSach;
    }

    public void setThitSach(String thitSach) {
        this.thitSach = thitSach;
    }

    public String getCa() {
        return Ca;
    }

    public void setCa(String ca) {
        Ca = ca;
    }


}
