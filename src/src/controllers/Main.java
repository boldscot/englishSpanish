package src.controllers;

import java.io.File;
import java.util.Collection;
import java.util.List;

import models.Data;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import utils.ReadInData;
import utils.Serializer;
import utils.XMLSerializer;

public class Main {
	public static MaxHeap mh;
	
	public Main() throws Exception {
		File dataFile = new File("data.xml");
		Serializer serializer = new XMLSerializer(dataFile);

		mh = new MaxHeap(serializer);
		
		if (dataFile.isFile() ) {
			mh.load();
		} else {
			new ReadInData();
		}	
	}
	
	public static void main (String[] args) throws Exception {
		Main main = new Main();
		
		Shell shell = ShellFactory.createConsoleShell
				("lm", "?help for instructions", main);
		shell.commandLoop();
	}
	
	@Command(description="Get all data entries")
	public void getData () {
		Collection<Data> data = mh.getDataEntries();
		for (int i = 0 ; i < data.size(); ++i) {
			System.out.println("Index " + i + "\n" + ( (List<Data>) data).get(i) );
		}
	}
	
	@Command(description="Add a data entry")
	public void addData (@Param(name="spanish") String spanish, 
			@Param(name="english") String english) {
		mh.createDataEntry(spanish, english);
	}
	
	@Command(description="Search for data entry")
	public void searchForEntry (@Param(name="englishWord") String englishWord ) {
		mh.findSpanishWord(englishWord);
	}
	
	@Command(description = "store data")
	public void store () throws Exception {
		mh.store();

	}
}
