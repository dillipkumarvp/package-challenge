package com.mobiquityinc.packer.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.mobiquityinc.packer.bean.GiftItem;
import com.mobiquityinc.packer.service.IPackageChallengeService;

/**
 * 
 * Implementation service for to determine best Package from the given input
 * GiftItems.
 * 
 * @author dillipkumar.vp
 *
 */
public class PackageChallengeServiceImpl implements IPackageChallengeService {

	double limit;
	double bestWeight = 100;
	List<GiftItem> items;
	List<List<GiftItem>> combinations;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mobiquityinc.service.IPackageChallengeService#determineBestPackage
	 * (double, java.util.List)
	 */
	@Override
	public String determineBestPackage(double limit, List<GiftItem> giftItems) {

		this.limit = limit;
		this.items = giftItems;
		this.combinations = new ArrayList<List<GiftItem>>();

		String bestPackage;
		removeOverWeightItems();
		combinations = addCombinations();
		if (combinations.isEmpty()) {
			bestPackage = "-";
		} else {
			List<GiftItem> bestCombination = getBestPackage();
			Collections.sort(bestCombination);
			bestPackage = constructFormatedOutput(bestCombination);
		}

		return bestPackage;
	}

	/**
	 * Find and remove items with weight over the limit
	 */
	private void removeOverWeightItems() {
		Iterator<GiftItem> iter = items.iterator();
		while (iter.hasNext()) {
			GiftItem item = iter.next();
			if (item.getWeight() > limit)
				iter.remove();
		}
	}

	/**
	 * Method to create/add with Combinations of GiftItems.
	 * 
	 * @return List<GiftItem>
	 */
	private List<List<GiftItem>> addCombinations() {
		for (int x = 0; x < items.size(); x++) {
			GiftItem currentItem = items.get(x);
			int combinationSize = combinations.size();
			for (int y = 0; y < combinationSize; y++) {
				List<GiftItem> combination = combinations.get(y);
				List<GiftItem> newCombination = new ArrayList<>(combination);
				newCombination.add(currentItem);
				combinations.add(newCombination);
			}
			List<GiftItem> current = new ArrayList<>();
			current.add(currentItem);
			combinations.add(current);
		}
		return combinations;
	}

	/**
	 * Method to calculate the best package by comparing with total cost and
	 * with total weight.
	 * 
	 * @return List<GiftItem>
	 */
	private List<GiftItem> getBestPackage() {
		List<GiftItem> bestCombination = new ArrayList<>();
		double bestCost = 0;
		for (List<GiftItem> combination : combinations) {
			double combinationWeight = getTotalWeight(combination);
			if (combinationWeight > limit) {
				continue;
			} else {
				double combinationPrice = getTotalCost(combination);
				if (combinationPrice > bestCost) {
					bestCost = combinationPrice;
					bestCombination = combination;
					bestWeight = combinationWeight;
				} else if (combinationPrice == bestCost) {
					if (combinationWeight < bestWeight) {
						bestCost = combinationPrice;
						bestCombination = combination;
						bestWeight = combinationWeight;
					}
				}
			}
		}
		return bestCombination;
	}

	/**
	 * Get Total Weight for the given testcase/entry
	 * 
	 * @param items
	 * @return double
	 */
	private double getTotalWeight(List<GiftItem> items) {
		double total = 0;
		for (GiftItem i : items) {
			total += i.getWeight();
		}
		return total;
	}

	/**
	 * Get Total cost for the given inputentry GiftItem 
	 * 
	 * @param items
	 * @return double
	 */
	private double getTotalCost(List<GiftItem> items) {
		double total = 0;
		for (GiftItem i : items) {
			total += i.getCost();
		}
		return total;
	}

	/**
	 * Method to Construct string with the index numbers for all best
	 * combinations seperated by comma.
	 * 
	 * @param items
	 * @return String
	 */
	private String constructFormatedOutput(List<GiftItem> items) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (GiftItem i : items) {
			if (isFirst) {
				sb.append(i.getIndex());
				isFirst = false;
			} else {
				sb.append("," + i.getIndex());
			}
		}
		return sb.toString();
	}
}
