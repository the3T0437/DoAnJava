/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SanBanh;

import LoaiSan.LoaiSan;
import ChiNhanh.*;
import doan.LackingException;

/**
 *
 * @author thanh
 */
public class SanBanh {
	String maSB; 
	ChiNhanh chiNhanh; 
	LoaiSan loaiSan;

	public SanBanh(String maSB, LoaiSan loaiSan, ChiNhanh chiNhanh) throws IllegalArgumentException{
		setMaSB(maSB); 
		setChiNhanh(chiNhanh);
		setLoaiSan(loaiSan);
	}

	public String getMaSB() {
		return maSB;
	}

	public void setMaSB(String maSB) throws IllegalArgumentException{
		if (maSB == null || maSB.trim().equals(""))
			throw new IllegalArgumentException("Mã sân banh không thể rỗng");
		this.maSB = maSB;
	}

	public void setChiNhanh(ChiNhanh chiNhanh) {
		this.chiNhanh = chiNhanh;
	}

	public void setLoaiSan(LoaiSan loaiSan) {
		this.loaiSan = loaiSan;
	}

	public ChiNhanh getChiNhanh() {
		return chiNhanh;
	}

	public LoaiSan getLoaiSan() {
		return loaiSan;
	}

	public String getMaCN() throws LackingException{
		if (chiNhanh == null)
			throw new LackingException("loại sân không xác định");
		return chiNhanh.getMaCN();
	}

	public String getMaCNNotThrow(){
		if (chiNhanh == null)
			return "";
		return chiNhanh.getMaCN();
	}

	public String getMaLoaiSan() throws LackingException{
		if (loaiSan == null)
			throw new LackingException("loại sân không xác định");
		return loaiSan.getMaLoaiSan();
	}

	public String getMaLoaiSanNotThrow(){
		if (loaiSan == null)
			return "";
		return loaiSan.getMaLoaiSan();
	}

	public int getDonGia() throws LackingException{
		if (loaiSan == null)
			throw new LackingException("loại sân không xác định");
		return loaiSan.getDonGia();
	}

}
