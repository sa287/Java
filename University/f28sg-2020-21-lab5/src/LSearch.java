public class LSearch {


	private class Node {
		private Entry value;
		private Node nextNode;

		public Node(Entry v) {
			value = v;
			nextNode = null;
		}

		public Entry getValue() {
			return value;
		}

		public Node getNextNode() {
			return nextNode;
		}

		// Sets the NextNode to the given Node
		public void setNextNode(Node n) {
			nextNode = n;
		}
	}

	// Holds a reference to the head of the list
	private Node headNode;

	public LSearch() {
		headNode = null;
	}

	public void addAtHead(Entry e) {
		Node newNode = new Node(e); 
		newNode.setNextNode(headNode); 
		headNode = newNode; 
	}
	
	/*
	 * Part 3: complete
	 */	
	/**
	 * @param name, name to be found in string form	
	 * @return the number like in previous examples. It does this by creating a temp Node so we can iterate through the linked list
	 * and keep a hold of the current position. It is initialized at the headNode so we can start at the front of the list.
	 * It creates a loop that will end once the end of the list has been reached ( when iterator variable uses .getNextNode and its contents
	 * is set to null, this means it has been reached). The loop keeps checking if the iterators name matches the parameters. If it does it will return the number of the iterator 
	 * Node. Otherwise it will just set the iterator to the next node in the list. If the loop is ended without returning a number, it will return -1
	 * as obviously the item has not been found. 
	 */
	public int linearSearch(String name){
		// your code
		Node iterator = headNode;
		
		while (iterator!=null) {
			if (iterator.getValue().getName().equals(name))
				return iterator.getValue().getNumber();
			iterator = iterator.getNextNode();
		}
		return -1; 
	}
}
