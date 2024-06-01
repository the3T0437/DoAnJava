/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package HoaDon;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import NhanVien.NhanVien;
import SanBanh.SanBanh;
import KhachHang.KhachHang;
import java.time.LocalDate;

/**
 *
 * @author thanh
 */
public class HoaDonTest {
	
	public HoaDonTest() {
	}
	
	@Before
	public void setUp() {
	}

	@Test
	public void testConstructor(){
		NhanVien nv1 = new NhanVien("nv01", "Van A", LocalDate.parse("1111-11-11"), "1111", "nhan vien", 0);
		KhachHang kh1 = new KhachHang("kh01", "Khach 01", "2222");
		SanBanh sb1 = new SanBanh("sb01", null, null);

		HoaDon hd1 = new HoaDon("hd01", kh1, nv1, sb1);

		assertEquals(true, hd1.getMaHD().equals("hd01"));
		assertEquals(true, hd1.getKhachHang() == kh1);
		assertEquals(true, hd1.getNhanVien() == nv1);
		assertEquals(true, hd1.getSanBanh() == sb1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testContructorException(){
		HoaDon hd01 = new HoaDon("", null, null, null);
	}

}
