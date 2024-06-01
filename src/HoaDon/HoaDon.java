/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDon;
import KhachHang.KhachHang;
import NhanVien.NhanVien;
import SanBanh.SanBanh;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author thanh
 */
public class HoaDon {
	private String maHD;
	private KhachHang khachHang;
	private NhanVien nhanVien; 
	private SanBanh sanBanh;

	public HoaDon(String maHD, KhachHang khachHang, NhanVien nhanVien, SanBanh sanBanh) throws IllegalArgumentException{
		setMaHD(maHD);
		setKhachHang(khachHang);
		setNhanVien(nhanVien);
		setSanBanh(sanBanh);
	}

	public String getMaHD() {
		return maHD;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public SanBanh getSanBanh() {
		return sanBanh;
	}

	public void setMaHD(String maHD) throws IllegalArgumentException {
		if(maHD == null || maHD.trim().equals(""))
			throw new IllegalArgumentException("Mã hoá đơn không được rỗng");

		this.maHD = maHD;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public void setSanBanh(SanBanh sanBanh) {
		this.sanBanh = sanBanh;
	}

	public String getMaSBNotThrow(){
		if (sanBanh != null)
			return sanBanh.getMaSB(); 
		return "";
	}

	public String getTenKHNotThrow(){
		if (khachHang!= null)
			return khachHang.getHoTen();
		return "";
	}

	public String getMaKHNotThrow(){
		if (khachHang!= null)
			return khachHang.getMaKhachHang();
		return "";
	}

	public String getTenNVNotThrow(){
		if (nhanVien != null)
			return nhanVien.getTen();

		return "";
	}

	public String getMaNVNotThrow(){
		if (nhanVien != null)
			return nhanVien.getMaNhanVien();

		return "";
	}

	public void ghiFile(FileWriter fw) throws IOException{
		String str = String.format("%s _ %s _ %s _ %s\n",
			getMaHD(), getMaKHNotThrow(), getMaNVNotThrow(), getMaSBNotThrow());
		fw.write(str);
	}
}
