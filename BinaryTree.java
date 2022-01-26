package trees;

public class BinaryTree {

	public static void main(String[] args) {
		Tree t1 = new Tree();
		t1.insert(50, '*');
		t1.insert(25, 'A');
		t1.insert(75, '+');
		t1.insert(65, 'B');
		t1.insert(80, 'C');
		
		System.out.println(t1.minimum().fData + " " + t1.minimum().iData);
		System.out.println(t1.maximum().fData + " " + t1.maximum().iData);
	}

}

class Node
{
	int iData;
	char fData;
	Node leftChild;
	Node rightChild;
}


class Tree
{
private Node root;		// the only data field in Tree


public Node find(int key)
	{
	Node current = root;
	while(current.iData != key) {
		if(key < current.iData) 
			current = current.leftChild;
		else
			current = current.rightChild;
		if(current == null)
			return null;
	}
	return current;
	}

public void insert(int id, char dd) {
	Node newNode = new Node();
	newNode.iData = id;
	newNode.fData = dd;
	
	if(root == null)						//if empty
		root = newNode;
	else{
		Node current = root;				//start at root
		Node parent;						//to store parent value
		while(true) {
			parent = current;				//Assume current node is parent until reach one without children
			if(id < current.iData) {		//go left to parents child
				current = current.leftChild;//now current is no longer parent but current parents child
				if(current == null) {		//If child not exist
					parent.leftChild = newNode;	//null child value is now  newnode, with id and dd
					return;
				}
			} else {
				current = current.rightChild;
				if(current == null) {
					parent.rightChild = newNode;
					return;
				}
			}
		}  //exit while loop, program exits at return statements above
	}
	
}

public void inOrder(Node localRoot) {
	if(localRoot != null) {
		inOrder(localRoot.leftChild);
		System.out.print(/*localRoot.iData + " " +*/ localRoot.fData);
		inOrder(localRoot.rightChild);
	}
}

public void preOrder(Node localRoot) {
	if(localRoot != null) {
		System.out.print(/*localRoot.iData + " " +*/ localRoot.fData);
		preOrder(localRoot.leftChild);
		preOrder(localRoot.rightChild);
	}
} 

public void postOrder(Node localRoot) {
	if(localRoot != null) {
		postOrder(localRoot.leftChild);
		postOrder(localRoot.rightChild);
		System.out.print(/*localRoot.iData + " " +*/ localRoot.fData);
	}
} 

public Node minimum() {
	Node current, last = null;
	current = root;
	while(current != null) {
		last = current;
		current = last.leftChild;
	}
	return last;
}

public Node maximum() {
	Node current, last = null;
	current = root;
	while(current != null) {
		last = current;
		current = last.rightChild;
	}
	return last;
}

public boolean delete(int key) {
	Node current = root;
	Node parent = root;
	boolean isLeftChild = true;
	
	while(current.iData != key) {
		parent = current;
		if(key < current.iData) {
			isLeftChild = true;
			current = current.leftChild;
		}
		else 
		{
			isLeftChild = false;
			current = current.rightChild;
		}
		if(current == null)
			return false;
	}
	
	if(current.leftChild==null && current.rightChild == null) {		//node with no children
		if(current == root)
			root = null;
		else if(isLeftChild)
			parent.leftChild = null;
		else
			parent.rightChild = null;
		}
	
	//if no right child replace with left subtree
	else if(current.rightChild == null)
		if(current == root)
			root = current.leftChild;
		else if(isLeftChild)
			parent.leftChild = current.leftChild;
		else
			parent.rightChild = current.leftChild;
	
	//if no left child replace with right subtree
	else if(current.leftChild==null)
		if(current == root)
			root = current.rightChild;
		else if(isLeftChild)
			parent.leftChild = current.rightChild;
		else
			parent.rightChild = current.rightChild;
	
	//If node to be deleted has two children
	
	else {
		Node successor = getSuccessor(current);
		if(current == root)
			root = successor;
		else if(isLeftChild)
			parent.leftChild = successor;
		else
			parent.rightChild = successor;
			successor.leftChild = current.leftChild;
	}
	return true;
			
}



//Return node with next highest value after delNode goes to right child, then right childs left descendants
private Node getSuccessor(Node delNode) {
	Node successorParent = delNode;
	Node successor = delNode;
	Node current = delNode.rightChild;	//go to right
	while(current != null) {			//until no more left children
		successorParent = successor;
		successor = current;
		current = current.leftChild;	//go to left child
	}
	
	if(successor != delNode.rightChild) {	//if successor not right child
		successorParent.leftChild = successor.rightChild;
		successor.rightChild = delNode.rightChild;
	}
	return successor;
}
}


//Imagine using hand to go around tree, over and under, 

/*
git remote add origin https://github.com/TKDuff/BinaryTreeVisual.git
git branch -M main
git push -u origin main
*/