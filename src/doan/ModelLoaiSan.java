/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author thanh
 */
public class ModelLoaiSan {

	private static List<LoaiSan> danhSachLoaiSan = new ArrayList<>();
	public static Comparator comp = getXapXepTheoMaLS();
	public void clearAll(){
		while (danhSachLoaiSan.size() != 0)
			danhSachLoaiSan.remove(0);
	}

	// them doi tuong
	public List<LoaiSan> getDS(){
		return danhSachLoaiSan;
	}
	public void them(LoaiSan loaiSanMoi) {
		for (LoaiSan loaiSan : danhSachLoaiSan) {
			if (loaiSan.getMaLoaiSan().equals(loaiSanMoi.getMaLoaiSan())) {
				throw new IllegalArgumentException("mã loại sân bị trùng");
			}
		}
		danhSachLoaiSan.add(loaiSanMoi);
	}
	//tim loai loaiSan

	public LoaiSan tim(String maLoaiSan) {
		for (LoaiSan san : danhSachLoaiSan) {
			if (san.getMaLoaiSan().equals(maLoaiSan)) {
				return san;
			}
		}
		return null;
	}
	// xoa khach hang

	public void xoa(String maLoaiSan) {
		LoaiSan loaiSan = tim(maLoaiSan);
		if (loaiSan == null)
			throw new IllegalArgumentException("loại sân không tồn tại");
		danhSachLoaiSan.remove(loaiSan);
	}

	// sua thong tin
	public void sua(LoaiSan loaiSanMoi) {
		LoaiSan loaiSanDuocSua = tim(loaiSanMoi.getMaLoaiSan());
		if (loaiSanDuocSua == null){
			throw new IllegalArgumentException("loại sân không tồn tại");
		}

		loaiSanDuocSua.setValue(loaiSanMoi);
	}
	// ghi vao file

	public void ghiFile(String fileLoaiSan) throws IOException {
		try {
			FileWriter fileWriter = new FileWriter(fileLoaiSan);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			LoaiSan loaiSan = null;
			for (int i = 0; i < danhSachLoaiSan.size(); i++) {
				loaiSan = danhSachLoaiSan.get(i);
				bufferedWriter.write(loaiSan.getMaLoaiSan() + "_" + loaiSan.getDonGia() + "\n");
			}
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("ghi file thất bại");
		}
	}

	// doc file
	public void docFile(String fileLoaiSan) throws IOException {
		ArrayList<LoaiSan> dsLoaiSan = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(fileLoaiSan);
			Scanner sc = new Scanner(fileReader); 
			String line = "";
			while (sc.hasNext() && (line = sc.nextLine()) != "") {
				String[] info = line.split("_"); 
				String maLoaiSan = info[0];
				int donGia = Integer.parseInt(info[1]);
				
				dsLoaiSan.add(new LoaiSan(maLoaiSan, donGia));
			}
			
			sc.close();
			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("đọc file thất bại");
		}

		ModelLoaiSan.danhSachLoaiSan = dsLoaiSan; 
	}

	public void setComparator(Comparator comp){
		this.comp = comp;
	}

	public void sapXep(){
		Collections.sort(danhSachLoaiSan, comp);
	}
	// sap xep theo ma loaiSan
	public static Comparator getXapXepTheoMaLS() {
		return Comparator.comparing(LoaiSan::getMaLoaiSan);
	}
	public static Comparator getXapXepTheoDonGia() {
		return Comparator.comparing(LoaiSan::getDonGia);
	}
}
