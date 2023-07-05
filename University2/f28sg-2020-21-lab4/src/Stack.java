class StackException extends RuntimeException{    
	public StackException(String err) {
		super(err);
	}
}

public class Stack<T> {
	private Object[] S;
	private int top;
	private int capacity;
	
	private static int DEFAULT_SIZE = 100;
	
	public Stack(int size){
		capacity = size;
		S = new Object[size];
		top = -1;
	}

	public Stack(){
		this(DEFAULT_SIZE);
	}
	
	public boolean isEmpty(){
		return top < 0;
	}
	
	public int size(){
		return top+1; 
	}
	

	public void push(T e){
		if(size() == capacity)
			throw new StackException("stack is full");
		S[++top] = e;
	}
	
	@SuppressWarnings("unchecked")
	public T pop() throws StackException{
		if(isEmpty())
			throw new StackException("stack is empty");
		// this is type safe because we type checked the push method
		return (T) S[top--];
	}
	
	@SuppressWarnings("unchecked")
	public T top() throws StackException{
		if(isEmpty())
			throw new StackException("stack is empty");
		// this is type safe because we type checked the push method
		return (T) S[top];
	}
	
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		if(size() > 0)
			buf.append(S[0]);
		for(int i = 1; i <= top;i++){
			buf.append(", " + S[i]);
		}
		buf.append("]");
		return buf.toString();
	}
}