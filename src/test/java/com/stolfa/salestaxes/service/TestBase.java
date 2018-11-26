package com.stolfa.salestaxes.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.stolfa.salestaxes.model.Item;
import com.stolfa.salestaxes.util.ItemBuilder;

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
	
	/**
	 * Return new Item instance, reading json object.
	 * 
	 * @param dato
	 * @param index
	 * @return
	 */
	protected Item readItemFromJson(JSONObject dato, int index){
		final String isExemptTaskKey = "item_" + index + ".isExemptTax";
		final String isImportedKey = "item_" + index + ".isImported";
		final String nameKey = "item_" + index + ".name";
		final String quantityKey = "item_" + index + ".quantity";
		final String priceKey = "item_" + index + ".price";
		
		Item item = new ItemBuilder().with($ -> {
			$.isExemptTax = dato.getBoolean(isExemptTaskKey);
			$.isImported = dato.getBoolean(isImportedKey);
		}).with($ -> {
			$.name = dato.getString(nameKey);
			$.quantity = dato.getInt(quantityKey);
			$.price = dato.getDouble(priceKey);
		}).createItem();
		
		return item;
	}
	
	
}
