package eus.healthit.bchef.core.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

public class API {

	/* ######################## GET ############################# */
	public static JSONObject searchRecipe(String kw, int page) {
		try {
			return APIutils.getRequest(
					"/api/search?like=" + URLEncoder.encode(kw, StandardCharsets.UTF_8.toString()) + "&page=" + page);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject getAllUsers() {
		return APIutils.getRequest("/api/user/allusers");
	}

	public static JSONObject getHistoryBetween(int userId, Timestamp timestamp, Timestamp timestamp2) {
		return APIutils
				.getRequest("/api/user/history?userid=" + userId + "&from=" + timestamp + "&until=" + timestamp2);
	}

	public static JSONObject searchIngredient(String kw) {
		try {
			return APIutils
					.getRequest("/api/ingredient?like=" + URLEncoder.encode(kw, StandardCharsets.UTF_8.toString()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String lumbra() {
		return APIutils.getRequest("/api/lumbra").getString("lumbra");

	}

	public static JSONObject getPage(int i) {
		return APIutils.getRequest("/api/page?num=" + i);
	}

	public static JSONObject getUserName(int i) {
		return APIutils.getRequest("/api/user/name?id=" + i);
	}

	public static JSONObject getUserById(int i) {
		return APIutils.getRequest("/api/user/byId?id=" + i);
	}

	public static JSONObject searchByIngredients(Set<String> set) {
		int i = 0;
		List<String> list = new ArrayList<>();
		for (String string : set) {
			list.add("ing" + i + "=" + string);
		}
		return APIutils.getRequest("/api/byingredients?" + String.join("&", list));
	}

	public static JSONObject checkUser(String username) {
		return APIutils.getRequest("/api/register/check?username=" + username);
	}

	/* ######################## PUT ############################# */
	public static JSONObject auth(JSONObject json) {
		return APIutils.putRequest("/api/auth", json);
	}

	public static JSONObject reauth(JSONObject json) {
		return APIutils.putRequest("/api/user/reauth", json);
	}

	public static JSONObject rate(JSONObject json) {
		return APIutils.putRequest("/api/user/rate", json);
	}

	public static JSONObject updateUserConfig(JSONObject json) {
		return APIutils.putRequest("/api/user/config", json);
	}

	public static JSONObject addToHistory(JSONObject json) {
		return APIutils.putRequest("/api/user/visit", json);
	}

	public static JSONObject save(JSONObject json) {
		return APIutils.putRequest("/api/user/save", json);
	}

	public static JSONObject unsave(JSONObject json) {
		return APIutils.putRequest("/api/user/unsave", json);
	}

	public static JSONObject shoplistAdd(JSONObject json) {
		return APIutils.putRequest("/api/user/shoplist/add", json);
	}

	public static JSONObject shoplistRemove(JSONObject json) {
		return APIutils.putRequest("/api/user/shoplist/remove", json);
	}

	public static JSONObject shoplistTick(JSONObject json) {
		return APIutils.putRequest("/api/user/shoplist/tick", json);
	}

	public static JSONObject follow(JSONObject json) {
		return APIutils.putRequest("/api/user/follow", json);
	}

	public static JSONObject unfollow(JSONObject json) {
		return APIutils.putRequest("/api/user/unfollow", json);
	}

	/* ######################## POST ############################# */
	public static JSONObject addRecipe(JSONObject json) {
		return APIutils.postRequest("/api/register/recipe", json);
	}

	public static JSONObject addUser(JSONObject json) {
		return APIutils.postRequest("/api/register/user", json);
	}

}
