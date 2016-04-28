package com.donzy.graph;
import java.util.Stack;

import com.donzy.graph.Graph;

public class DepthFirstPaths {
	private boolean[] marked; //顶点是否调用过dfs()
	private int[] edgeTo;  //到达本节点的路径的最后一个节点
	private int s;  //起点
	
	public DepthFirstPaths(Graph G,int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G,s);
	}
	
	private void dfs(Graph G,int v){
		marked[v] = true;
		for(int w:G.adj(v)){
			edgeTo[w] = v;
			dfs(G,w);
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
