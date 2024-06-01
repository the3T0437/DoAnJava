


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package KhachHang;
/**
 *
 * @author VINH
 */
import KhachHang.KhachHang;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
public class KhachHangTest {
    public KhachHangTest(){
    }
    @Test
    public void controctorKhachHang(){
        String MaKH ="1"; 
        String HoTen="1";
        String SoDT = "012345678901";
        KhachHang khachHang = new KhachHang(MaKH,HoTen,SoDT);
        assertEquals(MaKH,khachHang.getMaKhachHang());
        assertEquals(HoTen,khachHang.getMaKhachHang());
        assertEquals(SoDT,khachHang.getSoDienThoai());
    }
    @Test
    public void controctorThrowException() throws IllegalArgumentException{
        KhachHang khachHang = new KhachHang("null", "Tran Anh Tuan", "01234567891");
    }
   
    
}
