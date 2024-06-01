/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thanh
 */
import SanBanh.ControllerSanBanh;
import LoaiSan.ControllerLoaiSan;
import HoaDon.ControllerHoaDon;
import NhanVien.ControllerNhanVien; 
import KhachHang.ControllerKhachHang;
import ChiNhanh.ControllerChiNhanh;
import java.awt.event.*;

public class MainController {
	ControllerHoaDon ctl_HoaDon;
	ControllerLoaiSan ctl_LoaiSan; 
	ControllerSanBanh ctl_SanBanh;
	ControllerNhanVien ctl_NhanVien;
	ControllerKhachHang ctl_KhachHang; 
	ControllerChiNhanh ctl_ChiNhanh; 

	Frm_Main form;

	public static void main(String[] args){
		MainController ctl_Main = new MainController(); 
		ctl_Main.form.setVisible(true);
	}
	
	public MainController(){
		ctl_HoaDon = new ControllerHoaDon();
		ctl_LoaiSan = new ControllerLoaiSan();
		ctl_SanBanh = new ControllerSanBanh();
		ctl_NhanVien = new ControllerNhanVien();
		ctl_KhachHang = new ControllerKhachHang();
		ctl_ChiNhanh = new ControllerChiNhanh();

		form = new Frm_Main();
		form.getBtn_hoaDon().addActionListener(openHoaDon);
		form.getBtn_SanBanh().addActionListener(openSanBanh);
		form.getBtn_LoaiSan().addActionListener(openLoaiSan);
		form.getBtn_NhanVien().addActionListener(openNhanVien);
		form.getBtn_KhachHang().addActionListener(openKhachHang);
		form.getBtn_ChiNhanh().addActionListener(openChiNhanh);
	}

	private ActionListener openHoaDon = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			ctl_HoaDon.setVisibleTrue();
		}
	};

	private ActionListener openSanBanh = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			ctl_SanBanh.setVisibleTrue();
		}
	};

	private ActionListener openLoaiSan = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			ctl_LoaiSan.setVisibleTrue();
		}
	};

	private ActionListener openKhachHang= new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			ctl_KhachHang.setVisibleTrue();
		}
	};

	private ActionListener openNhanVien= new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			ctl_NhanVien.setVisibleTrue();
		}
	};
	
	private ActionListener openChiNhanh= new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			ctl_ChiNhanh.setVisibleTrue();
		}
	};
}
