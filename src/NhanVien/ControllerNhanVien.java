package NhanVien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;

public class ControllerNhanVien {

    private Model_NhanVien model;
    private ViewNhanVien view;

    public ControllerNhanVien() {
        this.model = Model_NhanVien.getInstance();
        this.view = new ViewNhanVien();

        view.addThemButtonListener(new ThemButtonListener());
        view.addXoaButtonListener(new XoaButtonListener());
        view.addCapNhatButtonListener(new CapNhatButtonListener());
        view.addSapXepTheoMaButtonListener(new SapXepTheoMaButtonListener());
        view.addLuuButtonListener(new LuuButtonListener());
        view.addDocButtonListener(new DocButtonListener());
        view.getTable().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        view.getTable().getSelectionModel().addListSelectionListener(new TableSelectionListener());

        updateTable();
    }

	public void setVisibleTrue(){
		this.view.setVisible(true);
	}

    private void updateTable() {
        List<NhanVien> danhSachNhanVien = model.getDanhSachNhanVien();
        view.setDataTable(danhSachNhanVien);
    }

    class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                JTable table = view.getTable();
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String ma = (String) table.getValueAt(selectedRow, 0);
                    String ten = (String) table.getValueAt(selectedRow, 1);
                    String ngaySinh = table.getValueAt(selectedRow, 2).toString();
                    String soDT = (String) table.getValueAt(selectedRow, 3);
                    String chucVu = (String) table.getValueAt(selectedRow, 4);
                    String luong = table.getValueAt(selectedRow, 5).toString();

                    view.setTxtMaNhanVien(ma);
                    view.setTxtTen(ten);
                    view.setTxtNgaySinh(ngaySinh);
                    view.setTxtSoDienThoai(soDT);
                    view.setTxtChucVu(chucVu);
                    view.setTxtLuong(luong);
                }
            }
        }
    }

    class ThemButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ma = view.getTxtMaNhanVien().getText().trim();
            String ten = view.getTxtTen().getText().trim();
            String ngaySinhStr = view.getTxtNgaySinh().getText().trim();
            String soDT = view.getTxtSoDienThoai().getText().trim();
            String chucVu = view.getTxtChucVu().getText().trim();
            String luongStr = view.getTxtLuong().getText().trim();

            if (ma.isEmpty() || ten.isEmpty() || ngaySinhStr.isEmpty() || soDT.isEmpty() || chucVu.isEmpty() || luongStr.isEmpty()) {
                baoLoi("Không được để trống bất kỳ trường nào");
                return;
            }

            if (!soDT.matches("\\d+")) {
                baoLoi("Số điện thoại chỉ được chứa các ký tự số");
                return;
            }

            try {
                double luong = Double.parseDouble(luongStr);
                LocalDate ngaySinh = LocalDate.parse(ngaySinhStr);
                NhanVien nhanVien = new NhanVien(ma, ten, ngaySinh, soDT, chucVu, luong);
                model.themNhanVien(nhanVien);
                updateTable();
                thongBao("Thêm nhân viên thành công!");
            } catch (DateTimeParseException ex) {
                baoLoi("Ngày sinh không hợp lệ (định dạng yyyy-MM-dd)");
            } catch (NumberFormatException ex) {
                baoLoi("Lương phải là một số hợp lệ");
            } catch (IllegalArgumentException ex) {
                baoLoi(ex.getMessage());
            } catch (Exception ex) {
                baoLoi("Đã xảy ra lỗi: " + ex.getMessage());
            }
        }
    }

    class XoaButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JTable table = view.getTable();
            int[] selectedRows = table.getSelectedRows();

            if (selectedRows.length == 0) {
                baoLoi("Vui lòng chọn ít nhất một nhân viên để xóa");
                return;
            }

            int response = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa nhân viên được chọn không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                List<String> maNhanViens = new ArrayList<>();
                for (int row : selectedRows) {
                    String ma = (String) table.getValueAt(row, 0);
                    maNhanViens.add(ma);
                }
                model.xoa(maNhanViens);
                updateTable();
                thongBao("Xóa nhân viên thành công!");
            }
        }
    }

    class CapNhatButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ma = view.getTxtMaNhanVien().getText().trim();
            String ten = view.getTxtTen().getText().trim();
            String ngaySinhStr = view.getTxtNgaySinh().getText().trim();
            String soDT = view.getTxtSoDienThoai().getText().trim();
            String chucVu = view.getTxtChucVu().getText().trim();
            String luongStr = view.getTxtLuong().getText().trim();

            if (ma.isEmpty() || ten.isEmpty() || ngaySinhStr.isEmpty() || soDT.isEmpty() || chucVu.isEmpty() || luongStr.isEmpty()) {
                baoLoi("Không được để trống bất kỳ trường nào");
                return;
            }

            if (!soDT.matches("\\d+")) {
                baoLoi("Số điện thoại chỉ được chứa các ký tự số");
                return;
            }

            if (!model.isNhanViennull(ma)) {
                baoLoi("Mã nhân viên không tồn tại");
                return;
            }

            try {
                double luong = Double.parseDouble(luongStr);
                LocalDate ngaySinh = LocalDate.parse(ngaySinhStr);
                NhanVien nhanVienMoi = new NhanVien(ma, ten, ngaySinh, soDT, chucVu, luong);
                model.capNhatNhanVien(ma, nhanVienMoi);
                updateTable();
                thongBao("Cập nhật nhân viên thành công!");
            } catch (DateTimeParseException ex) {
                baoLoi("Ngày sinh không hợp lệ (định dạng yyyy-MM-dd)");
            } catch (NumberFormatException ex) {
                baoLoi("Lương phải là một số hợp lệ");
            } catch (Exception ex) {
                baoLoi("Đã xảy ra lỗi: " + ex.getMessage());
            }
        }
    }

    class SapXepTheoMaButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.sapXepNhanViensTheoMa();
            updateTable();
            thongBao("Sắp xếp theo mã nhân viên thành công!");
        }
    }

    class LuuButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.luuVaoFile(model.PATH);
                JOptionPane.showMessageDialog(view, "Lưu thành công!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Lỗi khi lưu: " + ex.getMessage());
            }
        }
    }

    class DocButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.docTuFile(model.PATH);
                updateTable();
                JOptionPane.showMessageDialog(view, "Đọc thành công!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Lỗi khi đọc: " + ex.getMessage());
            }
        }
    }

    public void baoLoi(String loi) {
        JOptionPane.showMessageDialog(null, "Lỗi: " + loi, "Báo lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public void thongBao(String thongbao) {
        JOptionPane.showMessageDialog(null, thongbao, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        ControllerNhanVien controller = new ControllerNhanVien();
        controller.view.setVisible(true);
    }
}
