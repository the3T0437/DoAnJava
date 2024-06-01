/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDon;
import java.awt.event.*;
import NhanVien.*;
import KhachHang.*;
import SanBanh.*;
import SanBanh.Model_SanBanh;
import java.io.IOException;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import HoaDon.HoaDon;
import doan.ListenerXoaKH;
import doan.ListenerXoaNV;
import doan.ListenerXoaSanBanh;
/**
 *
 * @author thanh
 */
public class ControllerHoaDon {
	Model_HoaDon mdHoaDon;
	Model_NhanVien mdNhanVien;
	Modal_KH mdKhachHang; 
	Model_SanBanh mdSanBanh; 
	FrmHoaDon form;

	public ControllerHoaDon(){
		mdHoaDon = Model_HoaDon.getInstance();
		mdNhanVien = Model_NhanVien.getInstance();
		mdKhachHang = Modal_KH.getInstance();
		mdSanBanh = Model_SanBanh.getInstance();

		form = new FrmHoaDon();
		form.getBtn_Them().addActionListener(themBtn);
		form.getBtn_Xoa().addActionListener(xoaBtn);
		form.getBtn_Sua().addActionListener(suaBtn);
		form.getBtn_Luu().addActionListener(ghiFileBtn);
		form.getBtn_Doc().addActionListener(docFileBtn);
		form.getTable_Output().addMouseListener(selectElement);
		form.getCbx_Sort().addItemListener(sortListener);

		mdKhachHang.addListener(listenerXoaKH);
		mdSanBanh.addListener(listenerXoaSB);
		mdNhanVien.addListener(listenerXoaNV);
	}

	public static void main(String[] args){
		ControllerHoaDon controller = new ControllerHoaDon();

		controller.form.setVisible(true);
	}

	public void setVisibleTrue(){
		this.form.setVisible(true);
	}

	private ActionListener themBtn = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			try{
				String maHD = form.getTf_MaHD().getText();
				String maKH = form.getTf_MaKH().getText(); 
				String maNV = form.getTf_MaNV().getText(); 
				String maSB = form.getTf_MaSB().getText();

				HoaDon hd = mdHoaDon.createHoaDon(maHD, maKH, maNV, maSB);
				mdHoaDon.them(hd);
				mdHoaDon.sapXep();
				hienThi();
				thongBao("thêm thành công");
			} catch(IllegalArgumentException e){
				baoLoi(e.getMessage());
			}
		}
	};

	private ActionListener xoaBtn = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			int[] indexes = form.getTable_Output().getSelectedRows();

			if (indexes.length == 0){
				baoLoi("không có hoá đơn nào được chọn để xoá");
				return;
			}

			int isDeleted = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá", "Thông báo",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (isDeleted == JOptionPane.NO_OPTION)
				return;

			for (int i = indexes.length -1; i >= 0; i--){
				String maHD = (String)form.getTable_Output().getValueAt(indexes[i], 0);
				mdHoaDon.xoa(maHD);
			}
			hienThi();
			thongBao("Xoá thành công");
		}
	};

	private ActionListener suaBtn = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			try{
				String maHD = form.getTf_MaHD().getText();
				String maNV = form.getTf_MaNV().getText(); 
				String maKH = form.getTf_MaKH().getText(); 
				String maSB = form.getTf_MaSB().getText();

				HoaDon hd = mdHoaDon.createHoaDon(maHD, maKH, maNV, maSB);
				mdHoaDon.sua(hd);
				mdHoaDon.sapXep();
				hienThi();
				thongBao("Sửa thành công");
			} catch(IllegalArgumentException e){
				baoLoi(e.getMessage());
			}
		}
	};

	private ActionListener ghiFileBtn = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			try{
				mdHoaDon.ghiFile(Model_HoaDon.PATH);
				thongBao("Ghi thành công");
			}catch(IOException e){
				baoLoi("Ghi thất bại: " + e.getMessage());
			}
		}
	};

	private ActionListener docFileBtn = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			try{
				mdHoaDon.docFile(Model_HoaDon.PATH);
				hienThi();
				thongBao("Đọc thành công");
			}catch(IOException e){
				baoLoi("Đọc thất bại: " + e.getMessage());
			}
		}
	};

	/**
	 * sap xep
	 */
	private ItemListener sortListener = new ItemListener(){
		public void itemStateChanged(ItemEvent evt){
			Comparator comp = null;
			int selectedIndex = form.getCbx_Sort().getSelectedIndex();
			switch(selectedIndex){
				case 0:
					comp = Model_HoaDon.MAHD_ASC;
					break;
				case 1:
					comp = Model_HoaDon.MAKH_ASC;
					break;
				case 2:
					comp = Model_HoaDon.TENKH_ASC;
					break;
				case 3:
					comp = Model_HoaDon.MANV_ASC;
					break;
				case 4:
					comp = Model_HoaDon.TENNV_ASC;
					break;
				case 5:
					comp = Model_HoaDon.MASB_ASC;
					break;
			}

			mdHoaDon.setComparator(comp);
			hienThi();
		}
	};

	private MouseListener selectElement = new MouseAdapter(){
		public void mouseClicked(MouseEvent evt){
			int[] selectedList = form.getTable_Output().getSelectedRows(); 
			if(selectedList.length != 1)
				return;
			int selectedRow = selectedList[0];

			String maHoaDon= (String)form.getTable_Output().getValueAt(selectedRow, 0);
			String maKhachHang= (String)form.getTable_Output().getValueAt(selectedRow, 1);
			String maNhanVien = (String)form.getTable_Output().getValueAt(selectedRow, 3);
			String maSanBanh = (String)form.getTable_Output().getValueAt(selectedRow, 5);

			form.getTf_MaHD().setText(maHoaDon);
			form.getTf_MaKH().setText(maKhachHang);
			form.getTf_MaNV().setText(maNhanVien);
			form.getTf_MaSB().setText(maSanBanh);
		}
	};

	private void hienThi(){
		DefaultTableModel dtm = (DefaultTableModel)form.getTable_Output().getModel();
		dtm.setRowCount(0);

		for (HoaDon hd : mdHoaDon.getList()){
			String maHD = hd.getMaHD(); 
			String maNV = hd.getMaNVNotThrow(); 
			String maKH = hd.getMaKHNotThrow(); 
			String maSB = hd.getMaSBNotThrow();

			String tenNV = hd.getTenNVNotThrow();
			String tenKH = hd.getTenKHNotThrow();

			dtm.addRow(new String[]{maHD, maKH, tenKH, maNV, tenNV, maSB});
		}

		form.getTable_Output().setModel(dtm);
	}
	
	public static void baoLoi(String loi){
		JOptionPane.showMessageDialog(null, "lỗi: " + loi, "Báo Lỗi", JOptionPane.ERROR_MESSAGE);
	}
	public static void thongBao(String tb){
		JOptionPane.showMessageDialog(null, tb, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
	}

	/*
		xu ly khi cac thanh phan bi xoa o noi khac
	*/

	private ListenerXoaNV listenerXoaNV= new ListenerXoaNV() {
		@Override
		public void xuLy(NhanVien nv) {
			hienThi();
		}
	};

	private ListenerXoaKH listenerXoaKH= new ListenerXoaKH() {
		@Override
		public void xuLy(KhachHang kh) {
			hienThi();
		}
	};

	private ListenerXoaSanBanh listenerXoaSB= new ListenerXoaSanBanh() {
		@Override
		public void xuLy(SanBanh sb) {
			hienThi();
		}
	};
}
