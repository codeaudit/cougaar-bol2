package org.cougaar.tutorial.booksonline.web.orders;

import org.cougaar.tutorial.booksonline.web.model.ShoppingCart;
import org.cougaar.tutorial.booksonline.web.model.ShoppingCartItem;
import org.cougaar.tutorial.booksonline.web.WebConstants;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class cart extends HttpJspBase {


    static {
    }
    public cart( ) {
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

            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(0,74);to=(1,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(1,78);to=(2,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(2,68);to=(3,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(3,38);to=(4,0)]
                out.write("\r\n");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(4,2);to=(6,0)]
                
                ShoppingCart cart = (ShoppingCart)request.getAttribute("cart");
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(6,2);to=(7,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/header.html";from=(0,0);to=(65,0)]
                out.write("<html>\r\n\t<head>\r\n\t<style type=\"text/css\">\r\n<!--\r\n/* GENERIC */\r\nspan.wrapper { font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif }\r\nspan.title { color: #000000; font-size: 20px; font-weight: bold; font-family: verdana, sans-serif }\r\nspan.body, td, dd { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\nul, ol, blockquote, p { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\nh1 { color: #000000; font-weight: bold; font-size: 20px; font-family: verdana, sans-serif }\r\nh2 { color: #000000; font-weight: bold; font-size: 18px; font-family: verdana, sans-serif }\r\nh3 { color: #000000; font-weight: bold; font-size: 16px; font-family: verdana, sans-serif }\r\nh4 { color: #000000; font-weight: bold; font-size: 14px; font-family: verdana, sans-serif }\r\nh5 { color: #000000; font-weight: bold; font-size: 12px; font-family: verdana, sans-serif }\r\ndt { color: #000000; font-size: 13px; font-family: verdana, sans-serif; font-weight:bold; padding-top:5px }\r\n\r\na:link { color:#0000ff }\r\na:visited { color:#660066 }\r\na:active { color:#000000 }\r\n\r\na.tab:link { text-decoration: none; color: #333366; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab:active { text-decoration: none; color: #000000; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab:visited { text-decoration: none; color: #333366; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\n\r\na.tab2:link { text-decoration: none; color: #ffffff; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold}\r\na.tab2:active { text-decoration: none; color: #000000; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab2:visited { text-decoration: none; color: #ffffff; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\n\r\na.footer:link    { font-size: 10px; font-family: verdana, sans-serif; color:#0000ff }\r\na.footer:visited { font-size: 10px; font-family: verdana, sans-serif; color:#660066 }\r\na.footer:active  { font-size: 10px; font-family: verdana, sans-serif; color:#000000 }\r\n\r\nspan.copyright { color: #000000; font-size: 10px; font-family: verdana, sans-serif }\r\nspan.date      { color: #cccccc; font-size: 10px; font-family: verdana, sans-serif }\r\nspan.byline { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\n\r\ncode,tt,pre { font-family: courier, \"courier new\", monaco; font-size: 12px; color: #000000 }\r\n\r\ntd.dkgray { background:#999999 }\r\ntd.white { background:#ffffff }\r\ntd.yellow { background:#ffffcc }\r\n-->\r\n</style>\r\n\t\r\n\t\t<title>Books Online</title>\r\n\t</head>\r\n<body>\r\n\r\n<table width=\"800\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n\t<tr>\r\n\t\t<td colspan=\"2\"><b><font color=\"#666666\">BOL Demo</font></b></td>\r\n\t\t\r\n\t</tr>\r\n\t<tr><td colspan=\"2\">&nbsp;</td>\r\n\t</tr>\r\n\t<tr bgcolor=\"#CAE2F0\"><td colspan=\"2\">&nbsp;</td>\r\n\t</tr>\r\n\t<tr>\r\n\t\t<td width=\"100\" bgcolor=\"#CAE2F0\">&nbsp;</td>\r\n\t\t<td width=\"700\" bgcolor=\"#CAE2F0\">\r\n\t\t\t\r\n\t\t\r\n\t\t\r\n\r\n\t\t\t\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(7,44);to=(9,0)]
                out.write("\r\n\t\t<a href=\"bol\">Home</a> | <a href=\"bol\">Search</a>\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav.html";from=(0,0);to=(4,16)]
                out.write("\t</td>\r\n\t</tr>\r\n\t<tr>\r\n\t\t<td valign=\"top\" bgcolor=\"#CAE2F0\">\r\n\t\t\t<a href=\"bol?");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav.html";from=(4,19);to=(4,142)]
                out.print(org.cougaar.tutorial.booksonline.web.WebConstants.MODE+"="+org.cougaar.tutorial.booksonline.web.WebConstants.MODE_VIEW_CART);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav.html";from=(4,144);to=(6,16)]
                out.write("\">View cart</a><br><br>\r\n\t\t\tCheck orders Status(Coming Soon)<br>\r\n\t\t\t<a href=\"bol?");

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
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(9,45);to=(11,0)]
                out.write("\r\n\t\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav2.html";from=(0,0);to=(1,27)]
                out.write("</td>\r\n\t\t<td valign=\"top\"><center>");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(11,46);to=(13,2)]
                out.write("\r\n\t<table width=\"80%\" align=\"center\">\r\n\t\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(13,4);to=(13,73)]
                if(cart==null || cart.getItems()==null || cart.getItems().size()==0){
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(13,75);to=(15,2)]
                out.write("\r\n\t\t<tr><td>No items in cart</td></tr>\r\n\t\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(15,4);to=(17,2)]
                }else{
                			ArrayList items =(ArrayList)cart.getItems();
                		
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(17,4);to=(19,30)]
                out.write("\r\n\t\t<form action=\"bol\" method=\"get\">\r\n\t\t<input type=\"hidden\" value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(19,33);to=(19,62)]
                out.print(WebConstants.MODE_UPDATE_CART);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(19,64);to=(19,72)]
                out.write("\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(19,75);to=(19,92)]
                out.print(WebConstants.MODE);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(19,94);to=(32,2)]
                out.write("\">\r\n\t\t<tr>\r\n\t\t\t<td colspan=\"4\">\r\n\t\t\t\tShopping Cart:\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Title</td>\t\r\n\t\t\t<td>Quantity</td>\r\n\t\t\t<td>Remove from cart</td>\r\n\t\t\t<td>Unit Price</td>\r\n\t\t\t\r\n\t\t</tr>\r\n\t\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(32,4);to=(34,3)]
                for(int i=0;i<items.size();i++){
                			ShoppingCartItem item = (ShoppingCartItem)items.get(i);
                			
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(34,5);to=(35,31)]
                out.write("\r\n\t\t\t<input type=\"hidden\"  name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(35,34);to=(35,61)]
                out.print(WebConstants.BOOK_ISBN_NAME);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(35,63);to=(35,72)]
                out.write("\" value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(35,75);to=(35,89)]
                out.print(item.getIsbn());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(35,91);to=(37,8)]
                out.write("\">\r\n\t\t\t<tr>\r\n\t\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(37,11);to=(37,26)]
                out.print(item.getTitle());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(37,28);to=(38,42)]
                out.write("</td>\r\n\t\t\t\t<td><input size=\"2\" type=\"text\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(38,45);to=(38,59)]
                out.print(item.getIsbn());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(38,61);to=(38,62)]
                out.write(":");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(38,65);to=(38,91)]
                out.print(WebConstants.BOOK_QUANTITY);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(38,93);to=(38,102)]
                out.write("\" value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(38,105);to=(38,123)]
                out.print(item.getQuantity());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(38,125);to=(39,37)]
                out.write("\"></td>\r\n\t\t\t\t<td><input type=\"checkbox\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(39,40);to=(39,54)]
                out.print(item.getIsbn());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(39,56);to=(39,57)]
                out.write(":");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(39,60);to=(39,84)]
                out.print(WebConstants.BOOK_REMOVE);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(39,86);to=(40,23)]
                out.write("\"></td>\r\n\t\t\t\t<td align=\"right\">$");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(40,26);to=(40,41)]
                out.print(item.getPrice());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(40,43);to=(42,3)]
                out.write("</td>\r\n\t\t\t</tr>\r\n\t\t\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(42,5);to=(42,6)]
                }
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(42,8);to=(45,23)]
                out.write("\r\n\t\t\t<tr>\r\n\t\t\t\t<td colspan=\"3\" align=\"right\">Total</td>\r\n\t\t\t\t<td align=\"right\">$");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(45,26);to=(45,41)]
                out.print(cart.getTotal());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(45,43);to=(53,2)]
                out.write("</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td colspan=\"4\">\r\n\t\t\t\t\t<input type=\"submit\" value=\"Update Cart\">\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t</form>\r\n\t\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(53,4);to=(53,5)]
                }
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/cart.jsp";from=(53,7);to=(56,0)]
                out.write("\r\n\t\t\r\n\t</table>\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/footer.html";from=(0,0);to=(9,7)]
                out.write("\t\t</center>\r\n\t\t</td>\r\n\t</tr>\r\n<tr>\r\n\t<td>&nbsp;</td>\r\n\t<td align=\"right\"><i>Powered by Cougaar</i></td>\r\n</tr>\r\n</table>\r\n</body>\r\n</html>");

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
