import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Html {
	private static BufferedWriter bufferedWriter;
	
	Html() {
		try {
			bufferedWriter = new BufferedWriter(new FileWriter("output.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void opening(String b1, String b2, String b3) {
		try {
			bufferedWriter.append("<h3><b>카페 매니지먼트 알고리즘 결과</b></h3>\r\n");
			bufferedWriter.append("<h4>카페 직원 : " + b1 + ", " + b2 + ", " + b3 + "</h4>\r\n");
			bufferedWriter.append("<h4>영업 시간 : 10:00 - 22:00</h4><br>\r\n");
			bufferedWriter.append("<h4>주문 수행 결과(완료 시간 기준)</h4>\r\n");
			bufferedWriter.append("<table>\r\n");
			bufferedWriter.append("<tr>\r\n");
			bufferedWriter.append("<th>주문 시간 |</th>\r\n");
			bufferedWriter.append("<th>완료 시간 |</th>\r\n");
			bufferedWriter.append("<th>손님ID |</th>\r\n");
			bufferedWriter.append("<th>[커피 종류]-[개수]</th>\r\n");
			bufferedWriter.append("</tr>\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addTable(Order order) {
		try {
			bufferedWriter.append("<tr>\r\n");
			bufferedWriter.append("<td>" + CafeManagement.intToTime(order.getTime()) + "</td>\r\n");
			bufferedWriter.append("<td><b>" + CafeManagement.intToTime(CafeManagement.curTime) + "</b></td>\r\n");
			bufferedWriter.append("<td>" + order.getId() + "</td>\r\n");
			for(Coffee c : order.getCoffeeArray()) {
				bufferedWriter.append("<td>[" + c.getKinds() + "]-[" + c.getNumber() + "]</td>\r\n");
			}
			bufferedWriter.append("</tr>\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ending() {
		try {
			bufferedWriter.append("</table>\r\n");
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
