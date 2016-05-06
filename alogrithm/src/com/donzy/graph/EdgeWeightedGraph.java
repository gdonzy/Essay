package com.donzy.graph;

import java.util.ArrayList;

//加权无向图
public class EdgeWeightedGraph {
	private int V;  //顶点的数目
	private int E;  //边的数目
	private ArrayList<Edge>[] adj;
	
	public EdgeWeightedGraph(int V){
		this.V = V;
		this.E = 0;
		adj = (ArrayList<Edge>[]) new ArrayList[V];
	}
	
	public int V(){
		return V;	
	}
	public int E(){
		return E;	
	}
	
	public ArrayList<Edge> adj(int v){
		return adj[v];	
	}
	
	public void addEdge(Edge e){
		int v = e.either();	
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	public Iterable<Edge> edges(int v){
		return adj[v];	
	}
}
