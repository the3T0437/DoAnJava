/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package doan;

import SanBanh.SanBanh;
import LoaiSan.LoaiSan;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thanh
 */
public class SanBanhTest {
	
	public SanBanhTest() {
	}
	
	@Before
	public void setUp() {
	}

	/**
	 * Test of getMaSB method, of class SanBanh.
	 */
	
	@Test
	public void testConstructor() throws LackingException{
		LoaiSan ls1 = new LoaiSan("ls01", 01);
		LoaiSan ls2 = new LoaiSan("ls02", 02);
		LoaiSan ls3 = new LoaiSan("ls03", 03);
		
		SanBanh sb = new SanBanh("sb01", ls1, null); 

		assertEquals("sb01", sb.getMaSB());
		assertEquals(ls1, sb.getLoaiSan());
		assertEquals(ls1.getDonGia(), sb.getDonGia());
		assertEquals(ls1.getMaLoaiSan(), sb.getMaLoaiSan());
		assertEquals(null, sb.getChiNhanh());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorException() throws IllegalArgumentException{
		SanBanh sb = new SanBanh(null, null, null);
	}
}
