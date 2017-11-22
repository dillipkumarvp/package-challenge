/**
 * Test cases for class PackageChallengeServiceImpl.java
 * 
 * @author dillipkumar.vp
 */

package com.mobiquityinc.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;

import com.mobiquityinc.packer.bean.GiftItem;
import com.mobiquityinc.packer.service.impl.PackageChallengeServiceImpl;


public class TestPackageChallengeServiceImpl {

	List<GiftItem> giftItems = null;

	@Mock
	private PackageChallengeServiceImpl packageChallenge = new PackageChallengeServiceImpl();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		giftItems = new ArrayList<>();
		GiftItem item1 = new GiftItem();
		item1.setIndex(1);
		item1.setWeight(53.38);
		item1.setCost(45);
		GiftItem item2 = new GiftItem();
		item2.setIndex(2);
		item2.setWeight(20.62);
		item2.setCost(98);
		GiftItem item3 = new GiftItem();
		item3.setIndex(3);
		item3.setWeight(78.48);
		item3.setCost(3);
		giftItems.add(item1);
		giftItems.add(item2);
		giftItems.add(item3);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test Case to remove Over weight items
	 * 
	 * @throws Exception
	 */
	@Test
	public void TestremoveOverWeightItems() throws Exception {

		Whitebox.setInternalState(packageChallenge, "limit", 60);
		Whitebox.setInternalState(packageChallenge, "items", giftItems);
		Whitebox.invokeMethod(packageChallenge, "removeOverWeightItems");
		Assert.assertEquals(2, giftItems.size());
	}

	/**
	 * Test case to check no items to remove
	 * 
	 * @throws Exception
	 */
	@Test
	public void TestremoveOverWeightItemsNone() throws Exception {

		Whitebox.setInternalState(packageChallenge, "limit", 15);
		Whitebox.setInternalState(packageChallenge, "items", giftItems);
		Whitebox.invokeMethod(packageChallenge, "removeOverWeightItems");
		Assert.assertEquals(0, giftItems.size());
	}

	/**
	 * Test case to check all possible combinations.
	 * 
	 * @throws Exception
	 */
	@Test
	public void TestaddCombinations() throws Exception {

		Whitebox.setInternalState(packageChallenge, "limit", 75);
		Whitebox.setInternalState(packageChallenge, "items", giftItems);
		Whitebox.setInternalState(packageChallenge, "combinations",
				new ArrayList<List<GiftItem>>());
		List<List<GiftItem>> list = Whitebox.invokeMethod(packageChallenge,
				"addCombinations");
		Assert.assertEquals(7, list.size());
	}

	/**
	 * Testcase to check find the best package from the possible combinations.
	 * 
	 * @throws Exception
	 */
	@Test
	public void TestgetBestPackage() throws Exception {

		Whitebox.setInternalState(packageChallenge, "limit", 75);
		Whitebox.setInternalState(packageChallenge, "items", giftItems);
		Whitebox.setInternalState(packageChallenge, "combinations",
				new ArrayList<List<GiftItem>>());
		Whitebox.invokeMethod(packageChallenge, "addCombinations");
		List<List<GiftItem>> list = Whitebox.invokeMethod(packageChallenge,
				"getBestPackage");
		Assert.assertEquals(2, list.size());
	}

	/**
	 * Test case to check the addition of weight
	 * 
	 * @throws Exception
	 */
	@Test
	public void TestgetTotalWeight() throws Exception {

		double actual = Whitebox.invokeMethod(packageChallenge,
				"getTotalWeight", giftItems);
		DecimalFormat df = new DecimalFormat();
		Assert.assertEquals(String.valueOf(152.48),
				String.valueOf(df.format(actual)));
	}

	/**
	 * Test case to check the addition of Cost
	 * 
	 * @throws Exception
	 */
	@Test
	public void TestgetTotalCost() throws Exception {

		double actual = Whitebox.invokeMethod(packageChallenge, "getTotalCost",
				giftItems);
		DecimalFormat df = new DecimalFormat();
		Assert.assertEquals(String.valueOf(146),
				String.valueOf(df.format(actual)));
	}

	/**
	 * Test case to construct the output index number for the given Limit value.
	 * 
	 * @throws Exception
	 */
	@Test
	public void TestconstructFormatedOutput() throws Exception {

		Whitebox.setInternalState(packageChallenge, "limit", 30);
		Whitebox.setInternalState(packageChallenge, "items", giftItems);
		Whitebox.setInternalState(packageChallenge, "combinations",
				new ArrayList<List<GiftItem>>());
		Whitebox.invokeMethod(packageChallenge, "addCombinations");
		List<List<GiftItem>> list = Whitebox.invokeMethod(packageChallenge,
				"getBestPackage");

		String actual = Whitebox.invokeMethod(packageChallenge,
				"constructFormatedOutput", list);
		Assert.assertEquals("" + 2, "" + actual);
	}

}
