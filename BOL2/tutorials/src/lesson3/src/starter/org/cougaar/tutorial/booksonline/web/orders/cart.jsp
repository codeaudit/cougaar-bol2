<%@page import="org.cougaar.tutorial.booksonline.web.model.ShoppingCart"%>
<%@page import="org.cougaar.tutorial.booksonline.web.model.ShoppingCartItem"%>
<%@page import="org.cougaar.tutorial.booksonline.web.WebConstants"%>
<%@page import="java.util.ArrayList"%>
<%
ShoppingCart cart = (ShoppingCart)request.getAttribute("cart");
%>
<%@include file="../includes/header.html" %>
		<a href="bol">Home</a> | <a href="bol">Search</a>
<%@include file="../includes/sidenav.html" %>
	
<%@include file="../includes/sidenav2.html" %>
	<table width="80%" align="center">
		<%if(cart==null || cart.getItems()==null || cart.getItems().size()==0){%>
		<tr><td>No items in cart</td></tr>
		<%}else{
			ArrayList items =(ArrayList)cart.getItems();
		%>
		<form action="bol" method="get">
		<input type="hidden" value="<%=WebConstants.MODE_UPDATE_CART%>" name="<%=WebConstants.MODE%>">
		<tr>
			<td colspan="4">
				Shopping Cart:
			</td>
		</tr>
		<tr>
			<td>Title</td>	
			<td>Quantity</td>
			<td>Remove from cart</td>
			<td>Unit Price</td>
			
		</tr>
		<%for(int i=0;i<items.size();i++){
			ShoppingCartItem item = (ShoppingCartItem)items.get(i);
			%>
			<input type="hidden"  name="<%=WebConstants.BOOK_ISBN_NAME%>" value="<%=item.getIsbn()%>">
			<tr>
				<td><%=item.getTitle()%></td>
				<td><input size="2" type="text" name="<%=item.getIsbn()%>:<%=WebConstants.BOOK_QUANTITY%>" value="<%=item.getQuantity()%>"></td>
				<td><input type="checkbox" name="<%=item.getIsbn()%>:<%=WebConstants.BOOK_REMOVE%>"></td>
				<td align="right">$<%=item.getPrice()%></td>
			</tr>
			<%}%>
			<tr>
				<td colspan="3" align="right">Total</td>
				<td align="right">$<%=cart.getTotal()%></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="submit" value="Update Cart">
				</td>
			</tr>
		</form>
		<%}%>
		
	</table>
<%@include file="../includes/footer.html" %>