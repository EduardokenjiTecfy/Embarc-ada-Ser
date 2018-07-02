package com.lexmark.example.customicon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.lexmark.prtapp.scan.ScanCancelledException;
import com.lexmark.prtapp.scan.ScanConsumer;
import com.lexmark.prtapp.scan.ScanData;

public class ScanDocuments implements ScanConsumer {

	public static void Execs(String categoria ,String idDocumento, byte[] arquivo)
			throws Exception {

		String URLFormat = "http://35.192.175.82/webTeste/RequestService.asmx/insertDocument";

		URL url = new URL(URLFormat);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.connect();
		Activator.getLog().debug("------------------------------------ENVIO------------------------------------");
		String es="" ;
		try{
		  es = base.encode(arquivo);
		Activator.getLog().debug(es);
		}catch (Exception e) {
			// TODO: handle exceptio
			Activator.getLog().debug("FDPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
		}
		String input = "{  \"id\": \"" + es + "\", \"ra\":\"" + idDocumento + "\", \"categoria\":\"" + categoria + "\"}";
		  //   String input = "{  \"id\": \"" + es + "\", \"ra\":\"" + idDocumento + "\"}";
		
	      // String input = "{  \"id\": " + es + ", \"ra\": " + idDocumento +  "}";
		//String input = "{  \"id\": \"" + es + "\"}";
		
		OutputStream outputStream = conn.getOutputStream();
		outputStream.write(input.getBytes());
		outputStream.flush();

		if (conn.getResponseCode() == 200) {

			JSONParser parser = new JSONParser();

			BufferedReader bufferReader = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), "UTF-8"));

			Object obj = parser.parse(bufferReader);

			JSONObject jsonObject = (JSONObject) obj;

			String isConcluid = (String) jsonObject.get("d");

			
			conn.disconnect();
		} else {

			conn.disconnect();

			Activator.getLog().debug(
					"Simplismente não funciono.\n\n \n\n Cod erro:"
							+ conn.getResponseCode() + "  \nMSG: "
							+ conn.getResponseMessage());
			throw new Exception();

		}

	}

	private File saveFile = null;

	public ScanDocuments(File file,String Ra,String categoria) {
		byte[] bytesArray = new byte[(int) file.length()];

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.read(bytesArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // read file into bytes[]
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// saveFile = file;

		try {
			Execs(categoria,Ra, bytesArray );
		} catch (Exception e) {
			Activator.getLog().debug(
					"ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO NO ENVIO");
			Activator.getLog().debug(
			e.getMessage());
		}

	}

	public void consume(ScanData data) {

		InputStream is = null;
		FileOutputStream fos = null;

		try {

			fos = new FileOutputStream(saveFile);

			while ((is = data.nextImageFile()) != null) {

				byte[] buff = new byte[16384];
				int bytesRead;

				while ((bytesRead = is.read(buff)) != -1) {

					fos.write(buff, 0, bytesRead);

				}

			}

		} catch (ScanCancelledException e) {

			Activator.getLog().debug("The scan has been CANCELLED!!");

		} catch (Exception e) {

			Activator.getLog().debug("Exception thrown while scanning", e);

		} finally {
			try {
				if (fos != null)

					fos.close();

			} catch (IOException ignore) {
				Activator.getLog().debug("Problem closing streams", ignore);

			}
		}

		Activator.getLog().debug("FileSaver.consume: Finished!");

	}

}
