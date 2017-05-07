package edu.awt.springmvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import edu.awt.springmvc.bean.Book;
import edu.awt.springmvc.bean.Issue;
import edu.awt.springmvc.bean.Member;

@Service
public class LMSService {
	
	@Autowired
	JdbcTemplate db;

	public List<Member> getAllMembers(){
		String sql = "SELECT * FROM members";
		return db.query(sql, new BeanPropertyRowMapper<Member>(Member.class));
	}
	
	public Member getMemberById(int memberid){
		String sql = "SELECT * FROM members WHERE memberid = ?";
		return db.queryForObject(sql, new Object[]{memberid}, new BeanPropertyRowMapper<Member>(Member.class));
	}

	public Book getBookById(int bookid){
		String sql = "SELECT * FROM books WHERE bookid = ?";
		return db.queryForObject(sql, new Object[]{bookid}, new BeanPropertyRowMapper<Book>(Book.class));
	}	
	
	public List<Issue> GetIssues(int memberid){
		String sql = "Select bookid,title from books where bookid IN(select bookid from issue where memberid =?)";
		return db.query(sql, new Object[]{memberid}, new BeanPropertyRowMapper<Issue>(Issue.class));
	}
	
	public List<Map<String, Object>> GetIssuesBook(int bookid){
		String sql = "select b.*,i.memberid from books b left outer join issue i on b.bookid = i.bookid where b.bookid = ?";
		return db.queryForList(sql, bookid);
	}
	
	public void InsertIssue(int memberid, int bookid){
		String sql = "Insert into issue(memberid, bookid) VALUES(?,?)";
		UpdateBook(1,bookid);
		db.update(sql, new Object[]{memberid,bookid});
	}
	
	public void UpdateBook(int status, int bookid){
		String sql ="update books SET status=? where bookid=?";
		db.update(sql,new Object[]{status,bookid});
	}
	
	public void ReturnBook(int memberid, int bookid){
		String sql = "Delete from issue where memberid=? AND bookid=?";
		UpdateBook(0,bookid);
		db.update(sql, new Object[]{memberid,bookid});
	}
			
}
