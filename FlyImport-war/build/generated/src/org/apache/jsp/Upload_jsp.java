package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Upload_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"de\">\n");
      out.write("    <head>\n");
      out.write("        <title>Fly2 - Buchungsdatenverarbeitung</title>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link href=\"css/style.css\" type=\"text/css\" rel=\"stylesheet\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1 class=\"ueber1\">Fly2 - Buchungsdatenverarbeitung</h1>\n");
      out.write("        \n");
      out.write("        <h2 class=\"ueber2\">Dieses Programm erlaubt das Importieren der Fluggesellschafts-Dateien in die Datenbank!<br /><br />\n");
      out.write("            .csv/.xls/.xlsx Dateien werden verarbeitet!\n");
      out.write("        </h2>\n");
      out.write("        <form method=\"POST\" action=\"Upload\" enctype=\"multipart/form-data\" >\n");
      out.write("           \n");
      out.write("            <label for=\"file\">Datei für Upload auswählen:</label>\n");
      out.write("            <input type=\"file\" \n");
      out.write("                   name=\"file\" \n");
      out.write("                   id=\"file\" \n");
      out.write("                   accept=\"file_extension, .csv, .xls, .xlsx\" />\n");
      out.write("            <label for=\"destination\">Ziel festlegen:</label>\n");
      out.write("            <input type=\"text\" \n");
      out.write("                   value=\"C:\\tmp\" \n");
      out.write("                   name=\"destination\"\n");
      out.write("                   id=\"destination\" />\n");
      out.write("            <input type=\"submit\" \n");
      out.write("                   value=\"Verarbeitung beginnen\" \n");
      out.write("                   name=\"upload\" \n");
      out.write("                   id=\"upload\" />\n");
      out.write("        </form>\n");
      out.write("        <p class=\"warn\">\n");
      out.write("            Bitte warten ! Der Ladevorgang kann einige Minuten dauern. \n");
      out.write("        </p>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
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
