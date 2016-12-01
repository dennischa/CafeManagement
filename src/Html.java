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
			bufferedWriter.append("<h3><b>ī�� �Ŵ�����Ʈ �˰��� ���</b></h3>\r\n");
			bufferedWriter.append("<h4>ī�� ���� : " + b1 + ", " + b2 + ", " + b3 + "</h4>\r\n");
			bufferedWriter.append("<h4>���� �ð� : 10:00 - 22:00</h4><br>\r\n");
			bufferedWriter.append("<h4>�ֹ� ���� ���(�Ϸ� �ð� ����)</h4>\r\n");
			bufferedWriter.append("<table>\r\n");
			bufferedWriter.append("<tr>\r\n");
			bufferedWriter.append("<th>�ֹ� �ð� |</th>\r\n");
			bufferedWriter.append("<th>�Ϸ� �ð� |</th>\r\n");
			bufferedWriter.append("<th>�մ�ID |</th>\r\n");
			bufferedWriter.append("<th>[Ŀ�� ����]-[����]</th>\r\n");
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
