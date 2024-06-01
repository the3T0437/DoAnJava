/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ChiNhanh;

import KhachHang.Modal_KH;
import NhanVien.Model_NhanVien;
import NhanVien.NhanVien;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author VINH
 */
public class Modal_CNTest {
    String pathEmpty = "empty.txt"; 
    String pathTest = "testDanhSach_ChiNhanh.txt"; 
    
    public Modal_CNTest() {

    }
    Modal_CN mdChiNhanh;
    Model_NhanVien mdNhanVien; 

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() throws Exception {
        mdChiNhanh = Modal_CN.getInstance();
        mdChiNhanh.clearAll();
        mdNhanVien = Model_NhanVien.getInstance();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of tim method, of class Modal_CN.
     */
    @Test
    public void testTim_ChiNhanh() {

        NhanVien nv = new NhanVien("NV001", " TenCN", LocalDate.of(1990, 1, 1), "0123456789", "cv", 0);
        ChiNhanh cn1 = new ChiNhanh("MaCN1", "TenCN1", "DiaChi1", "0123456789", nv);

        mdChiNhanh.them(cn1);

        ChiNhanh CNTim = mdChiNhanh.tim(cn1.getMaCN());
        assertEquals(cn1, CNTim);

        ChiNhanh CNTim2 = mdChiNhanh.tim("cn3");
        assertEquals(null, CNTim2);
    }

    /**
     * Test of them method, of class Modal_CN.
     */
    @Test
    public void testThem() {
        String MaCN = "CN01";
        String TenCN = "1";
        String DiaChi = "1";
        String SoDT = "0123456789";
        NhanVien nv = new NhanVien("NV001", " TenCN", LocalDate.of(1990, 1, 1), "0123456789", "cv", 0);
        ChiNhanh cn = new ChiNhanh(MaCN, TenCN, DiaChi, SoDT, nv);
        mdChiNhanh.them(cn);
        assertEquals(1, mdChiNhanh.getDSCN().size());

    }

    /**
     * Test of xoa method, of class Modal_CN.
     */
    @Test
    public void testXoa() {
        String MaCN = "CN01";
        String TenCN = "1";
        String DiaChi = "1";
        String SoDT = "0123456789";
        NhanVien nv = new NhanVien("NV001", " TenCN", LocalDate.of(1990, 1, 1), "0123456789", "cv", 0);
        NhanVien nv2 = new NhanVien("NV002", " TenCN", LocalDate.of(1990, 1, 1), "0123456789", "cv", 0);
        ChiNhanh cn = mdChiNhanh.createChiNhanh(MaCN, TenCN, DiaChi, SoDT, nv.getMaNhanVien());
        mdChiNhanh.them(cn);
        mdChiNhanh.xoa("CN01");

        assertEquals(0, mdChiNhanh.getDSCN().size());

    }

    /**
     * Test of sua method, of class Modal_CN.
     */
    @Test
    public void testSua() {

        String MaCN = "CN01";
        String TenCN = "1";
        String DiaChi = "1";
        String SoDT = "0123456789";
        NhanVien nv = new NhanVien("NV001", " TenCN", LocalDate.of(1990, 1, 1), "0123456789", "cv", 0);
        ChiNhanh cn = new ChiNhanh(MaCN, TenCN, DiaChi, SoDT, nv);

    }

    /**
     * Test of sapXep method, of class Modal_CN.
     */
    @Test
    public void testSapXep() {
        NhanVien nv1 = new NhanVien("NV001", " TenCN", LocalDate.of(1990, 1, 1), "0123456789", "cv", 0);
        NhanVien nv2 = new NhanVien("NV002", " TenCN", LocalDate.of(1990, 1, 1), "0123456789", "cv", 0);
        NhanVien nv3 = new NhanVien("NV003", " TenCN", LocalDate.of(1990, 1, 1), "0123456789", "cv", 0);
        ChiNhanh cn1 = new ChiNhanh("MaCN1", "TenCN1", "DiaChi1", "0123456789", nv1);
        ChiNhanh cn2 = new ChiNhanh("MaCN2", "TenCN3", "DiaChi2", "0123456789", nv2);
        ChiNhanh cn3 = new ChiNhanh("MaCN3", "TenCN2", "DiaChi3", "0123456789", nv3);

        mdChiNhanh.them(cn1);
        mdChiNhanh.them(cn2);
        mdChiNhanh.them(cn3);

        Collections.shuffle(mdChiNhanh.getDSCN());
        mdChiNhanh.sapXep();
        for (int i = 1; i < mdChiNhanh.getDSCN().size(); i++) {
            String maKhachHang1 = mdChiNhanh.getDSCN().get(i - 1).getMaCN();
            String maKhachHang2 = mdChiNhanh.getDSCN().get(i).getMaCN();
            assertEquals(true, maKhachHang1.compareTo(maKhachHang2) < 0);
        }

    }

    /**
     * Test of ghiFile method, of class Modal_CN.
     */
    @Test
    public void testGhiFile() throws IOException {
        NhanVien nv = new NhanVien("NV001", " TenCN", LocalDate.of(1990, 1, 1), "0123456789", "cv", 0);
        NhanVien nv2 = new NhanVien("NV002", " TenCN", LocalDate.of(1990, 1, 1), "0123456789", "cv", 0);
        NhanVien nv3 = new NhanVien("NV003", " TenCN", LocalDate.of(1990, 1, 1), "0123456789", "cv", 0);
        ChiNhanh cn1 = new ChiNhanh("MaCN1", "TenCN1", "DiaChi1", "0123456789", nv);
        ChiNhanh cn2 = new ChiNhanh("MaCN2", "TenCN3", "DiaChi2", "0123456789", nv2);
        ChiNhanh cn3 = new ChiNhanh("MaCN3", "TenCN2", "DiaChi3", "0123456789", nv3);

        mdNhanVien.docTuFile(pathEmpty);
        mdNhanVien.themNhanVien(nv);
        mdNhanVien.themNhanVien(nv2);
        mdNhanVien.themNhanVien(nv3);
                
        mdChiNhanh.them(cn3);
        mdChiNhanh.them(cn2);
        mdChiNhanh.them(cn1);
        mdChiNhanh.ghiFile(pathTest);
        mdChiNhanh.docFile(pathTest);
        assertEquals(3, mdChiNhanh.getDSCN().size());
    }

    /**
     * Test of docFile method, of class Modal_CN.
     */
    @Test
    public void testDocFile() throws IOException {
        mdChiNhanh.docFile("ChiNhanh.txt");
        assertEquals(true, mdChiNhanh != null);
    }

}
