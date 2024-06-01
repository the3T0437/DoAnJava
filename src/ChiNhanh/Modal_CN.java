/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChiNhanh;

import HoaDon.ControllerHoaDon;
import NhanVien.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import doan.ListenerXoaNV;
import doan.ListenerXoaCN;

/**
 *
 * @author VINH
 */
public class Modal_CN {

    public static String PATH = "DanhSach_ChiNhanh.txt";
    public static final String PATHNV = "nhanvien.txt";
    public static final Comparator MACN_SapXepMaCN = Comparator.comparing(ChiNhanh::getMaCN);
    public static final Comparator MACN_SapXepTenCN = Comparator.comparing(ChiNhanh::getTenCN);

    private static Model_NhanVien mdNV = Model_NhanVien.getInstance();
    private static List<ChiNhanh> dsChiNhanh = null;
    public static List<NhanVien> dsNV = null;

    private static Comparator comp;

    /*
    	dam bao mot Model_CN ton tai
    */
    private Modal_CN() {
        setComparator(Modal_CN.MACN_SapXepMaCN);

	mdNV.addListener(listenerXoaNV);
    }

    private static Modal_CN instance = new Modal_CN();
    public static Modal_CN getInstance(){
	    return instance;
    }

	/*
	 * thong bao den cac class khac khi xoa
	 */
	private static List<ListenerXoaCN> listenerXoaCN = new ArrayList<>();
	public void addListener(ListenerXoaCN listener){
		listenerXoaCN.add(listener);
	}
	public void removeListener(ListenerXoaCN listener){
		listenerXoaCN.remove(listener);
	}
	public void thongBaoKhiXoa(ChiNhanh cn){
		for(ListenerXoaCN listener : listenerXoaCN){
			listener.xuLy(cn);
		}
	}

	//them xoa sua
    public void setComparator(Comparator<ChiNhanh> comp) {
        this.comp = comp;
    }

    public List<ChiNhanh> getDSCN() {
        if (dsChiNhanh != null) {
            return dsChiNhanh;
        }

        try {
            (getInstance()).docFile(PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return dsChiNhanh;
    }

    public void clearAll() {
        while (getDSCN().size() != 0) {
            dsChiNhanh.remove(0);
        }
    }

    public ChiNhanh tim(String maCN) {
        for (int i = 0; i < getDSCN().size(); i++) {
            if (getDSCN().get(i).getMaCN().equals(maCN)) {
                return getDSCN().get(i);
            }
        }

        return null;
    }

    public ChiNhanh tim(ChiNhanh cn) {
        return tim(cn.getMaCN());
    }

    public static ChiNhanh createChiNhanh(String MaCN, String TenCN, String DiaChi, String SoDT, String MaQL) throws IllegalArgumentException {
        NhanVien nhanVien = null;
        if (!MaQL.equals("")) {
            for (NhanVien nv : mdNV.getDanhSachNhanVien()) {
                if (MaQL.equals(nv.getMaNhanVien())) {
                    nhanVien = nv;
                    break;
                }
            }
        }

        return new ChiNhanh(MaCN, TenCN, DiaChi, SoDT, nhanVien);
    }

    public void them(ChiNhanh cn) throws IllegalArgumentException {
        if (tim(cn.getMaCN()) != null) {
            throw new IllegalArgumentException("Mã Chi nhánh đã tồn tại");
        }

        dsChiNhanh.add(cn);
    }

    public void xoa(String maCN) throws IllegalArgumentException {
        ChiNhanh cn = tim(maCN);
        if (cn == null) {
            throw new IllegalArgumentException("Mã Sân Banh không tồn tại tồn tại");
        }
	thongBaoKhiXoa(cn);
        dsChiNhanh.remove(cn);
    }

    public void sua(ChiNhanh CNSua) throws IllegalArgumentException {
        ChiNhanh cn = tim(CNSua.getMaCN());

        if (cn == null) {
            throw new IllegalArgumentException("Chi nhánh không tồn tại");
        }
        cn.setDiaChi(CNSua.getDiaChi());
        cn.setSDT(CNSua.getSoDT());
        cn.setTenCN(CNSua.getTenCN());
        NhanVien nv = mdNV.tim(CNSua.getMaQLNotThrow());
        
        cn.setQuanLy(nv);
    }

    public void sapXep() {
        Collections.sort(getDSCN(), comp);
    }

    // ghi vao file
    public void ghiFile(String fileChiNhanh) throws IOException {
        try {
            try (FileWriter fileWriter = new FileWriter(fileChiNhanh); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                for (ChiNhanh cn : dsChiNhanh) {
                    String maQL = (cn.getQuanLy() != null) ? cn.getQuanLy().getMaNhanVien() : "null";
                    bufferedWriter.write(cn.getMaCN() + "_ " + cn.getTenCN() + "_ " + cn.getDiaChi() + "_ " + cn.getSoDT() + "_ " + maQL + "\n");
                }
            }
        } catch (IOException e) {
            throw new IOException("ghi file thất bại");
        }
    }

    /**
     *
     * @param fileKH
     * @throws IOException
     */
    // doc file
    public void docFile(String fileKH) throws IOException {
        List<ChiNhanh> dsCN = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileKH); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] info = line.split("_");
                
                dsCN.add(createChiNhanh(info[0].trim(), info[1].trim(), info[2].trim(), info[3].trim(), info[4].trim()));
                
            }
        } catch (IOException e) {
            throw new IOException("đọc file thất bại");
        }
        Modal_CN.dsChiNhanh = dsCN;
    }


    /*
    	xu ly khi NhanVien bi xoa o noi khac
    */
    public ListenerXoaNV listenerXoaNV = new ListenerXoaNV(){
		public void xuLy(NhanVien nv){
				for (ChiNhanh cn : getDSCN())
					if (cn.getMaQLNotThrow().equalsIgnoreCase(nv.getMaNhanVien()))
						cn.setQuanLy(null);
		}
	};
}
