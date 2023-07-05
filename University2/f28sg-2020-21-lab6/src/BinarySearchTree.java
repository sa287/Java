public class BinarySearchTree {

	/**
	 * @author Subhan
	 *
	 */
	private class BSTNode { // private class to hold a tree node

		private int value;
		private BSTNode leftChild; // left subtree
		private BSTNode rightChild; // right subtree

		public BSTNode(int v) {
			value = v;
			leftChild = null;
			rightChild = null;
		}

		public BSTNode getLeftChild() {
			return leftChild;
		}

		public BSTNode getRightChild() {
			return rightChild;
		}

		public void setLeftChild(BSTNode n) {
			leftChild = n;
		}

		public void setRightChild(BSTNode n) {
			rightChild = n;
		}

		public int getValue() {
			return value;
		}

		// inserting nodes
		public void insert(BSTNode n) {
			// insert somewhere into the left subtree
			if (n.value < this.value) {
				if (this.getLeftChild() == null) {
					// add node n as left child
					this.setLeftChild(n);
				} else {
					// otherwise recurse down the left subtree
					this.getLeftChild().insert(n);
				}
			}

			// insert somewhere into the right subtree
			else if (n.value > this.value) {
				if (this.getRightChild() == null) {
					// add node n as right child
					this.setRightChild(n);
				} else {
					// otherwise recurse down the right subtree
					this.getRightChild().insert(n);
				}
			}

			// a duplicate which we don't allow (could also raise exception)
			else {
				return;
			}
		}

		// lookup a node
		public boolean search(int v) {
			// lookup somewhere into the left subtree
			if (v < this.value) {
				if (this.getLeftChild() == null) {
					// value v cannot be in the tree
					return false;
				} else {
					// otherwise recurse down the left subtree
					return this.getLeftChild().search(v);
				}
			} else if (v > this.value) {
				if (this.getRightChild() == null) {
					// value v cannot be in the tree
					return false;
				} else {
					// otherwise recurse down the right subtree
					return this.getRightChild().search(v);
				}
			}

			// otherwise this.value == v
			return true;
		}

		// useful for the delete method
		public BSTNode getLargestValueNode() {
			// descend down the right subtree until we get the largest value.
			// i.e. until we cannot continue to go down the right subtree
			if (this.getRightChild() == null) {
				return this;
			} else {
				return this.getRightChild().getLargestValueNode();
			}
		}

		// deleting a node with the given value
		public BSTNode delete(int v) {
			// this is not the node we want to remove
			if (v != this.value) {
				// go right?
				if (v > this.value && this.getRightChild() != null) {
					// we're going to update our right child reference
					this.rightChild = this.getRightChild().delete(v);
					return this;
				}

				// go left?
				else if (v < this.value && this.getLeftChild() != null) {
					// we're going to update our left child reference
					this.leftChild = this.getLeftChild().delete(v);
					return this;
				}

				// we are trying to delete a non-existent value
				else {
					return this;
				}
			}

			// this is the node we want to remove
			else {
				// difficult case: does it have two children?
				if (this.getLeftChild() != null && this.getRightChild() != null) {

					// step 1: find the largest value in the left subtree
					BSTNode largestLeft = this.getLeftChild().getLargestValueNode();

					// step 2: set this node's value to the value of the largest left node value
					this.value = largestLeft.getValue();

					// step 3: delete the largest node on left then set Node returned as left child.
					this.leftChild = this.getLeftChild().delete(largestLeft.getValue());

					return this;
				}

				// the easy cases

				// has only left subtree
				else if (this.getLeftChild() != null) {
					// return to the parent node what this node's left child is
					return this.getLeftChild();
				}

				// has only right subtree
				else if (this.getRightChild() != null) {
					// return to the parent node what this node's right child is
					return this.getRightChild();
				}

				// has no subtrees (is a leaf node)
				else {
					System.out.println("Deleting Node with value " + this.value);
					return null;
				}
			}
		}

		public int numberOfNodes() {
			int valueToReturn = 1; // this is 1 to count this node
			if (leftChild != null)
				valueToReturn += leftChild.numberOfNodes(); // add the nodes in the left subtree
			if (rightChild != null)
				valueToReturn += rightChild.numberOfNodes(); // add the nodes in the right subtree
			return valueToReturn; // return the number of nodes
		}

		// Part 1: complete
		
		/** checks if current node is equal to null, if so it will break. If the current node has a left child it will
		 * recursively start again with the left child as the current node. after finding the lowest left child to start 
		 * the in order traversal it will start to print out values. It will do the same with the right children.
		 * 
		 */
		public void inOrderTraversalPrint() {
			if (this == null) {
				return;
			}
			if (this.getLeftChild()!=null) {
				this.getLeftChild().inOrderTraversalPrint();
			}
			System.out.println(this.getValue());
			if (this.getRightChild()!=null) {
				this.getRightChild().inOrderTraversalPrint();
			}
			
			
			
			
		}
			
		

		// Part 2: complete
		/**
		 * @param dl, the doubly linked list with values that are returned like the previous q1 example, except with
		 * adding at the tail of the list instead of printing, and when it recurses, it will do so whilst keeping the dl list.
		 */
		public void inOrderTraversal(DLinkedList<Integer> dl) {
			if (this == null) {
				return;
			}
			if (this.getLeftChild()!=null) {
				this.getLeftChild().inOrderTraversal(dl);
			}
			dl.addAtTail(this.getValue());
			if (this.getRightChild()!=null) {
				this.getRightChild().inOrderTraversal(dl);
			}
		}

	}

	private BSTNode rootNode = null;

	public void insert(int v) {
		if (rootNode == null) {
			rootNode = new BSTNode(v);
		} else {
			rootNode.insert(new BSTNode(v));
		}
	}

	public void delete(int v) {
		if (rootNode != null) {
			rootNode = rootNode.delete(v);
		}
	}

	public boolean search(int v) {
		if (rootNode != null) {
			return rootNode.search(v);
		} else {
			return false;
		}
	}

	public boolean isEmpty() {
		return (rootNode == null);
	}

	public int size() {
		if (rootNode == null) {
			return 0;
		} else {
			return rootNode.numberOfNodes();
		}
	}

	// Part 1
	public void inOrderTraversalPrint() {
		if (rootNode != null)
			rootNode.inOrderTraversalPrint();
	}

	// Part 2: complete
	/**
	 * @return dll, the finished doubly linked list, this works the same way as the example given in q1. checks if first node, root, is not null, if so,
	 *  it will start the cycle.
	 */
	public DLinkedList<Integer> inOrderTraversal() {
		DLinkedList<Integer> dll = new DLinkedList<>();
		/* your code goes here */
		if (rootNode!=null)
			rootNode.inOrderTraversal(dll);
		/* remove this */
		return dll;
	}

	public static void main(String[] args) {

		System.out.println("******* Tree 1 : 3 nodes ***********");
		BinarySearchTree bst1 = new BinarySearchTree();
		bst1.insert(1);
		bst1.insert(2);
		bst1.insert(3);
		bst1.insert(4);
		bst1.inOrderTraversalPrint();

		System.out.println("******* Tree 2 : 1 node ***********");
		BinarySearchTree bst2 = new BinarySearchTree();
		bst2.insert(1);
		bst2.inOrderTraversalPrint();

		System.out.println("******* Tree 3 : empty ***********");
		BinarySearchTree bst3 = new BinarySearchTree();
		bst3.inOrderTraversalPrint();

	}
}
