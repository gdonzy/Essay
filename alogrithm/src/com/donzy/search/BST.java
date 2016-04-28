package com.donzy.search;

public class BST<Key extends Comparable<Key>,Value>{
	private Node root;   //二叉树根节点

	private class Node{
		private Key key;          //键
		private Value val;        //值
		private Node left,right;  //子节点链接
		private int N;            //该节点的子节点数目

		public Node(Key key,Value val,int N){
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	public int size(){
		return size(root);	
	}

	private int size(Node x){
		if(x == null){ return 0;}	
		else return x.N;
	}

	public Value get(Key key){
		return get(root,key);	
	}

	private Value get(Node x,Key key){
		if(x == null) {return null;}				
		int cmp = key.compareTo(x.key);
		if(cmp > 0) {
			return get(x.right,key);
		}
		else if(cmp < 0){
			return get(x.left,key);	
		}
		else{
			return x.val;
		}
	}

	public void put(Key key,Value val){
		root = put(root,key,val);	
	}

	private Node put(Node x,Key key,Value val){
		if (x == null) {
			return new Node(key,val,1);	
		}	
		int cmp = key.compareTo(x.key);
		if (cmp > 0){
			x.right = put(x.right,key,val);		
		}
		else if(cmp < 0){
			x.left = put(x.left,key,val);	
		}
		else{
			x.val = val;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Key min(){
		return min(root).key;	
	}

	private Node min(Node x){
		if (x.left == null) return x;	
		return min(x.left);
	}

	public Key max(){
		return max(root).key;
	}

	private Node max(Node x){
		if(x.right == null) {return x;}	
		return max(x.right);
	}
	
	public Key floor(Key key){
		Node ret = floor(root,key);
		if(ret == null) return null;
		return ret.key;
	}

	private Node floor(Node x,Key key){
		if(x == null){ return null;}
		int cmp = key.compareTo(x.key);
		if(cmp > 0) return floor(x,key);
		if(cmp == 0) return x;
		Node ret = floor(x.right,key);
		if(ret == null){
			return x;
		}
		else{
			return ret;	
		}
	}


	public Key select(int index) {
		Node ret = select(root,index);
		if(ret == null) return null;
		return ret.key;
	}	

	private Node select(Node x,int cnt) {
		//返回排名为cnt的节点
		if(x == null) return null;		
		int n = size(x.left)+1;
		if(n > cnt)	{return select(x.left,cnt);}
		if(n < cnt) {return select(x.right,cnt-n);}
		return x;
	}

	public int rank(Key key){
		return rank(root,key);
	}

	private int rank(Node x,Key key){
		//返回键值小于key的节点数量	
		if(x == null) return 0;
		int cmp = key.compareTo(x.key);
		if(cmp > 0) {return size(x.left)+ 1 + rank(x.right,key);}
		else if(cmp < 0) {return rank(x.left,key);}
		else return size(x.left);
	}

	public void deleteMin(){
		root = deleteMin(root);	
	}

	private Node deleteMin(Node x){
		if(x == null) return null;
		if(x.left == null) return x.right;	
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void delete(Key key){
		root = delete(root,key);	
	}

	private Node delete(Node x,Key key) {
		//删除键值为key的节点
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp > 0){x.right = delete(x.right,key);}
		else if(cmp < 0) {x.left = delete(x.left,key);}
		else {
			if(x.left == null) {return x.right;}			
			if(x.right == null) {return x.left;} 
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
}


