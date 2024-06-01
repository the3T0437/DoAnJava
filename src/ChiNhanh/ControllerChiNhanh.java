/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChiNhanh;

import NhanVien.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.*;

import doan.ListenerXoaNV;
import doan.ListenerXoaCN;
/**
 *
 * @author thanh
 */
public class ControllerChiNhanh {

    private Modal_CN mdChiNhanh ;
    private frm_ChiNhanh form ;
    private Model_NhanVien mdNV ;

	public void setVisibleTrue(){
		this.form.setVisible(true);
	}

    public static void main(String[] args) {
        ControllerChiNhanh controllercn = new ControllerChiNhanh();
        controllercn.form.setVisible(true);
    }

    public ControllerChiNhanh() {
        mdChiNhanh = Modal_CN.getInstance();
        mdNV = Model_NhanVien.getInstance();
        form = new frm_ChiNhanh();

        form.getBtn_Them().addActionListener(themAction);
        form.getBtn_Xoa().addActionListener(xoaAction);
        form.getBtn_Sua().addActionListener(suaAction);
        form.getBtn_Luu().addActionListener(luuAction);
        form.getBtn_Doc().addActionListener(docAction);
        form.getTable_Output().addMouseListener(getSelectedElemenetListener);
        form.getCbx_Sort().addItemListener(sortListener);

	mdNV.addListener(listenerXoaNV);
	mdChiNhanh.addListener(listenerXoaCN);
    }

    private ActionListener themAction = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            //lay du lieu
            try 
            {
                String maCN = form.getTf_MaCN().getText();
                String TenCN = form.getTf_TenCN().getText();
                String DiaChi = form.getTf_DC().getText();
                String SoDT = form.getTf_SoDT().getText();
                String MaQL = form.getTf_QL().getText();

                if (TenCN.trim().equals("")) {
                    baoLoi("Không có tên chi nhánh");
                    return;
                }
                if(DiaChi.trim().equals("")){
                    baoLoi("Không có địa chỉ");
                    return;
                }
                if (SoDT == null || SoDT.length() != 10 || SoDT.charAt(0) != '0') {
                    baoLoi("Số điện thoại phải bắt đầu bằng số 0 và có độ dài là 10 chữ số.");
                    return;
                }
                for (char c : SoDT.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        baoLoi("Số điện thoại chỉ được chứa chữ số.");
                        return;
                    }
                }
                
                ChiNhanh cn = Modal_CN.createChiNhanh(maCN, TenCN, DiaChi, SoDT, MaQL);
                mdChiNhanh.them(cn);
                thongBao("Thêm thành công");
                hienThi();
                reset();
            } 
            catch (Exception e) 
            {
                baoLoi(e.getMessage());
            }
        }
    };

    public void reset() {
        form.getTf_MaCN().setText("");
        form.getTf_TenCN().setText("");
        form.getTf_DC().setText("");
        form.getTf_SoDT().setText("");
        form.getTf_QL().setText("");
    }

    public void hienThi() {
        //get titles
        int cols = form.getTable_Output().getColumnCount();
        String[] titles = new String[cols];

        for (int i = 0; i < cols; i++) {
            titles[i] = form.getTable_Output().getColumnName(i);
        }

        DefaultTableModel dtm = new DefaultTableModel(titles, 0);

        //them cac phan tu vao model bang
        for (ChiNhanh cn : mdChiNhanh.getDSCN()) {
            String maCN = cn.getMaCN();
            String MaQL = cn.getMaQLNotThrow();
            String TenCN = cn.getTenCN();
            String DiaChi = cn.getDiaChi();
            String SoDT = cn.getSoDT();

            dtm.addRow(new String[]{maCN, TenCN, DiaChi, SoDT, MaQL});
        }

        form.getTable_Output().setModel(dtm);
    }

    private ActionListener xoaAction = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            int[] selectedRow = form.getTable_Output().getSelectedRows();
            if (selectedRow.length == 0) {
                baoLoi("không có đối tượng được chọn để xoá");
                return;
            }

            int isDelete = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (isDelete == JOptionPane.NO_OPTION) {
                return;
            }

            for (int i = selectedRow.length - 1; i >= 0; i--) {
                String maCN = (String) form.getTable_Output().getValueAt(selectedRow[i], 0);

                mdChiNhanh.xoa(maCN);
            }

            thongBao("Xoá thành công");
            hienThi();
        }
    };

    private ActionListener suaAction = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            try {
                String maCN = form.getTf_MaCN().getText();
                String TenCN = form.getTf_TenCN().getText();
                String DiaChi = form.getTf_DC().getText();
                String SoDT = form.getTf_SoDT().getText();
                String MaQL = form.getTf_QL().getText();
                ChiNhanh CNMoi = Modal_CN.createChiNhanh(maCN, TenCN, DiaChi, SoDT, MaQL);
                mdChiNhanh.sua(CNMoi);
                mdChiNhanh.sapXep();
                hienThi();
                thongBao("sửa thành công");
            } catch (IllegalArgumentException e) {
                baoLoi(e.getMessage());
            }
        }
    };

    private ActionListener luuAction = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            try {
                mdChiNhanh.ghiFile(Modal_CN.PATH);
                thongBao("Lưu file thành công");
            } catch (Exception e) {
                baoLoi(e.getMessage());
            }
        }
    };

    private ActionListener docAction = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            try {
                mdChiNhanh.docFile(Modal_CN.PATH);
                thongBao("Đọc file thành công");
                hienThi();
            } catch (Exception e) {
                e.printStackTrace();
                baoLoi(e.getMessage());
            }
        }
    };

    private MouseListener getSelectedElemenetListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            int[] selectedList = form.getTable_Output().getSelectedRows();
            if (selectedList.length != 1) {
                return;
            }
            int selectedRow = selectedList[0];

            String MaCN = (String) form.getTable_Output().getValueAt(selectedRow, 0);
            String TenCN = (String) form.getTable_Output().getValueAt(selectedRow, 1);
            String DiaChi = (String) form.getTable_Output().getValueAt(selectedRow, 2);
            String SoDT = (String) form.getTable_Output().getValueAt(selectedRow, 3);
            String MaQL = (String) form.getTable_Output().getValueAt(selectedRow, 4);

            form.getTf_MaCN().setText(MaCN);
            form.getTf_TenCN().setText(TenCN);
            form.getTf_DC().setText(DiaChi);
            form.getTf_SoDT().setText(SoDT);
            form.getTf_QL().setText(MaQL);

        }
    };

    private final ItemListener sortListener = new ItemListener() {
        public void itemStateChanged(ItemEvent evt) {
            int index = form.getCbx_Sort().getSelectedIndex();

            switch (index) {
                case 0:
                    mdChiNhanh.setComparator(Modal_CN.MACN_SapXepMaCN);
                    break;
                case 1:
                    mdChiNhanh.setComparator(Modal_CN.MACN_SapXepTenCN);
                    break;
            }

            mdChiNhanh.sapXep();
            hienThi();
        }
    };

    public void baoLoi(String loi) {
        JOptionPane.showMessageDialog(null, "lỗi: " + loi, "Báo Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public void thongBao(String tb) {
        JOptionPane.showMessageDialog(null, tb, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
    }


    	// xu ly khi KH bi xoa o noi khac
    	private ListenerXoaNV listenerXoaNV = new ListenerXoaNV(){
		public void xuLy(NhanVien nv){
			hienThi();
		}
	};
	private ListenerXoaCN listenerXoaCN = new ListenerXoaCN() {
		@Override
		public void xuLy(ChiNhanh cn) {
			hienThi();
		}
	};
}
