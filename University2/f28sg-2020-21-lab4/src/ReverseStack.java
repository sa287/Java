public class ReverseStack {

	/*
	 * 1: complete implementation for (int i = 0; i < st.size();i++) {
	 * queue.enqueue(st.pop()); System.out.println(queue.size());
	 * 
	 * 
	 * } for (int i = 0; i < queue.size();i++) {
	 * System.out.println(queue.toString()); st.push(queue.dequeue().toString());
	 * 
	 * }
	 */

	
	/**
	 * @param st, takes in stack with only string elements called st, creates temp variable which is the same size of st
	 * and then creates a new queue to the size of st using temp but plus one to ensure no overflows.
	 * For loops to iterate the popping of each element in the stack, where those values go into the queue with the enqueue method.
	 * The other loop pushes every dequeued element in the queue in string form into the stack at the start, st.
	 */
	public static void reverseStack(Stack<String> st) {
		int temp = st.size();
		Queue queue = new Queue(temp + 1);
		for (int i = 0; i < temp; i++) {
			queue.enqueue(st.pop());

		}
		for (int i = 0; i < temp; i++) {
			st.push(queue.dequeue().toString());

		}

	}

}
