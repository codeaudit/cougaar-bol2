<%@page import="org.cougaar.tutorial.booksonline.web.WebConstants"%>
<%@page import="org.cougaar.core.util.UID"%>
<%
UID uid = (UID)request.getAttribute("UID");
%>
<%@include file="../includes/header.html" %>
	<a href="bol">Home</a> | <a href="bol">Search</a>
<%@include file="../includes/sidenav.html" %>
<%@include file="../includes/sidenav2.html" %>
	<table>
		<tr>
			<td><h3>Order Submitted: <%=uid.toString()%></h3></td>
		</tr>
	</table>
			
	
<%@include file="../includes/footer.html" %>