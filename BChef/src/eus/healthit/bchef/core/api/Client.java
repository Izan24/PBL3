package eus.healthit.bchef.core.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.json.simple.JSONObject;

import eus.healthit.bchef.core.models.User;

public class Client {

	private URL url;

	public Client(URL url) {
		this.url = url;
	}

	public User postUser(Map<String, String> userMap) {
		JSONObject json = new JSONObject(userMap);
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-16");
			// con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			try (OutputStream os = con.getOutputStream()) {
				// System.out.println(json.toString());
				byte[] input = json.toString().getBytes("utf-8");
				os.write(input, 0, input.length);
				System.out.println("Sending... " + input.length + " bytes");
			} catch (Exception e) {
				System.out.println("Couldnt send... " + e.getMessage());
			}
			try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				System.out.println(response.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getProba() {
		HttpURLConnection con;

		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setDoOutput(true);
//			try (OutputStream os = con.getOutputStream()) {
//				// System.out.println(json.toString());
//				byte[] input = json.toString().getBytes("utf-8");
//				os.write(input, 0, input.length);
//			} catch (Exception e) {
//				System.out.println("Couldnt send... " + e.getMessage());
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
