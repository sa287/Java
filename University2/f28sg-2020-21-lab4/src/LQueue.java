/**
 * @author Subhan
 *
 */
public class LQueue {
	
	private class Node{
		Object element;
		Node next;
		
		public Node(Object e, Node n){
			element = e;
			next = n;
		}
		
		public Node(Object e){
			element = e;
			next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private int size;
	
	public LQueue(){
		head = null;
		tail = null;	
	}
	
	/*
	 * Part 3: complete the following methods
	 */
	
	// Part 3: complete	
	
	/**
	 * @return boolean values true or false, depending if the value of the tail node is null, i.e., the list is empty.
	 */
	public boolean isEmpty(){
		if (tail == null) {
			return true;
		}
		else {
			return false;
		}
		
	}
		
	/**
	 * @return size, the pre-calculated class variable of the linked list queue. It is modified in the enqueue and dequeue methods.
	 */
	public int size(){
		return size; 
	}

	/**
	 * @param o, if the linked list is empty, it will add in the new node with the value of the object o in it,
	 * set the head and tail of the linked list to that node, and add up the size variable. If its not empty, 
	 * add the node to the end of the list and set its next node to the current tail. Set the new node to be the new tail and
	 * then add the size up.
	 * 
	 * 
	 */
	public void enqueue(Object o){
		if (isEmpty()) {
			Node newNode = new Node(o);
			head = tail = newNode;
			size++;
		}
		else {
			Node newNode = new Node(o,tail);
			tail.next = newNode;
			size++;
		}

	}
	// Part 3: complete	
	
	/**
	 * @return temp.element, the value of what was in the head node using the temporary node temp. Making sure to remove the current head
	 * and setting a new head to whatever is next in line, whilst also subtracting from the size of the queue.
	 * @throws QueueException when the queue is found empty using the isEmpty() method
	 */
	public Object dequeue() throws QueueException{
		if (isEmpty()) {
			throw new QueueException("Error. Queue is empty");
			
		}
		else{
			
			Node temp = head;
			head = head.next;
	
			size--;
			return temp.element;
		}
		// dummy value
	}
	
	// Part 3: complete
	/**
	 * @return head.element, the value that is in the head node. this happens only when the isEmpty function returns false, meaning when the linked list is not empty.
	 * @throws QueueException Otherwise it will throw this due to the list being empty.
	 */
	public Object front() throws QueueException{
		if (isEmpty()) {
			throw new QueueException("Error. Queue is empty");
		}
		else {
			return head.element;
		}
		 // dummy value
	}
	
}
