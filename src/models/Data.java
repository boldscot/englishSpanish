package models;


public class Data implements Comparable<Data>{
	private String english, spanish;
	private int weight;
	
	public Data (String spanish, String english, int weight) {
		this.spanish = spanish;
		this.english = english;
		this.weight = weight;
	}

	/**
	 * @return the spanish
	 */
	public String getSpanish() {
		return spanish;
	}

	/**
	 * @param spanish the spanish to set
	 */
	public void setSpanish(String spanish) {
		this.spanish = spanish;
	}
	
	/**
	 * @return the english
	 */
	public String getEnglish() {
		return english;
	}

	/**
	 * @param english the english to set
	 */
	public void setEnglish(String english) {
		this.english = english;
	}
	
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Data compareData) {
		int compareNodeWeight = compareData.getWeight();
		return this.getWeight() - compareNodeWeight;
	}
	
	@Override
	public String toString() {
		return "Spanish word: " + spanish + "\n"
				+ "English translation: " + english + "\n" 
				+ "Node weight: " + weight + "\n";
	}
	
	

}
