package apiautomation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Utilities {

	public static String csvKeyValsToJson(String[] data) {
		int columnCount = data.length;
		String jsonPayload = "{";
		for (int k = 8; k < columnCount; k++) {

			jsonPayload += data[k] + ":";
			k++;
			if (k == columnCount - 1) {
				jsonPayload += data[k];
			} else {
				jsonPayload += data[k] + ",";
			}

		}
		jsonPayload += "}";
		return jsonPayload;

	}

	public static String giveJsonPayloadFromFile(String jsonFilePath) {

		String jsonPayload = "";
		try {
			File file = new File(jsonFilePath);
			Scanner sc;
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				jsonPayload += sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong in utilities.");
			e.printStackTrace();
		}

		if (jsonPayload == "") {
			System.out.println("ERROR generating jsonPayload. jsonPayload is null");
		}

		return jsonPayload;
	}



}
