package com.donzy.sort;

import java.util.NoSuchElementException;

public class MinPQ<Key extends Comparable<Key>>{
	private Key[] pq;
	private int N;
	
	public MinPQ(int initCapacity){
		pq = (Key [])new Object[initCapacity];
		N = 0;
	}
	
	public MinPQ(){
		this(1);	
	}
	
	public boolean isEmpty(){
		return N == 0;	
	}
	
	public int size(){
		return N;	
	}
	
	public Key min(){
		if( isEmpty()) throw new  NoSuchElementException("queue ie empty");
		return pq[1];
	}
	
	private void resize(int capacity){
		assert capacity > N;	
		Key[] temp = (Key []) new Object[capacity];
		for(int i = 1; i <= N; i++){
			temp[i] = pq[i];	
		}
		pq = temp;
	}
	
	public void insert(Key k){
		if(N == pq.length -1) resize(2*pq.length);	
		pq[++N] = k;
		swim(N);
	}
	
	public Key delMin(){
		if(isEmpty()) throw new NoSuchElementException("queue is empty");
		exch(1,N);
		Key min = pq[N--];
		sink(1);
		pq[N+1] = null;
		if(N >0 && (N == (pq.length -1)/4)) resize(pq.length/2);
		return min;
	}
	
	private void swim(int k){
		while(k > 1 && greater(k/2,k)){
			exch(k,k/2);
			k = k/2;
		}
	}
	
	private void sink(int k){
		while(2*k <= N){
			int j = 2*k;	
			if (j < N && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
		}
	}
	private boolean greater(int i,int j){
		return pq[i].compareTo(pq[j]) > 0;	
	}
	
	private void exch(int i,int j){
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
}