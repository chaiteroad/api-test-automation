package apiautomation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class APITestFramework {

	public static String fs = System.getProperty("file.separator");
	public static String currentDir = System.getProperty("user.dir") + fs;
	public static String pathToDriver = currentDir + "src" + fs + "resources" + fs;
	public static String driverFilePath = pathToDriver + "driver.csv";
	public static String pathToJson = pathToDriver + "jsons"+fs;
	public static String jsonFilePath;

	public static void main(String[] args) throws Exception {

		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(driverFilePath)); // this is the driver file
			FileWriter csvWriter = new FileWriter(pathToDriver + "result.csv"); // this is the results file

			// write column headers in the results file
			csvWriter.append(String.join(",", "Test Case ID", "Expected Response Code", "Actual Response Code", "Status","Execution Timestamp"));
			csvWriter.append("\n");
			String row;

			// read data from driver file.
			// iterate over each row

			while ((row = csvReader.readLine()) != null) {
				String jsonPayload = null;
				// store row in an array.
				String[] data = row.split(",");

				String[] results = new String[5];

				boolean executeFlag = false;
				String execFlag = data[1].toUpperCase();
				if (execFlag.equals("Y")) {
					executeFlag = true;
				}

				// read execution flag from 0th element. if Y then proceed
				if (executeFlag) {
					// read method from 3rd element
					// save 1st column as baseURI, 2nd as apiPath, 4th as apiInput and 5th as
					// expectedResCode
					String tcId = data[0];
					String baseUri = data[2].toLowerCase();
					String apiPath = data[3];
					String apiMethod = data[4].toLowerCase();
					String inputParam = data[5];
					int expResCode = Integer.parseInt(data[7]);
					results[0] = tcId;
					results[1] = Integer.toString(expResCode);
					jsonFilePath = pathToJson + data[6];
					if (data[6].length() > 0) {
						jsonPayload = apiautomation.Utilities.giveJsonPayloadFromFile(jsonFilePath);
					}

					Date dt = new Date();
					String arr[];

					// based on the api method, call the right method with the details as inputs
					switch (apiMethod) {

					case "get":
						apiPath = apiPath + "/" + inputParam;
						arr = apiautomation.CallMethods.httpGet(baseUri, apiPath, expResCode);
						results[2] = arr[0];
						results[3] = arr[1];
						results[4] = dt.toString();
						csvWriter.append(String.join(",", results));
						csvWriter.append("\n");
						break;

					case "post":
						apiPath = apiPath + "/" + inputParam;
						arr = apiautomation.CallMethods.httpPost(jsonPayload, baseUri, apiPath, expResCode);
						results[2] = arr[0];
						results[3] = arr[1];
						results[4] = dt.toString();
						csvWriter.append(String.join(",", results));
						csvWriter.append("\n");
						break;

					case "put":
						
						apiPath = apiPath + "/" + inputParam;
						arr = apiautomation.CallMethods.httpPut(jsonPayload, baseUri, apiPath, expResCode);
						results[2] = arr[0];
						results[3] = arr[1];
						results[4] = dt.toString();
						csvWriter.append(String.join(",", results));
						csvWriter.append("\n");
						break;

					case "patch":
						apiPath = apiPath + "/" + inputParam;
						arr = apiautomation.CallMethods.httpPatch(jsonPayload, baseUri, apiPath, expResCode);
						results[2] = arr[0];
						results[3] = arr[1];
						results[4] = dt.toString();
						csvWriter.append(String.join(",", results));
						csvWriter.append("\n");
						break;

					case "delete":
						apiPath = apiPath + "/" + inputParam;
						arr = apiautomation.CallMethods.httpDelete(jsonPayload, baseUri, apiPath, expResCode);
						results[2] = arr[0];
						results[3] = arr[1];
						results[4] = dt.toString();
						csvWriter.append(String.join(",", results));
						csvWriter.append("\n");
						break;

					}
					System.out.println("Execution done for TC ID - " + tcId);
				}

			}

			csvReader.close();
			csvWriter.close();

		} catch (IOException e) {
			System.out.println("Something went wrong!");
			e.printStackTrace();
		}

	}

}
