package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import controllers.Main;

public class ReadInData {

	public ReadInData() throws Exception {

		File data = new File("data/spanishDictionary.txt");
		BufferedReader inData = new BufferedReader(new FileReader(data));
		try {
			String wordData= "";
			//each field is separated(delimited) by a '|'
			String delims = "[|]";
			while ( (wordData = inData.readLine() ) != null) {
				// parse data details string
				String[] dataTokens = wordData.split(delims);
				//create data nodes from txt file
				if (dataTokens.length == 2) {
					Main.mh.createDataEntry(dataTokens[0], dataTokens[1]);
				} else {
					throw new Exception("Invalid member length: " + dataTokens.length);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inData != null) inData.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
