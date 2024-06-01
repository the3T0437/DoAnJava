package KhachHang;



import KhachHang.Modal_KH;
import KhachHang.KhachHang;
import java.io.IOException;
import java.util.Collections;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author VINH
 */
public class ModalKhachHangTest {
    public ModalKhachHangTest() {
	}
	
	   Modal_KH mdKhachHang; 
	@Before
	public void setUp() {
		mdKhachHang = Modal_KH.getInstance();
		mdKhachHang.clearAll();
	}

	/**
	 * Test of testThem method, of class ModelLoaiSan.
	 */
	@Test
	public void testThem() {
		assertEquals(0, mdKhachHang.getDS().size());

		mdKhachHang.ThemKhachHang(new KhachHang("kh01","007","01234567890"));
		mdKhachHang.ThemKhachHang(new KhachHang("kh02","006","01234567890"));

		assertEquals(2, mdKhachHang.getDS().size());
	}

	//@Test (expected = IllegalArgumentException.class)
	public void testThemException() throws IllegalArgumentException{
		mdKhachHang.ThemKhachHang(new KhachHang("kh01","007","01234567890"));
		mdKhachHang.ThemKhachHang(new KhachHang("kh02","006","01234567890"));
	}

	/**
	 * Test of testTim method, of class ModelKhachHang.
	 */
	@Test
	public void testTim() {
		KhachHang kh1 = new KhachHang("kh01","007","01234567890");
		KhachHang kh2 = new KhachHang("kh02","007","01234567890");
		mdKhachHang.ThemKhachHang(kh1);
		mdKhachHang.ThemKhachHang(kh2);

		KhachHang KHTimDuoc = mdKhachHang.timKhachHang(kh1.getMaKhachHang()); 
		assertEquals(kh1,KHTimDuoc);
		KhachHang KHTimDuoc2 = mdKhachHang.timKhachHang(kh2.getMaKhachHang()); 
		assertEquals(kh2,KHTimDuoc2);
                
                
		KhachHang KHTimDuoc3 = mdKhachHang.timKhachHang("kh03");
		assertEquals(null, KHTimDuoc3);
	}

	/**
	 * Test of testXoa method, of class ModelKhachHang.
	 */
	@Test
	public void testXoa() {
		KhachHang kh1 = new KhachHang("kh01","007","01234567890");
		KhachHang kh2 = new KhachHang("kh02","007","01234567890");
		
		mdKhachHang.ThemKhachHang(kh1);
		mdKhachHang.ThemKhachHang(kh2);

		mdKhachHang.xoaKhachHang(kh1.getMaKhachHang());
		assertEquals(1, mdKhachHang.getDS().size());
	}
	

	/**
	 * Test of testSua method, of class ModelKhachHang.
     * @throws java.lang.IllegalAccessException
	 */
	@Test
	public void testSua() throws IllegalAccessException {
		KhachHang kh1 = new KhachHang("kh01","007","01234567890");
		mdKhachHang.ThemKhachHang(kh1); 

		KhachHang khMoi = new KhachHang("kh01","000","01234567890"); 
		mdKhachHang.suaKhachHang(khMoi);

		assertEquals(khMoi.getMaKhachHang(), mdKhachHang.timKhachHang("kh01").getMaKhachHang());
		assertEquals(khMoi.getHoTen(), mdKhachHang.timKhachHang("kh01").getHoTen());
                assertEquals(khMoi.getSoDienThoai(),mdKhachHang.timKhachHang("kh01").getSoDienThoai());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSuaException() throws IllegalArgumentException{
		KhachHang kh01 =new KhachHang("kh01","007","01234567890");
		mdKhachHang.ThemKhachHang(kh01); 

		KhachHang khmoi = new KhachHang("kh02","007","01111111111"); 
		mdKhachHang.suaKhachHang(khmoi);
                
	}

	/**
	 * Test of ghiFile method, of class ModelKhachHang.
	 */
	@Test
	public void testGhiFile() throws IOException {
		KhachHang kh1 = new KhachHang("kh01","007","01234567890");
		KhachHang kh2 = new KhachHang("kh02","001","01234567890");

		mdKhachHang.ThemKhachHang(kh1);
		mdKhachHang.ThemKhachHang(kh2);

		mdKhachHang.ghiFile("DanhSach_KhachHang.txt");
		mdKhachHang.DocFile("DanhSach_KhachHang.txt");
		assertEquals(2, mdKhachHang.getDS().size());
	}

	/**
	 * Test of docFile method, of class ModelKhachHang.
	 */
	@Test
	public void testDocFile() throws IOException {
		mdKhachHang.DocFile("DanhSach_KhachHang.txt"); 
		assertEquals(true, mdKhachHang != null);
	}

	/**
	 * Test of XapXepTheoMaLS method, of class ModelKhachHang.
	 */
	@Test
	public void testXapXepTheoMaKH() {
		KhachHang kh1 = new KhachHang("kh01","007","01234567890");
                KhachHang kh4 = new KhachHang("kh04","004","01234567890");
		KhachHang kh2 = new KhachHang("kh02","005","01234567890");
                KhachHang kh3 = new KhachHang("kh03","006","01234567890");
		

		mdKhachHang.ThemKhachHang(kh1);
		mdKhachHang.ThemKhachHang(kh2);
		mdKhachHang.ThemKhachHang(kh3);
		mdKhachHang.ThemKhachHang(kh4);

		Collections.shuffle(mdKhachHang.getDS());
		mdKhachHang.setComparator(Modal_KH.sapXepKhachHangTheoMaKH());
		mdKhachHang.sapXep();
		for (int i = 1; i < mdKhachHang.getDS().size(); i++){
			String maKhachHang1 = mdKhachHang.getDS().get(i-1).getMaKhachHang();
			String maKhachHang2 = mdKhachHang.getDS().get(i).getMaKhachHang();
			assertEquals(true, maKhachHang1.compareTo(maKhachHang2) < 0);
		}
	}

	/**
	 * Test of XapXepTheoDonGia method, of class ModelKhachHang.
	 */
	@Test
	public void testXapXepTheoHoTen() {
		KhachHang kh1 = new KhachHang("kh01","007","01234567890");
                KhachHang kh4 = new KhachHang("kh04","004","01234567890");
		KhachHang kh2 = new KhachHang("kh02","005","01234567890");
                KhachHang kh3 = new KhachHang("kh03","006","01234567890");

		mdKhachHang.ThemKhachHang(kh1);
		mdKhachHang.ThemKhachHang(kh2);
		mdKhachHang.ThemKhachHang(kh3);
		mdKhachHang.ThemKhachHang(kh4);


		Collections.shuffle(mdKhachHang.getDS());
		mdKhachHang.setComparator(Modal_KH.sapXepKhachHangTheoTenKH());
		mdKhachHang.sapXep();
		for (int i = 1; i < mdKhachHang.getDS().size(); i++){
			String HoTenkh1 = mdKhachHang.getDS().get(i-1).getHoTen();
			String HoTenkh2 = mdKhachHang.getDS().get(i).getHoTen();
			assertEquals(true,HoTenkh1.compareTo(HoTenkh2)< 0);
		}
	}
}
