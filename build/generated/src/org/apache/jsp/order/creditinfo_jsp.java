package org.apache.jsp.order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class creditinfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<section id=\"creditInfo\">   \n");
      out.write("    <h1>Enter your credit card information</h1>\n");
      out.write("    <form action=\"#\" method=\"post\" name=\"creditinfoForm\" onsubmit=\"placeOrder();return false\" >\n");
      out.write("        <input type=\"hidden\" name=\"todo\" value=\"order\">   \n");
      out.write("        <label class=\"pad_top\">Credit card type</label>\n");
      out.write("        <select name=\"creditCardType\">\n");
      out.write("            <option value=\"Visa\">Visa</option>\n");
      out.write("            <option value=\"MasterCard\">MasterCard</option>\n");
      out.write("            <option value=\"AmericanExpress\">AmericanExpress</option>\n");
      out.write("        </select><br>\n");
      out.write("        <label class=\"pad_top\">Card Holder</label>\n");
      out.write("        <input type=\"text\" name=\"firstname\" placeHolder=\"firstname\">\n");
      out.write("        <input type=\"text\" name=\"lastname\" placeHolder=\"lastname\"><br>\n");
      out.write("        <label class=\"pad_top\">Credit card number</label>\n");
      out.write("        <input type=\"text\" name=\"creditCardNumber\" value=\"\"><br>\n");
      out.write("        <label class=\"pad_top\">Expiration Date</label>\n");
      out.write("        <select name=\"expirationMonth\">\n");
      out.write("            <option value=\"01\">01</option>\n");
      out.write("            <option value=\"02\">02</option>\n");
      out.write("            <option value=\"03\">03</option>\n");
      out.write("            <option value=\"04\">04</option>\n");
      out.write("            <option value=\"05\">05</option>\n");
      out.write("            <option value=\"06\">06</option>\n");
      out.write("            <option value=\"07\">07</option>\n");
      out.write("            <option value=\"08\">08</option>\n");
      out.write("            <option value=\"09\">09</option>\n");
      out.write("            <option value=\"10\">10</option>\n");
      out.write("            <option value=\"11\">11</option>\n");
      out.write("            <option value=\"12\">12</option>\n");
      out.write("        </select> /\n");
      out.write("        <select name=\"expirationYear\">\n");
      out.write("            <option value=\"2020\">2020</option>\n");
      out.write("            <option value=\"2021\">2021</option>\n");
      out.write("            <option value=\"2022\">2022</option>\n");
      out.write("            <option value=\"2023\">2023</option>\n");
      out.write("            <option value=\"2024\">2024</option>\n");
      out.write("            <option value=\"2025\">2025</option>\n");
      out.write("        </select><br>\n");
      out.write("\n");
      out.write("        <input type=\"submit\" value=\"Place Order\" class=\"margin_left\">\n");
      out.write("    </form>\n");
      out.write("</section>");
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
