//Linked List Queue
public class OrderQueue {
	private class Node {
		private Order order;
		private Node nextNode;

		Node(Order order) {
			this.order = order;
			this.nextNode = null;
		}
	}

	private Node front;
	private Node rear;

	OrderQueue() {
		this.front = null;
		this.rear = null;
	}

	public boolean empty() {
		return front == null;
	}

	// enqueue
	public void enqueue(Order order) {
		Node node = new Node(order);

		if(empty()) {
			rear = node;
			front = node;
		}
		else {
			rear.nextNode = node;
			rear = node;
		}
	}

	public Order peek() {
		if(empty()) throw new ArrayIndexOutOfBoundsException();
		return front.order;
	}

	// dequeue
	public Order dequeue() {
		Order item = peek();
		front = front.nextNode;

		if(empty())
			rear = null;

		return item;
	}
}