package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import utils.Serializer;
import models.Data;


public class MaxHeap implements MaxHeapInterface {

	private Serializer serializer;
	private ArrayList<Data> wordData = new ArrayList<>();

	public MaxHeap (Serializer serializer) {
		this.serializer = serializer;
	}
	
	public MaxHeap () {}

	@SuppressWarnings("unchecked")
	public void load() throws Exception {
		serializer.read();
		wordData 		     = (ArrayList<Data>) 	 serializer.pop();
	}

	public void store() throws Exception {
		serializer.push(wordData);
		serializer.write(); 
	}

	public void findSpanishWord(String englishWord) {
		int root = 0, traversals = 0;
		Data currentNode = null;
		if (wordData.size() < 1) {
			System.out.println("Empty tree");
		}
		Stack <Data> dataStack = new Stack<Data>();
		dataStack.push(wordData.get(root));
		
		while (!dataStack.empty()) {
			currentNode = dataStack.pop();
			++traversals;
			if (isWord(currentNode, englishWord)) {
				System.out.println("Match found:  " + currentNode.getEnglish() + "\n" 
						+ "Spanish translation = " + currentNode.getSpanish() + ", Number of traversals = " + traversals + "\n" 
						+ "If this isn't the word you were looking for please check your spelling and try again!" + "\n");
			}
			if (hasRightChild(wordData.indexOf(currentNode)) ){
				dataStack.push(wordData.get(2 * wordData.indexOf(currentNode) + 2) );
			}
			if (hasLeftChild(wordData.indexOf(currentNode)) ){
				dataStack.push(wordData.get(2 * wordData.indexOf(currentNode) + 1) );
			}
		}
	} 

	private boolean hasLeftChild(int node) {
		return (2 * node + 1) < wordData.size();
	}

	private boolean hasRightChild(int node) {
		return( 2 * node + 2) < wordData.size();
	}

	private boolean isWord (Data node, String englishWord) {
		return node.getEnglish().contains(englishWord);
	}

	public void createDataEntry(String spanish, String english) {
		int weight = spanish.length() + english .length();
		add(new Data(spanish, english, weight) );
		siftUp();
	}

	public List<Data> getDataEntries() {
		return wordData;
	}

	@Override
	public void add(Data newEntry) {
		wordData.add(newEntry);
	}

	@Override
	public Data removeMax() {
		if (wordData.size() == 0) {
			System.out.println("Empty array.");
		}
		else if (wordData.size() == 0) {
			return wordData.remove(0);
		}
		Data maxNode = wordData.get(0);
		wordData.set(0, wordData.remove(wordData.size()-1));
		siftDown();
		return maxNode;
	}

	public void siftUp() {
		int dataToSift = wordData.size() - 1;
		while (dataToSift > 0) {
			int parentOfDataToSift = (dataToSift - 1) / 2;
			Data item = wordData.get(dataToSift);
			Data parent = wordData.get(parentOfDataToSift);
			if (item.compareTo(parent) > 0) {
				// swap
				wordData.set(dataToSift, parent);
				wordData.set(parentOfDataToSift, item);

				// move up one level
				dataToSift = parentOfDataToSift;
			} else {
				break;
			}
		}
	}

	public void siftDown() {
		int dataToSift = 0;
		int leftChild = 2 * dataToSift + 1;

		while (leftChild < wordData.size() ) {
			int max = leftChild, rightChild = leftChild + 1;
			// there is a right child
			if (rightChild < wordData.size()) { 		
				if (wordData.get(rightChild).compareTo(wordData.get(leftChild) ) > 0) {
					max++;
				}
			}
			if (wordData.get(dataToSift).compareTo(wordData.get(max) ) < 0) {
				// switch
				Data temp = wordData.get(dataToSift);
				wordData.set(dataToSift, wordData.get(max) );
				wordData.set(max, temp);
				dataToSift = max;
				leftChild = 2 * dataToSift + 1;
			} else {
				break;
			}
		}
	}

	@Override
	public Data getMax() {
		return wordData.get(0);
	}

	@Override
	public boolean isEmpty() {
		return wordData.isEmpty();
	}

	@Override
	public int getSize() {
		return wordData.size();
	}

	@Override
	public void clear() {
		wordData.clear();
	}


}
