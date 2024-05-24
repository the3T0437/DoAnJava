/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package doan;

import SanBanh.SanBanh;
import SanBanh.Model_SanBanh;
import LoaiSan.LoaiSan;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thanh
 */
public class Model_SanBanhTest {
	public static Model_SanBanh mdSanBanh; 
	private static String pathTestEmpty = "testEmptyModelSanBanh.txt";
	private static String pathTest= "testModelSanBanh.txt";
	
	public Model_SanBanhTest() {
	}
	
	@Before
	public void setUp() {
		mdSanBanh = new Model_SanBanh();
		
		try{
			mdSanBanh.docFile(pathTestEmpty);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	/*
	 * tao gia tri cho mdSanBanh
	 */

	private void initVal(){
		LoaiSan ls1 = new LoaiSan("ls01", 10);
		LoaiSan ls2 = new LoaiSan("ls01", 20);
		LoaiSan ls3 = new LoaiSan("ls01", 30);
		LoaiSan ls4 = new LoaiSan("ls01", 40);
		LoaiSan ls5 = new LoaiSan("ls01", 50);

		SanBanh sb1 = new SanBanh("sb01", ls5, null); 
		SanBanh sb2 = new SanBanh("sb02", ls3, null); 
		SanBanh sb3 = new SanBanh("sb03", ls1, null); 
		SanBanh sb4 = new SanBanh("sb04", ls2, null); 
		SanBanh sb5 = new SanBanh("sb05", ls4, null); 
		SanBanh sb6 = new SanBanh("sb05", null, null); 

		mdSanBanh.them(sb1);
		mdSanBanh.them(sb2);
		mdSanBanh.them(sb3);
		mdSanBanh.them(sb4);
		mdSanBanh.them(sb5);
	}
	/**
	 * Test of tim method, of class Model_SanBanh.
	 */
	@Test
	public void testTim_String() {
		initVal();
		LoaiSan ls1 = new LoaiSan("ls01", 10);
		SanBanh sb3 = new SanBanh("sb03", ls1, null); 

		SanBanh sbTim3 = mdSanBanh.tim("sb03");
		assertEquals(sb3.getMaLoaiSanNotThrow(), sbTim3.getMaLoaiSanNotThrow());
	}

	/**
	 * Test of tim method, of class Model_SanBanh.
	 */
	@Test
	public void testTim_SanBanh() {
		initVal();
		LoaiSan ls1 = new LoaiSan("ls01", 10);
		SanBanh sb3 = new SanBanh("sb03", ls1, null); 

		SanBanh sbTim3 = mdSanBanh.tim(sb3);
		assertEquals(sb3.getMaLoaiSanNotThrow(), sbTim3.getMaLoaiSanNotThrow());
	}

	/**
	 * Test of them method, of class Model_SanBanh.
	 */
	@Test
	public void testThem() {
		SanBanh sb1 = new SanBanh("sb01", null, null); 
		SanBanh sb2 = new SanBanh("sb02", null, null); 

		mdSanBanh.them(sb1);
		mdSanBanh.them(sb2);
		assertEquals(2, mdSanBanh.getDSSanBanh().size());
		mdSanBanh.xoa(sb1.getMaSB());
		mdSanBanh.xoa(sb2.getMaSB());

	}

	/**
	 * Test of xoa method, of class Model_SanBanh.
	 */
	@Test
	public void testXoa() {
		SanBanh sb1 = new SanBanh("sb01", null, null); 
		SanBanh sb2 = new SanBanh("sb02", null, null); 

		mdSanBanh.them(sb1);
		mdSanBanh.them(sb2);
		assertEquals(2, mdSanBanh.getDSSanBanh().size());
		mdSanBanh.xoa(sb1.getMaSB());
		mdSanBanh.xoa(sb2.getMaSB());
		assertEquals(0, mdSanBanh.getDSSanBanh().size());
	}

	/**
	 * Test of sua method, of class Model_SanBanh.
	 */
	@Test
	public void testSua() throws LackingException {
		SanBanh sb1 = new SanBanh("sb01", null, null);
		LoaiSan ls1 = new LoaiSan("ls01", 10);

		mdSanBanh.them(sb1);
		
		SanBanh sbMoi = new SanBanh("sb01", ls1, null); 
		mdSanBanh.sua(sbMoi);

		assertEquals(sb1.getDonGia(), sbMoi.getDonGia());
	}

	/**
	 * Test of sapXep method, of class Model_SanBanh.
	 */
	@Test
	public void testSapXep() {
		
		LoaiSan ls1 = new LoaiSan("ls01", 10);
		LoaiSan ls2 = new LoaiSan("ls01", 20);
		LoaiSan ls3 = new LoaiSan("ls01", 30);
		LoaiSan ls4 = new LoaiSan("ls01", 40);
		LoaiSan ls5 = new LoaiSan("ls01", 50);

		SanBanh sb1 = new SanBanh("sb01", ls5, null); 
		SanBanh sb2 = new SanBanh("sb02", ls3, null); 
		SanBanh sb3 = new SanBanh("sb03", ls1, null); 
		SanBanh sb4 = new SanBanh("sb04", ls2, null); 
		SanBanh sb5 = new SanBanh("sb05", ls4, null); 

		mdSanBanh.them(sb1);
		mdSanBanh.them(sb2);
		mdSanBanh.them(sb3);
		mdSanBanh.them(sb4);
		mdSanBanh.them(sb5);

		mdSanBanh.setComparator(Model_SanBanh.MALOAISAN_ASC);
		mdSanBanh.sapXep();
		List<SanBanh> listSB = mdSanBanh.getDSSanBanh();
		for (int i = 1; i < listSB.size(); i++){
			String maLS1= listSB.get(i-1).getMaLoaiSanNotThrow();
			String maLS2= listSB.get(i).getMaLoaiSanNotThrow();

			assertEquals(true, maLS1.compareTo(maLS2) <= 0);
		}
		mdSanBanh.setComparator(Model_SanBanh.MACHINHANH_ASC);
		mdSanBanh.sapXep();
		listSB = mdSanBanh.getDSSanBanh();
		for (int i = 1; i < listSB.size(); i++){
			String maCN1= listSB.get(i-1).getMaCNNotThrow();
			String maCN2= listSB.get(i).getMaCNNotThrow();

			assertEquals(true, maCN1.compareTo(maCN2) <= 0);
		}
		mdSanBanh.setComparator(Model_SanBanh.MASANBANH_ASC);
		mdSanBanh.sapXep();
		listSB = mdSanBanh.getDSSanBanh();
		for (int i = 1; i < listSB.size(); i++){
			String maSB1= listSB.get(i-1).getMaSB();
			String maSB2= listSB.get(i).getMaSB();

			assertEquals(true, maSB1.compareTo(maSB2) <= 0);
		}
	}

	/**
	 * Test of ghiFile method, of class Model_SanBanh.
	 */
	@Test
	public void testGhiFile() throws Exception {
		initVal();
		int oldLength = mdSanBanh.getDSSanBanh().size();

		mdSanBanh.ghiFile(pathTest);

		while (mdSanBanh.getDSSanBanh().size() != 0){
			mdSanBanh.xoa( mdSanBanh.getDSSanBanh().get(0).getMaSB());
		}
		
		mdSanBanh.docFile(pathTest);

		assertEquals(oldLength, mdSanBanh.getDSSanBanh().size());
	}

	/**
	 * Test of docFile method, of class Model_SanBanh.
	 * lan dau tien co le se sai, vi can testGhiFile method ghi file
	 */
	@Test
	public void testDocFile() throws Exception {
		mdSanBanh.docFile(pathTest);
		assertEquals(true, mdSanBanh.getDSSanBanh().size() != 0);
	}
}
