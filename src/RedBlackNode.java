package edu.csupomona.cs.cs241.prog_assgmnt_2;

import java.io.Serializable;

class RedBlackNode<V> implements Serializable{
	public RedBlackNode(V value){
		data = value;
	}

	public RedBlackNode<V> parent;
	public RedBlackNode<V> leftChild;
	public RedBlackNode<V> rightChild;

	public V data;
	public boolean isRed = true;

	public boolean isLeftChild(){
		if(parent != null && parent.leftChild == this){
			return true;
		}
		return false;
	}

	public RedBlackNode<V> uncle(){
		if(parent.isLeftChild()){
			return parent.parent.rightChild;
		}
		else{
			return parent.parent.leftChild;
		}
	}
}
