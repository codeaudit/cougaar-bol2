<%@page import="org.cougaar.tutorial.booksonline.web.WebConstants"%>
<%@page import="org.cougaar.tutorial.booksonline.web.model.*"%>
<%@page import="java.util.ArrayList"%>
<%
ShoppingCart cart = (ShoppingCart)request.getAttribute("cart");

%>
<%@include file="../includes/header.html" %>
		<a href="bol">Home</a> | <a href="bol">Search</a>

<%@include file="../includes/sidenav.html" %>
	
<%@include file="../includes/sidenav2.html" %>
<%if(cart!=null && cart.getItems()!=null && cart.getItems().size()>0){
	ArrayList items = (ArrayList)cart.getItems();
%>
	<form action="bol" method="get">
	<input type="hidden" name="<%=WebConstants.MODE%>" value="<%=WebConstants.MODE_SUBMIT_ORDER%>">
	<table width="80%" align="center">
		<tr>
			<th colspan="5" align="left">Customer information</th>
		</tr>
		<tr>
			<td>Address 1</td>
			<td><input type="text" value="" name="<%=WebConstants.PAYMENT_ADDRESS_1%>"></td>
			<td>Address 2</td>
			<td><input type="text" value="" name="<%=WebConstants.PAYMENT_ADDRESS_2%>"></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>City</td>
			<td><input type="text" name="<%=WebConstants.CITY%>"></td>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td>State</td>
			<td><input type="text" name="<%=WebConstants.STATE%>">
			<td>Postal Code</td>
			<td><input type="text" name="<%=WebConstants.ZIP%>">
			<td>&nbsp;</td>
		</tr>
		<tr>
		
			<td colspan="5"><hr></td>
		</tr>
		<tr>
			<th colspan="5" align="left">Payment details</th>
		</tr>
		<tr>
			<td>Card Type</td>
			<td><select name="<%=WebConstants.PAYMENT_TYPE_NAME%>">
					<option value="<%=WebConstants.PAYMENT_MASTERCARD%>">MasterCard</option>
					<option value="<%=WebConstants.PAYMENT_VISA%>">Visa</option>
					<option value="<%=WebConstants.PAYMENT_AMERICAN_EXPRESS%>">AMEX</option>
				</select>
			</td>
			<td>Name on card</td>
			<td>
				<input type="text" name="<%=WebConstants.PAYMENT_NAME%>">
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Card Number <font color="red">* required</font></td>
			<td><input type="password" name="<%=WebConstants.PAYMENT_CARD_NUMBER%>"></td>
			<td>Card Expiration Data</td>
			<td><input type="text" name="<%=WebConstants.PAYMENT_CARD_EXPIRATION%>"></td>
			<td>(format: MM/YYYY)</td>
		<tr>
		
			<td colspan="5"><hr></td>
		</tr>
		<tr>
			<th colspan="5" align="left">Shipping details</th>
		</tr>
		<tr>
			<td>Shipping method</td>
			<td colspan="4" align="left">
				<select name="<%=WebConstants.SHIPPING_METHOD%>">
					<option value="<%=WebConstants.SHIPPING_GROUND%>">Ground</option>
					<option value="<%=WebConstants.SHIPPING_NEXTDAY%>">Next Day</option>
					<option value="<%=WebConstants.SHIPPING_2NDDAY%>">Second Day</option>
				</select>
			</td>
		</tr>
		<tr>
		
			<td colspan="5"><hr></td>
		</tr>
		<tr>
			<th colspan="5" align="left">Order details</th>
		</tr>
		<tr>	
			<td>ISBN</td>
			<td>Title</td>
			<td>Price</td>
			<td>Quantity</td>
			<td>Cost</td>
		</tr>
		<%for(int i=0;i<items.size();i++){
			ShoppingCartItem item = (ShoppingCartItem)items.get(i);
			%>
			<tr>
				<td><%=item.getIsbn()%></td>
				<td><%=item.getTitle()%></td>
				<td>$<%=item.getPrice()%></td>
				<td><%=item.getQuantity()%></td>
				<td>$<%=(item.getPrice() * item.getQuantity())%></td>
			</tr>
		<%}%>
		<tr>
			<td colspan="5" align="right">Total</td>
		</tr>
		<tr>
			<td colspan="5" align="right">$<%=cart.getTotal()%></td>
		</tr>	
		<tr>
		
			<td colspan="5">
				<input type="submit" value="Submit order">
			</td>
		</tr>
	
	</table>
	</form>
<%}else{%>
	No items in shopping Cart!
<%}%>
<%@include file="../includes/footer.html" %>
