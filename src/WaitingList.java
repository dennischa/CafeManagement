import java.util.ArrayList;

public class WaitingList {
	private ArrayList<Order> orderList;

	WaitingList(){
		orderList = new ArrayList<Order>();
	}

	public void completeOrder(Coffee c) {
		if(c.getNumber() != 0)
		{
			for(int i=0; i<orderList.size(); i++) {
				if(orderList.get(i).getId() == c.getNumber()) {
					orderList.get(i).subTotalNumber();
					if(orderList.get(i).getTotalNumber() == 0) {
						System.out.println(CafeManagement.intToTime(CafeManagement.curTime) + " " + orderList.get(i).getId() + "번 손님 주문이 완료");
						CafeManagement.html.addTable(orderList.remove(i));
					}
					return;
				}
			}
		}
	}

	public void add(Order o) {
		orderList.add(o);
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}
}