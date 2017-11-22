package com.mobiquityinc.packer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.packer.bean.GiftItem;
import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.service.IPackageChallengeService;
import com.mobiquityinc.packer.service.impl.PackageChallengeServiceImpl;

/**
 * 
 * 
 * @author dillipkumar.vp
 *
 */
public class Packer {

	/*
	 * Default private constructor
	 */
	private Packer() {
	}

	public static void main(String[] args) throws APIException {
		Packer.pack(args[0]);
	}

	/**
	 * Method to parse the input file and Construct the GiftItem object as input
	 * to PackageHelper to determine the best GiftPackage.
	 * 
	 * @param filePath
	 * @throws APIException
	 * @throws IOException
	 */
	public static void pack(String filePath) throws APIException {
		String packageDetails = null;
		File file = new File(filePath);
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = in.readLine()) != null) {
				if (line.length() == 0)
					continue;
				String[] lineArray = line.split(":");
				double weightLimit = Integer.parseInt(lineArray[0].trim());
				String[] stringItems = lineArray[1].trim().split(" ");
				List<GiftItem> inputs = new ArrayList<>();
				for (String stringItem : stringItems) {
					String[] itemDetails = stringItem.split(",");
					int index = Integer.parseInt(itemDetails[0].substring(1));
					double weight = Double.parseDouble(itemDetails[1]);
					// Removing € character
					double cost = Double.parseDouble(itemDetails[2].substring(
							1, itemDetails[2].length() - 1));
					GiftItem item = new GiftItem();
					item.setIndex(index);
					item.setWeight(weight);
					item.setCost(cost);
					inputs.add(item);
				}

				// Service call to determine the bestPackage for the
				// inputline/GiftItem
				IPackageChallengeService iPackageChallengeService = new PackageChallengeServiceImpl();
				packageDetails = iPackageChallengeService.determineBestPackage(weightLimit, inputs);
				System.out.println(packageDetails);
			}
		} catch (FileNotFoundException e) {
			throw new APIException(
					"Eror in Reading the inputfile as the Arguments does not match: ",
					e);
		} catch (IOException e) {
			throw new APIException(e);
		}
	}
}
