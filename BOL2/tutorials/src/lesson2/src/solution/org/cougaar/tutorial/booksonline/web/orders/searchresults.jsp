<%@page import="org.cougaar.tutorial.booksonline.web.model.BookModel"%>
<%@page import="org.cougaar.tutorial.booksonline.web.WebConstants"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList books = (ArrayList)request.getAttribute("books");
%>
<%@page import="org.cougaar.tutorial.booksonline.web.WebConstants"%>
<%@include file="../includes/header.html" %>
	<a href="bol">Home</a>
<%@include file="../includes/sidenav.html" %>
	
<%@include file="../includes/sidenav2.html" %>
<form action="bol" method="get">
	<input type="hidden" name="<%=WebConstants.MODE%>" value="<%=WebConstants.MODE_ADD_TO_CART%>">
	<table width="80%" align="center">	
		<tr>
			<td colspan="6">Search Results</td>
		</tr>
		<tr>
			<td>Title</td><td>isbn</td><td>Author</td><td>price</td><td>Availability</td><td>Add to cart</td>
		</tr>
		<%for(int i=0;i<books.size();i++){
			BookModel book = (BookModel)books.get(i);
			%>
			<tr>
				<td><a href="bol?<%=WebConstants.MODE+"="+WebConstants.MODE_VIEW_DETAILS+"&"+WebConstants.BOOK_ISBN_NAME+"="+book.getIsbn()%>"><%=book.getTitle()%></a></td>
				<td><%=book.getIsbn()%></td>
				<td><%=book.getAuthor()%></td>
				<td><%=book.getOurPrice()%></td>
				<td><%if(book.isInStock()){%>In Stock<%}else{%>Out of stock<%}%>
				<td align="right"><input type="checkbox" name="<%=WebConstants.BOOK_ISBN_NAME%>" value="<%=book.getIsbn()%>"></td>
			</tr>
		<%}%>
		<%if(books.size()>0){%>
			<tr>
				<td colspan="6"><input type="submit" value="Add books to cart"></td>
			</tr>
		<%}%>
	</table>
	</form>
<%@include file="../includes/footer.html" %>