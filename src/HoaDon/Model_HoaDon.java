/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDon;
import NhanVien.Model_NhanVien;
import NhanVien.NhanVien;
import SanBanh.Model_SanBanh;
import SanBanh.SanBanh;
import KhachHang.Modal_KH;
import KhachHang.KhachHang;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import doan.*;

/**
 *
 * @author thanh
 */
public  class Model_HoaDon {
	public static final String PATH = "DanhSach_HoaDon.txt";

	Model_NhanVien mdNhanVien = Model_NhanVien.getInstance();
	Model_SanBanh mdSanBanh = Model_SanBanh.getInstance();
	Modal_KH mdKhachHang = Modal_KH.getInstance();

	private static List<HoaDon> listHoaDon = null; 
	
	public Model_HoaDon(){
		comp = Model_HoaDon.MAHD_ASC;

		/* add listener */
		mdKhachHang.addListener(listenerXoaKH);
		mdSanBanh.addListener(listenerXoaSB);
		mdNhanVien.addListener(listenerXoaNV);
	}

	/**
	 * Dam bao chi co 1 instance cua Model_HoaDon
	 */
	private static Model_HoaDon instance = new Model_HoaDon();
	public static Model_HoaDon getInstance(){
		return instance;
	}

	public List<HoaDon> getList(){
		try{
			if (listHoaDon == null){
				getInstance().docFile(PATH);
			}
		}catch(IOException e){
			//do nothing
		}

		return listHoaDon;
	}

	/**
	 * tao hoa don
	 */
	public HoaDon createHoaDon(String maHD, String maKH,String maNV, String maSB) throws IllegalArgumentException{
		KhachHang khachHang = null;
		NhanVien nhanVien = null;
		SanBanh sanBanh = null;
		
		if (maKH != null && !maKH.trim().equals("")){
			khachHang = mdKhachHang.timKhachHang(maKH);
			if (khachHang == null)
				throw new IllegalArgumentException("Khách hàng không tồn tại");
		}

		if (maNV != null && !maNV.trim().equals("")){
			nhanVien = mdNhanVien.tim(maNV);
			if (nhanVien == null)
				throw new IllegalArgumentException("Nhân viên không tồn tại");
		}		

		if (maSB != null && !maSB.trim().equals("")){
			sanBanh = mdSanBanh.tim(maSB);
			if (sanBanh == null)
				throw new IllegalArgumentException("Sân banh không tồn tại");
		}

		return new HoaDon(maHD, khachHang, nhanVien, sanBanh);
	}

	/**
	 * tim them xoa sua ghi doc
	 */

	public HoaDon tim(String maHD){
		for (HoaDon hd : getList())
			if (hd.getMaHD().equals(maHD))
				return hd; 

		return null;
	}

	public void them(HoaDon hoaDon) throws IllegalArgumentException{
		if (tim(hoaDon.getMaHD()) != null)
			throw new IllegalArgumentException("Mã hoá đơn đã tồn tại");

		getList().add(hoaDon);
	}

	public void xoa(String maHD) throws IllegalArgumentException{
		HoaDon hoaDon = tim(maHD);
		if (hoaDon == null)
			throw new IllegalArgumentException("Mã hoá đơn không tồn tại để xoá");

		getList().remove(hoaDon);
	}
	
	public void sua(HoaDon hoaDon) throws IllegalArgumentException{
		HoaDon hoaDonSua = tim(hoaDon.getMaHD());
		if (hoaDonSua == null)
			throw new IllegalArgumentException("Mã hoá đơn không tồn tại để sửa");

		hoaDonSua.setKhachHang(hoaDon.getKhachHang());
		hoaDonSua.setNhanVien(hoaDon.getNhanVien());
		hoaDonSua.setSanBanh(hoaDon.getSanBanh());
	}

	public void ghiFile(String path) throws IOException{
		try{
			FileWriter fw = new FileWriter(path);

			for(HoaDon hd : getList()){
				hd.ghiFile(fw);
			}

			fw.close();
		}catch (IOException e){
			throw new IOException("xảy ra lỗi trong quá trình ghi file");
		}
	}

	public void docFile(String path) throws IOException{
		ArrayList<HoaDon> result = new ArrayList<>();
		try{
			FileReader fr = new FileReader(path); 
			Scanner sc = new Scanner(fr); 

			while (sc.hasNext()){
				String line = sc.nextLine();
				if (line.trim().equals(""))
					break;
				
				String[] info = line.split("_"); 
				String maHD = info[0].trim();
				String maKH = info[1].trim();
				String maNV = info[2].trim();
				String maSB = info[3].trim();

				KhachHang kh = mdKhachHang.timKhachHang(maKH);
				NhanVien nv = mdNhanVien.tim(maNV);
				SanBanh sb = mdSanBanh.tim(maSB);

				result.add(new HoaDon(maHD, kh, nv, sb));
			}
		}catch(IOException e){
			listHoaDon = new ArrayList<>();
			throw new IOException("File để đọc không tồn tại, dữ liệu của danh sách hoá đơn rỗng");
		}

		listHoaDon = result;
	}

	/**
	 * clear all
	 */
	public void clearAll(){
		while(getList().size() != 0)
			getList().remove(0);
	}

	/**
	 * sap xep
	 */
	public void sapXep(){
		Collections.sort(listHoaDon, this.comp);
	}

	public void setComparator(Comparator comp){
		this.comp = comp;
		sapXep();
	}

	public static final Comparator MAHD_ASC = Comparator.comparing(HoaDon::getMaHD);
	public static final Comparator MANV_ASC = Comparator.comparing(HoaDon::getMaNVNotThrow);
	public static final Comparator MAKH_ASC = Comparator.comparing(HoaDon::getMaKHNotThrow);
	public static final Comparator TENNV_ASC = Comparator.comparing(HoaDon::getTenNVNotThrow);
	public static final Comparator TENKH_ASC = Comparator.comparing(HoaDon::getTenKHNotThrow);
	public static final Comparator MASB_ASC = Comparator.comparing(HoaDon::getMaSBNotThrow);
	public static Comparator comp = Model_HoaDon.MAHD_ASC;

	/*
		xu ly khi xoa: NhanVien, KhachHang, SanBanh
	*/

	public ListenerXoaKH listenerXoaKH = new ListenerXoaKH(){
		public void xuLy(KhachHang kh){
				for (HoaDon hd : getList())
					if (hd.getMaKHNotThrow().equalsIgnoreCase(kh.getMaKhachHang()))
						hd.setKhachHang(null);
		}
	};

	public ListenerXoaNV listenerXoaNV = new ListenerXoaNV(){
		public void xuLy(NhanVien nv){
				for (HoaDon hd : getList())
					if (hd.getMaNVNotThrow().equalsIgnoreCase(nv.getMaNhanVien()))
						hd.setNhanVien(null);
		}
	};

	public ListenerXoaSanBanh listenerXoaSB = new ListenerXoaSanBanh(){
		public void xuLy(SanBanh sb){
				for (HoaDon hd : getList())
					if (hd.getMaSBNotThrow().equalsIgnoreCase(sb.getMaSB()))
						hd.setSanBanh(null);
		}
	};
}
