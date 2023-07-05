import java.util.ArrayList;

/**
 * @author Subhan
 *
 */
public class DLinkedList {

	private class Node {
		private int value;
		private Node nextNode;
		private Node prevNode;

		public Node(int v) {
			value = v;
			nextNode = null;
			prevNode = null;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int v) {
			value = v;
		}

		public Node getNextNode() {
			return nextNode;
		}

		public void setNextNode(Node n) {
			nextNode = n;
		}

		public Node getPrevNode() {
			return prevNode;
		}

		public void setPrevNode(Node n) {
			prevNode = n;
		}

	}

	// Holds a reference to the head and tail of the list
	private Node headNode;
	private Node tailNode;

	public DLinkedList() {
		headNode = null;
		tailNode = null;
	}

	public Object getHeadValue() {
		if (headNode == null)
			return null;
		return headNode.value;
	}

	public Object getTailValue() {
		if (tailNode == null)
			return null;
		return tailNode.value;
	}

	public void addAtHead(int o) {
		Node newNode = new Node(o);
		newNode.setNextNode(headNode);
		if (headNode != null)
			headNode.setPrevNode(newNode);
		headNode = newNode;
		// special case for empty list
		if (tailNode == null)
			tailNode = newNode;
	}

	public void addAtTail(int o) {
		Node newNode = new Node(o);
		// this means that headNode == null too!
		if (tailNode == null) {
			tailNode = newNode;
			headNode = newNode;
		} else {
			newNode.setPrevNode(tailNode);
			tailNode.setNextNode(newNode);
			tailNode = newNode;
		}
	}

	public int deleteAtHead() {
		// list is empty
		if (headNode == null) {
			headNode = null;
			tailNode = null;
			return -1;
		}
		// singleton: must update tailnode too
		if (headNode == tailNode) {
			int res = headNode.getValue();
			headNode = null;
			tailNode = null;
			return res;
		}

		int res = headNode.getValue();
		headNode = headNode.getNextNode();
		headNode.setPrevNode(null);
		return res;
	}

	public int deleteAtTail() {
		// list is empty
		if (tailNode == null) {
			headNode = null;
			tailNode = null;
			return -1;
		}
		// singleton: must update tailnode too
		if (headNode == tailNode) {
			int res = tailNode.getValue();
			headNode = null;
			tailNode = null;
			return res;
		}
		int res = tailNode.getValue();
		tailNode = tailNode.getPrevNode();
		tailNode.setNextNode(null);
		return res;
	}

	public int delete(Node n) {
		if (n == null)
			return -1;
		Node next = n.getNextNode();
		Node prev = n.getPrevNode();
		int val = n.getValue();
		if (prev != null)
			prev.setNextNode(next);
		if (next != null)
			next.setPrevNode(prev);
		// deleting at the end
		if (n == tailNode)
			tailNode = prev;
		// deleteing at beginning
		if (n == headNode)
			headNode = next;
		return val;
	}

	public void insertAfter(Node n, int val) {
		if (n == null) { // this is the headNode
			addAtHead(val);
			return;
		}
		Node next = n.getNextNode();
		Node newNode = new Node(val);
		newNode.setPrevNode(n);
		newNode.setNextNode(next);
		n.setNextNode(newNode);
		if (next == null) { // insert at tail
			tailNode = newNode;
		} else {
			next.setPrevNode(newNode);
		}
	}

	// computes the size of the list
	public int size() {
		if (headNode == null)
			return 0;
		Node n = headNode;
		int size = 0;
		while (n != null) {
			size++;
			n = n.getNextNode();
		}
		return size;
	}

	// Predicate to check if the linked list is sorted
	public boolean isSorted() {
		if (headNode == null || headNode.nextNode == null)
			return true;
		Node i = headNode.nextNode;
		while (i != null) {
			if (i.getValue() < i.getPrevNode().getValue())
				return false;
			i = i.nextNode;
		}
		return true;
	}

	// toString methods to override printing of object
	public String toString() {
		Node n = headNode;
		StringBuffer buf = new StringBuffer();
		while (n != null) {
			buf.append(n.getValue());
			buf.append(" ");
			n = n.getNextNode();
		}
		return buf.toString();
	}

	/**
	 * Sorted the doubly linked list using the insertion-sort algorithm.
	 * 
	 * This is Question 4
	 * 
	 * Look at how insertionSort in ArraySort.java does insertion sort on arrays.
	 * The task is to perform the same algorithm, but sorting nodes linked together
	 * in a doubly linked list.
	 */
	
	
	public Node sorting (Node compare, Node newNode)  
	{  
		//create temp node to be used later on
	    Node temp;  
	  
	    // if doubly linked list is empty , it will just insert the newNode. this is like a base case.
	    if (compare == null)  
	        compare = newNode;  
	  
	    // If the newNode has to be inserted to the beginning, this following code covers this. It uses the insertAfter method given.
	    else if (compare.getValue() >= newNode.getValue()) 
	    {  
	    	insertAfter(newNode,compare.getValue()); 
	    }  
	  
	    else
	    {  
	        temp = compare;  
	  
	        // locate the place where the newNode is going to be inserted in.
	        while (temp.getNextNode() != null &&  
	            temp.getNextNode().getValue() < newNode.getValue())  
	            temp = temp.getNextNode();  
	  
	        //Make the doubly linked list work by adding links for the newly inserted node
	  
	        newNode.setNextNode(temp.getNextNode());  
	  
	        // if the newNode is not inserted at the end of the list, set it to the one before.
	        if (temp.getNextNode() != null)  
	            newNode.getNextNode().setPrevNode(newNode);  
	        //set the temp next node = new Node
	        temp.setNextNode(newNode); 
	        //set the value of the newNode = temp Node
	        newNode.setPrevNode(temp);  
	    }  
	    // return the comparison node.
	    return compare; 
	}  
	
	
	
	
	
	/**insertion sort was hard for me to get around implementing, you can see prototypes of me trying to do it all in one
	 * here lol. I decided to split up the sorting aspect into another function(see above).
	 * Sorts the list in order, using insertion sort algorithm template.
	 * 
	 */
	public void insertionSort() {
		/**	
		Node current = headNode;
		Node iterator = new Node(0);
		while (current!=null) {
			System.out.println(current.getValue());
			iterator.setValue(delete(current.getNextNode()));
			System.out.println(iterator.getValue());
			if(current.getValue() >= iterator.getValue()) {
				insertAfter(iterator,current.getValue());
			}
			else {
				current = current.getNextNode();	
			}
			
			current = current.getNextNode();	
		
		Node current = headNode;
		Node iterator = new Node(0);
		int temp = size();
		for (int i = 1; i < temp; i++) {
			iterator.setValue(delete(current.getNextNode()));
			System.out.println(iterator.getValue());
			System.out.println(current.getValue());
			while(i>=1 && iterator.getValue() > current.getValue()) {
				insertAfter(iterator,current.getValue());
				current = current.getNextNode();
			}
		}
		
		
		**/
		// create a node that is equal to the first element of the list.
	
		Node current = headNode;
		// Creates two new nodes that are used later on. sort is used to store sorted nodes and next is used as an iterator.
		Node sort = new Node(0);
		Node next = null;
		// while the current Node is not null it will do the following
		while (current!=null) {
			// set the next node equal to the next node of the current node.
			next = current.getNextNode();
			// cut links between the current variable. i realise i could use the delete method given but it returning a value 
			// threw me off.
			current.setPrevNode(null);
			current.setNextNode(null);
			// call the sorting function with the current node and sort node.
			sort = sorting(sort,current);
			//iterate the loop to fully sort the list.
			current = next;
		
			
		}
		// set the headNode to the start of the sorted linked list. I used getNExtNode because as i have to initialise
		// sort to 0, 0 would always be at the start. this gets passed it, not in a clean way but it works.
		headNode = sort.getNextNode();
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		DLinkedList d = new DLinkedList();
		d.addAtHead(4);
		d.addAtHead(1);
		d.addAtHead(7);
		d.addAtHead(10);
		System.out.println("Before sorting: " + d); // this will call the toString method
		d.insertionSort();
		System.out.println("After sorting: " + d);
	}

}
