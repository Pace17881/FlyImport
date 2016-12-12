package beans;

import dtos.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import javax.ejb.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;

@Stateless
public class ReadFile implements ReadFileLocal
{

    @Override
    public ArrayList<FI_DTO> readIn(File inFile)
    {
        ArrayList<FI_DTO> lst = new ArrayList<>();

        String fName = inFile.getName();
        String ext = fName.substring(fName.lastIndexOf('.'), fName.length());
        if (ext.equals(".csv"))
            readCSV(inFile, lst);
        if (ext.equals(".xls"))
            readXLS(inFile, lst);
        if (ext.equals(".xlsx"))
            readXLSX(inFile, lst);

        return lst;
    }

    private void readCSV(File f, ArrayList<FI_DTO> lst)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(f));

            String line;
            int cnt = 0;
            long lines = getLineCount(f);

            while ((line = br.readLine()) != null)
            {
                if (cnt > 1 && cnt < lines - 1)
                {
                    FI_Buchung FI_B;
                    FI_Fluggesellschaft FI_FG;
                    FI_Fluglinie FI_FL;
                    FI_Flugzeug FI_FZ;
                    FI_Passagier FI_P;

                    String[] rowArr = strSplitTrim(line);
                    String[] strDauer = rowArr[10].split(":");

                    short hDauer, mDauer, dauer, linie, sitzeBelegt, anzahlSitze;
                    float preis = strToFloat(rowArr[12]);
                    int buchungNr, passagierNr;

                    hDauer = Short.parseShort(strDauer[0]);
                    mDauer = Short.parseShort(strDauer[1]);
                    linie = Short.parseShort(rowArr[3]);
                    sitzeBelegt = Short.parseShort(rowArr[15]);
                    anzahlSitze = Short.parseShort(rowArr[16]);
                    buchungNr = Integer.parseInt(rowArr[17]);
                    passagierNr = Integer.parseInt(rowArr[19]);

                    dauer = (short) (hDauer * 60 + mDauer);

                    FI_FG = new FI_Fluggesellschaft(rowArr[0], rowArr[1]);

                    FI_FL = new FI_Fluglinie(linie, dauer, sitzeBelegt, preis,
                            getDate(rowArr[11]), rowArr[4], rowArr[6],
                            rowArr[5], rowArr[7], rowArr[9], rowArr[8]);

                    FI_FZ = new FI_Flugzeug(rowArr[13], rowArr[14], anzahlSitze);

                    FI_B = new FI_Buchung(getDate(rowArr[18]), buchungNr);

                    FI_P = new FI_Passagier(passagierNr, rowArr[20], rowArr[21],
                            rowArr[22], rowArr[23], rowArr[24], rowArr[25]);

                    lst.add(new FI_DTO(FI_B, FI_FG, FI_FL, FI_FZ, FI_P));
                }
                cnt++;
            }
            br.close();
        }
        catch (NumberFormatException nfe)
        {
            throw new EJBException("Parsing-Fehler: " + nfe.getMessage());
        }
        catch (IOException ioe)
        {
            throw new EJBException("Dateifehler: " + ioe.getMessage());
        }

    }

    private void readXLS(File f, ArrayList<FI_DTO> lst)
    {
        try
        {
            FileInputStream file = new FileInputStream(f);

            //Get the workbook instance for XLS file 
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();

            for (int i = firstRow + 2; i <= lastRow - 1; i++)
            {
                HSSFRow row = sheet.getRow(i);

                String[] strDauer = row.getCell(10).getStringCellValue().trim().split(":");
                short hDauer = Short.parseShort(strDauer[0]);
                short mDauer = Short.parseShort(strDauer[1]);
                short dauer = (short) (hDauer * 60 + mDauer);

                FI_Fluggesellschaft FI_FG = new FI_Fluggesellschaft(
                        row.getCell(0).getStringCellValue(),
                        row.getCell(1).getStringCellValue());

                FI_Fluglinie FI_FL = new FI_Fluglinie(
                        (short) row.getCell(3).getNumericCellValue(),
                        dauer,
                        (short) row.getCell(15).getNumericCellValue(),
                        (float) row.getCell(12).getNumericCellValue(),
                        row.getCell(11).getDateCellValue(),
                        row.getCell(4).getStringCellValue(),
                        row.getCell(6).getStringCellValue(),
                        row.getCell(5).getStringCellValue(),
                        row.getCell(7).getStringCellValue(),
                        row.getCell(9).getStringCellValue(),
                        row.getCell(8).getStringCellValue());

                FI_Flugzeug FI_FZ = new FI_Flugzeug(
                        row.getCell(13).getStringCellValue(),
                        row.getCell(14).getStringCellValue(),
                        (short) row.getCell(16).getNumericCellValue());

                FI_Buchung FI_B = new FI_Buchung(
                        row.getCell(18).getDateCellValue(),
                        (int) row.getCell(17).getNumericCellValue());

                HSSFCell cellPlz = row.getCell(22);
                String plz = "";
                String anrede = "";
                String strasse = "";
                try
                {
                    cellPlz.setCellType(1);
                    plz = cellPlz.getStringCellValue();
                    anrede = row.getCell(20).getStringCellValue();
                    strasse = row.getCell(24).getStringCellValue();
                }
                catch (NullPointerException e)
                {
                }

                FI_Passagier FI_P = new FI_Passagier(
                        (int) row.getCell(19).getNumericCellValue(),
                        anrede,
                        row.getCell(21).getStringCellValue(),
                        plz,
                        row.getCell(23).getStringCellValue(),
                        strasse,
                        row.getCell(25).getStringCellValue());

                lst.add(new FI_DTO(FI_B, FI_FG, FI_FL, FI_FZ, FI_P));
            }

            file.close();
        }
        catch (FileNotFoundException e)
        {
            throw new EJBException(e.getMessage());
        }
        catch (IOException e)
        {
            throw new EJBException(e.getMessage());
        }
    }

    private void readXLSX(File f, ArrayList<FI_DTO> lst)
    {
        try
        {
            FileInputStream file = new FileInputStream(f);

            XSSFWorkbook workBook = new XSSFWorkbook(f);

            XSSFSheet sheet = workBook.getSheetAt(0);

            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();

            for (int i = firstRow + 2; i <= lastRow - 1; i++)
            {
                XSSFRow row = sheet.getRow(i);

                String[] strDauer = row.getCell(10).getStringCellValue().trim().split(":");
                short hDauer = Short.parseShort(strDauer[0]);
                short mDauer = Short.parseShort(strDauer[1]);
                short dauer = (short) (hDauer * 60 + mDauer);

                FI_Fluggesellschaft FI_FG = new FI_Fluggesellschaft(
                        row.getCell(0).getStringCellValue(),
                        row.getCell(1).getStringCellValue());

                FI_Fluglinie FI_FL = new FI_Fluglinie(
                        (short) row.getCell(3).getNumericCellValue(),
                        dauer,
                        (short) row.getCell(15).getNumericCellValue(),
                        (float) row.getCell(12).getNumericCellValue(),
                        row.getCell(11).getDateCellValue(),
                        row.getCell(4).getStringCellValue(),
                        row.getCell(6).getStringCellValue(),
                        row.getCell(5).getStringCellValue(),
                        row.getCell(7).getStringCellValue(),
                        row.getCell(9).getStringCellValue(),
                        row.getCell(8).getStringCellValue());

                FI_Flugzeug FI_FZ = new FI_Flugzeug(
                        row.getCell(13).getStringCellValue(),
                        row.getCell(14).getStringCellValue(),
                        (short) row.getCell(16).getNumericCellValue());

                FI_Buchung FI_B = new FI_Buchung(
                        row.getCell(18).getDateCellValue(),
                        (int) row.getCell(17).getNumericCellValue());

                XSSFCell cellPlz = row.getCell(22);
                String plz = "";
                String anrede = "";
                String strasse = "";
                try
                {
                    cellPlz.setCellType(1);
                    plz = cellPlz.getStringCellValue();
                    anrede = row.getCell(20).getStringCellValue();
                    strasse = row.getCell(24).getStringCellValue();
                }
                catch (NullPointerException e)
                {
                }

                FI_Passagier FI_P = new FI_Passagier(
                        (int) row.getCell(19).getNumericCellValue(),
                        anrede,
                        row.getCell(21).getStringCellValue(),
                        plz,
                        row.getCell(23).getStringCellValue(),
                        strasse,
                        row.getCell(25).getStringCellValue());

                lst.add(new FI_DTO(FI_B, FI_FG, FI_FL, FI_FZ, FI_P));
            }

            file.close();

        }
        catch (FileNotFoundException e)
        {
            throw new EJBException(e.getMessage());
        }
        catch (IOException | InvalidFormatException e)
        {
            throw new EJBException(e.getMessage());
        }
    }

    private String[] strSplitTrim(String s)
    {
        String[] sArr = s.split(";");

        for (int i = 0; i < sArr.length; i++)
            sArr[i] = sArr[i].trim();
        return sArr;
    }

    private long getLineCount(File f)
    {
        long lines = 0;

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(f));

            while (br.readLine() != null)
                lines++;
            br.close();

        }
        catch (IOException e)
        {
        }
        return lines;
    }

    private Date getDate(String date)
    {
        int year = Integer.parseInt(date.substring(6, 10));
        int month = Integer.parseInt(date.substring(3, 5));
        int day = Integer.parseInt(date.substring(0, 2));
        LocalDate ld = LocalDate.of(year, month, day);

        return java.sql.Date.valueOf(ld);
    }

    private float strToFloat(String s)
    {
        float preis = 0.0f;
        if (s.length() > 0)
        {
            String clean = s.replaceAll("\\.", "");
            clean = clean.replaceAll(",", ".");
            try
            {
                preis = Float.parseFloat(clean);
            }
            catch (NumberFormatException e)
            {
                throw new EJBException(e.getMessage());
            }
        }
        return preis;
    }
}
