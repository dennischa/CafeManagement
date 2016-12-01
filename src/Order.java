import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Order {
	private int id;
	private int time;
	private int totalNumber;
	private ArrayList<Coffee> Coffees;

	Order(int id, String time, ArrayList<Coffee> cL){
		this.id = id;
		this.time = timeToInt(time);
		Coffees = new ArrayList<Coffee>();
		Coffees = cL;

		Collections.sort(Coffees, myCompare);

		this.totalNumber = 0;
		for(Coffee c : Coffees){
			this.totalNumber += c.getNumber();
		}
	}

	private int timeToInt(String time) {
		String[] timeArr = time.split(":");
		int hour = Integer.parseInt(timeArr[0]);
		int minute = Integer.parseInt(timeArr[1]);

		return hour * 60 + minute;
	}

	Comparator<Coffee> myCompare = new Comparator<Coffee>() {
		public int compare(Coffee c1, Coffee c2) {
			return getCoffeeWeight(c2.getKinds()) - getCoffeeWeight(c1.getKinds());
		}
	};

	private int getCoffeeWeight(String coffee) {
		if("Espresso".compareTo(coffee) == 0)
			return 1;
		else if("Americano".compareTo(coffee) == 0)
			return 2;
		else if("CaffeLatte".compareTo(coffee) == 0)
			return 3;
		else if("CaramelMacchiato".compareTo(coffee) == 0)
			return 4;
		else if("CaffeMocha".compareTo(coffee) == 0)
			return 5;
		else if("Cappuccino".compareTo(coffee) == 0)
			return 6;
		else {
			System.out.println("Order.java getCoffeeWeight() error");
			return 0;
		}
	}

	public void printOrder() {
		System.out.println(id);
		System.out.println(time);		
		for(Coffee c : Coffees){
			System.out.println(c.getKinds() + " " + c.getNumber());				
		}
	}
	public int getId() {
		return this.id;
	}

	public int getTime() {
		return this.time;
	}

	public int getTotalNumber() {
		return this.totalNumber;
	}

	public ArrayList<Coffee> getCoffeeArray() {
		return this.Coffees;
	}
	
	public void subTotalNumber() {
		this.totalNumber--;
	}
}