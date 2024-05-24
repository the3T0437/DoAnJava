package NhanVien;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JOptionPane;

public class Model_NhanVien {

    private List<NhanVien> danhSachNhanVien;
    public static final String PATH = "nhanvien.txt";

    public Model_NhanVien() {
        danhSachNhanVien = new ArrayList<>();
    }

    public List<NhanVien> layTatCaNhanViens() {
        return danhSachNhanVien;
    }

    public void themNhanVien(NhanVien nv) {
        for (NhanVien nhanVien : danhSachNhanVien) {
            if (nhanVien.getMaNhanVien().equals(nv.getMaNhanVien())) {
                throw new IllegalArgumentException("Mã nhân viên đã tồn tại");
            }
        }
        danhSachNhanVien.add(nv);
    }

    public void xoaNhanVien(String ma) {
        danhSachNhanVien.removeIf(nv -> nv.getMaNhanVien().equals(ma));
    }

    public void capNhatNhanVien(String ma, NhanVien nvMoi) {
        for (int i = 0; i < danhSachNhanVien.size(); i++) {
            if (danhSachNhanVien.get(i).getMaNhanVien().equals(ma)) {
                danhSachNhanVien.set(i, nvMoi);
                return;
            }
        }
    }

    public void sapXepNhanViensTheoMa() {
        danhSachNhanVien.sort((nv1, nv2) -> nv1.getMaNhanVien().compareTo(nv2.getMaNhanVien()));
    }

    public void luuVaoFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            for (NhanVien nv : danhSachNhanVien) {
                bw.write(nv.getMaNhanVien() + "," + nv.getTen() + "," + nv.getNgaySinh() + "," + nv.getSoDienThoai() + "," + nv.getChucVu() + "," + nv.getLuong());
                bw.newLine();
            }
        }
    }

    public void docTuFile() throws IOException {
        danhSachNhanVien.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String ma = parts[0].trim();
                    String ten = parts[1].trim();
                    String ngaySinhStr = parts[2].trim();
                    String soDT = parts[3].trim();
                    String chucVu = parts[4].trim();
                    double luong = Double.parseDouble(parts[5].trim());

                    LocalDate ngaySinh = LocalDate.parse(ngaySinhStr);

                    NhanVien nv = new NhanVien(ma, ten, ngaySinh, soDT, chucVu, luong);
                    danhSachNhanVien.add(nv);
                }
            }
        }
    }
}
