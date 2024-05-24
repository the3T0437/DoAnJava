/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoaiSan;

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

import doan.ListenerXoaLoaiSan;
/**
 *
 * @author thanh
 */

public class Model_LoaiSan {
	private static final String PATH = "danhSach_LoaiSan.txt"; 

	private static List<LoaiSan> danhSachLoaiSan = null;
	public static Comparator comp = getXapXepTheoMaLS();
	public void clearAll(){
		while (this.getDS().size() != 0)
			this.getDS().remove(0);
	}

	/*
	 * Dam bao chi co 1 instance cua Model_LoaiSan
	 */
	private Model_LoaiSan(){}
	private static Model_LoaiSan instance = new Model_LoaiSan();
	public static Model_LoaiSan getInstance(){
		return instance;
	}	

	/*
	 * thong bao den cac class khac khi xoa
	 */
	private static List<ListenerXoaLoaiSan> listenerXoaLoaiSan = new ArrayList<>();
	public void addListener(ListenerXoaLoaiSan listener){
		listenerXoaLoaiSan.add(listener);
	}
	public void removeListener(ListenerXoaLoaiSan listener){
		listenerXoaLoaiSan.remove(listener);
	}
	public void thongBaoKhiXoa(LoaiSan loaiSan){
		for(ListenerXoaLoaiSan listener : listenerXoaLoaiSan){
			listener.xuLy(loaiSan);
		}
	}

	// them doi tuong
	public List<LoaiSan> getDS(){
		if(danhSachLoaiSan != null)
			return danhSachLoaiSan;

		try{
			(new Model_LoaiSan()).docFile(PATH);
		}catch(Exception e){
			e.printStackTrace();
		}

		return danhSachLoaiSan;
	}
	public void them(LoaiSan loaiSanMoi) {
		for (LoaiSan loaiSan : this.getDS()) {
			if (loaiSan.getMaLoaiSan().equals(loaiSanMoi.getMaLoaiSan())) {
				throw new IllegalArgumentException("mã loại sân bị trùng");
			}
		}
		this.getDS().add(loaiSanMoi);
	}
	//tim loai loaiSan

	public LoaiSan tim(String maLoaiSan) {
		for (LoaiSan san : this.getDS()) {
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
		this.getDS().remove(loaiSan);

		thongBaoKhiXoa(loaiSan);
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
			for (int i = 0; i < this.getDS().size(); i++) {
				loaiSan = this.getDS().get(i);
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

		Model_LoaiSan.danhSachLoaiSan = dsLoaiSan; 
	}

	public void setComparator(Comparator comp){
		this.comp = comp;
	}

	public void sapXep(){
		Collections.sort(this.getDS(), comp);
	}
	// sap xep theo ma loaiSan
	public static Comparator getXapXepTheoMaLS() {
		return Comparator.comparing(LoaiSan::getMaLoaiSan);
	}
	public static Comparator getXapXepTheoDonGia() {
		return Comparator.comparing(LoaiSan::getDonGia);
	}
}
