package com.testDetails;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.io.FileReader;
import java.io.IOException;

public class JsonDataDecode {

	@Test
	public void checkForeignPlayers() throws ParseException {
		
		String TeamDetails = readData("" + System.getProperty("user.dir") + "\\src\\test\\resources\\TeamDetails.json");
		
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(TeamDetails); 
        JSONArray jsonArray = (JSONArray) json.get("player");
              
        int countForeignPlayers = 0;
        for(int i = 0;i<jsonArray.size();i++) {
        	JSONObject jsonNew = (JSONObject) parser.parse("" + jsonArray.get(i)); 
        	String country = (String) jsonNew.get("country");
        	if(!country.equalsIgnoreCase("India")) {
        		System.out.println("One of the Foreign player is " + jsonNew.get("name"));
        		countForeignPlayers++;
        	}
        }
        System.out.println("Total Foreign players are " + countForeignPlayers);
		Assert.assertEquals(countForeignPlayers,4);
	}
	
	@Test
	public void checkForWicketKeeper() throws ParseException {
		
		String TeamDetails = readData("" + System.getProperty("user.dir") + "\\src\\test\\resources\\TeamDetails.json");
		
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(TeamDetails); 
        JSONArray jsonArray = (JSONArray) json.get("player");
              
        int countWicketKeeperPlayers = 0;
        for(int i = 0;i<jsonArray.size();i++) {
        	JSONObject jsonNew = (JSONObject) parser.parse("" + jsonArray.get(i)); 
        	String role = (String) jsonNew.get("role");
        	if(role.equalsIgnoreCase("Wicket-keeper")) {
        		System.out.println("Wicket-keeper player is " + jsonNew.get("name"));
        		countWicketKeeperPlayers++;
        	}
        }
        System.out.println("Total Wicket-Keeper players are " + countWicketKeeperPlayers);
		Assert.assertEquals(countWicketKeeperPlayers,1);
	}
	
	private static String readData(String filePath)
    {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
            String str;
            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
	
	
}
