package com.donzy.graph;

import java.util.LinkedList;
import java.util.Queue;

public class KruskalMST {
	private Queue<Edge> mst;
	
	public KruskalMST(EdgeWeightedGraph G){
		mst = new LinkedList<Edge>();	
	}
}
