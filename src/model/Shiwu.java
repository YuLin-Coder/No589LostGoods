package model;

import java.io.Serializable;
/*拾物品*/
public class Shiwu implements Serializable{
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getWpname() {
		return wpname;
	}
	public void setWpname(String wpname) {
		this.wpname = wpname;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private int u_id;
	private User user;
	
	private String wpname;//物品名称
	public Shiwu() {
		super();
	}
	
	private String imgUrl;//物品图片
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	private String detail;//物品详细描述信息
	private String place;//捡到地点

	private String createTime;//捡到时间
	private String phone;//联系电话
	
	
	

}
