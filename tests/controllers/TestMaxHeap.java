package controllers;

import static org.junit.Assert.*;
import models.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMaxHeap {
	private MaxHeap maxHeap;
	
	@Before
	public void setUp() {
		maxHeap = new MaxHeap();
		maxHeap.createDataEntry("hola", "hello");
		maxHeap.createDataEntry("ver", "to see");
	}
	
	@After
	public void tearDown() {
		maxHeap = null;
	}
	
	@Test
	public void testCreateDataEntry() {
		assertEquals(maxHeap.getDataEntries().size(), 2);
		maxHeap.createDataEntry("a", "to");
		assertEquals(maxHeap.getDataEntries().size(), 3);
	}
	
	@Test
	public void testSiftUp() {
		assertEquals(maxHeap.getDataEntries().get(0).getWeight(), 9);
		maxHeap.createDataEntry("encontrar", "to find");
		assertEquals(maxHeap.getDataEntries().get(0).getWeight(), 16);
	}
	
	@Test
	public void testRemoveMax() {
		maxHeap.createDataEntry("encontrar", "to find");
		assertEquals(maxHeap.getDataEntries().get(0).getWeight(), 16);
		maxHeap.removeMax();
		assertEquals(maxHeap.getDataEntries().get(0).getWeight(), 9);
	}
	
	@Test 
	public void testSiftDown() {
		maxHeap.getDataEntries().add(0, new Data("no", "no", 4));
		assertEquals(maxHeap.getDataEntries().get(0).getWeight(), 4);
		maxHeap.siftDown();
		assertNotEquals(maxHeap.getDataEntries().get(0).getWeight(), 4);
	}
	
	@Test 
	public void testClear() {
		maxHeap.clear();
		assertEquals(maxHeap.getDataEntries().size(), 0);
	}

}
