/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VINH
 */
public class ControllerKhachHang {
    private final Modal_KH md_KhachHang;
    private final frn_KhachHang Form_KH;
     
        public ControllerKhachHang(){
            md_KhachHang = Modal_KH.getInstance();
            Form_KH = new frn_KhachHang();
            Form_KH.getBtn_them().addActionListener(this.getThem());
            Form_KH.getBtn_luu().addActionListener(this.getLuu());
            Form_KH.getBtn_xoa().addActionListener(this.getXoa());
            Form_KH.getBtn_sua().addActionListener(this.getSua());
            Form_KH.getBtn_doc().addActionListener(this.getDoc());
            Form_KH.getTablt_output().addMouseListener(this.getSelect());
            Form_KH.getcBx_Sort().addItemListener(this.getSort());
        }
    
	public void setVisibleTrue(){
		this.Form_KH.setVisible(true);
	}

        public static void main(String[] args){
        ControllerKhachHang controllerKhachHang = new ControllerKhachHang();
        controllerKhachHang.Form_KH.setVisible(true);
        }
    
     
     
        public void hienThi(){
         md_KhachHang.sapXep();
         List<KhachHang>list = md_KhachHang.getDS();
         int countCol = Form_KH.getTablt_output().getColumnCount();
		String[] t = new String[countCol];
		for (int i = 0; i < countCol; i++){
			t[i] = Form_KH.getTablt_output().getColumnName(i);
		}

		DefaultTableModel dtm = new DefaultTableModel(t, 0); 
		for(KhachHang kh : list){
			String maKH = kh.getMaKhachHang(); 
			String HoTen = kh.getHoTen(); 
			String SoDT = kh.getSoDienThoai();

			dtm.addRow(new String[]{maKH, HoTen,SoDT});
		}

		Form_KH.getTablt_output().setModel(dtm);
        }
        
        private ActionListener getThem(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent evt)
                        {
				try
                                {
					String maKH = Form_KH.getTF_MaKH().getText(); 
					String HoTen = Form_KH.getTF_HoTen().getText(); 
					String SoDT = Form_KH.getTF_SoDT().getText();
                                        
                                        if(maKH.isEmpty()){
                                            baoLoi("Mã khách hàng không được để trống");
                                            return;
                                        }
                                        if(HoTen.isEmpty()){
                                            baoLoi("Họ Tên khách hàng không được để trống");
                                            return;
                                        }
                                        if(SoDT.isEmpty() || SoDT.length() != 11){
                                            baoLoi("Số điện thoại khách hàng không được để trống và phải có 11 số");
                                            return;
                                        }
                                        for (int i = 0; i < SoDT.length(); i++) {
                                            if (!Character.isDigit(SoDT.charAt(i))) {
                                                throw new IllegalArgumentException("Số điện thoại chỉ được chứa chữ số.");
                                            }
                                        }
					boolean isAdded = md_KhachHang.ThemKhachHang(new KhachHang(maKH,HoTen,SoDT));
                                        if(isAdded){
                                            thongBao("Thêm thành công");
                                            hienThi();
                                            resetTF();
                                        } 
                                        else 
                                        {
                                            baoLoi("Không thể thêm khách hàng vào bảng");
                                        }
				}catch(NumberFormatException e){
					baoLoi("không thêm vào được");
				}
                                catch(IllegalArgumentException e)
                                {
					baoLoi(e.getMessage());
				}			
                        }
		};
	}
        
        public void resetTF(){
            Form_KH.getTF_MaKH().setText("");
            Form_KH.getTF_HoTen().setText("");
            Form_KH.getTF_SoDT().setText("");
        }
	private ActionListener getXoa(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				int[] selectedRow = Form_KH.getTablt_output().getSelectedRows(); 
				if (selectedRow.length == 0){
					baoLoi("không có đối tượng được chọn để xoá");
					return;
				}

				int isDelete = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (isDelete == JOptionPane.NO_OPTION)
					return;

				for (int i = selectedRow.length - 1; i >= 0; i--){
					String maKH = (String)Form_KH.getTablt_output().getValueAt(selectedRow[i], 0);

					md_KhachHang.xoaKhachHang(maKH);
				}

				thongBao("Xoá thành công");
				hienThi();
			}
		};
	}

	private ActionListener getSua(){
        return new ActionListener(){
            public void actionPerformed(ActionEvent evt){
            try{
                String maKH = Form_KH.getTF_MaKH().getText(); 
                String HoTen = Form_KH.getTF_HoTen().getText(); 
                String SoDT = Form_KH.getTF_SoDT().getText();	
                KhachHang kh = new KhachHang(maKH,HoTen,SoDT);
                md_KhachHang.suaKhachHang(kh);
                hienThi();
                thongBao("sửa thành công");
            } catch (IllegalArgumentException ex) {
                baoLoi(ex.getMessage());
            } 
        }
    };
}

	private ActionListener getLuu(){
		return (ActionEvent evt) -> {
                    try{
                        md_KhachHang.ghiFile(Modal_KH.PATH);
                        thongBao("Lưu file thành công");
                    }catch(Exception e){
                        baoLoi(e.getMessage());
                    }
                };
	}
        

	private ActionListener getDoc(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try{
					md_KhachHang.DocFile(Modal_KH.PATH);
					thongBao("Đọc file thành công");
					hienThi();
				} catch (Exception e){
					baoLoi(e.getMessage());
				}
			}
		};
	}
        
        private MouseListener getSelect(){
		return new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				int[] selectedList = Form_KH.getTablt_output().getSelectedRows(); 
				if(selectedList.length != 1)
					return;
				int selectedRow = selectedList[0];

				String MaKH = (String)Form_KH.getTablt_output().getValueAt(selectedRow, 0);
				String HoTen = (String)Form_KH.getTablt_output().getValueAt(selectedRow, 1);
                                String SoDT = (String)Form_KH.getTablt_output().getValueAt(selectedRow, 2);
				Form_KH.getTF_MaKH().setText(MaKH);
				Form_KH.getTF_HoTen().setText(HoTen);
                                Form_KH.getTF_SoDT().setText(SoDT);
			}
		};
	}

	private ItemListener getSort(){
            return new ItemListener(){
                public void itemStateChanged(ItemEvent evt){
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        Comparator<KhachHang> comp = null;
                        int selectedIndex = Form_KH.getcBx_Sort().getSelectedIndex();
                        switch(selectedIndex){
                            case 0:
                                comp = Modal_KH.sapXepKhachHangTheoMaKH();
                                break;
                            case 1:
                                comp = Modal_KH.sapXepKhachHangTheoTenKH();
                                break;
                        }

                        md_KhachHang.setComparator(comp);
                        hienThi();
                    }
                }
            };
        }
        public void baoLoi(String loi){
		JOptionPane.showMessageDialog(null, "lỗi: " + loi, "Báo Lỗi", JOptionPane.ERROR_MESSAGE);
	}
	public void thongBao(String tb){
		JOptionPane.showMessageDialog(null, tb, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
	}
}
