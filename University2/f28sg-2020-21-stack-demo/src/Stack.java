class StackException extends RuntimeException{    
	public StackException(String err) {
		super(err);
	}
}

public class Stack {
	private Object[] S;
	private int topIndex;
	private int capacity;
	
	private static int DEFAULT_SIZE = 100;
	
	public Stack(int size){
		capacity = size;
		S = new Object[size];
		topIndex = -1;
	}

	public Stack(){
		this(DEFAULT_SIZE);
	}
	
	/**
	 * @return true if there are no elements in the stack
	 */
	public boolean isEmpty(){
		// TODO
		// return false;
		//return (top ==-1);
		return(size() == 0);
		
	}
	
	/**
	 * @return a count of the number of elements in the stack
	 */
	public int size(){
		// TODO
		return (topIndex + 1);
	}
	

	/** Adds a new element to the stack
	 * 
	 * @param e the object to add to the top of the stack
	 */
	public void push(Object e){
		// CHECK IF FULL
		if (capacity == size()) {
			// IF ITS FULL THEN THROW EXCEPTION
			throw new StackException("Stack is full.");
		}
	
		S[topIndex] = e;
		topIndex++;
		
	}
	
	/** Removes an element from the top of the stack
	 * 
	 * @return the object that was at the top of the stack
	 * @throws StackException if the stack is empty
	 */
	public Object pop() throws StackException{
		// TODO
		if (isEmpty()) {
			throw new StackException("The stack is empty.");
		}
		
		Object objectToReturn = S[topIndex];
		topIndex--;
		return objectToReturn;
		
	}
	
	/** Returns object at the top of the stack
	 * 
	 * @return the object at the top of the stack
	 * @throws StackException is the stack is empty
	 */
	public Object top() throws StackException{
		if (isEmpty()) {
			throw new StackException("The stack is empty.");
		}
		return  S[topIndex];
		
		
	}
	
	/** A helper method to pretty-print the elements in the stack
	 *
	 */
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		if(size() > 0)
			buf.append(S[0]);
		for(int i = 1; i <= topIndex;i++){
			buf.append(", " + S[i]);
		}
		buf.append("]");
		return buf.toString();
	}
}
