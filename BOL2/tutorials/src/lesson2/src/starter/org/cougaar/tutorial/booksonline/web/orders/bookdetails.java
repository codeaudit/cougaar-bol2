package org.cougaar.tutorial.booksonline.web.orders;

import org.cougaar.tutorial.booksonline.web.WebConstants;
import org.cougaar.tutorial.booksonline.web.model.BookModel;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class bookdetails extends HttpJspBase {


    static {
    }
    public bookdetails( ) {
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

            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(0,68);to=(1,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(1,71);to=(2,0)]
                out.write("\r\n");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(2,2);to=(5,0)]
                
                BookModel book = (BookModel)request.getAttribute("book");
                
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(5,2);to=(6,0)]
                out.write("\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/header.html";from=(0,0);to=(65,0)]
                out.write("<html>\r\n\t<head>\r\n\t<style type=\"text/css\">\r\n<!--\r\n/* GENERIC */\r\nspan.wrapper { font-size: 13px; font-family: Verdana, Arial, Helvetica, sans-serif }\r\nspan.title { color: #000000; font-size: 20px; font-weight: bold; font-family: verdana, sans-serif }\r\nspan.body, td, dd { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\nul, ol, blockquote, p { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\nh1 { color: #000000; font-weight: bold; font-size: 20px; font-family: verdana, sans-serif }\r\nh2 { color: #000000; font-weight: bold; font-size: 18px; font-family: verdana, sans-serif }\r\nh3 { color: #000000; font-weight: bold; font-size: 16px; font-family: verdana, sans-serif }\r\nh4 { color: #000000; font-weight: bold; font-size: 14px; font-family: verdana, sans-serif }\r\nh5 { color: #000000; font-weight: bold; font-size: 12px; font-family: verdana, sans-serif }\r\ndt { color: #000000; font-size: 13px; font-family: verdana, sans-serif; font-weight:bold; padding-top:5px }\r\n\r\na:link { color:#0000ff }\r\na:visited { color:#660066 }\r\na:active { color:#000000 }\r\n\r\na.tab:link { text-decoration: none; color: #333366; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab:active { text-decoration: none; color: #000000; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab:visited { text-decoration: none; color: #333366; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\n\r\na.tab2:link { text-decoration: none; color: #ffffff; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold}\r\na.tab2:active { text-decoration: none; color: #000000; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\na.tab2:visited { text-decoration: none; color: #ffffff; font-size: 14px; font-family: verdana, sans-serif; font-weight:bold }\r\n\r\na.footer:link    { font-size: 10px; font-family: verdana, sans-serif; color:#0000ff }\r\na.footer:visited { font-size: 10px; font-family: verdana, sans-serif; color:#660066 }\r\na.footer:active  { font-size: 10px; font-family: verdana, sans-serif; color:#000000 }\r\n\r\nspan.copyright { color: #000000; font-size: 10px; font-family: verdana, sans-serif }\r\nspan.date      { color: #cccccc; font-size: 10px; font-family: verdana, sans-serif }\r\nspan.byline { color: #000000; font-size: 13px; font-family: verdana, sans-serif }\r\n\r\ncode,tt,pre { font-family: courier, \"courier new\", monaco; font-size: 12px; color: #000000 }\r\n\r\ntd.dkgray { background:#999999 }\r\ntd.white { background:#ffffff }\r\ntd.yellow { background:#ffffcc }\r\n-->\r\n</style>\r\n\t\r\n\t\t<title>Books Online</title>\r\n\t</head>\r\n<body>\r\n\r\n<table width=\"800\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n\t<tr>\r\n\t\t<td colspan=\"2\"><b><font color=\"#666666\">BOL Demo</font></b></td>\r\n\t\t\r\n\t</tr>\r\n\t<tr><td colspan=\"2\">&nbsp;</td>\r\n\t</tr>\r\n\t<tr bgcolor=\"#CAE2F0\"><td colspan=\"2\">&nbsp;</td>\r\n\t</tr>\r\n\t<tr>\r\n\t\t<td width=\"100\" bgcolor=\"#CAE2F0\">&nbsp;</td>\r\n\t\t<td width=\"700\" bgcolor=\"#CAE2F0\">\r\n\t\t\t\r\n\t\t\r\n\t\t\r\n\r\n\t\t\t\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(6,44);to=(7,40)]
                out.write("\r\n\t\t<a href=\"bol\">Home</a> | <a href=\"bol?");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(7,43);to=(7,60)]
                out.print(WebConstants.MODE);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(7,62);to=(7,63)]
                out.write("=");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(7,66);to=(7,95)]
                out.print(WebConstants.MODE_BOOK_SEARCH);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(7,97);to=(8,18)]
                out.write("\">Search</a>\r\n\t\t\t| <a href=\"bol?");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(8,21);to=(8,38)]
                out.print(WebConstants.MODE);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(8,40);to=(8,41)]
                out.write("=");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(8,44);to=(8,71)]
                out.print(WebConstants.MODE_CHECK_OUT);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(8,73);to=(9,0)]
                out.write("\">Checkout</a>\r\n");

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
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(9,45);to=(11,0)]
                out.write("\r\n\t\r\n");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/../includes/sidenav2.html";from=(0,0);to=(1,27)]
                out.write("</td>\r\n\t\t<td valign=\"top\"><center>");

            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(11,46);to=(14,29)]
                out.write("\r\n\tbook details\r\n\t<form action=\"bol\" method=\"get\">\r\n\t\t<input type=\"hidden\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(14,32);to=(14,49)]
                out.print(WebConstants.MODE);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(14,51);to=(14,60)]
                out.write("\" value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(14,63);to=(14,92)]
                out.print(WebConstants.MODE_ADD_TO_CART);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(14,94);to=(15,29)]
                out.write("\">\r\n\t\t<input type=\"hidden\" name=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(15,32);to=(15,59)]
                out.print(WebConstants.BOOK_ISBN_NAME);
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(15,61);to=(15,70)]
                out.write("\" value=\"");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(15,73);to=(15,87)]
                out.print(book.getIsbn());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(15,89);to=(16,1)]
                out.write("\">\r\n\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(16,3);to=(16,18)]
                if(book!=null){
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(16,20);to=(20,7)]
                out.write("\r\n\t<table>\r\n\t\t<tr>\r\n\t\t\t<td>Title</td>\r\n\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(20,10);to=(20,25)]
                out.print(book.getTitle());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(20,27);to=(24,7)]
                out.write("</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>By</td>\r\n\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(24,10);to=(24,26)]
                out.print(book.getAuthor());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(24,28);to=(28,7)]
                out.write("</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Publisher</td>\r\n\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(28,10);to=(28,29)]
                out.print(book.getPublisher());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(28,31);to=(32,7)]
                out.write("</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Publisher Notes</td>\r\n\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(32,10);to=(32,34)]
                out.print(book.getPublisherNotes());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(32,36);to=(36,7)]
                out.write("</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Pages</td>\r\n\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(36,10);to=(36,26)]
                out.print(book.getLength());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(36,28);to=(40,7)]
                out.write("</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>TOC</td>\r\n\t\t\t<td>");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(40,10);to=(40,23)]
                out.print(book.getTOC());
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(40,25);to=(50,1)]
                out.write("</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td colspan=\"2\">\r\n\t\t\t\t<input type=\"submit\" value=\"Add To Cart\">\r\n\t\t\t\t<input type=\"button\" value=\"Back to Results\" onclick=\"javascript:history.back();\">\r\n\t\t\t</td>\r\n\t\t</tr>\t\r\n\t\t\r\n\t</table>\r\n\t");

            // end
            // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(50,3);to=(50,4)]
                }
            // end
            // HTML // begin [file="/org/cougaar/tutorial/booksonline/web/orders/bookdetails.jsp";from=(50,6);to=(52,0)]
                out.write("\r\n\t</form>\r\n");

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
