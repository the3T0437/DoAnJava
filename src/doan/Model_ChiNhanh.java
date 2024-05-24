/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author thanh
 */
public class Model_ChiNhanh {
	private List<ChiNhanh> listChiNhanh;
	private Comparator<ChiNhanh> thuTu;

	public Model_ChiNhanh(){
		listChiNhanh = new ArrayList<>();
		thuTu = Model_ChiNhanh.MaCN_ASC;
	}

	public ChiNhanh tim(ChiNhanh cn){
		return tim(cn.getMaCN());
	}

	public ChiNhanh tim(String maCN){
		for (int i = 0; i < listChiNhanh.size(); i++){
			if (listChiNhanh.get(i).getMaCN().equals(maCN))
				return listChiNhanh.get(i);
		}

		return null;
	}

	public ChiNhanh getChiNhanh(String maCN){
		ChiNhanh cn = tim(maCN); 
		return cn;
	}

	public List<ChiNhanh> getDSCN(){
		return listChiNhanh;
	}

	public void them(ChiNhanh cn){
		if (tim(cn) == null)
			throw new IllegalArgumentException("Mã chi nhánh đã tồn tại");

		listChiNhanh.add(cn);
		sapXep();
	}

	public void xoa(String maCN){
		if (tim(maCN) == null)
			throw new IllegalArgumentException("Mã chi nhánh không tồn tại");

		listChiNhanh.remove(tim(maCN));
	}

	public void xoa(ChiNhanh cn){
		xoa(cn.getMaCN());
	}

	public void sua(ChiNhanh cn){
		if (tim(cn) == null)
			throw new IllegalArgumentException("Lỗi: Mã chi nhánh không tồn tại");

		tim(cn).setNewValue(cn);
	}

	public void sapXep(){
		listChiNhanh.sort(thuTu);
	}

	public void sapXep(Comparator<ChiNhanh> comparator){
		thuTu = comparator;
		sapXep();
	}

	public void doc(String path) throws FileNotFoundException, IllegalArgumentException, IOException{
		FileInputStream inputFile;
		try{
			inputFile = new FileInputStream(path);
		}catch(FileNotFoundException e){
			throw new FileNotFoundException("lỗi: file input không tồn tại");
		}
		Scanner sc = new Scanner(inputFile);

		listChiNhanh = new ArrayList<>();
		String line = null;
		while (sc.hasNext() && (line = sc.nextLine()) != null){
			String[] strArr = line.split("_"); 
			String maCN = strArr[0]; 
			String maQL = strArr[1]; 
			String tenCN = strArr[2]; 
			String diaChi = strArr[3]; 
			String soDT = strArr[4];

			listChiNhanh.add(new ChiNhanh(maCN, maQL, tenCN, diaChi, soDT));
		}
		inputFile.close();

		sapXep();
	}

	public void viet(String path) throws FileNotFoundException, IOException{
		FileWriter outputFile = null;
		try{
			outputFile = new FileWriter(path); 
		}
		catch(IOException e){
			throw new FileNotFoundException("lỗi: file input không tồn tại");
		}

		for (ChiNhanh cn : listChiNhanh){
			outputFile.write(cn + "\n");
		}
		outputFile.close();
	}

	//comparetor
	//Ma chi nhanh
	public static final Comparator<ChiNhanh> MaCN_ASC = (ChiNhanh cn1, ChiNhanh cn2) ->{
		return cn1.getMaCN().compareTo(cn2.getMaCN());
	};
	public static final Comparator<ChiNhanh> MaCN_DESC= (ChiNhanh cn1, ChiNhanh cn2) ->{
		return -1 * MaCN_ASC.compare(cn1, cn2);
	};
	
	//Ma Quan Ly 
	public static final Comparator<ChiNhanh> MaQL_ASC = (ChiNhanh cn1, ChiNhanh cn2) ->{
		return cn1.getMaQL().compareTo(cn2.getMaQL());
	};
	public static final Comparator<ChiNhanh> MaQL_DESC= (ChiNhanh cn1, ChiNhanh cn2) ->{
		return -1 * MaQL_ASC.compare(cn1, cn2);
	};

	//Ten Chi Nhanh
	public static final Comparator<ChiNhanh> TenCN_ASC = (ChiNhanh cn1, ChiNhanh cn2) ->{
		return cn1.getTenCN().compareTo(cn2.getTenCN());
	};
	public static final Comparator<ChiNhanh> TenCN_DESC= (ChiNhanh cn1, ChiNhanh cn2) ->{
		return -1 * TenCN_ASC.compare(cn1, cn2);
	};

	//Dia Chi
	public static final Comparator<ChiNhanh> DiaChi_ASC = (ChiNhanh cn1, ChiNhanh cn2) ->{
		return cn1.getDiaChi().compareTo(cn2.getDiaChi());
	};
	public static final Comparator<ChiNhanh> DiaChi_DESC= (ChiNhanh cn1, ChiNhanh cn2) ->{
		return -1 * DiaChi_ASC.compare(cn1, cn2);
	};

	//So Dien Thoai
	public static final Comparator<ChiNhanh> SoDT_ASC = (ChiNhanh cn1, ChiNhanh cn2) ->{
		return cn1.getSoDT().compareTo(cn2.getSoDT());
	};
	public static final Comparator<ChiNhanh> SoDT_DESC= (ChiNhanh cn1, ChiNhanh cn2) ->{
		return -1 * SoDT_ASC.compare(cn1, cn2);
	};


}
