/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ChiNhanh;
import NhanVien.NhanVien;
import java.time.LocalDate;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author VINH
 */
public class ChiNhanhTest {
    
    public ChiNhanhTest() {
    }

    @Before
    public void setUp() throws Exception {
    }
    
    /**
     * Test of getMaCN method, of class ChiNhanh.
     */
    @Test
    public void testConstructor() {
        String MaCN = "CN001";
        String tenCN = "A";
        String DiaChi = "adc";
        String soDienThoai = "0123456789";
        
        NhanVien nv = new NhanVien("NV001", "Ten", LocalDate.of(1990,1,1), "0123456789", "12345", 50000);


        ChiNhanh cn = new ChiNhanh(MaCN,tenCN,DiaChi,soDienThoai,nv);
        Assert.assertEquals(MaCN,cn.getMaCN());
        Assert.assertEquals(tenCN,cn.getTenCN());
        Assert.assertEquals(DiaChi,cn.getDiaChi());
        Assert.assertEquals(soDienThoai, cn.getSoDT());
        Assert.assertEquals(nv.getMaNhanVien(), cn.getQuanLy().getMaNhanVien());
    }

}
