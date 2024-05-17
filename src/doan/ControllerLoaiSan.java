/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doan;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.Comparator;
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
		form.getTable_output().addMouseListener(this.getSelectedElemenetListener());
		form.getCbx_Sort().addItemListener(this.getSortListener());
	}

	public void hienThi(){
		mdLoaiSan.sapXep();
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
		JOptionPane.showMessageDialog(null, "lỗi: " + loi, "Báo Lỗi", JOptionPane.ERROR_MESSAGE);
	}
	public void thongBao(String tb){
		JOptionPane.showMessageDialog(null, tb, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
	}

	private ActionListener getThemListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try{
					String maLoaiSan = form.getTf_MaLoaiSan().getText(); 
					String donGiaStr = form.getTf_DonGia().getText(); 
					int donGia = Integer.parseInt(donGiaStr); 

					mdLoaiSan.them(new LoaiSan(maLoaiSan, donGia));

					thongBao("Thêm thành công");
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
				int[] selectedRow = form.getTable_output().getSelectedRows(); 
				if (selectedRow.length == 0){
					baoLoi("không có đối tượng được chọn để xoá");
					return;
				}

				int isDelete = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (isDelete == JOptionPane.NO_OPTION)
					return;

				for (int i = selectedRow.length - 1; i >= 0; i--){
					String maLoaiSan = (String)form.getTable_output().getValueAt(selectedRow[i], 0);

					mdLoaiSan.xoa(maLoaiSan);
				}

				thongBao("Xoá thành công");
				hienThi();
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
					thongBao("Lưu file thành công");
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
					thongBao("Đọc file thành công");
					hienThi();
				} catch (Exception e){
					baoLoi(e.getMessage());
				}
			}
		};
	}

	private MouseListener getSelectedElemenetListener(){
		return new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				int[] selectedList = form.getTable_output().getSelectedRows(); 
				if(selectedList.length != 1)
					return;
				int selectedRow = selectedList[0];

				String maLoaiSan = (String)form.getTable_output().getValueAt(selectedRow, 0);
				String donGiaStr = (String)form.getTable_output().getValueAt(selectedRow, 1);

				form.getTf_MaLoaiSan().setText(maLoaiSan);
				form.getTf_DonGia().setText(donGiaStr);
			}
		};
	}

	private ItemListener getSortListener(){
		return new ItemListener(){
			public void itemStateChanged(ItemEvent evt){
				Comparator comp = null;
				int selectedIndex = form.getCbx_Sort().getSelectedIndex();
				switch(selectedIndex){
					case 0:
						comp = ModelLoaiSan.getXapXepTheoMaLS();
						break;
					case 1:
						comp = ModelLoaiSan.getXapXepTheoDonGia();
						break;
				}

				mdLoaiSan.setComparator(comp);
				hienThi();
			}
		};
	}
}
