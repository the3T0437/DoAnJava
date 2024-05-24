/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package doan;

import LoaiSan.LoaiSan;
import LoaiSan.Model_LoaiSan;
import java.io.IOException;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thanh
 */
public class ModelLoaiSanTest {
	
	public ModelLoaiSanTest() {
	}
	
	Model_LoaiSan mdLoaiSan; 
	@Before
	public void setUp() {
		mdLoaiSan = new Model_LoaiSan();
		mdLoaiSan.clearAll();
	}

	/**
	 * Test of them method, of class Model_LoaiSan.
	 */
	@Test
	public void testThem() {
		assertEquals(0, mdLoaiSan.getDS().size());

		mdLoaiSan.them(new LoaiSan("ls01", 01));
		mdLoaiSan.them(new LoaiSan("ls02", 02));

		assertEquals(2, mdLoaiSan.getDS().size());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testThemException() throws IllegalArgumentException{
		mdLoaiSan.them(new LoaiSan("ls01", 01));
		mdLoaiSan.them(new LoaiSan("ls01", 02));
	}

	/**
	 * Test of tim method, of class Model_LoaiSan.
	 */
	@Test
	public void testTim() {
		LoaiSan ls1 = new LoaiSan("ls01", 01);
		LoaiSan ls2 = new LoaiSan("ls02", 02);
		mdLoaiSan.them(ls1);
		mdLoaiSan.them(ls2);

		LoaiSan loaiSanTimThay = mdLoaiSan.tim(ls1.getMaLoaiSan()); 
		assertEquals(ls1,loaiSanTimThay);
		LoaiSan loaiSanTimThay2 = mdLoaiSan.tim(ls2.getMaLoaiSan()); 
		assertEquals(ls2,loaiSanTimThay2);
		LoaiSan loaiSanTimThay3 = mdLoaiSan.tim("ls03");
		assertEquals(null, loaiSanTimThay3);
	}

	/**
	 * Test of xoa method, of class Model_LoaiSan.
	 */
	@Test
	public void testXoa() {
		LoaiSan ls1 = new LoaiSan("ls01", 01);
		LoaiSan ls2 = new LoaiSan("ls02", 02);
		
		mdLoaiSan.them(ls1);
		mdLoaiSan.them(ls2);

		mdLoaiSan.xoa(ls1.getMaLoaiSan());
		assertEquals(1, mdLoaiSan.getDS().size());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testXoaException() throws IllegalArgumentException{
		mdLoaiSan.xoa("ls09");
	}

	/**
	 * Test of sua method, of class Model_LoaiSan.
	 */
	@Test
	public void testSua() {
		LoaiSan ls1 =new LoaiSan("ls01", 01);
		mdLoaiSan.them(ls1); 

		LoaiSan ls1_moi = new LoaiSan("ls01", 02); 
		mdLoaiSan.sua(ls1_moi);

		assertEquals(ls1_moi.getMaLoaiSan(), mdLoaiSan.tim("ls01").getMaLoaiSan());
		assertEquals(ls1_moi.getDonGia(), mdLoaiSan.tim("ls01").getDonGia());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSuaException(){
		LoaiSan ls1 =new LoaiSan("ls01", 01);
		mdLoaiSan.them(ls1); 

		LoaiSan ls2_moi = new LoaiSan("ls02", 02); 
		mdLoaiSan.sua(ls2_moi);
	}

	/**
	 * Test of ghiFile method, of class Model_LoaiSan.
	 */
	@Test
	public void testGhiFile() throws IOException {
		LoaiSan ls1 = new LoaiSan("ls01", 01);
		LoaiSan ls2 = new LoaiSan("ls02", 02);
		LoaiSan ls3 = new LoaiSan("ls03", 03);

		mdLoaiSan.them(ls1);
		mdLoaiSan.them(ls2);
		mdLoaiSan.them(ls3);

		mdLoaiSan.ghiFile("danhSach_LoaiSan.txt");
		mdLoaiSan.docFile("danhSach_LoaiSan.txt");
		assertEquals(3, mdLoaiSan.getDS().size());
	}

	/**
	 * Test of docFile method, of class Model_LoaiSan.
	 */
	@Test
	public void testDocFile() throws IOException {
		mdLoaiSan.docFile("danhSach_LoaiSan.txt"); 
		assertEquals(true, mdLoaiSan.getDS().size() != 0);
	}

	/**
	 * Test of XapXepTheoMaLS method, of class Model_LoaiSan.
	 */
	@Test
	public void testXapXepTheoMaLS() {
		LoaiSan ls1 = new LoaiSan("ls01", 01);
		LoaiSan ls2 = new LoaiSan("ls02", 02);
		LoaiSan ls3 = new LoaiSan("ls03", 03);
		LoaiSan ls4 = new LoaiSan("ls04", 04);
		LoaiSan ls5 = new LoaiSan("ls05", 05);
		LoaiSan ls6 = new LoaiSan("ls06", 06);

		mdLoaiSan.them(ls1);
		mdLoaiSan.them(ls2);
		mdLoaiSan.them(ls3);
		mdLoaiSan.them(ls4);
		mdLoaiSan.them(ls5);
		mdLoaiSan.them(ls6);

		Collections.shuffle(mdLoaiSan.getDS());
		mdLoaiSan.setComparator(Model_LoaiSan.getXapXepTheoMaLS());
		mdLoaiSan.sapXep();
		for (int i = 1; i < mdLoaiSan.getDS().size(); i++){
			String maLoaiSan1 = mdLoaiSan.getDS().get(i-1).getMaLoaiSan();
			String maLoaiSan2 = mdLoaiSan.getDS().get(i).getMaLoaiSan();
			assertEquals(true, maLoaiSan1.compareTo(maLoaiSan2) < 0);
		}
	}

	/**
	 * Test of XapXepTheoDonGia method, of class Model_LoaiSan.
	 */
	@Test
	public void testXapXepTheoDonGia() {
		LoaiSan ls1 = new LoaiSan("ls01", 01);
		LoaiSan ls2 = new LoaiSan("ls02", 02);
		LoaiSan ls3 = new LoaiSan("ls03", 03);
		LoaiSan ls4 = new LoaiSan("ls04", 04);
		LoaiSan ls5 = new LoaiSan("ls05", 05);
		LoaiSan ls6 = new LoaiSan("ls06", 06);

		mdLoaiSan.them(ls1);
		mdLoaiSan.them(ls2);
		mdLoaiSan.them(ls3);
		mdLoaiSan.them(ls4);
		mdLoaiSan.them(ls5);
		mdLoaiSan.them(ls6);

		Collections.shuffle(mdLoaiSan.getDS());
		mdLoaiSan.setComparator(Model_LoaiSan.getXapXepTheoDonGia());
		mdLoaiSan.sapXep();
		for (int i = 1; i < mdLoaiSan.getDS().size(); i++){
			int donGia1 = mdLoaiSan.getDS().get(i-1).getDonGia();
			int donGia2 = mdLoaiSan.getDS().get(i).getDonGia();
			assertEquals(true, donGia1 < donGia2);
		}
	}
	
}
