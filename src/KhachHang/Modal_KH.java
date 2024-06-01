/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import doan.ListenerXoaKH;

/**
 *
 * @author VINH
 */
public class Modal_KH {
    public static final String PATH = "DanhSach_KhachHang.txt";
    private static  List<KhachHang>danhSachKhachHang = null;
    public static Comparator comp = sapXepKhachHangTheoMaKH();

    /*
    	Dam bao chi co 1 Model_KH
    */
    private Modal_KH(){}
    private static Modal_KH mdKhachHang = new Modal_KH();
    public static Modal_KH getInstance(){
	    return mdKhachHang; 
    }
    
    	/*
	 * thong bao den cac class khac khi xoa
	 */
	private static List<ListenerXoaKH> listenerXoaKH= new ArrayList<>();
	public void addListener(ListenerXoaKH listener){
		listenerXoaKH.add(listener);
	}
	public void removeListener(ListenerXoaKH listener){
		listenerXoaKH.remove(listener);
	}
	public void thongBaoKhiXoa(KhachHang kh){
		for(ListenerXoaKH listener : listenerXoaKH){
			listener.xuLy(kh);
		}
	}
    
    public List<KhachHang> getDS()
    {
        if(danhSachKhachHang != null)
        {
            return danhSachKhachHang;
        }
        try
        {
            getInstance().DocFile(PATH);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
	}
        return danhSachKhachHang;
    }
    
     public void clearAll(){
		while (getDS().size() != 0)
			danhSachKhachHang.remove(0);
	}
    // in danh sach khach hang
       public void inDanhSachKhachHang()
    {
        for(KhachHang kh: getDS())
        {
            System.out.println(kh.ToString());
        }
    }
   
    // them khach hang
    public boolean ThemKhachHang(KhachHang khachHang)
    {
        
        for(KhachHang kh: getDS())
        {
            if(kh.getMaKhachHang().equals(khachHang.getMaKhachHang()))
            {
                return  false;//Trùng mã phòng, ko thêm được
            }
        }
        getDS().add(khachHang);
        return true;
    }
    
    // tim khach hang
    public KhachHang timKhachHang(String maKhachHang)
    {
        for(KhachHang kh: getDS())
        {
            if(kh.getMaKhachHang().trim().equals(maKhachHang.trim()))
            {
                return kh;
            }
        }
        return null;
    }
    // xoa khach hang
     public boolean xoaKhachHang(String maKhachHang)
    {
        KhachHang khachhang=timKhachHang(maKhachHang);
        if(khachhang!=null)
        {
            getDS().remove(khachhang);
	    thongBaoKhiXoa(khachhang);
            return  true;
        }
        return  false;
    }
    
     // cap nhat khach hang
      public void suaKhachHang(KhachHang khMoi) throws IllegalArgumentException
    {
        KhachHang kh=timKhachHang(khMoi.getMaKhachHang());
        if(kh==null)
        {
             throw new IllegalArgumentException("không tìm thấy mã khách hàng");
        }          
            kh.setHoTen(khMoi.getHoTen());
            kh.setSoDienThoai(khMoi.getSoDienThoai());
    }
     // sap xep tang dan theo ma khach hang
      public static Comparator sapXepKhachHangTheoMaKH(){
        return Comparator.comparing(KhachHang::getMaKhachHang);
      }
      // sắp xếp theo họ tên
      public static Comparator sapXepKhachHangTheoTenKH(){
        return Comparator.comparing(KhachHang::getHoTen);
      }
      public void setComparator(Comparator comp){
		this.comp = comp;
	}
      public void sapXep(){
		Collections.sort(this.getDS(), comp);
	}
      // luu danh sach khach hang vao file
      public void ghiFile(String fileKH) throws IOException{
          try {
			FileWriter fileWriter = new FileWriter(fileKH);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			KhachHang khachHang = null;
			for (int i = 0; i < this.getDS().size(); i++) {
				khachHang = this.getDS().get(i);
				bufferedWriter.write(khachHang.getMaKhachHang() + "_" + khachHang.getHoTen()+ "_" + khachHang.getSoDienThoai() + "\n");
			}
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("ghi file thất bại");
		}
      }
      // docjdj file        
      public void DocFile(String fileKH) throws IOException{
            List<KhachHang> DSKH = new ArrayList<>();
            try (FileReader fileReader = new FileReader(fileKH);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) 
            {
            String line;
                while ((line = bufferedReader.readLine()) != null) 
                {
                    String[] khInfo = line.split("_");
                    DSKH.add(new KhachHang(khInfo[0], khInfo[1], khInfo[2]));
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
                throw new IOException("đọc file thất bại");
            }
            Modal_KH.danhSachKhachHang = DSKH;
        }
}
