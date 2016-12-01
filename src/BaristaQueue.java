
// Linked List Queue
public class BaristaQueue {
	private class Node {
		private int id;
		private String coffee;
		private int remainingTime;
		private Node nextNode;

		Node(int id, String coffee) {
			this.id = id;
			this.coffee = coffee;
			this.remainingTime = getCost(coffee) / stat;
			this.nextNode = null;
		}
	}

	private Node front;
	private Node rear;
	private int remainingTime;
	private int stat;
	private String rank;

	BaristaQueue(String rank) {
		this.front = null;
		this.rear = null;
		this.remainingTime = 0;
		this.rank = rank;

		if(rank == "manager")
			this.stat = 4;
		else if(rank == "regular")
			this.stat = 2;
		else if(rank == "intern")
			this.stat = 1;
	}

	// Ŀ�� �ڽ�Ʈ
	private int getCost(String coffee) {
		if("Espresso".compareTo(coffee) == 0)
			return 4;
		else if("Americano".compareTo(coffee) == 0)
			return 8;
		else if("CaffeLatte".compareTo(coffee) == 0)
			return 12;
		else if("CaramelMacchiato".compareTo(coffee) == 0)
			return 16;
		else if("CaffeMocha".compareTo(coffee) == 0)
			return 20;
		else if("Cappuccino".compareTo(coffee) == 0)
			return 24;
		else {
			System.out.println("BaristaQueue.java getCost() error");
			return 0;
		}
	}

	public boolean empty() {
		return front == null;
	}

	// enqueue
	public void enqueue(int id, String coffee) {
		Node node = new Node(id, coffee);

		remainingTime += getCost(coffee) / stat;

		if(empty()) {
			rear = node;
			front = node;
		}
		else {
			rear.nextNode = node;
			rear = node;
		}
	}

	public String peek() {
		if(empty()) throw new ArrayIndexOutOfBoundsException();
		return front.coffee;
	}

	// dequeue
	public String dequeue() {
		String coffee = peek();
		front = front.nextNode;

		if(empty())
			rear = null;

		return coffee;
	}

	public int getId() {
		return this.front.id;
	}

	public int getStat() {
		return this.stat;
	}

	// ���� ������ Ŀ�Ǹ� �����µ� �ɸ��� �� �ð��� ���� ����Ͽ� ����
	public int prediction(String coffee) {
		return remainingTime + getCost(coffee) / stat;
	}

	// Ŀ�ǰ� �ϼ��� ��� id�� ����, �ƴ� ��� 0�� ����
	public Coffee oneMinutePassed() {
		Coffee completeCoffee;
		if(!empty()) {
			this.remainingTime--;
			this.front.remainingTime--;

			if(this.front.remainingTime == 0) {
				completeCoffee = new Coffee(this.front.coffee, this.getId());
				System.out.println(CafeManagement.intToTime(CafeManagement.curTime) + " " + rank + " " + completeCoffee.getNumber() + "�� �մ� " + completeCoffee.getKinds());

				this.dequeue();
				return completeCoffee;
			}
		}

		completeCoffee = new Coffee("none", 0);
		return completeCoffee;
	}
}
