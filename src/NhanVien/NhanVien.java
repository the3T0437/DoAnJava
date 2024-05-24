/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVien;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class NhanVien {
     private String maNhanVien;
    private String ten;
    private LocalDate ngaySinh;
    private String soDienThoai;
    private String chucVu;
    private double luong;

    @Override
    public String toString() {
        return "NhanVien{" + "maNhanVien=" + maNhanVien + ", ten=" + ten + ", ngaySinh=" + ngaySinh + ", soDienThoai=" + soDienThoai + ", chucVu=" + chucVu + ", luong=" + luong + '}';
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public NhanVien(String maNhanVien, String ten, LocalDate ngaySinh, String soDienThoai, String chucVu, double luong) {
        this.maNhanVien = maNhanVien;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.chucVu = chucVu;
        this.luong = luong;
    }

}
