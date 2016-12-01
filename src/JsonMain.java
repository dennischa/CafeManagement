import java.io.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonMain {
	public OrderQueue JsonToOrderQ() {
		String xmlStr = "";
		// 파일 입력
		try {
			BufferedReader in = new BufferedReader(new FileReader("input.xml"));
			String s;
			while ((s = in.readLine()) != null) {
				xmlStr = xmlStr + s;
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Json JsonObj1 = new Json(xmlStr);
		String x = JsonObj1.convert();
		OrderQueue orderQ = new OrderQueue();

		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(x);
			JSONArray orderList = (JSONArray) jsonObject.get("order");

			for (int i = 0; i < orderList.size(); i++) {
				JSONObject order = (JSONObject) orderList.get(i);
				int id = Integer.parseInt(order.get("id").toString());
				String time = (String) order.get("time");
				ArrayList<Coffee> CS = new ArrayList<Coffee>();

				if (order.get("coffee").getClass().getSimpleName().equals("JSONArray")) {
					JSONArray cL = (JSONArray) order.get("coffee");
					for (int j = 0; j < cL.size(); j++) {
						JSONObject coffee = (JSONObject) cL.get(j);
						String kinds = coffee.get("kinds").toString();
						int number = Integer.parseInt(coffee.get("number").toString());
						Coffee c = new Coffee(kinds, number);
						CS.add(c);
					}
				}
				else{
					JSONObject coffee = (JSONObject)order.get("coffee");
					String kinds = coffee.get("kinds").toString();
					int number = Integer.parseInt(coffee.get("number").toString());
					Coffee c = new Coffee(kinds, number);
					CS.add(c);
				}
				Order o = new Order(id, time, CS);
				orderQ.enqueue(o);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderQ;
	}
}
