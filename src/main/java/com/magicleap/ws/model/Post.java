package com.magicleap.ws.model;

import java.sql.Date;

public class Post {

	private String postid;
	
	private String postdetails;
	
	private Date postdate;
	
	private String userid;
	




	public String getPostid() {
		return postid;
	}





	public void setPostid(String postid) {
		this.postid = postid;
	}





	public String getPostdetails() {
		return postdetails;
	}





	public void setPostdetails(String postdetails) {
		this.postdetails = postdetails;
	}





	public Date getPostdate() {
		return postdate;
	}





	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}





	public String getUserid() {
		return userid;
	}





	public void setUserid(String userid) {
		this.userid = userid;
	}





	@Override
	public String toString() {
		return "User [postid=" + postid + ", postdetails=" + postdetails + ", postdate=" + postdate +", userid=" + userid
				+  "]";
	}


}
