
class StackException extends RuntimeException{    
		public StackException(String err) {
			super(err);
		}
}



public class Stack {
	
	private class Node{
		int element;
		Node next;
		
		public Node(int e, Node n){
			element = e;
			next = n;
		}
		
		public int getValue() {
			return element;
		}
		
		public Node getNext() {
			return next;
		}
	}
	
	// this is a reference to the head node of the linked list
	private Node top;
	
	// keep track of the number of elements in the stack
	private int size;
	
	public Stack(){
		top = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		return top == null;
	}
	
	public int size(){
		return size; 
	}
	
	/**
	 * creates a new node to be pushed into the stack, (added to the linked list) with the int o value.
	 * increments the size of the list to match and the header node.
	 */
	public void push(int o){  
		Node oldNode = top;
		Node newNode = new Node(o,oldNode); 
		top = newNode;
		size++;
	}
	
	/**
	 * @return the value of the node which is popped from the stack
	 * @throws StackException when the stack is empty and a pop is tried
	 */
	public int pop() throws StackException{
		if (isEmpty()) {
			throw new StackException("The stack is empty.");
		}
		else {
			Node oldNode = top;
			top = oldNode.getNext();
			size--;
			return oldNode.getValue();
			
		}
		
		
		 // dummy remove
	}
	
	
	/**
	 * @return top node of the stack's value,
	 * @throws StackException when the stack is empty
	 */
	public int top() throws StackException{
		if (isEmpty()) {
			throw new StackException("Stack is empty.");
		}
		
		else{
			Node start = top;
			Node stackTop = null;
			for (int i = 0;i < size();i++) {
				System.out.println(start.getValue());
				stackTop = start;
				start.getNext();
			
			}
			return stackTop.getValue();
		
		
		}
		
	
		
		
		 // dummy remove
	}
	
	
	

}
