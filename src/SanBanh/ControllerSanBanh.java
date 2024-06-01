/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SanBanh;
import ChiNhanh.*;
import LoaiSan.LoaiSan;
import SanBanh.SanBanh;
import SanBanh.Model_SanBanh;
import SanBanh.Frm_SanBanh;
import LoaiSan.Model_LoaiSan;
import doan.LackingException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.util.Comparator;

import doan.ListenerXoaLoaiSan;
import doan.ListenerXoaCN;

//test
import LoaiSan.ControllerLoaiSan;
/**
 *
 * @author thanh
 */
public class ControllerSanBanh {
	private Model_SanBanh mdSanBanh; 
	private Model_LoaiSan mdLoaiSan; 
	private Modal_CN mdChiNhanh; 
	private Frm_SanBanh form;

	public static void main(String[] args){
		ControllerSanBanh controllerSB = new ControllerSanBanh();
				//controllerSB.form.getCbx_Sort().addItemListener();

		controllerSB.form.setVisible(true);
		ControllerLoaiSan controllerLS = new ControllerLoaiSan();
		controllerLS.visibleTrue();
	}

	public void setVisibleTrue(){
		this.form.setVisible(true);
	}
	
	public ControllerSanBanh(){
		mdLoaiSan = Model_LoaiSan.getInstance();
		mdSanBanh = Model_SanBanh.getInstance();
		mdChiNhanh = Modal_CN.getInstance();
		
		form = new Frm_SanBanh();

		form.getBtn_Them().addActionListener(themAction);
		form.getBtn_Xoa().addActionListener(xoaAction);
		form.getBtn_Sua().addActionListener(suaAction);
		form.getBtn_Luu().addActionListener(luuAction);
		form.getBtn_Doc().addActionListener(docAction);
		form.getTable_Output().addMouseListener(getSelectedElemenetListener);
		form.getCbx_Sort().addItemListener(sortListener);

		mdLoaiSan.addListener(listenerXoaLoaiSan);
		mdChiNhanh.addListener(listenerXoaCN);
	}

	public void hienThi(){
		//get titles
		int cols = form.getTable_Output().getColumnCount(); 
		String[] titles = new String[cols];

		for (int i = 0; i < cols; i++){
			titles[i] = form.getTable_Output().getColumnName(i); 
		}

		DefaultTableModel dtm = new DefaultTableModel(titles, 0); 

		//them cac phan tu vao model bang
		for(SanBanh sb : mdSanBanh.getDSSanBanh()){
			String maSB = sb.getMaSB();
			String maLS = sb.getMaLoaiSanNotThrow(); 
			String donGia;
			try{
				donGia = Integer.toString(sb.getDonGia());
			}catch(LackingException e){
				donGia = "null";
			}
			String maCN = sb.getMaCNNotThrow();

			dtm.addRow(new String[]{maSB, maLS, donGia, maCN});
		}

		form.getTable_Output().setModel(dtm);
	}

	private ActionListener themAction = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			//lay du lieu
			String maSB = form.getTf_MaSB().getText();
			String maLS = form.getTf_MaLoaiSan().getText();
			String maCN = form.getTf_MaCN().getText();

			try{
				SanBanh sb = mdSanBanh.createSanBanh(maSB, maLS, maCN);
				mdSanBanh.them(sb);
				mdSanBanh.sapXep();

				thongBao("Thêm thành công");
				hienThi();
			}
			catch(Exception e){
				baoLoi(e.getMessage());
			}
		}
	};

	private ActionListener xoaAction = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
				int[] selectedRow = form.getTable_Output().getSelectedRows(); 
				if (selectedRow.length == 0){
					baoLoi("không có đối tượng được chọn để xoá");
					return;
				}

				int isDelete = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (isDelete == JOptionPane.NO_OPTION)
					return;

				for (int i = selectedRow.length - 1; i >= 0; i--){
					String maSB= (String)form.getTable_Output().getValueAt(selectedRow[i], 0);

					mdSanBanh.xoa(maSB);
				}

				thongBao("Xoá thành công");
				hienThi();
		}
	};

	private ActionListener suaAction = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
				try{
					String maSB = form.getTf_MaSB().getText();
					String maLoaiSan = form.getTf_MaLoaiSan().getText(); 
					String maCN = form.getTf_MaCN().getText();

					SanBanh sbMoi = Model_SanBanh.createSanBanh(maSB, maLoaiSan, maCN);
					mdSanBanh.sua(sbMoi);
					mdSanBanh.sapXep();
					hienThi();
					thongBao("sửa thành công");
				}catch(IllegalArgumentException e){
					baoLoi(e.getMessage()); 
				}
		}
	};

	private ActionListener luuAction = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
				try{
					mdSanBanh.ghiFile(Model_SanBanh.PATH);
					thongBao("Lưu file thành công");
				}catch(Exception e){
					baoLoi(e.getMessage());
				}
		}
	};
	
	private ActionListener docAction = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
				try{
					mdSanBanh.docFile(Model_SanBanh.PATH);
					thongBao("Đọc file thành công");
					hienThi();
				} catch (Exception e){
					baoLoi(e.getMessage());
				}
		}
	};

	private MouseListener getSelectedElemenetListener = new MouseAdapter(){
		public void mouseClicked(MouseEvent evt){
				int[] selectedList = form.getTable_Output().getSelectedRows(); 
				if(selectedList.length != 1)
					return;
				int selectedRow = selectedList[0];

				String maSB = (String)form.getTable_Output().getValueAt(selectedRow, 0);
				String maLoaiSan = (String)form.getTable_Output().getValueAt(selectedRow, 1);
				String donGiaStr = (String)form.getTable_Output().getValueAt(selectedRow, 2);
				String maCN = (String)form.getTable_Output().getValueAt(selectedRow, 3);

				form.getTf_MaSB().setText(maSB);
				form.getTf_MaCN().setText(maCN);
				form.getTf_MaLoaiSan().setText(maLoaiSan);
				
		}
	};

	private ItemListener sortListener = new ItemListener(){
		public void itemStateChanged(ItemEvent evt){
			int index = form.getCbx_Sort().getSelectedIndex(); 

			switch(index){
				case 0:
					mdSanBanh.setComparator(Model_SanBanh.MASB_ASC);
					break;
				case 1:
					mdSanBanh.setComparator(Model_SanBanh.MALS_ASC);
					break;
				case 2:
					mdSanBanh.setComparator(Model_SanBanh.DONGIA_ASC);
					break;
				case 3:
					mdSanBanh.setComparator(Model_SanBanh.MACN_ASC);
					break;
			}

			mdSanBanh.sapXep();
			hienThi();
		}
	};

	

	public void baoLoi(String loi){
		JOptionPane.showMessageDialog(null, "lỗi: " + loi, "Báo Lỗi", JOptionPane.ERROR_MESSAGE);
	}
	public void thongBao(String tb){
		JOptionPane.showMessageDialog(null, tb, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
	}

	/*
	 * xu ly khi xoa loai san
	 */

	ListenerXoaLoaiSan listenerXoaLoaiSan = new ListenerXoaLoaiSan() {
		@Override
		public void xuLy(LoaiSan loaiSan) {
			hienThi();
		}
	};

	ListenerXoaCN listenerXoaCN = new ListenerXoaCN() {
		@Override
		public void xuLy(ChiNhanh cn) {
			hienThi();
		}
	};
}
