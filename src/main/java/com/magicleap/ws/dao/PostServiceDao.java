package com.magicleap.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.magicleap.ws.model.Post;
import com.magicleap.ws.utility.DBUtility;

@Component
public class PostServiceDao {
	
	private Connection connection;

	public PostServiceDao() {
		connection = DBUtility.getConnection();
	}
	
	public Timestamp getDateTime() {
		return new java.sql.Timestamp(new java.util.Date().getTime());
	}

	public boolean addPost(Post post) {
		try {
			
			PreparedStatement psMax = 
					connection.prepareStatement("SELECT max(postid) as postid FROM posts");
			
			ResultSet rs = psMax.executeQuery();
			String id="";
			while(rs.next())
				 id = rs.getString("postid");
			 int postid = Integer.parseInt(id) + 1;
			
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into posts(postid,postdetails,postdate,userid, posttitle) values (?,?,?,?,?)");
			
			preparedStatement.setString(1, postid+"");
			preparedStatement.setString(2, post.getPostdetails());
			preparedStatement.setTimestamp(3, getDateTime());
			preparedStatement.setString(4, post.getUserid());
			preparedStatement.setString(5, post.getPosttitle());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void updatePost(Post post) throws ParseException {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update posts set postdetails=?,postdate=?,userid=?,posttitle=?" +
							"where postid=?");
			System.out.println("indao"+post.getPosttitle());
			preparedStatement.setString(1, post.getPostdetails());
			preparedStatement.setTimestamp(2, getDateTime());
			preparedStatement.setString(3, post.getUserid());
			preparedStatement.setString(4, post.getPosttitle());
			preparedStatement.setString(5, post.getPostid());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public boolean deletePost(String postId) {
		boolean ret = false;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from posts where postid=?");
			preparedStatement.setString(1, postId);
			preparedStatement.executeUpdate();
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}


	public List<Post> getAllPosts() {
		List<Post> posts = new ArrayList<Post>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from posts");
			while (rs.next()) {
				Post post = new Post();
				
				post.setPostid(rs.getString("postid"));
				post.setPostdetails(rs.getString("postdetails"));
				post.setPostdate(rs.getDate("postdate"));				
				post.setUserid(rs.getString("userid"));
				post.setPosttitle(rs.getString("posttitle"));
				
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return posts;
	}
	
	public List<Post> getAllPostByUserId(String userId) {
		List<Post> posts = new ArrayList<Post>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from posts where userid=?");
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Post post = new Post();
				
				post.setPostid(rs.getString("postid"));
				post.setPostdetails(rs.getString("postdetails"));
				post.setPostdate(rs.getDate("postdate"));				
				post.setUserid(rs.getString("userid"));
				post.setPosttitle(rs.getString("posttitle"));
				
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return posts;
	}
	
	public Post getPostById(String postId) {
		Post post = new Post();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from posts where postid=?");
			preparedStatement.setString(1, postId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
			
				post.setPostid(rs.getString("postid"));
				post.setPostdetails(rs.getString("postdetails"));
				post.setPostdate(rs.getDate("postdate"));				
				post.setUserid(rs.getString("userid"));
				post.setPosttitle(rs.getString("posttitle"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return post;
	}
	

}

