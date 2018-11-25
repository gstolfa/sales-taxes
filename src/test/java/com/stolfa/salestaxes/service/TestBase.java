package com.stolfa.salestaxes.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestBase {

	/**
	 * 
	 * @param file
	 * @return
	 */
	protected String readFile(String file){
		String jsonData = "";
		BufferedReader br = null;
		try {
			String line;
			InputStream inputStream = this.getClass().getResourceAsStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			br = new BufferedReader(inputStreamReader);
			while ((line = br.readLine()) != null) {
				jsonData += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return jsonData;
	}	
	
	/**
	 * 
	 * @param listFile
	 * @return
	 */
	protected Iterator<Object[]> getFromJson(String...listFile){
		
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		
		for (String fileName : listFile) {

			String str = readFile(fileName);
			
			JSONObject json =  new JSONObject(str);
			
			JSONArray jsArray = json.getJSONArray("data");


			for (int i = 0; i < jsArray.length(); i++) {
				JSONObject arr = jsArray.getJSONObject(i);
				data.add(new Object[]{arr});	
			}
			
		}
		
		return data.iterator();
	}
	

}
