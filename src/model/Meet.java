package model;

import java.io.Serializable;

public class Meet implements Serializable{	
	private int id;
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
    public String getWpname() {
		return wpname;
	}
	public void setWpname(String wpname) {
		this.wpname = wpname;
	}
	private String wpname;//物品名称	
	private String bz;
	private String phone;
	private String rlsj;
	public String getRlsj() {
		return rlsj;
	}
	public void setRlsj(String rlsj) {
		this.rlsj = rlsj;
	}
	private int u_id;//外键，关联用户表
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private User user;
}
