package com.donzy.search;

public class RedBlackBST<Key extends Comparable<Key>,Value >{
	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private class Node{
		Key key;	
		Value val;
		Node left,right;
		int N;
		boolean color;

		public Node(Key key,Value val,int N,boolean color){
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	
	private boolean isRed(Node x){
		if(x == null) return false;	
		return x.color == true;
	}

	public boolean isEmpty(){
		return isEmpty(root);
	}
	
	private boolean isEmpty(Node h){
		return h == null;
	}

	public int size(){
		return size(root);	
	}

	private int size(Node x){
		if(x == null){ return 0;}	
		else return x.N;
	}
	
	public Key min(){
		return min(root).key;	
	}

	private Node min(Node x){
		if (x.left == null) return x;	
		return min(x.left);
	}

	private Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;	
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	} 	
	
	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	private Node flipColors(Node h){
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;

		return h;
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
		root.color = BLACK;
	}

	private Node put(Node h,Key key,Value val){
		if(h == null) return new Node(key,val,1,RED);		
		int cmp = key.compareTo(h.key);
		if(cmp > 0) h.right = put(h.right,key,val);
		else if(cmp < 0) h.left = put(h.left,key,val);
		else h.val = val;
		
		if(isRed(h.right) && !isRed(h.left)) rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left)) rotateRight(h);
		if(isRed(h.left) && isRed(h.right)) flipColors(h);

		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}

	private Node balance(Node h){
		if(isRed(h.right)) h = rotateLeft(h);  

		if(isRed(h.right) && !isRed(h.left)) rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left)) rotateRight(h);
		if(isRed(h.left) && isRed(h.right)) flipColors(h);

		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}

	private Node moveRedLeft(Node h){
		//鍋囪鑺傜偣h涓虹孩鑹诧紝h.left鍜宧.left.left閮芥槸榛戣壊銆�
		flipColors(h);	
		if(isRed(h.right.left)){
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
		}
		return h;
	}

	private Node moveRedRight(Node h){
		//鍋囪鑺傜偣h涓虹孩鑹诧紝h.right鍜宧.right.left閮芥槸榛戣壊
		flipColors(h);	
		if(!isRed(h.left.left)){
			h = rotateRight(h);
		}
		return h;
	}

	public void deleteMin(){
		if(!isRed(root.left) && !isRed(root.right)){
			root.color = RED;
		}
		root = deleteMin(root);
		if(!isEmpty()) root.color = BLACK;
	}

	private Node deleteMin(Node h){
		if(h.left == null){
			return null;
		}
		if(!isRed(h.left) && !isRed(h.left.left)){
			h = moveRedLeft(h);
		}
		h.left = deleteMin(h.left);
		return balance(h);
	}

	public void deleteMax(){
		if(!isRed(root.left) && !isRed(root.right)){
			root.color = RED;
		}
		root = deleteMax(root);
		if(!isEmpty()){
			root.color = BLACK;
		} 
	}

	private Node deleteMax(Node h){
		if(isRed(h.left)){
			h = rotateRight(h);
		}	
		if(h.right == null){
			return null;
		}
		if(!isRed(h.right) && !isRed(h.right.left)){
			h = moveRedRight(h);
		}
		h.right = deleteMax(h.right);
		return balance(h);
	}

	public void delete(Key key){
		if(!isRed(root.left) && !isRed(root.right)){
			root.color = RED;	
		}
		root = delete(root,key);
		if(!isEmpty()){ root.color = BLACK;}
	}

	private Node delete(Node h,Key key){
		if(key.compareTo(h.key) < 0){
			if(!isRed(h.left) && !isRed(h.left.left)){
				h = moveRedLeft(h);
			}	
			h.left = delete(h.left,key);
		}		
		else{
			if(isRed(h.left))
				h = rotateRight(h);
			if(key.compareTo(h.key) == 0 && (h.right == null))
				return null;
			if(!isRed(h.right) && !isRed(h.right.left))
				h = moveRedRight(h);
			if(key.compareTo(h.key) == 0){
				h.val = get(h.right,min(h.right).key);	
				h.key = min(h.right).key;
				h.right = deleteMin(h.right);
			}
			else h.right = delete(h.right,key);
		}
		return balance(h);
	}

}
