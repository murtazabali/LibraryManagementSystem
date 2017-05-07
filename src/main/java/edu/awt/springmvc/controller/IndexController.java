package edu.awt.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.awt.springmvc.bean.Book;
import edu.awt.springmvc.service.LMSService;

@Controller
public class IndexController {
	
	@Autowired
	LMSService lms;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Index(){
		return "index";
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String Details(	@RequestParam(required=false) Integer memberid, 
							@RequestParam(required=false) Integer bookid,
							@RequestParam String cmd,
							ModelMap map){
		if(cmd.equals("Return")){
			lms.ReturnBook(memberid, bookid);
		}
		if(cmd.equals("Issue")){
			lms.InsertIssue(memberid, bookid);
		}
		if(memberid!=null){
			map.addAttribute("member", lms.getMemberById(memberid));
			map.addAttribute("issues", lms.GetIssues(memberid));
		}
		if(bookid != null){
			List<Map<String, Object>> list = lms.GetIssuesBook(bookid);		
			list.forEach(row ->{
				if((Integer)row.get("memberid")!= null){
					String message = "Already Issued to MemberID = "+(Integer)row.get("memberid")+"";
					map.addAttribute("message", message);
				}
				Book book = new Book();
				book.setBookid((Integer)row.get("bookid"));
				book.setTitle((String)row.get("title"));
				book.setStatus((Integer)row.get("status"));
				map.addAttribute("book", book);
			});
		}
		return "index";
	}
	

}
