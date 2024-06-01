/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SanBanh;

import LoaiSan.Model_LoaiSan;
import LoaiSan.LoaiSan;
import ChiNhanh.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import doan.ListenerXoaLoaiSan;
import doan.ListenerXoaSanBanh;
import doan.ListenerXoaCN;

/**
 *
 * @author thanh
 */
public class Model_SanBanh {
	public static final String PATH= "danhSach_SanBanh.txt"; 
	private static Modal_CN mdChiNhanh = Modal_CN.getInstance();
	private static Model_LoaiSan mdLoaiSan = Model_LoaiSan.getInstance();
	private static List<SanBanh> dsSanBanh = null;


	private static Comparator comp;

	private Model_SanBanh(){
		comp = MASB_ASC;
		mdLoaiSan.addListener(this.listenerXoaLoaiSan);
		mdChiNhanh.addListener(listenerXoaCN);
	}
	
	/*
		dam bao chi co 1 instance Model_SanBanh 
	*/
	private static Model_SanBanh instance = new Model_SanBanh();
	public static Model_SanBanh getInstance(){
		return instance;
	}
	/*
	 * thong bao den cac class khac khi xoa
	 */
	private static List<ListenerXoaSanBanh> listenerXoaSB= new ArrayList<>();
	public void addListener(ListenerXoaSanBanh listener){
		listenerXoaSB.add(listener);
	}
	public void removeListener(ListenerXoaSanBanh listener){
		listenerXoaSB.remove(listener);
	}
	public void thongBaoKhiXoa(SanBanh sb){
		for(ListenerXoaSanBanh listener : listenerXoaSB){
			listener.xuLy(sb);
		}
	}

	public void setComparator(Comparator<SanBanh> comp){
		this.comp = comp;
	}

	public List<SanBanh> getDSSanBanh(){
		if (dsSanBanh != null)
			return dsSanBanh; 

		try{
			(getInstance()).docFile(PATH);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return dsSanBanh;
	}

	public static SanBanh createSanBanh(String maSB, String maLoaiSan, String maChiNhanh) throws IllegalArgumentException{
		LoaiSan loaiSan = null;
		ChiNhanh chiNhanh = null; 

		//tim loai san neu loai san khac null
		if (maLoaiSan == null || maLoaiSan.trim().equals(""))
			loaiSan = null;
		else{
			loaiSan = mdLoaiSan.tim(maLoaiSan.trim());
			if (loaiSan == null)
				throw new IllegalArgumentException("mã loại sân không tồn tại");
		}

		//tim chi nhanh neu loai san khac null
		if (maChiNhanh == null || maChiNhanh.trim().equals(""))
			chiNhanh = null;
		else{
			chiNhanh = mdChiNhanh.tim(maChiNhanh.trim());
			if (chiNhanh == null)
				throw new IllegalArgumentException("mã chi nhánh không tồn tại");
		}

		Model_SanBanh mdSanBanh = new Model_SanBanh();

		return new SanBanh(maSB, loaiSan, chiNhanh);
	}

	public SanBanh tim(String maSanBanh){
		for (int i = 0; i < getDSSanBanh().size(); i++){
			if (getDSSanBanh().get(i).getMaSB().equals(maSanBanh))
				return getDSSanBanh().get(i);
		}

		return null;
	}

	public SanBanh tim(SanBanh sb){
		return tim(sb.getMaSB());
	}

	public void them(SanBanh sb) throws IllegalArgumentException{
		if (tim(sb.getMaSB()) != null)
			throw new IllegalArgumentException("Mã Sân Banh đã tồn tại");

		dsSanBanh.add(sb);
	}

	public void xoa(String maSB) throws IllegalArgumentException{
		SanBanh sb = tim(maSB); 
		if (sb == null)
			throw new IllegalArgumentException("Mã Sân Banh không tồn tại tồn tại");
		dsSanBanh.remove(sb);
		thongBaoKhiXoa(sb);
	}

	public void sua(SanBanh sbMoi) throws IllegalArgumentException{
		SanBanh sb = tim(sbMoi.getMaSB());

		if (sb == null)
			throw new IllegalArgumentException("Sân Banh không tồn tại");

		sb.setLoaiSan(sbMoi.getLoaiSan());
		sb.setChiNhanh(sbMoi.getChiNhanh());
	}

	public void sapXep(){
		dsSanBanh.sort(comp);
	}

	// ghi vao file

	public void ghiFile(String fileLoaiSan) throws IOException {
		try {
			FileWriter fileWriter = new FileWriter(fileLoaiSan);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			SanBanh sb = null;
			for (int i = 0; i < dsSanBanh.size(); i++) {
				sb = dsSanBanh.get(i);
				bufferedWriter.write(sb.getMaSB() + "_ " + sb.getMaLoaiSanNotThrow() + "_ " + sb.getMaCNNotThrow() + "\n");
			}
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("ghi file thất bại");
		}
	}

	// doc file
	public void docFile(String path) throws IOException {
		ArrayList<SanBanh> dsSanBanh = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(path);
			Scanner sc = new Scanner(fileReader); 
			String line = "";
			while (sc.hasNext() && (line = sc.nextLine()) != "") {
				String[] info = line.split("_"); 
				String maSB = info[0];
				String maLoaiSan = info[1];
				String maCN = info[2];
				
				LoaiSan loaiSan = mdLoaiSan.tim(maLoaiSan.trim());
				ChiNhanh chiNhanh = mdChiNhanh.tim(maCN.trim());

				dsSanBanh.add(new SanBanh(maSB, loaiSan, chiNhanh));
			}
			
			sc.close();
			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
			Model_SanBanh.dsSanBanh = dsSanBanh;
			throw new IOException("đọc file thất bại, danh sách sân banh sẽ nhận giá trị rỗng");
		}

		Model_SanBanh.dsSanBanh = dsSanBanh; 
	}

	public static final Comparator MASB_ASC = Comparator.comparing(SanBanh::getMaSB);
	public static final Comparator MALS_ASC = Comparator.comparing(SanBanh::getMaLoaiSanNotThrow);
	public static final Comparator MACN_ASC = Comparator.comparing(SanBanh::getMaCNNotThrow);
	public static final Comparator DONGIA_ASC= new Comparator<SanBanh>(){
		public int compare(SanBanh sb1, SanBanh sb2){
			if (sb1.getMaLoaiSanNotThrow().equals(""))
				return -1;
			if (sb2.getMaLoaiSanNotThrow().equals(""))
				return 1; 

			try{
				return Integer.compare(sb1.getDonGia(), sb2.getDonGia());
			}catch(Exception e){
				e.printStackTrace();
			}

			return -1;
		}
	};

	/*
		xu ly khi cac thanh phan bi xoa o noi khac
	*/
	ListenerXoaLoaiSan listenerXoaLoaiSan = new ListenerXoaLoaiSan() {
		public void xuLy(LoaiSan loaiSan) {
			for (SanBanh sb : getDSSanBanh())
				if (loaiSan.isEquals(sb.getLoaiSan()))
					sb.setLoaiSan(null);
		}
	};
	
	ListenerXoaCN listenerXoaCN = new ListenerXoaCN() {
		public void xuLy(ChiNhanh cn) {
			System.out.println(cn.getMaCN());
			for (SanBanh sb : getDSSanBanh()){
				System.out.print(" " + sb.getMaCNNotThrow());
				if (cn.getMaCN().equalsIgnoreCase(sb.getMaCNNotThrow()))
					sb.setChiNhanh(null);
			}
			System.out.println(); 
		}
	};

}
