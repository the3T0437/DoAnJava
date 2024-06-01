package NhanVien;

import NhanVien.NhanVien;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JOptionPane;
import doan.ListenerXoaNV;

public class Model_NhanVien {

    private List<NhanVien> danhSachNhanVien;
    public static final String PATH = "DanhSach_NhanVien.txt";

    /*
    	dam bao chi co 1 model nhan vien
    */
    private Model_NhanVien() {
        this.danhSachNhanVien = null;
    }
    private static Model_NhanVien instance = new Model_NhanVien();
    public static Model_NhanVien getInstance(){
	    return instance; 
    }

	/*
	 * thong bao den cac class khac khi xoa
	 */
	private static List<ListenerXoaNV> listenerXoaNV= new ArrayList<>();
	public void addListener(ListenerXoaNV listener){
		listenerXoaNV.add(listener);
	}
	public void removeListener(ListenerXoaNV listener){
		listenerXoaNV.remove(listener);
	}
	public void thongBaoKhiXoa(NhanVien nv){
		for(ListenerXoaNV listener : listenerXoaNV){
			listener.xuLy(nv);
		}
	}

	// them xoa sua
    public List<NhanVien> getDanhSachNhanVien() {
        if (danhSachNhanVien == null) {
            danhSachNhanVien = new ArrayList<>();
            try {
                docTuFile(PATH);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return danhSachNhanVien;
    }

    public void themNhanVien(NhanVien nv) {
        List<NhanVien> danhSach = getDanhSachNhanVien();
        for (NhanVien nhanVien : danhSach) {
            if (nhanVien.getMaNhanVien().equals(nv.getMaNhanVien())) {
                throw new IllegalArgumentException("Mã nhân viên đã tồn tại");
            }
        }
        danhSach.add(nv);
    }
        public void xoa(String maNV) {
        NhanVien nv = tim(maNV);
        if (nv == null)
            throw new IllegalArgumentException("Mã nhân viên không tồn tại");

	thongBaoKhiXoa(nv);
        danhSachNhanVien.remove(nv);
    }

     public void xoa(List<String> maNhanViens) {
        for (String maNV : maNhanViens) {
            xoa(maNV);
        }
    }

    public void capNhatNhanVien(String ma, NhanVien nvMoi) {
        List<NhanVien> danhSach = getDanhSachNhanVien();
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaNhanVien().equals(ma)) {
                danhSach.set(i, nvMoi);
                return;
            }
        }
    }

    public void sapXepNhanViensTheoMa() {
        List<NhanVien> danhSach = getDanhSachNhanVien();
        danhSach.sort((nv1, nv2) -> nv1.getMaNhanVien().compareTo(nv2.getMaNhanVien()));
    }

    public NhanVien tim(String maNV) {
        List<NhanVien> danhSach = getDanhSachNhanVien();
        for (NhanVien nhanVien : danhSach) {
            if (nhanVien.getMaNhanVien().equals(maNV))
                return nhanVien;
        }
        return null;
    }

    public void luuVaoFile( String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            List<NhanVien> danhSach = getDanhSachNhanVien();
            for (NhanVien nv : danhSach) {
                bw.write(nv.getMaNhanVien() + "," + nv.getTen() + "," + nv.getNgaySinh() + "," + nv.getSoDienThoai() + "," + nv.getChucVu() + "," + nv.getLuong());
                bw.newLine();
            }
        }
    }

    public void docTuFile(String path) throws IOException {
        danhSachNhanVien = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
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
    public boolean isNhanViennull(String ma)
    {
         return danhSachNhanVien.stream().anyMatch(nv -> nv.getMaNhanVien().equals(ma));
    }
     public List<NhanVien> layNhanVienCoLuongLonHon(double luong, List<NhanVien> danhSachNhanVien) {
        List<NhanVien> nhanViensLuongCao = new ArrayList<>();
        for (NhanVien nv : danhSachNhanVien) {
            if (nv.getLuong() > luong) {
                nhanViensLuongCao.add(nv);
            }
        }
        return nhanViensLuongCao;
    }

    public double tinhTongLuong(List<NhanVien> danhSachNhanVien) {
        double tongLuong = 0;
        for (NhanVien nv : danhSachNhanVien) {
            tongLuong += nv.getLuong();
        }
        return tongLuong;
    }
}
