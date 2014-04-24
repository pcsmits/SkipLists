package edu.csupomona.cs.cs241.prog_assgmnt_2;

import java.io.Serializable;
import java.util.ArrayList;

class RedBlackTree<V extends Comparable<V>> implements Serializable{
	public RedBlackTree(){}
	public RedBlackTree(V value){
		Insert(value);
	}

	static final long serialVersionUID = 1L;

	private RedBlackNode<V> root;

	public void Insert(V value){
		if(root == null){
			root = new RedBlackNode<V>(value);
			//root.parent = root;
			root.isRed = false;
			return;
		}
		RedBlackNode<V> tmp = find(value);
		//avoid adding duplicate values
		if(tmp.data.compareTo(value) == 0){
			return;
		}
		if(value.compareTo(tmp.data) == 1){
			tmp.rightChild = new RedBlackNode<V>(value);
			tmp.rightChild.parent = tmp;
			balance(tmp.rightChild);
		}
		else{
			tmp.leftChild = new RedBlackNode<V>(value);
			tmp.leftChild.parent = tmp;
			balance(tmp.leftChild);
		}
	}

	public void Remove(V value){
		
	}

	public boolean isEmpty(){
		if(root == null){
			return true;
		}
		return false;
	}

	public ArrayList<V> toArrayList(){
		traversal.clear();
		ioTraverse(root);
		return traversal;
	}

	private ArrayList<V> traversal = new ArrayList<V>();

	private void ioTraverse(RedBlackNode<V> node){
		if(node == null){return;}
		ioTraverse(node.leftChild);
		traversal.add(node.data);
		ioTraverse(node.rightChild);
	}

	private void rotateRight(RedBlackNode<V> node){
		if(node != root){
			if(node.isLeftChild()){
				node.parent.leftChild = node.leftChild;
				node.leftChild.parent = node.parent;
				node.leftChild = node.leftChild.rightChild;
				node.leftChild.parent = node;
				node.parent.leftChild.rightChild = node;
				node.parent = node.parent.leftChild;
			}
			else{
				node.parent.rightChild = node.leftChild;
				node.leftChild.parent = node.parent;
				node.leftChild = node.leftChild.rightChild;
				node.leftChild.parent = node;
				node.rightChild.rightChild = node;
				node.parent = node.parent.rightChild;
			}
		}
		else{
			root = node.leftChild;
			root.parent.leftChild = root.rightChild;
			root.rightChild.parent = root.parent;
			root.rightChild = root.parent;
			root.rightChild.parent = root;
			root.parent = null;
		}
	}

	private void rotateLeft(RedBlackNode<V> node){
		if(node != root){
			if(node.isLeftChild()){
				node.parent.leftChild = node.rightChild;
				node.rightChild.parent = node.parent;
				node.rightChild = node.rightChild.leftChild;
				node.rightChild.parent = node;
				node.parent.leftChild.leftChild = node;
				node.parent = node.parent.leftChild;
			}
			else{
				node.parent.rightChild = node.rightChild;
				node.rightChild.parent = node.parent;
				node.rightChild = node.rightChild.leftChild;
				node.rightChild.parent = node;
				node.parent.rightChild.leftChild = node;
				node.parent = node.parent.rightChild;
			}
		}
		else{
			root = node.rightChild;
			root.parent.rightChild = root.leftChild;
			root.leftChild.parent = root.parent;
			root.leftChild = root.parent;
			root.leftChild.parent = root;
			root.parent = null;
		}
	}

	//returns the node containing the value, or its parent if the node's space is null
	private RedBlackNode<V> find(V value){
		RedBlackNode<V> current = root;
		while(true){
			if(value.compareTo(current.data) >= 1){
				if(current.rightChild != null){
					current = current.rightChild;
				}
				else{
					return current;
				}
			}
			else if(value.compareTo(current.data) <= -1){
				if(current.leftChild != null){
					current = current.leftChild;
				}
				else{
					return current;
				}
			}
			else{
				return current;
			}
		}
	}

	private void balance(RedBlackNode<V> node){
		if(node == root || !node.parent.isRed){
			return;
		}
		else if(node.parent.isRed && node.uncle() != null && node.uncle().isRed){
			node.parent.isRed = false;
			node.uncle().isRed = false;
			node.parent.parent.isRed = true;
			balance(node.parent.parent);
			root.isRed = false;
			return;
		}
		else if(node.parent.isRed && (node.uncle() == null || !node.uncle().isRed)){
			if(!node.isLeftChild() && node.parent.isLeftChild()){
				rotateLeft(node.parent);
				balance(node.leftChild);
			}
			if(node.isLeftChild() && !node.parent.isLeftChild()){
				rotateRight(node.parent);
				balance(node.rightChild);
			}
			if(node.isLeftChild() && node.parent.isLeftChild()){
				rotateRight(node.parent.parent);
				boolean tmp = node.parent.isRed;
				node.parent.isRed = node.parent.rightChild.isRed;
				node.parent.rightChild.isRed = tmp;
			}
			if(!node.isLeftChild() && !node.parent.isLeftChild()){
				rotateLeft(node.parent.parent);
				boolean tmp = node.parent.isRed;
				node.parent.isRed = node.parent.leftChild.isRed;
				node.parent.leftChild.isRed = tmp;
			}
			root.isRed = false;
		}
	}
}
