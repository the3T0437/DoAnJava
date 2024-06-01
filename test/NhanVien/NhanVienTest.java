/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package NhanVien;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenovo
 */
public class NhanVienTest {
    
     @Test
    public void testConstructor() {
        String maNhanVien = "NV001";
        String ten = "Nguyen Van A";
        LocalDate ngaySinh = LocalDate.of(2000, 4, 23);
        String soDienThoai = "123456789";
        String chucVu = "Quản lý";
        double luong = 1000.0;

        NhanVien nv = new NhanVien(maNhanVien, ten, ngaySinh, soDienThoai, chucVu, luong);

        assertEquals(maNhanVien, nv.getMaNhanVien());
        assertEquals(ten, nv.getTen());
        assertTrue(ngaySinh.isEqual(nv.getNgaySinh())); 
        assertEquals(soDienThoai, nv.getSoDienThoai());
        assertEquals(chucVu, nv.getChucVu());
        assertEquals(luong, nv.getLuong(), 0.001);
    }
}
