/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package doan;

import LoaiSan.LoaiSan;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thanh
 */
public class LoaiSanTest {
	
	public LoaiSanTest() {
	}
	
	@Test
	public void constructorLoaiSan(){
		String maLoaiSan ="loaiSan01";
		int donGia = 1000;
		LoaiSan loaiSan = new LoaiSan(maLoaiSan, donGia);
		assertEquals(maLoaiSan, loaiSan.getMaLoaiSan());
		assertEquals(donGia, loaiSan.getDonGia());
	}

	@Test (expected = IllegalArgumentException.class)
	public void constructorThrowExcpetion() throws IllegalArgumentException{
		LoaiSan loaiSan = new LoaiSan(null, 10); 
	}
}
