package org.cougaar.tutorial.booksonline.web.orders;

import org.cougaar.tutorial.booksonline.web.model.BookModel;
import org.cougaar.tutorial.booksonline.web.WebConstants;
import java.util.ArrayList;
import org.cougaar.tutorial.booksonline.web.WebConstants;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class searchresults extends HttpJspBase {


    static {
    }
    public searchresults( ) {
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

            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(0,71);to=(1,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(1,68);to=(2,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(2,38);to=(3,0)]
                out.write("\r\n");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(3,2);to=(5,0)]
                
                	ArrayList books = (ArrayList)request.getAttribute("books");
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(5,2);to=(6,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(6,68);to=(7,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/header.html";from=(0,0);to=(65,0)]
                out.write("<html>\r\n\t<head>\r\n\t<style type=\"text/css\">\r\n<!--\r\n/* GENERIC */\r\nspan.wrapper { font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif }\r\nspan.title { color: #000000; font-size: 20px; font-weight: bold; font-family: verdana, sans-serif }\r\nspan.body, td, dd { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\nul, ol, blockquote, p { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\nh1 { color: #000000; font-weight: bold; font-size: 20px; font-family: verdana, sans-serif }\r\nh2 { color: #000000; font-weight: bold; font-size: 18px; font-family: verdana, sans-serif }\r\nh3 { color: #000000; font-weight: bold; font-size: 16px; font-family: verdana, sans-serif }\r\nh4 { color: #000000; font-weight: bold; font-size: 14px; font-family: verdana, sans-serif }\r\nh5 { color: #000000; font-weight: bold; font-size: 12px; font-family: verdana, sans-serif }\r\ndt { color: #000000; font-size: 13px; font-family: verdana, sans-serif; font-weight:bold; padding-top:5px }\r\n\r\na:link { color:#0000ff }\r\na:visited { color:#660066 }\r\na:active { color:#000000 }\r\n\r\na.tab:link { text-decoration: none; color: #333366; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab:active { text-decoration: none; color: #000000; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab:visited { text-decoration: none; color: #333366; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\n\r\na.tab2:link { text-decoration: none; color: #ffffff; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold}\r\na.tab2:active { text-decoration: none; color: #000000; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab2:visited { text-decoration: none; color: #ffffff; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\n\r\na.footer:link    { font-size: 10px; font-family: verdana, sans-serif; color:#0000ff }\r\na.footer:visited { font-size: 10px; font-family: verdana, sans-serif; color:#660066 }\r\na.footer:active  { font-size: 10px; font-family: verdana, sans-serif; color:#000000 }\r\n\r\nspan.copyright { color: #000000; font-size: 10px; font-family: verdana, sans-serif }\r\nspan.date      { color: #cccccc; font-size: 10px; font-family: verdana, sans-serif }\r\nspan.byline { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\n\r\ncode,tt,pre { font-family: courier, \"courier new\", monaco; font-size: 12px; color: #000000 }\r\n\r\ntd.dkgray { background:#999999 }\r\ntd.white { background:#ffffff }\r\ntd.yellow { background:#ffffcc }\r\n-->\r\n</style>\r\n\t\r\n\t\t<title>Books Online</title>\r\n\t</head>\r\n<body>\r\n\r\n<table width=\"800\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n\t<tr>\r\n\t\t<td colspan=\"2\"><b><font color=\"#666666\">BOL Demo</font></b></td>\r\n\t\t\r\n\t</tr>\r\n\t<tr><td colspan=\"2\">&nbsp;</td>\r\n\t</tr>\r\n\t<tr bgcolor=\"#CAE2F0\"><td colspan=\"2\">&nbsp;</td>\r\n\t</tr>\r\n\t<tr>\r\n\t\t<td width=\"100\" bgcolor=\"#CAE2F0\">&nbsp;</td>\r\n\t\t<td width=\"700\" bgcolor=\"#CAE2F0\">\r\n\t\t\t\r\n\t\t\r\n\t\t\r\n\r\n\t\t\t\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(7,44);to=(9,0)]
                out.write("\r\n\t<a href=\"bol\">Home</a>\r\n");

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
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(9,45);to=(11,0)]
                out.write("\r\n\t\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav2.html";from=(0,0);to=(1,27)]
                out.write("</td>\r\n\t\t<td valign=\"top\"><center>");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(11,46);to=(13,28)]
                out.write("\r\n<form action=\"bol\" method=\"get\">\r\n\t<input type=\"hidden\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(13,31);to=(13,48)]
                out.print(WebConstants.MODE);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(13,50);to=(13,59)]
                out.write("\" value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(13,62);to=(13,91)]
                out.print(WebConstants.MODE_ADD_TO_CART);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(13,93);to=(21,2)]
                out.write("\">\r\n\t<table width=\"80%\" align=\"center\">\t\r\n\t\t<tr>\r\n\t\t\t<td colspan=\"6\">Search Results</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Title</td><td>isbn</td><td>Author</td><td>price</td><td>Availability</td><td>Add to cart</td>\r\n\t\t</tr>\r\n\t\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(21,4);to=(23,3)]
                for(int i=0;i<books.size();i++){
                			BookModel book = (BookModel)books.get(i);
                			
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(23,5);to=(25,21)]
                out.write("\r\n\t\t\t<tr>\r\n\t\t\t\t<td><a href=\"bol?");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(25,24);to=(25,127)]
                out.print(WebConstants.MODE+"="+WebConstants.MODE_VIEW_DETAILS+"&"+WebConstants.BOOK_ISBN_NAME+"="+book.getIsbn());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(25,129);to=(25,131)]
                out.write("\">");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(25,134);to=(25,149)]
                out.print(book.getTitle());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(25,151);to=(26,8)]
                out.write("</a></td>\r\n\t\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(26,11);to=(26,25)]
                out.print(book.getIsbn());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(26,27);to=(27,8)]
                out.write("</td>\r\n\t\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(27,11);to=(27,27)]
                out.print(book.getAuthor());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(27,29);to=(28,8)]
                out.write("</td>\r\n\t\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(28,11);to=(28,29)]
                out.print(book.getOurPrice());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(28,31);to=(29,8)]
                out.write("</td>\r\n\t\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(29,10);to=(29,31)]
                if(book.isInStock()){
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(29,33);to=(29,41)]
                out.write("In Stock");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(29,43);to=(29,49)]
                }else{
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(29,51);to=(29,63)]
                out.write("Out of stock");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(29,65);to=(29,66)]
                }
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(29,68);to=(30,51)]
                out.write("\r\n\t\t\t\t<td align=\"right\"><input type=\"checkbox\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(30,54);to=(30,81)]
                out.print(WebConstants.BOOK_ISBN_NAME);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(30,83);to=(30,92)]
                out.write("\" value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(30,95);to=(30,109)]
                out.print(book.getIsbn());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(30,111);to=(32,2)]
                out.write("\"></td>\r\n\t\t\t</tr>\r\n\t\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(32,4);to=(32,5)]
                }
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(32,7);to=(33,2)]
                out.write("\r\n\t\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(33,4);to=(33,23)]
                if(books.size()>0){
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(33,25);to=(37,2)]
                out.write("\r\n\t\t\t<tr>\r\n\t\t\t\t<td colspan=\"6\"><input type=\"submit\" value=\"Add books to cart\"></td>\r\n\t\t\t</tr>\r\n\t\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(37,4);to=(37,5)]
                }
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/searchresults.jsp";from=(37,7);to=(40,0)]
                out.write("\r\n\t</table>\r\n\t</form>\r\n");

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
