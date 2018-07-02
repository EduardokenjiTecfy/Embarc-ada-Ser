package com.lexmark.example.customicon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ListDocuments
{

   public static Document[] Exec(String urlwebservice, String ra, String codcurso) throws Exception
   {
     return Exec(urlwebservice,  ra, codcurso, false);
   }

   public static Document[] Exec(String urlwebservice, String ra, String codcurso, boolean Required) throws Exception
   {

      String URLFormat = urlwebservice + "/listDocument";

      URL url = new URL(URLFormat);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");

      conn.connect();

      String input = "{  \"ra\": " + ra + ", \"codcurso\": " + codcurso + ", \"requered\": " + Required + "}";

      OutputStream outputStream = conn.getOutputStream();

      outputStream.write(input.getBytes());

      outputStream.flush();

      if (conn.getResponseCode() == 200)
      {
         
         JSONParser parser = new JSONParser();

         BufferedReader bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

         Object obj = parser.parse(bufferReader);

         JSONObject jsonObject = (JSONObject) obj;
         JSONObject jsonaObject2 = (JSONObject) jsonObject.get("d");
         JSONArray jsonarray2 = (JSONArray) jsonaObject2.get("RetornoDocument");

         Document[] documenttypeitens = new Document[jsonarray2.size()];

         for (int k = 0; k < jsonarray2.size(); k++)
         {
            JSONObject jsonObjectInt2 = (JSONObject) jsonarray2.get(k);

            String cod = (String) jsonObjectInt2.get("Cod");
            String textname = (String) jsonObjectInt2.get("TextName");
            String name = (String) jsonObjectInt2.get("Name");
            
            documenttypeitens[k] = new Document(name, cod, textname);
         }

         conn.disconnect();
         return documenttypeitens;

      }
      else
      {
         conn.disconnect();
         Activator.getLog().debug("Simplismente não funciono");
         throw new Exception();
        
      }

   }
 
}