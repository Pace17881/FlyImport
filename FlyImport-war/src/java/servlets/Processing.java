package servlets;

import beans.*;
import dtos.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Processing", urlPatterns =
{
    "/Processing"
})
public class Processing extends HttpServlet
{

    @EJB
    private ReadFileLocal readFile;
    @EJB
    private MoveDBLocal moveDB;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"de\">");
        out.println("<head>");
        out.println("<title>Servlet Processing</title>");
        out.println("<link href=\"css/style.css\" type=\"text/css\" rel=\"stylesheet\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 class=\"ueber1\">Ausgabe</h1>");
        HttpSession session = request.getSession();
        //File inFile = new File("C:\\tmp\\AA.csv");
        File inFile = (File) session.getAttribute("file");

        if (inFile.exists())
        {
            ArrayList<FI_DTO> lst = readFile.readIn(inFile);
            Report_DTO rep = moveDB.persistData(lst);

            outputLists(rep, out);
        } else
        {
            out.println("<p>Die angegebene Datei wurde nicht gefunden</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    private void outputLists(Report_DTO rep, PrintWriter out)
    {
        
        String meldung = " Keine Datensätze ";

        out.println("<h2 class=\"ueber2\">Erfolgreich importiert: " + rep.getGoodList().size() + "</h2>");
        if (!rep.getGoodList().isEmpty())
        {
            ausgabeVerarb(rep.getGoodList(), out);
        } else
        {
            out.println("<p>***" + meldung + "***</p>");
        }

        out.println("<h2 class=\"ueber2\">Doppelte Buchungen: " + rep.getDoppList().size() + "</h2>");
        if (!rep.getDoppList().isEmpty())
        {
            ausgabeVerarb(rep.getDoppList(),out);
        } else
        {
            out.println("<p>***" + meldung + "***</p>");
        }

        out.println("<h2 class=\"ueber2\">Fehlerhaft: " + rep.getErrList().size() + "</h2>");
        if (!rep.getErrList().isEmpty())
        {
            ausgabeVerarb(rep.getErrList(),out);
        } else
        {
            out.println("<p>***" + meldung + "***</p>");
        }

    }

    private void ausgabeVerarb(ArrayList<FI_DTO> liste, PrintWriter out)
    {
        int gesanzahl = liste.size();
        out.println("</table>");

        out.println("</p>");

        out.println("<table class=\"grund\">");

        out.println("<tr class=\"kopfzeile\">");
        out.println("<th class=\"tabkopf\">Lf_Nr</th>");
        out.println("<th class=\"tabkopf\">Passagier_NR</th>");
        out.println("<th class=\"tabkopf\">Name</th>");
        out.println("<th class=\"tabkopf\">Linie_ID</th>");
        out.println("<th class=\"tabkopf\">ST_FH_ID</th>");
        out.println("<th class=\"tabkopf\">ZI_FH_ID</th>");
        out.println("<th class=\"tabkopf\">Gesellschaft_Name</th>");
        out.println("<th class=\"tabkopf\">Buchung_Datum</th>");
        out.println("<th class=\"tabkopf\">Abflug_Datum</th>");
        out.println("</tr>");

        int laufnr = 1;

        int geteilt;

        if (gesanzahl > 100)
        {
            geteilt = gesanzahl / 100;
        } else
        {
            geteilt = 1;
        }

        for (int i = 0; i < gesanzahl; i += geteilt)
        {

            FI_Buchung FI_B = liste.get(i).getFI_B();
            FI_Fluggesellschaft FI_FG = liste.get(i).getFI_FG();
            FI_Fluglinie FI_FL = liste.get(i).getFI_FL();
            FI_Passagier FI_P = liste.get(i).getFI_P();

            out.println("<tr class=\"dattupel\">");
            out.println("<td class=\"tdrechts\">" + laufnr++ + "</td>");
            out.println("<td class=\"tdmitte\">" + FI_P.getPassagierNR() + "</td>");
            out.println("<td class=\"tdlinks\">" + FI_P.getName() + "</td>");
            out.println("<td class=\"tdmitte\">" + FI_FL.getLinie() + "</td>");
            out.println("<td class=\"tdmitte\">" + FI_FL.getStartStadt() + "</td>");
            out.println("<td class=\"tdmitte\">" + FI_FL.getZielStadt() + "</td>");
            out.println("<td class=\"tdmitte\">" + FI_FG.getGesellschaftName() + "</td>");
            out.println("<td class=\"tdmitte\">" + FI_B.getBuchungDatum() + "</td>");
            out.println("<td class=\"tdmitte\">" + FI_FL.getAbflugDatum() + "</td>");
            out.println("</tr>");

        }

        out.println("</table>");

        out.println("<p>");
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
