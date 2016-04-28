package com.donzy.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	public BreadthFirstPaths(Graph G,int s){
		marked = new boolean[G.V()];	
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G,s);
	}
	
	private void bfs(Graph G,int s){
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		marked[s] = true;
		queue.add(s);
		while(!queue.isEmpty()){
			int v = queue.poll();	
			for(int w : G.adj(v)){
				if(!marked[w]){
					edgeTo[w] = v;	
					marked[w] = true;
					queue.add(w);
				}
			}
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];	
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;	
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != s; x=edgeTo[x]){
			path.push(x);	
		}
		path.push(s);
		return path;			
	}
	
	
	
	
}
