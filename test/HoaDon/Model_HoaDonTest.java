/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package HoaDon;

import javax.management.MBeanAttributeInfo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import KhachHang.*; 
import NhanVien.*;
import SanBanh.*;
import java.time.LocalDate;

/**
 *
 * @author thanh
 */
public class Model_HoaDonTest {
	private String pathEmpty = "emptyPath.txt";
	private String pathTestHD = "testDanhSach_HoaDon.txt";

	// temp
	private String pathTestSB = "testDanhSach_SanBanh.txt";
	private String pathTestNV = "testDanhSach_NhanVien.txt"; 
	private String pathTestKH = "testDanhSach_KhachHang.txt";

	Model_HoaDon mdHoaDon = Model_HoaDon.getInstance();

	public Model_HoaDonTest() {
	}
	
	@Before
	public void setUp() {
		try{
			mdHoaDon.docFile(pathEmpty);
		}catch(Exception e){
			//do nothing
		}
	}

	/**
	 * Test of getList method, of class Model_HoaDon.
	 */
	@Test
	public void testGetList() {
		NhanVien nv1 = new NhanVien("nv01", "Van A", LocalDate.parse("1111-11-11"), "1111", "nhan vien", 0);
		KhachHang kh1 = new KhachHang("kh01", "Khach 01", "0123456789");
		SanBanh sb1 = new SanBanh("sb01", null, null);

		HoaDon hd1 = new HoaDon("hd01", kh1, nv1, sb1);

		mdHoaDon.them(hd1);
		
		assertEquals(1, mdHoaDon.getList().size());
	}

	/**
	 * Test of createHoaDon method, of class Model_HoaDon.
	 */
	/*
	@Test
	public void testCreateHoaDon() {
		
	}
	*/

	/**
	 * Test of tim method, of class Model_HoaDon.
	 */
	@Test
	public void testTim() {
		SanBanh sb1 = new SanBanh("sb01", null, null);
		HoaDon hd1 = new HoaDon("hd01", null, null, null); 
		HoaDon hd2 = new HoaDon("hd02", null, null, null); 
		HoaDon hd3 = new HoaDon("hd03", null, null, sb1); 
		HoaDon hd4 = new HoaDon("hd04", null, null, null); 
		mdHoaDon.them(hd1);
		mdHoaDon.them(hd2);
		mdHoaDon.them(hd3);
		mdHoaDon.them(hd4);

		assertEquals(sb1, mdHoaDon.tim("hd03").getSanBanh());
		assertEquals(null, mdHoaDon.tim("hd0000"));
	}

	/**
	 * Test of them method, of class Model_HoaDon.
	 */
	@Test
	public void testThem() {
		SanBanh sb1 = new SanBanh("sb01", null, null);
		HoaDon hd1 = new HoaDon("hd01", null, null, null); 
		HoaDon hd2 = new HoaDon("hd02", null, null, null); 
		HoaDon hd3 = new HoaDon("hd03", null, null, sb1); 
		HoaDon hd4 = new HoaDon("hd04", null, null, null); 
		mdHoaDon.them(hd1);
		mdHoaDon.them(hd2);
		mdHoaDon.them(hd3);
		mdHoaDon.them(hd4);

		assertEquals(4, mdHoaDon.getList().size());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testThemException(){
		HoaDon hd1 = new HoaDon("hd01", null, null, null); 
		HoaDon hd2 = new HoaDon("hd01", null, null, null);

		mdHoaDon.them(hd1);
		mdHoaDon.them(hd2);
	}

	/**
	 * Test of xoa method, of class Model_HoaDon.
	 */
	@Test
	public void testXoa() {
		SanBanh sb1 = new SanBanh("sb01", null, null);
		HoaDon hd1 = new HoaDon("hd01", null, null, null); 
		HoaDon hd2 = new HoaDon("hd02", null, null, null); 
		HoaDon hd3 = new HoaDon("hd03", null, null, sb1); 
		HoaDon hd4 = new HoaDon("hd04", null, null, null); 
		mdHoaDon.them(hd1);
		mdHoaDon.them(hd2);
		mdHoaDon.them(hd3);
		mdHoaDon.them(hd4);

		assertEquals(4, mdHoaDon.getList().size());

		mdHoaDon.xoa("hd01");
		mdHoaDon.xoa("hd02");

		assertEquals(2, mdHoaDon.getList().size());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testXoaException(){
		HoaDon hd1 = new HoaDon("hd01", null, null, null); 
		
		mdHoaDon.them(hd1); 
		mdHoaDon.xoa("hd02");
	}

	/**
	 * Test of sua method, of class Model_HoaDon.
	 */
	@Test
	public void testSua() {
		SanBanh sb1 = new SanBanh("sb01", null, null);
		HoaDon hd1 = new HoaDon("hd01", null, null, null); 
		HoaDon hd2 = new HoaDon("hd02", null, null, null); 
		HoaDon hd3 = new HoaDon("hd03", null, null, sb1); 
		HoaDon hd4 = new HoaDon("hd04", null, null, null); 
		mdHoaDon.them(hd1);
		mdHoaDon.them(hd2);
		mdHoaDon.them(hd3);
		mdHoaDon.them(hd4);

		assertEquals(sb1.getMaSB(), mdHoaDon.tim("hd03").getMaSBNotThrow());

		HoaDon hd3Sua = new HoaDon("hd03", null, null, null);  
		mdHoaDon.sua(hd3Sua);

		assertEquals(hd3Sua.getMaSBNotThrow(), mdHoaDon.tim("hd03").getMaSBNotThrow());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSuaException(){
		HoaDon hd1 = new HoaDon("hd01", null, null, null); 
		HoaDon hd2 = new HoaDon("hd02", null, null, null); 
		
		mdHoaDon.them(hd1); 
		mdHoaDon.sua(hd2);
	}
	/**
	 * Test of ghiFile and docFile method, of class Model_HoaDon.
	 */
	@Test
	public void testDocGhiFile() throws Exception{
		SanBanh sb1 = new SanBanh("sb01", null, null);
		HoaDon hd1 = new HoaDon("hd01", null, null, null); 
		HoaDon hd2 = new HoaDon("hd02", null, null, null); 
		HoaDon hd3 = new HoaDon("hd03", null, null, sb1); 
		HoaDon hd4 = new HoaDon("hd04", null, null, null); 
		mdHoaDon.them(hd1);
		mdHoaDon.them(hd2);
		mdHoaDon.them(hd3);
		mdHoaDon.them(hd4);

		mdHoaDon.ghiFile(pathTestHD);
		mdHoaDon.clearAll();
		assertEquals(0, mdHoaDon.getList().size());
		mdHoaDon.docFile(pathTestHD);
		assertEquals(4, mdHoaDon.getList().size());
		assertEquals(sb1.getMaSB(), mdHoaDon.tim("hd03").getSanBanh().getMaSB());
	}
	
}
