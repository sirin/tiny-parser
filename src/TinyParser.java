import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;


public class TinyParser {

	public static void main(String[] args) {
		String obj = "{\"vehicle\":{  \"color\":\"white\", \"bicycle\":{ \"color\": \"red\", \"price\": 19.95 } ,\"car\": { \"color\": \"blue\", \"price\":49.95, \"sport car\":{ \"color\":\"black\", \"price\": 119.95 }}}}";
		JSONObject jsonObj = new JSONObject(obj);
		String myObject = TinyParser.parser(jsonObj, "vehicle,car,sport car:color");
		System.out.println(myObject);
	}
	
	public static String parser(JSONObject jsonObject, String elementPath) {
		String splittedPath[] = elementPath.split(":");
		if (splittedPath.length < 1) {
			return jsonObject.toString();
		} else if (splittedPath.length == 1) {
			String objects = splittedPath[0];
			return TinyParser.findJSONObject(jsonObject, objects);
		} else {
			String objects = splittedPath[0];
			String keys = splittedPath[1];
			String obj = TinyParser.findJSONObject(jsonObject, objects);
			JSONObject jsonObj = new JSONObject(obj);
			return TinyParser.findJSONKey(jsonObj, keys);
		}
	}
	
	public static String findJSONObject(JSONObject jsonObject, String objects) {
		if (objects.length() > 0) {
			ArrayList<String> objectArray = new ArrayList<String>();
			if (objects.contains(",")) {
				String temp[] = objects.split(",");
				for (String s : temp) {
					objectArray.add(s);
				}
			} else {
				objectArray.add(objects);
			}
			for (int i = 0; i < objectArray.size(); i++) {
				try {
					jsonObject = jsonObject.getJSONObject(objectArray.get(i));
				} catch (JSONException e) {
					return "ERROR OBJECT: " + e.getMessage();
				}
			}
			return jsonObject.toString();
		} else {
			return jsonObject.toString();
		}
	}
	
	public static String findJSONKey(JSONObject jsonObject, String keys) {
		ArrayList<String> keyArray = new ArrayList<String>();
		String value = "";
		if (keys.length() > 0) {
			if (keys.contains(",")) {
				String temp[] = keys.split(",");
				for (String s : temp) {
					keyArray.add(s);
				}
			} else {
				keyArray.add(keys);
			}
			for (int j = 0; j < keyArray.size(); j++) {
				try {
					value = jsonObject.get(keyArray.get(j)).toString();
				} catch (JSONException e) {
					return "ERROR KEY: " + e.getMessage();
				}
			}
			return value;
		} else {
			return jsonObject.toString();
		}
	}
}
