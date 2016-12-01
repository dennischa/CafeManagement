import org.json.XML;
import org.json.JSONException;
import org.json.JSONObject;

public class Json {
	private static final int PRETTY_PRINT_INDENT_FACTOR = 4;
	public String str;

	public Json(String x) {
		this.str = x;
	}

	public String convert() { 
		String jsonPrettyPrintString = "";
		try {
			JSONObject xmlJSONObj = XML.toJSONObject(this.str);
			jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			//System.out.println(jsonPrettyPrintString);

		}catch (JSONException je) {
			System.out.println(je.toString());
		}
		return jsonPrettyPrintString;
	}
}