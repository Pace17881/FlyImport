package servlets;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "Upload", urlPatterns =
{
    "/Upload"
})
@MultipartConfig
public class Upload extends HttpServlet
{

    private final static Logger LOGGER
            = Logger.getLogger(Upload.class.getCanonicalName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        final String path = request.getParameter("destination");
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try
        {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
            writer.println("Die Datei " + fileName + " wurde gespeichert unter " + path);
            LOGGER.log(Level.INFO, "Datei{0} wird hochgeladen in {1}",
                    new Object[]
                    {
                        fileName, path
                    });

            File inFile = new File(path + "\\" + fileName);
            
            HttpSession session = request.getSession();
            session.setAttribute("file", inFile);
            RequestDispatcher rd = request.getRequestDispatcher("Processing");
            rd.forward(request, response);
            
            inFile.delete();

        }
        catch (FileNotFoundException fne)
        {
            writer.println("Keine Datei übergeben oder Pfad existiert nicht!");
            writer.println("<br/> Fehler: " + fne.getMessage());

            LOGGER.log(Level.SEVERE, "Probleme während des hochladens. Error: {0}",
                    new Object[]
                    {
                        fne.getMessage()
                    });
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
            if (filecontent != null)
            {
                filecontent.close();
            }
            if (writer != null)
            {
                writer.close();
            }
        }
    }

    private String getFileName(final Part part)
    {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";"))
        {
            if (content.trim().startsWith("filename"))
            {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
