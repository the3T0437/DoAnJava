/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doan;

/**
 *
 * @author VINH
 */
public class LoaiSan {
	private String maLoaiSan;
	private int donGia;

	public LoaiSan(String maLoaiSan, int donGia) throws IllegalArgumentException{
		setMaLoaiSan(maLoaiSan)	;
		setDonGia(donGia);
	}

	public String getMaLoaiSan() {
		return maLoaiSan;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setMaLoaiSan(String maLoaiSan) throws IllegalArgumentException{
		if (maLoaiSan == null || maLoaiSan.equals("")){
			throw new IllegalArgumentException("mã loại sân không thể rỗng");
		}
		this.maLoaiSan = maLoaiSan;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public void setValue(LoaiSan loaiSanMoi) {
		this.donGia = loaiSanMoi.donGia;
	}

	public String ToString() {
		return "ma loai san" + " " + maLoaiSan + " ," + "don gia" + " " + donGia;
	}

}
