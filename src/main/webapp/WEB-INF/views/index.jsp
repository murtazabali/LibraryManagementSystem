<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>
<style type="text/css">
table, tr, td, th {
	border: 1px solid black;
	border-collapse: collapse;
}

.txt {
	
}

.ibook {
	
}

.isu{
}

.iret{
}
</style>
<script type="text/javascript" src="webjars/jquery/3.2.1/dist/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//alert("JQuery");
		$('.txt').keydown(function(e) {
			if (e.keyCode == 13) {
				e.preventDefault();
				$('#frmMember').submit();
			}
		});
		
		$('a.ibook').click(function(){
 			$('input[name=bookid]').val($(this).attr('bookid'));
			$('#bid').text($(this).text());
			$('#tr1').hide();
			$('#sp1').hide();
			$('a.iret').show();
		})
		
		
		$('a.iret').click(function(){
			$('#cmd').val($(this).text());
			$('#frmMember').submit();
		})

		
		$('a.isu').click(function(){
			$('#cmd').val($(this).text());
			$('#frmMember').submit();
		})

	});
</script>
</head>
<body>

	<form id="frmMember" method="post">
		<input type="hidden" id="cmd" name="cmd">
		<div style="float: left; padding-right: 15px">
			<table>
				<tr>
					<td>Member ID :</td>
					<td><input type="text" class="txt" name="memberid" value="${member.memberid}"></td>
				</tr>
				<tr>
					<td>Member Name: </td>
					<td>${member.membername}</td>
				</tr>
				<c:forEach var="issue" items="${issues}">
					<tr>
						<td>${issue.bookid}</td>
						<td><a href="#" class="ibook" bookid="${issue.bookid}">${issue.title}</a></td>
					</tr>
				</c:forEach>
			</table>
			<a href="#" class="iret" style="display: none">Return</a>
		</div>
		<div style="float: left">
			<table>
				<tr>
					<td>Book ID : </td>
					<td><input type="text" class="txt" name="bookid" value="${book.bookid}"></td>
				</tr>
				<tr>
					<td>Title :</td>
					<td id="bid">${book.title}</td>
				</tr>
				<c:if test="${book.status == 0}">
					<tr id="tr1">
						<td colspan="2" style="text-align: right"><a href="#" class="isu">Issue</a></td>
					</tr>
				</c:if>
			</table>
			<span style="color: red" id="sp1"><c:out value="${message}"></c:out></span>
		</div>
	</form>
</body>
</html>