package controllers;

import models.Data;

public interface MaxHeapInterface {  
	
	public void add(Data newEntry);
	public Data removeMax();
	public Data getMax();
	public boolean isEmpty();
	public int getSize();
	public void clear();
}
