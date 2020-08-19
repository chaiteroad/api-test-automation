package apiautomation;

import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CallMethods {

	public static String[] httpGet(String baseUri, String apiPath, int expResCode) {

		String[] arr = new String[2];

		arr[1] = "Failed";

		RestAssured.baseURI = baseUri;

		// Response response = RestAssured.get(baseUri);

		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET, apiPath);

		int statusCode = response.getStatusCode();

		arr[0] = Integer.toString(statusCode);
		if (statusCode == expResCode)
			arr[1] = "Passed";

		return arr;
	}

	public static String[] httpPost(String jsonContent, String baseUri, String apiPath, int expResCode)
			throws IOException {

		String[] arr = new String[2];
		arr[1] = "Failed";

		// Set base URI for API call
		RestAssured.baseURI = baseUri;

		// Define API request
		RequestSpecification request = RestAssured.given();
		request.body(jsonContent);

		// Send the request and save the response
		Response response = request.post(apiPath);

		// Get the status code from the response and verify that it's as expected
		int statusCode = response.getStatusCode();

		arr[0] = Integer.toString(statusCode);
		if (statusCode == expResCode)
			arr[1] = "Passed";

		return arr;

	}

	public static String[] httpPut(String jsonContent, String baseUri, String apiPath, int expResCode)
			throws IOException {
		String[] arr = new String[2];
		arr[1] = "Failed";

		// Set base URI for API call
		RestAssured.baseURI = baseUri;

		// Define API request
		RequestSpecification request = RestAssured.given();
		request.body(jsonContent);

		// Send the request and save the response
		Response response = request.put(apiPath);

		// Get the status code from the response and verify that it's as expected
		int statusCode = response.getStatusCode();
		arr[0] = Integer.toString(statusCode);
		if (statusCode == expResCode)
			arr[1] = "Passed";

		return arr;

	}

	public static String[] httpPatch(String jsonContent, String baseUri, String apiPath, int expResCode)
			throws IOException {
		String[] arr = new String[2];
		arr[1] = "Failed";

		// Set base URI for API call
		RestAssured.baseURI = baseUri;

		// Define API request
		RequestSpecification request = RestAssured.given();
		request.body(jsonContent);

		// Send the request and save the response
		Response response = request.patch(apiPath);

		// Get the status code from the response and verify that it's as expected
		int statusCode = response.getStatusCode();
		arr[0] = Integer.toString(statusCode);
		if (statusCode == expResCode)
			arr[1] = "Passed";

		return arr;

	}

	public static String[] httpDelete(String jsonContent, String baseUri, String apiPath, int expResCode)
			throws IOException {
		String[] arr = new String[2];
		arr[1] = "Failed";
		// Create a json string from json object

		// Set base URI for API call
		RestAssured.baseURI = baseUri;

		// Define API request
		RequestSpecification request = RestAssured.given();
		// request.
		if (jsonContent != null) {
			request.body(jsonContent);
		}

		// Send the request and save the response
		Response response = request.delete(apiPath);

		// Get the status code from the response and verify that it's as expected
		int statusCode = response.getStatusCode();
		arr[0] = Integer.toString(statusCode);
		if (statusCode == expResCode)
			arr[1] = "Passed";

		return arr;

	}

}
