<%@page import="org.cougaar.tutorial.booksonline.web.WebConstants"%>
<%@include file="../includes/header.html" %>
	<!--top navs-->
<%@include file="../includes/sidenav.html" %>
	
<%@include file="../includes/sidenav2.html" %>
	<table>
		<tr>
			<td colspan="2">
				Welcome to BOL, enter your search criteria below
			</td>
		</tr>
		<form action="bol" method="Get">
		<input type="hidden" name="mode" value="<%=WebConstants.MODE_BOOK_SEARCH%>">
		<tr>
			<td>Search for keyword in</td>
			<td>
				<SELECT NAME="searchType" SIZE="1">
				<OPTION value="Title" SELECTED>Title</OPTION>
				<OPTION value="Author">Author</OPTION>
				<OPTION value="Subject">Subject</OPTION>
			</td>
		</tr>
		<tr>
			<td>Keyword:</td>
			<td><INPUT NAME="keyword" TYPE="TEXT"></td>
		</tr>
		<tr>
			<td colspan="2">
				<INPUT TYPE="Submit" Value="Search">
			</td>
		</tr>
		</form>
	</table>
			
	
<%@include file="../includes/footer.html" %>

