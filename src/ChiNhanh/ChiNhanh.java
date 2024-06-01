/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChiNhanh;

import NhanVien.*; 

/**
 *
 * @author VINH
 */
public final class ChiNhanh {

    public ChiNhanh(String maCN, String tenCN, String diaChi, String soDT, NhanVien QuanLy) {
        setMaCN(maCN);
        setTenCN(tenCN);
        setDiaChi(diaChi);
        setSDT(soDT);
        setQuanLy(QuanLy);
    }

    public String getMaCN() {
        return maCN;
    }

    public String getTenCN() {
        return tenCN;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public NhanVien getQuanLy() {
        return QuanLy;
    }
    
    public String getMaQL(){
        return QuanLy.getMaNhanVien();
    }
    
    public String getMaQLNotThrow(){
        if (QuanLy == null)
            return "";
        return getMaQL();
    }

    public void setQuanLy(NhanVien QuanLy) {
        this.QuanLy = QuanLy;
    }

    public void setMaCN(String maCN) {
        if (maCN == null || maCN.trim().equals("")) {
            throw new IllegalArgumentException("Mã chi nhánh không thể trống!");
        }
        this.maCN = maCN;
    }

    public void setTenCN(String tenCN) {
        if (tenCN == null || tenCN.trim().equals("")) {
            throw new IllegalArgumentException("Ten chi nhanh không thể trống!");
        }
        this.tenCN = tenCN;
    }

    public void setDiaChi(String diaChi) {
        if (diaChi == null || diaChi.trim().equals("")) {
            throw new IllegalArgumentException("Địa chỉ không thể trống!");
        }
        this.diaChi = diaChi;
    }

    public void setSDT(String soDT) {
        if (soDT == null || soDT.length() != 10) {
            throw new IllegalArgumentException("Số điện thoại phải có độ dài là 10 chữ số.");
        }
        for (char c : soDT.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Số điện thoại chỉ được chứa chữ số.");
            }
        }
        this.soDT = soDT;
    }

    private String maCN, tenCN, diaChi, soDT;

    private NhanVien QuanLy = null;

}
