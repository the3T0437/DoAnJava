/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doan;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author thanh
 */
public class ControllerLoaiSan {
	private final String path = "danhSach_LoaiSan.txt";
	private ModelLoaiSan mdLoaiSan;
	private frm_LoaiSan form; 

	public static void main(String[] args){
		ControllerLoaiSan ctlLoaiSan = new ControllerLoaiSan(); 
		ctlLoaiSan.form.setVisible(true);
	}

	public ControllerLoaiSan(){
		mdLoaiSan = new ModelLoaiSan(); 
		form = new frm_LoaiSan();

		form.getBtn_them().addActionListener(this.getThemListener());
		form.getBtn_xoa().addActionListener(this.getXoaListener());
		form.getBtn_sua().addActionListener(this.getSuaListener());
		form.getBtn_luu().addActionListener(this.getLuuListener());
		form.getBtn_doc().addActionListener(this.getDocListener());
	}

	public void hienThi(){
		List<LoaiSan> list = mdLoaiSan.getDS();

		int countCol = form.getTable_output().getColumnCount();
		String[] titleCol = new String[countCol];
		for (int i = 0; i < countCol; i++){
			titleCol[i] = form.getTable_output().getColumnName(i);
		}

		DefaultTableModel dtm = new DefaultTableModel(titleCol, 0); 
		for(LoaiSan ls : list){
			String maLoaiSan = ls.getMaLoaiSan(); 
			int donGia = ls.getDonGia(); 
			String donGiaStr = Integer.toString(donGia);

			dtm.addRow(new String[]{maLoaiSan, donGiaStr});
		}

		form.getTable_output().setModel(dtm);
	}

	public void baoLoi(String loi){
		form.getLbl_thongBao().setText("lỗi: " + loi);
	}

	private ActionListener getThemListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try{
					String maLoaiSan = form.getTf_MaLoaiSan().getText(); 
					String donGiaStr = form.getTf_DonGia().getText(); 
					int donGia = Integer.parseInt(donGiaStr); 

					mdLoaiSan.them(new LoaiSan(maLoaiSan, donGia));

					hienThi();
				}catch(NumberFormatException e){
					baoLoi("số không hợp lệ");
				}catch(IllegalArgumentException e){
					baoLoi(e.getMessage());
				}			}
		};
	}

	private ActionListener getXoaListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try{
					String maLoaiSan = form.getTf_MaLoaiSan().getText(); 
					
					if (mdLoaiSan.tim(maLoaiSan) == null){
						baoLoi("loại sân không tồn tại");
						return;
					}

					int isDelete = JOptionPane.showConfirmDialog(null, "bạn có chắc muốn xoá?", "Thông Báo", JOptionPane.YES_NO_OPTION);
					if (isDelete == JOptionPane.NO_OPTION)
						return;

					mdLoaiSan.xoa(maLoaiSan);
					hienThi();
						
				}catch(NumberFormatException e){
					baoLoi("số không hợp lệ");
				}catch(IllegalArgumentException e){
					baoLoi(e.getMessage()); 
				}
			}
		};
	}

	private ActionListener getSuaListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try{
					String maLoaiSan = form.getTf_MaLoaiSan().getText(); 
					String donGiaStr = form.getTf_DonGia().getText(); 
					int donGia = Integer.parseInt(donGiaStr); 

					LoaiSan lsMoi = new LoaiSan(maLoaiSan, donGia); 
					mdLoaiSan.sua(lsMoi);

					hienThi();
					JOptionPane.showMessageDialog(null, "sửa thành công");
				}catch(NumberFormatException e){
					baoLoi("số không hợp lệ");
				}catch(IllegalArgumentException e){
					baoLoi(e.getMessage()); 
				}
			}
		};
	}

	private ActionListener getLuuListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try{
					mdLoaiSan.ghiFile(path);
					JOptionPane.showMessageDialog(null, "lưu file thành công");
				}catch(Exception e){
					baoLoi(e.getMessage());
				}
			}
		};
	}

	private ActionListener getDocListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try{
					mdLoaiSan.docFile(path);
					JOptionPane.showMessageDialog(null, "đọc file thành công");
					hienThi();
				} catch (Exception e){
					baoLoi(e.getMessage());
				}
			}
		};
	}
}
