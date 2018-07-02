package com.lexmark.example.customicon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

 


public class StudentByRa {

	public static Student[] find(String urlwebservice, String ra)
			  {
		Student[] students =null;
		try {
			String URLFormat = urlwebservice + "/findStudentByRa";

			URL url = new URL(URLFormat);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.connect();
			Activator.getLog().debug(
					"              HttpURLConnection                  "
							+ URLFormat);
			String input = "{  \"ra\": \"" + ra + "\"}";

			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(input.getBytes());
			outputStream.flush();

			if (conn.getResponseCode() == 200) {

				JSONParser parser = new JSONParser();

				BufferedReader bufferReader = new BufferedReader(
						new InputStreamReader(conn.getInputStream(), "UTF-8"));

				Object obj = parser.parse(bufferReader);
				JSONObject jsonObject = (JSONObject) obj;

				JSONObject jsonaObject2 = (JSONObject) jsonObject.get("d");
				JSONArray jsonarray2 = (JSONArray) jsonaObject2
						.get("RetornoStudent");

				Activator.getLog().debug("Antes");
				Activator.getLog().debug(
						"Retorno do array dos Students " + jsonarray2.size()
								+ "!!!!!!!!!!!!!!!!");

				 students = new Student[jsonarray2.size()];

				for (int k = 0; k < jsonarray2.size(); k++) {

					JSONObject jsonObjectInt2 = (JSONObject) jsonarray2.get(k);

					String codcentro = (String) jsonObjectInt2.get("CODCENTRO");

					String codcurso = (String) jsonObjectInt2.get("CODCURSO");

					String cpfaluno = (String) jsonObjectInt2.get("CPFALUNO");

					String nomealuno = (String) jsonObjectInt2.get("NOMEALUNO");

					String nomecentro = (String) jsonObjectInt2
							.get("NOMECENTRO");

					String nomecurso = (String) jsonObjectInt2.get("NOMECURSO");

					String raFind = (String) jsonObjectInt2.get("RA");

					String siglacentro = (String) jsonObjectInt2
							.get("SIGLACENTRO");

					Student student = new Student(cpfaluno, raFind, nomealuno,
							codcurso, nomecurso, codcentro, siglacentro,
							nomecentro);
					students[k] = student;

				}

				conn.disconnect();
				return students;

			} else {
				conn.disconnect();
				Activator.getLog().debug(
						"Falha na execução da busca StudentsByRa. \n\n");
				throw new Exception();

			}
		} catch (Exception e) {
			Activator.getLog().debug("eroooooooooooooooooooooooooooo   ");
			Activator.getLog().debug(e.getMessage());
			return students;
		}
	}
}