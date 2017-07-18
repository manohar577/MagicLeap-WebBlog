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

import com.magicleap.ws.model.Comment;
import com.magicleap.ws.model.Post;
import com.magicleap.ws.utility.DBUtility;

@Component
public class CommentServiceDao {
	
	private Connection connection;

	public CommentServiceDao() {
		connection = DBUtility.getConnection();
	}
	
	public Timestamp getDateTime() {
		return new java.sql.Timestamp(new java.util.Date().getTime());
	}

	public boolean addComment(Comment comment) {
		try {
			
			PreparedStatement psMax = 
					connection.prepareStatement("SELECT max(commentid) as commentid FROM comments");
			
			ResultSet rs = psMax.executeQuery();
			String id="";
			while(rs.next())
				 id = rs.getString("commentid");
			 int commentid = Integer.parseInt(id) + 1;
			
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into comments(commentid,details,datetime,postid,userid) values (?,?,?,?,?)");
			
			preparedStatement.setString(1, commentid+"");
			preparedStatement.setString(2, comment.getDetails());
			preparedStatement.setTimestamp(3, getDateTime());
			preparedStatement.setString(4, comment.getPostid());
			preparedStatement.setString(5, comment.getUserid());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void updateComment(Comment comment) throws ParseException {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update comments set details=?,datetime=?,postid=?,userid=?" +
							"where commentid=?");
			preparedStatement.setString(1, comment.getDetails());
			preparedStatement.setTimestamp(2, getDateTime());
			preparedStatement.setString(3, comment.getPostid());
			preparedStatement.setString(4, comment.getUserid());
			preparedStatement.setString(5, comment.getCommentid());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public boolean deleteComment(String commentId) {
		boolean ret = false;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from comments where commentid=?");
			preparedStatement.setString(1, commentId);
			preparedStatement.executeUpdate();
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}


	public List<Comment> getAllComments() {
		List<Comment> comments = new ArrayList<Comment>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from comments");
			while (rs.next()) {
				Comment comment = new Comment();
				
				comment.setCommentid(rs.getString("commentid"));
				comment.setDetails(rs.getString("details"));
				comment.setDatetime(rs.getDate("datetime"));				
				comment.setPostid(rs.getString("postid"));
				comment.setUserid(rs.getString("userid"));
				
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comments;
	}
	
	public List<Comment> getAllCommentsByUserId(String userId) {
		List<Comment> comments = new ArrayList<Comment>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from comments where userid=?");
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				
				comment.setCommentid(rs.getString("commentid"));
				comment.setDetails(rs.getString("details"));
				comment.setDatetime(rs.getDate("datetime"));				
				comment.setPostid(rs.getString("postid"));
				comment.setUserid(rs.getString("userid"));
				
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comments;
	}
	
	public List<Comment> getCommentsByPostId(String postId) {
		List<Comment> comments = new ArrayList<Comment>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from comments where postid=?");
			preparedStatement.setString(1, postId);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Comment comment = new Comment();
				
				comment.setCommentid(rs.getString("commentid"));
				comment.setDetails(rs.getString("details"));
				comment.setDatetime(rs.getDate("datetime"));				
				comment.setPostid(rs.getString("postid"));
				comment.setUserid(rs.getString("userid"));
				
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comments;
	}
	
	public Comment getCommentById(String commentId) {
		Comment comment = new Comment();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from comments where commentid=?");
			preparedStatement.setString(1, commentId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
			
				comment.setCommentid(rs.getString("commentid"));
				comment.setDetails(rs.getString("details"));
				comment.setDatetime(rs.getDate("datetime"));				
				comment.setPostid(rs.getString("postid"));
				comment.setUserid(rs.getString("userid"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comment;
	}
	

}

