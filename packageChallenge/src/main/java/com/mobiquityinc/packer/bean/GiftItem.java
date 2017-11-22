package com.mobiquityinc.packer.bean;

/**
 * Bean/placeholder for each giftItem to friend. Attributes index, cost, weight
 * 
 * @author dillipkumar.vp
 *
 */
public class GiftItem implements Comparable<GiftItem> {

	private int index;
	private double cost;
	private double weight;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(GiftItem giftitem) {
		return this.index - giftitem.index;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
