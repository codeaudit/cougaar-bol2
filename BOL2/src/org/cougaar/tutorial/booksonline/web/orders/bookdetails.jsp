<%@page import="org.cougaar.tutorial.booksonline.web.WebConstants"%>
<%@page import="org.cougaar.tutorial.booksonline.web.model.BookModel"%>
<%
BookModel book = (BookModel)request.getAttribute("book");

%>
<%@include file="../includes/header.html" %>
		<a href="bol">Home</a> | <a href="bol?<%=WebConstants.MODE%>=<%=WebConstants.MODE_BOOK_SEARCH%>">Search</a>
			| <a href="bol?<%=WebConstants.MODE%>=<%=WebConstants.MODE_CHECK_OUT%>">Checkout</a>
<%@include file="../includes/sidenav.html" %>
	
<%@include file="../includes/sidenav2.html" %>
	book details
	<form action="bol" method="get">
		<input type="hidden" name="<%=WebConstants.MODE%>" value="<%=WebConstants.MODE_ADD_TO_CART%>">
		<input type="hidden" name="<%=WebConstants.BOOK_ISBN_NAME%>" value="<%=book.getIsbn()%>">
	<%if(book!=null){%>
	<table>
		<tr>
			<td>Title</td>
			<td><%=book.getTitle()%></td>
		</tr>
		<tr>
			<td>By</td>
			<td><%=book.getAuthor()%></td>
		</tr>
		<tr>
			<td>Publisher</td>
			<td><%=book.getPublisher()%></td>
		</tr>
		<tr>
			<td>Publisher Notes</td>
			<td><%=book.getPublisherNotes()%></td>
		</tr>
		<tr>
			<td>Pages</td>
			<td><%=book.getLength()%></td>
		</tr>
		<tr>
			<td>TOC</td>
			<td><%=book.getTOC()%></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="Add To Card">
				<input type="button" value="Back to Results" onclick="javascript:history.back();">
			</td>
		</tr>	
		
	</table>
	<%}%>
	</form>
<%@include file="../includes/footer.html" %>