package com.magicleap.ws.model;

import java.sql.Date;

public class Comment {

	private String commentid;
	
	private String details;
	
	private Date datetime;
	
	private String postid;
	
	private String userid;
	



	public String getCommentid() {
		return commentid;
	}




	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}




	public String getDetails() {
		return details;
	}




	public void setDetails(String details) {
		this.details = details;
	}




	public Date getDatetime() {
		return datetime;
	}




	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}




	public String getPostid() {
		return postid;
	}




	public void setPostid(String postid) {
		this.postid = postid;
	}




	public String getUserid() {
		return userid;
	}




	public void setUserid(String userid) {
		this.userid = userid;
	}




	@Override
	public String toString() {
		return "User [commentid=" + commentid + ",postid=" + postid + ", details=" + details + ", datetime=" + datetime +", userid=" + userid+
				", details=" + details+  "]";
	}


}
