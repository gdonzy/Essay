package com.donzy.graph;

import java.util.ArrayList;

public class Graph {
	private int V;  //顶点的数目
	private int E;  //边的数目
	private ArrayList<Integer>[] adj; //邻接表
	
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (ArrayList<Integer>[]) new ArrayList[V];
		for(int i=0;i<V;i++){
			adj[i] = new ArrayList<Integer>();	
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(int v,int w){
		adj[v].add(w);	
		adj[w].add(v);
		E++;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];	
	}
	
}
