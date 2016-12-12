<%-- 
    Document   : Upload
    Created on : 02.09.2016, 11:52:46
    Author     : thiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="de">
    <head>
        <title>Fly2 - Buchungsdatenverarbeitung</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <h1 class="ueber1">Fly2 - Buchungsdatenverarbeitung</h1>
        <h2 class="ueber2">Dieses Programm erlaubt das Importieren der Fluggesellschafts-Dateien in die Datenbank!<br /><br />
            .csv/.xls/.xlsx Dateien werden verarbeitet!
        </h2>
        <form method="POST" action="Upload" enctype="multipart/form-data" >
            <label for="file">Datei für Upload auswählen:</label>
            <input type="file" 
                   name="file" 
                   id="file" 
                   accept="file_extension, .csv, .xls, .xlsx" />
            <label for="destination">Ziel festlegen:</label>
            <input type="text" 
                   value="C:\tmp" 
                   name="destination"
                   id="destination" />
            <input type="submit" 
                   value="Verarbeitung beginnen" 
                   name="upload" 
                   id="upload" />
        </form>
        <p class="warn">
            Bitte warten ! Der Ladevorgang kann einige Minuten dauern. 
        </p>
    </body>
</html>









