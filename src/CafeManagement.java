import java.util.concurrent.TimeUnit;

public class CafeManagement {

	public static int curTime = 600;
	public static Html html;	
	private static BaristaQueue barista1;
	private static BaristaQueue barista2;
	private static BaristaQueue barista3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonMain jsonMain = new JsonMain();
		OrderQueue orderQ = new OrderQueue();
		WaitingList waitingList = new WaitingList();
		
		html = new Html();
		orderQ = jsonMain.JsonToOrderQ();

		// ����(�Ƿ�)�� ����
		String b1 = "manager", b2 = "regular", b3 = "intern";
		
		barista1 = new BaristaQueue(b1);
		barista2 = new BaristaQueue(b2);
		barista3 = new BaristaQueue(b3);
		html.opening(b1, b2, b3);

		// 10:00 ���� 22:00 ���� ������ �д����� ǥ��
		for(curTime = 600; curTime < 1320; ) {
			Order order;

			if(!orderQ.empty() && orderQ.peek().getTime() == curTime) {
				order = orderQ.dequeue();
				System.out.println(intToTime(curTime) + " " + order.getId() + "�� �ֹ�");
				for(Coffee c : order.getCoffeeArray()) {
					for(int i = 1; i <= c.getNumber(); i++) {
						//System.out.println(curTime + " " + order.getId() + " " + c.getKinds() + " " + i);
						coffeeRoasting(curTime, order.getId(), c.getKinds());
					}
				}

				waitingList.add(order);
			}
			
			// 1���� ���
			curTime++;
			// 1���� ���� �ϼ��� ��� �� id�� ����, �ƴ� ��� 0�� ����
			Coffee completeCoffee1 = barista1.oneMinutePassed();
			Coffee completeCoffee2 = barista2.oneMinutePassed();
			Coffee completeCoffee3 = barista3.oneMinutePassed();

			// Ŀ�ǰ� �ϼ��� ��� waitingList���� Ȯ���Ͽ� �ϼ��ƴٴ� ���� �˸�
			waitingList.completeOrder(completeCoffee1);
			waitingList.completeOrder(completeCoffee2);
			waitingList.completeOrder(completeCoffee3);
		}
		
		html.ending();
	}

	// 3���� �ٸ���Ÿ �� ���� ���� ���� �� �ִ� 1���� Ŀ�� ������ �ֹ�
	public static void coffeeRoasting(int curTime, int id, String coffee) {		
		int b1 = barista1.prediction(coffee);
		int b2 = barista2.prediction(coffee);
		int b3 = barista3.prediction(coffee);

		if(b1 < b2 && b1 < b3) // a�� ���� ���� Ŀ�Ǹ� ���� ���
			barista1.enqueue(id, coffee);
		else if(b2 < b1 && b2 < b3) // b�� ���� ���� Ŀ�Ǹ� ���� ���
			barista2.enqueue(id, coffee);
		else if(b3 < b1 && b3 < b2) // c�� ���� ���� Ŀ�Ǹ� ���� ���
			barista3.enqueue(id, coffee);
		else if(b1 == b2 && b2 == b3) { // a, b, c ���ÿ� Ŀ�Ǹ� ���� ���

			// ������ �ð��� ��� ���� ��� ���� stat �� ���� �ٸ���Ÿ���� �ֹ�
			if(barista1.getStat() <= barista2.getStat() && barista1.getStat() <= barista3.getStat())
				barista1.enqueue(id, coffee);
			else if(barista2.getStat() <= barista1.getStat() && barista2.getStat() <= barista3.getStat())
				barista2.enqueue(id, coffee);
			else if(barista3.getStat() <= barista1.getStat() && barista3.getStat() <= barista2.getStat())
				barista3.enqueue(id, coffee);
		}
		else if(b1 == b2) { // a, b�� c���� ���� ���ÿ� Ŀ�Ǹ� ���� ���
			if(barista1.getStat() <= barista2.getStat())
				barista1.enqueue(id, coffee);
			else
				barista2.enqueue(id, coffee);
		}
		else if(b1 == b3) { // a, c�� b���� ���� ���ÿ� Ŀ�Ǹ� ���� ���
			if(barista1.getStat() <= barista3.getStat())
				barista1.enqueue(id, coffee);
			else
				barista3.enqueue(id, coffee);
		}
		else if(b2 == b3) { // b, c�� a���� ���� ���ÿ� Ŀ�Ǹ� ���� ���
			if(barista2.getStat() <= barista3.getStat())
				barista2.enqueue(id, coffee);
			else
				barista3.enqueue(id, coffee);
		}
	}

	// �ð��� �� �������� �ð�:�� ���·� ��ȯ
	public static String intToTime(int minute) {
		long hours = TimeUnit.MINUTES.toHours(minute - minute % 60);
		long remainMinutes = minute - TimeUnit.HOURS.toMinutes(hours);
		String result = String.format("%02d:%02d", hours, remainMinutes);
		return result;
	}
}
