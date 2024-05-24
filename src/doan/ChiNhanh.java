/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doan;

/**
 *
 * @author thanh
 */
public class ChiNhanh {
	private String maCN, maQL, tenCN, diaChi, soDT;
	public ChiNhanh(String maCN, String maQL, String tenCN, String diaChi, String soDT) throws IllegalArgumentException{
		setMaCN(maCN);
		setMaQL(maQL);
		setTenCN(tenCN);
		setDiaChi(diaChi); 
		setSoDT(soDT);
	}

	public String getMaCN(){
		return maCN; 
	}
	public String getMaQL(){
		return maQL; 
	}
	public String getTenCN(){
		return tenCN; 
	}
	public String getDiaChi(){
		return diaChi; 
	}
	public String getSoDT(){
		return soDT;
	}

	public void setMaCN(String maCN) throws IllegalArgumentException{
		maCN = maCN.trim();
		if (maCN == null || maCN.equals(""))
			throw new IllegalArgumentException("mã chi nhánh không được rỗng");

		this.maCN = maCN; 
	}
	public void setMaQL(String maQL) throws IllegalArgumentException{
		maQL = maQL.trim();
		if (maQL == null || maCN.equals("")){
			this.maQL = "";
			return;
		}

		this.maQL = maQL; 
	}
	public void setTenCN(String tenCN) throws IllegalArgumentException{
		tenCN = tenCN.trim();
		if (tenCN == null || tenCN.equals(""))
			throw new IllegalArgumentException("tên chi nhánh không thể để rỗng");

		this.tenCN = tenCN;
	}
	public void setDiaChi(String diaChi) throws IllegalArgumentException{
		diaChi = diaChi.trim();
		if (diaChi == null || diaChi.equals("")){
			this.diaChi = "";
			return;
		}

		this.diaChi = diaChi;
	}

	public void setSoDT(String soDT) throws IllegalArgumentException{
		soDT = soDT.trim();
		if (soDT == null || soDT.equals("")){
			this.soDT = "";
			return;
		}
		if(!QLSBUtil.isNumber(soDT)){
			throw new IllegalArgumentException("số điện thoại không hợp lệ");
		}

		this.soDT = soDT;
	}

	public boolean isEquals(ChiNhanh cn){
		return 	maCN.equals(cn.maCN) &&
			maQL.equals(cn.maQL) &&
			tenCN.equals(cn.tenCN) &&
			diaChi.equals(cn.diaChi) &&
			soDT.equals(cn.soDT);
	}

	public void setNewValue(ChiNhanh cn){
		setMaCN(cn.maCN);
		setMaQL(cn.maQL);
		setTenCN(cn.tenCN);
		setDiaChi(cn.diaChi); 
		setSoDT(cn.soDT);
	}

	@Override 
	public String toString(){
		return String.format("%s_%s_%s_%s_%s", maCN, maQL, tenCN, diaChi, soDT);
	}
}
