package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class books_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("    <h2>Insert new book</h2>\r\n");
      out.write("    <form action=\"#\" name=\"bookForm\" method=\"post\" onsubmit=\";return false;\">\r\n");
      out.write("        <table>\r\n");
      out.write("            <thead>\r\n");
      out.write("            <th>id</th>\r\n");
      out.write("            <th>title</th>\r\n");
      out.write("            <th>author</th>\r\n");
      out.write("            <th>price</th>\r\n");
      out.write("            <th>inventory</th>\r\n");
      out.write("            <th>imgURL</th>\r\n");
      out.write("            <th>location</th>\r\n");
      out.write("            <th>vendor</th>\r\n");
      out.write("            <th>owner</th>\r\n");
      out.write("            </thead>\r\n");
      out.write("            <tbody>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td><input type=\"text\" name=\"id\" ></td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"title\" ></td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"author\" ></td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"price\" ></td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"inventory\" ></td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"imgURL\" ></td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"location\" ></td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"vendor\" ></td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"owner\" ></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("            </tbody>\r\n");
      out.write("        </table>\r\n");
      out.write("        <input type=\"submit\" value=\"Insert\">\r\n");
      out.write("\r\n");
      out.write("        \r\n");
      out.write("    </form>\r\n");
      out.write("    <form action=\"reports.htm\" method=\"post\">\r\n");
      out.write("        <input type=\"submit\" value=\"Reports\">\r\n");
      out.write("    </form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
