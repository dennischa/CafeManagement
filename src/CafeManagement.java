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

		// 직업(실력)을 설정
		String b1 = "manager", b2 = "regular", b3 = "intern";
		
		barista1 = new BaristaQueue(b1);
		barista2 = new BaristaQueue(b2);
		barista3 = new BaristaQueue(b3);
		html.opening(b1, b2, b3);

		// 10:00 부터 22:00 까지 영업을 분단위로 표현
		for(curTime = 600; curTime < 1320; ) {
			Order order;

			if(!orderQ.empty() && orderQ.peek().getTime() == curTime) {
				order = orderQ.dequeue();
				System.out.println(intToTime(curTime) + " " + order.getId() + "번 주문");
				for(Coffee c : order.getCoffeeArray()) {
					for(int i = 1; i <= c.getNumber(); i++) {
						//System.out.println(curTime + " " + order.getId() + " " + c.getKinds() + " " + i);
						coffeeRoasting(curTime, order.getId(), c.getKinds());
					}
				}

				waitingList.add(order);
			}
			
			// 1분이 경과
			curTime++;
			// 1분이 지나 완성된 경우 고객 id를 저장, 아닌 경우 0을 저장
			Coffee completeCoffee1 = barista1.oneMinutePassed();
			Coffee completeCoffee2 = barista2.oneMinutePassed();
			Coffee completeCoffee3 = barista3.oneMinutePassed();

			// 커피가 완성된 경우 waitingList에서 확인하여 완성됐다는 것을 알림
			waitingList.completeOrder(completeCoffee1);
			waitingList.completeOrder(completeCoffee2);
			waitingList.completeOrder(completeCoffee3);
		}
		
		html.ending();
	}

	// 3명의 바리스타 중 가장 빨리 끝낼 수 있는 1명에게 커피 제작을 주문
	public static void coffeeRoasting(int curTime, int id, String coffee) {		
		int b1 = barista1.prediction(coffee);
		int b2 = barista2.prediction(coffee);
		int b3 = barista3.prediction(coffee);

		if(b1 < b2 && b1 < b3) // a가 가장 빨리 커피를 만들 경우
			barista1.enqueue(id, coffee);
		else if(b2 < b1 && b2 < b3) // b가 가장 빨리 커피를 만들 경우
			barista2.enqueue(id, coffee);
		else if(b3 < b1 && b3 < b2) // c가 가장 빨리 커피를 만들 경우
			barista3.enqueue(id, coffee);
		else if(b1 == b2 && b2 == b3) { // a, b, c 동시에 커피를 만들 경우

			// 끝내는 시간이 모두 같은 경우 가장 stat 이 낮은 바리스타에게 주문
			if(barista1.getStat() <= barista2.getStat() && barista1.getStat() <= barista3.getStat())
				barista1.enqueue(id, coffee);
			else if(barista2.getStat() <= barista1.getStat() && barista2.getStat() <= barista3.getStat())
				barista2.enqueue(id, coffee);
			else if(barista3.getStat() <= barista1.getStat() && barista3.getStat() <= barista2.getStat())
				barista3.enqueue(id, coffee);
		}
		else if(b1 == b2) { // a, b가 c보다 빨리 동시에 커피를 만들 경우
			if(barista1.getStat() <= barista2.getStat())
				barista1.enqueue(id, coffee);
			else
				barista2.enqueue(id, coffee);
		}
		else if(b1 == b3) { // a, c가 b보다 빨리 동시에 커피를 만들 경우
			if(barista1.getStat() <= barista3.getStat())
				barista1.enqueue(id, coffee);
			else
				barista3.enqueue(id, coffee);
		}
		else if(b2 == b3) { // b, c가 a보다 빨리 동시에 커피를 만들 경우
			if(barista2.getStat() <= barista3.getStat())
				barista2.enqueue(id, coffee);
			else
				barista3.enqueue(id, coffee);
		}
	}

	// 시간을 분 단위에서 시간:분 형태로 변환
	public static String intToTime(int minute) {
		long hours = TimeUnit.MINUTES.toHours(minute - minute % 60);
		long remainMinutes = minute - TimeUnit.HOURS.toMinutes(hours);
		String result = String.format("%02d:%02d", hours, remainMinutes);
		return result;
	}
}
