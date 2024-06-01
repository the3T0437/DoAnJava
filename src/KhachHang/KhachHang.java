/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

/**
 *
 * @author VINH
 */
public class KhachHang {

    public KhachHang(String maKhachHang, String hoTen, String soDienThoai) {
	    setMaKhachHang(maKhachHang);
	    setHoTen(hoTen);
	    setSoDienThoai(soDienThoai);
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }
    public void setMaKhachHang(String maKhachHang) {
        if(maKhachHang == null || maKhachHang.trim().equals("")){
            throw new IllegalArgumentException("Mã khách hàng không thể trống!");
        }
        this.maKhachHang = maKhachHang;
    }
    public void setSoDienThoai(String soDienThoai) {
       
        if (soDienThoai == null || soDienThoai.length() != 10 ) 
        {
            throw new IllegalArgumentException("Số điện thoại phải có độ dài là 10 chữ số.");
        }
        for (int i = 0; i < soDienThoai.length(); i++) {
            if (!Character.isDigit(soDienThoai.charAt(i))) {
                throw new IllegalArgumentException("Số điện thoại chỉ được chứa chữ số.");
            }
        }
        this.soDienThoai = soDienThoai;       
    }
    public void setHoTen(String hoTen) {
        if(hoTen == null || hoTen.trim().equals("")){
            throw new IllegalArgumentException("tên khách hàng không thể trống!");
        }
        this.hoTen = hoTen;
    }

    private String maKhachHang;
    private String hoTen;
    private String soDienThoai;
    
    
    
    public String ToString(){
        return "ma khach hang" + " " +maKhachHang + " ," + "ho ten" + " " +hoTen + " ," + "so dien thoai" + " " + soDienThoai;
    }
  
}
