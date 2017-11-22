package com.mobiquityinc.packer.service;

import java.util.List;

import com.mobiquityinc.packer.bean.GiftItem;

/**
 * 
 * Interface to find the best combinations from the  GiftItems
 * 
 * @author dillipkumar.vp
 *
 */
@FunctionalInterface
public interface IPackageChallengeService {

	/**
	 * Method to calculate the best packages for the given GiftItems.
	 * @param limit
	 * @param giftItems
	 * @return String
	 */
	public String determineBestPackage (double limit, List<GiftItem> giftItems);
	
}
