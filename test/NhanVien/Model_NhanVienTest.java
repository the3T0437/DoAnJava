/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package NhanVien;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nhat
 */

public class Model_NhanVienTest {
     private String pathEmty  = "EmtyFile.txt";
     private String pathTest = "testDSNV.txt";
    private Model_NhanVien model;

    @Before
    public void setUp() {
        model = Model_NhanVien.getInstance();
    }

    @Test
    public void testThemNhanVien() throws IOException {
       
        try
        {
             model.docTuFile(pathEmty);
        }
        catch(Exception e)
        {
                  
        }
        NhanVien nv = new NhanVien("NV001", "Nguyen Van A", LocalDate.of(1990, 1, 1), "0123456789", "Nhan Vien", 5000.0);
        model.themNhanVien(nv);

        List<NhanVien> danhSach = model.getDanhSachNhanVien();
        assertEquals(1, danhSach.size());
        assertEquals("NV001", danhSach.get(0).getMaNhanVien());
        
    }

   

    @Test
   public void testXoaNhanVien() throws IOException {
         try
        {
             model.docTuFile(pathEmty);
        }
        catch(Exception e)
        {
                  
        }
        NhanVien nv = new NhanVien("NV001", "Nguyen Van A", LocalDate.of(1990, 1, 1), "0123456789", "Nhan Vien", 5000.0);
        model.themNhanVien(nv);

        model.xoa("NV001");
        List<NhanVien> danhSach = model.getDanhSachNhanVien();
        assertEquals(0, danhSach.size());
    }

    @Test
    public void testCapNhatNhanVien() throws IOException {
        try
        {
             model.docTuFile(pathEmty);
        }
        catch(Exception e)
        {
                  
        }
        NhanVien nv1 = new NhanVien("NV001", "Nguyen Van A", LocalDate.of(1990, 1, 1), "0123456789", "Nhan Vien", 5000.0);
        model.themNhanVien(nv1);

        NhanVien nvMoi = new NhanVien("NV001", "Nguyen Van B", LocalDate.of(1991, 2, 2), "0987654321", "Quan Ly", 6000.0);
        model.capNhatNhanVien("NV001", nvMoi);

        NhanVien nv = model.getDanhSachNhanVien().get(0);
        assertEquals("Nguyen Van B", nv.getTen());
        assertEquals("0987654321", nv.getSoDienThoai());
        assertEquals("Quan Ly", nv.getChucVu());
        assertEquals(6000.0, nv.getLuong(), 0.0);
    }

    @Test
    public void testSapXepNhanViensTheoMa() throws IOException {
        
             try
        {
             model.docTuFile(pathEmty);
        }
        catch(Exception e)
        {
                  
        }
        NhanVien nv1 = new NhanVien("NV002", "Nguyen Van B", LocalDate.of(1991, 2, 2), "0987654321", "Quan Ly", 6000.0);
        NhanVien nv2 = new NhanVien("NV001", "Nguyen Van A", LocalDate.of(1990, 1, 1), "0123456789", "Nhan Vien", 5000.0);
        model.themNhanVien(nv1);
        model.themNhanVien(nv2);

        model.sapXepNhanViensTheoMa();
        List<NhanVien> danhSach = model.getDanhSachNhanVien();
        assertEquals("NV001", danhSach.get(0).getMaNhanVien());
        assertEquals("NV002", danhSach.get(1).getMaNhanVien());
    }
     @Test
    public void testLuuVaDocTuFile() throws IOException {
         try
        {
             model.docTuFile(pathEmty);
        }
        catch(Exception e)
        {
                  
        }
        NhanVien nv = new NhanVien("NV001","Nguyen Van A", LocalDate.of(1990,01,01),"0123456789", "Nhan Vien",5000.0);
        model.themNhanVien(nv);
        model.luuVaoFile(pathTest);

        Model_NhanVien modelDoc = Model_NhanVien.getInstance();
        try
        {
             modelDoc.docTuFile(pathTest);
        }
        catch(Exception e)
        {
                  
        }
        List<NhanVien> danhSach = modelDoc.getDanhSachNhanVien();

        assertEquals(1, danhSach.size());
        assertEquals("NV001", danhSach.get(0).getMaNhanVien());
    }

    @Test
    public void testLayNhanVienCoLuongLonHon() {
     
        List<NhanVien> danhSachNhanVien = new ArrayList<>();
        danhSachNhanVien.add(new NhanVien("NV001","Nguyen Van A", LocalDate.of(1990,01,01),"0123456789", "Nhan Vien",5000.0));
        danhSachNhanVien.add(new NhanVien("NV002","Nguyen Van B", LocalDate.of(1990,01,01),"0123456789", "Nhan Vien",2000.0));
        danhSachNhanVien.add(new NhanVien("NV003","Nguyen Van C", LocalDate.of(1990,01,01),"0123456789", "Nhan Vien",7000.0));

       
        double luong = 3500;

        
        List<NhanVien> ketQua = model.layNhanVienCoLuongLonHon(luong, danhSachNhanVien);
        assertEquals(2, ketQua.size()); 
        
        assertEquals("NV001", ketQua.get(0).getMaNhanVien());
        assertEquals("NV003", ketQua.get(1).getMaNhanVien());
    }

    @Test
    public void testTinhTongLuong() {
     
        List<NhanVien> danhSachNhanVien = new ArrayList<>();
        danhSachNhanVien.add(new NhanVien("NV001","Nguyen Van A", LocalDate.of(1990,01,01),"0123456789", "Nhan Vien",5000.0));
        danhSachNhanVien.add(new NhanVien("NV002","Nguyen Van B", LocalDate.of(1990,01,01),"0123456789", "Nhan Vien",6000.0));
        danhSachNhanVien.add(new NhanVien("NV003","Nguyen Van C", LocalDate.of(1990,01,01),"0123456789", "Nhan Vien",2000.0));

       
        double ketQua = model.tinhTongLuong(danhSachNhanVien);
        assertEquals(13000, ketQua, 0.001); 
    }

    private double tinhTongLuong(List<NhanVien> danhSachNhanVien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private List<NhanVien> layNhanVienCoLuongLonHon(double luong, List<NhanVien> danhSachNhanVien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}