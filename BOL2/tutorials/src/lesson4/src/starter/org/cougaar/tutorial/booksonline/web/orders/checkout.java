package org.cougaar.tutorial.booksonline.web.orders;

import org.cougaar.tutorial.booksonline.web.WebConstants;
import org.cougaar.tutorial.booksonline.web.model.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class checkout extends HttpJspBase {


    static {
    }
    public checkout( ) {
    }

    private static boolean _jspx_inited = false;

    public final void _jspx_init() throws org.apache.jasper.runtime.JspException {
    }

    public void _jspService(HttpServletRequest request, HttpServletResponse  response)
        throws java.io.IOException, ServletException {

        JspFactory _jspxFactory = null;
        PageContext pageContext = null;
        HttpSession session = null;
        ServletContext application = null;
        ServletConfig config = null;
        JspWriter out = null;
        Object page = this;
        String  _value = null;
        try {

            if (_jspx_inited == false) {
                synchronized (this) {
                    if (_jspx_inited == false) {
                        _jspx_init();
                        _jspx_inited = true;
                    }
                }
            }
            _jspxFactory = JspFactory.getDefaultFactory();
            response.setContentType("text/html;ISO-8859-1");
            pageContext = _jspxFactory.getPageContext(this, request, response,
            			"", true, 8192, true);

            application = pageContext.getServletContext();
            config = pageContext.getServletConfig();
            session = pageContext.getSession();
            out = pageContext.getOut();

            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(0,68);to=(1,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(1,63);to=(2,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(2,38);to=(3,0)]
                out.write("\r\n");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(3,2);to=(6,0)]
                
                ShoppingCart cart = (ShoppingCart)request.getAttribute("cart");
                
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(6,2);to=(7,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/header.html";from=(0,0);to=(65,0)]
                out.write("<html>\r\n\t<head>\r\n\t<style type=\"text/css\">\r\n<!--\r\n/* GENERIC */\r\nspan.wrapper { font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif }\r\nspan.title { color: #000000; font-size: 20px; font-weight: bold; font-family: verdana, sans-serif }\r\nspan.body, td, dd { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\nul, ol, blockquote, p { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\nh1 { color: #000000; font-weight: bold; font-size: 20px; font-family: verdana, sans-serif }\r\nh2 { color: #000000; font-weight: bold; font-size: 18px; font-family: verdana, sans-serif }\r\nh3 { color: #000000; font-weight: bold; font-size: 16px; font-family: verdana, sans-serif }\r\nh4 { color: #000000; font-weight: bold; font-size: 14px; font-family: verdana, sans-serif }\r\nh5 { color: #000000; font-weight: bold; font-size: 12px; font-family: verdana, sans-serif }\r\ndt { color: #000000; font-size: 13px; font-family: verdana, sans-serif; font-weight:bold; padding-top:5px }\r\n\r\na:link { color:#0000ff }\r\na:visited { color:#660066 }\r\na:active { color:#000000 }\r\n\r\na.tab:link { text-decoration: none; color: #333366; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab:active { text-decoration: none; color: #000000; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab:visited { text-decoration: none; color: #333366; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\n\r\na.tab2:link { text-decoration: none; color: #ffffff; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold}\r\na.tab2:active { text-decoration: none; color: #000000; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab2:visited { text-decoration: none; color: #ffffff; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\n\r\na.footer:link    { font-size: 10px; font-family: verdana, sans-serif; color:#0000ff }\r\na.footer:visited { font-size: 10px; font-family: verdana, sans-serif; color:#660066 }\r\na.footer:active  { font-size: 10px; font-family: verdana, sans-serif; color:#000000 }\r\n\r\nspan.copyright { color: #000000; font-size: 10px; font-family: verdana, sans-serif }\r\nspan.date      { color: #cccccc; font-size: 10px; font-family: verdana, sans-serif }\r\nspan.byline { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\n\r\ncode,tt,pre { font-family: courier, \"courier new\", monaco; font-size: 12px; color: #000000 }\r\n\r\ntd.dkgray { background:#999999 }\r\ntd.white { background:#ffffff }\r\ntd.yellow { background:#ffffcc }\r\n-->\r\n</style>\r\n\t\r\n\t\t<title>Books Online</title>\r\n\t</head>\r\n<body>\r\n\r\n<table width=\"800\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n\t<tr>\r\n\t\t<td colspan=\"2\"><b><font color=\"#666666\">BOL Demo</font></b></td>\r\n\t\t\r\n\t</tr>\r\n\t<tr><td colspan=\"2\">&nbsp;</td>\r\n\t</tr>\r\n\t<tr bgcolor=\"#CAE2F0\"><td colspan=\"2\">&nbsp;</td>\r\n\t</tr>\r\n\t<tr>\r\n\t\t<td width=\"100\" bgcolor=\"#CAE2F0\">&nbsp;</td>\r\n\t\t<td width=\"700\" bgcolor=\"#CAE2F0\">\r\n\t\t\t\r\n\t\t\r\n\t\t\r\n\r\n\t\t\t\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(7,44);to=(10,0)]
                out.write("\r\n\t\t<a href=\"bol\">Home</a> | <a href=\"bol\">Search</a>\r\n\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav.html";from=(0,0);to=(4,16)]
                out.write("\t</td>\r\n\t</tr>\r\n\t<tr>\r\n\t\t<td valign=\"top\" bgcolor=\"#CAE2F0\">\r\n\t\t\t<a href=\"bol?");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav.html";from=(4,19);to=(4,142)]
                out.print(org.cougaar.tutorial.booksonline.web.WebConstants.MODE+"="+org.cougaar.tutorial.booksonline.web.WebConstants.MODE_VIEW_CART);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav.html";from=(4,144);to=(6,16)]
                out.write("\">View cart</a><br><br>\r\n\t\t\t<a href=\"\">Check orders Status</a>(Coming Soon)<br>\r\n\t\t\t<a href=\"bol?");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav.html";from=(6,19);to=(6,36)]
                out.print(WebConstants.MODE);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav.html";from=(6,38);to=(6,39)]
                out.write("=");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav.html";from=(6,42);to=(6,69)]
                out.print(WebConstants.MODE_CHECK_OUT);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav.html";from=(6,71);to=(6,85)]
                out.write("\">Checkout</a>");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(10,45);to=(12,0)]
                out.write("\r\n\t\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav2.html";from=(0,0);to=(1,27)]
                out.write("</td>\r\n\t\t<td valign=\"top\"><center>");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(12,46);to=(13,0)]
                out.write("\r\n");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(13,2);to=(15,0)]
                if(cart!=null && cart.getItems()!=null && cart.getItems().size()>0){
                	ArrayList items = (ArrayList)cart.getItems();
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(15,2);to=(17,28)]
                out.write("\r\n\t<form action=\"bol\" method=\"get\">\r\n\t<input type=\"hidden\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(17,31);to=(17,48)]
                out.print(WebConstants.MODE);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(17,50);to=(17,59)]
                out.write("\" value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(17,62);to=(17,92)]
                out.print(WebConstants.MODE_SUBMIT_ORDER);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(17,94);to=(24,41)]
                out.write("\">\r\n\t<table width=\"80%\" align=\"center\">\r\n\t\t<tr>\r\n\t\t\t<th colspan=\"5\" align=\"left\">Customer information</th>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Address 1</td>\r\n\t\t\t<td><input type=\"text\" value=\"\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(24,44);to=(24,74)]
                out.print(WebConstants.PAYMENT_ADDRESS_1);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(24,76);to=(26,41)]
                out.write("\"></td>\r\n\t\t\t<td>Address 2</td>\r\n\t\t\t<td><input type=\"text\" value=\"\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(26,44);to=(26,74)]
                out.print(WebConstants.PAYMENT_ADDRESS_2);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(26,76);to=(31,32)]
                out.write("\"></td>\r\n\t\t\t<td>&nbsp;</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>City</td>\r\n\t\t\t<td><input type=\"text\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(31,35);to=(31,52)]
                out.print(WebConstants.CITY);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(31,54);to=(36,32)]
                out.write("\"></td>\r\n\t\t\t<td colspan=\"3\">&nbsp;</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>State</td>\r\n\t\t\t<td><input type=\"text\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(36,35);to=(36,53)]
                out.print(WebConstants.STATE);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(36,55);to=(38,32)]
                out.write("\">\r\n\t\t\t<td>Postal Code</td>\r\n\t\t\t<td><input type=\"text\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(38,35);to=(38,51)]
                out.print(WebConstants.ZIP);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(38,53);to=(50,21)]
                out.write("\">\r\n\t\t\t<td>&nbsp;</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\r\n\t\t\t<td colspan=\"5\"><hr></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<th colspan=\"5\" align=\"left\">Payment details</th>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Card Type</td>\r\n\t\t\t<td><select name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(50,24);to=(50,54)]
                out.print(WebConstants.PAYMENT_TYPE_NAME);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(50,56);to=(51,20)]
                out.write("\">\r\n\t\t\t\t\t<option value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(51,23);to=(51,54)]
                out.print(WebConstants.PAYMENT_MASTERCARD);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(51,56);to=(52,20)]
                out.write("\">MasterCard</option>\r\n\t\t\t\t\t<option value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(52,23);to=(52,48)]
                out.print(WebConstants.PAYMENT_VISA);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(52,50);to=(53,20)]
                out.write("\">Visa</option>\r\n\t\t\t\t\t<option value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(53,23);to=(53,60)]
                out.print(WebConstants.PAYMENT_AMERICAN_EXPRESS);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(53,62);to=(58,29)]
                out.write("\">AMEX</option>\r\n\t\t\t\t</select>\r\n\t\t\t</td>\r\n\t\t\t<td>Name on card</td>\r\n\t\t\t<td>\r\n\t\t\t\t<input type=\"text\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(58,32);to=(58,57)]
                out.print(WebConstants.PAYMENT_NAME);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(58,59);to=(64,36)]
                out.write("\">\r\n\t\t\t</td>\r\n\t\t\t<td>&nbsp;</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Card Number <font color=\"red\">* required</font></td>\r\n\t\t\t<td><input type=\"password\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(64,39);to=(64,71)]
                out.print(WebConstants.PAYMENT_CARD_NUMBER);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(64,73);to=(66,32)]
                out.write("\"></td>\r\n\t\t\t<td>Card Expiration Data</td>\r\n\t\t\t<td><input type=\"text\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(66,35);to=(66,71)]
                out.print(WebConstants.PAYMENT_CARD_EXPIRATION);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(66,73);to=(78,18)]
                out.write("\"></td>\r\n\t\t\t<td>(format: MM/YYYY)</td>\r\n\t\t<tr>\r\n\t\t\r\n\t\t\t<td colspan=\"5\"><hr></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<th colspan=\"5\" align=\"left\">Shipping details</th>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Shipping method</td>\r\n\t\t\t<td colspan=\"4\" align=\"left\">\r\n\t\t\t\t<select name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(78,21);to=(78,49)]
                out.print(WebConstants.SHIPPING_METHOD);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(78,51);to=(79,20)]
                out.write("\">\r\n\t\t\t\t\t<option value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(79,23);to=(79,51)]
                out.print(WebConstants.SHIPPING_GROUND);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(79,53);to=(80,20)]
                out.write("\">Ground</option>\r\n\t\t\t\t\t<option value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(80,23);to=(80,52)]
                out.print(WebConstants.SHIPPING_NEXTDAY);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(80,54);to=(81,20)]
                out.write("\">Next Day</option>\r\n\t\t\t\t\t<option value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(81,23);to=(81,51)]
                out.print(WebConstants.SHIPPING_2NDDAY);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(81,53);to=(99,2)]
                out.write("\">Second Day</option>\r\n\t\t\t\t</select>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\r\n\t\t\t<td colspan=\"5\"><hr></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<th colspan=\"5\" align=\"left\">Order details</th>\r\n\t\t</tr>\r\n\t\t<tr>\t\r\n\t\t\t<td>ISBN</td>\r\n\t\t\t<td>Title</td>\r\n\t\t\t<td>Price</td>\r\n\t\t\t<td>Quantity</td>\r\n\t\t\t<td>Cost</td>\r\n\t\t</tr>\r\n\t\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(99,4);to=(101,3)]
                for(int i=0;i<items.size();i++){
                			ShoppingCartItem item = (ShoppingCartItem)items.get(i);
                			
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(101,5);to=(103,8)]
                out.write("\r\n\t\t\t<tr>\r\n\t\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(103,11);to=(103,25)]
                out.print(item.getIsbn());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(103,27);to=(104,8)]
                out.write("</td>\r\n\t\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(104,11);to=(104,26)]
                out.print(item.getTitle());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(104,28);to=(105,9)]
                out.write("</td>\r\n\t\t\t\t<td>$");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(105,12);to=(105,27)]
                out.print(item.getPrice());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(105,29);to=(106,8)]
                out.write("</td>\r\n\t\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(106,11);to=(106,29)]
                out.print(item.getQuantity());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(106,31);to=(107,9)]
                out.write("</td>\r\n\t\t\t\t<td>$");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(107,12);to=(107,50)]
                out.print((item.getPrice() * item.getQuantity()));
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(107,52);to=(109,2)]
                out.write("</td>\r\n\t\t\t</tr>\r\n\t\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(109,4);to=(109,5)]
                }
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(109,7);to=(114,34)]
                out.write("\r\n\t\t<tr>\r\n\t\t\t<td colspan=\"5\" align=\"right\">Total</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td colspan=\"5\" align=\"right\">$");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(114,37);to=(114,52)]
                out.print(cart.getTotal());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(114,54);to=(125,0)]
                out.write("</td>\r\n\t\t</tr>\t\r\n\t\t<tr>\r\n\t\t\r\n\t\t\t<td colspan=\"5\">\r\n\t\t\t\t<input type=\"submit\" value=\"Submit order\">\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\r\n\t</table>\r\n\t</form>\r\n");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(125,2);to=(125,8)]
                }else{
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(125,10);to=(127,0)]
                out.write("\r\n\tNo items in shopping Cart!\r\n");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(127,2);to=(127,3)]
                }
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(127,5);to=(128,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/footer.html";from=(0,0);to=(9,7)]
                out.write("\t\t</center>\r\n\t\t</td>\r\n\t</tr>\r\n<tr>\r\n\t<td>&nbsp;</td>\r\n\t<td align=\"right\"><i>Powered by Cougaar</i></td>\r\n</tr>\r\n</table>\r\n</body>\r\n</html>");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/checkout.jsp";from=(128,44);to=(129,0)]
                out.write("\r\n");

            // end

        } catch (Throwable t) {
            if (out != null && out.getBufferSize() != 0)
                out.clearBuffer();
            if (pageContext != null) pageContext.handlePageException(t);
        } finally {
            if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
        }
    }
}
