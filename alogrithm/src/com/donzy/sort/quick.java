package com.donzy.sort;
import java.util.Random;

public class quick
{
	private static boolean less(int v,int w){
		return v < w;	
	}
	
	private static void exch(int[] a,int i,int j) {
		int tmp = a[i];	
		a[i] = a[j];
		a[j] = tmp;
	}
	private static void show(int[] a) {
		for (int i=0; i<a.length; i++) {
			System.out.print(a[i] + "," );		
		}
		System.out.println();
	}
	public static boolean  isSorted(int[] a) {
		for (int i=1; i<a.length; i++){
			if(less(a[i],a[i-1])) return false;	
		}
		return true;
	}

	public static void sort(int[] a) {
		sort(a,0,a.length-1);
	}	

	public static void sort(int[] a,int lo,int hi){
		if(lo >= hi){ return ;}
		int j = partition(a,lo,hi);
		sort(a,lo,j-1);
		sort(a,j+1,hi);
	}
	
	public static int  partition(int[] a,int lo,int hi) {
		int i = lo, j = hi;	
		int v = a[lo];
		while(true){
			while(less(a[++i],v)) if(i == hi){break;}
			while(less(v,a[--j])) if(j == lo){break;}
			if(i >= j) break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
	}

	private static int[] mkList() {
		Random ran = new Random();
		int[] a = new int[50];
		for (int i  = 0 ; i < 50 ; i++) {
			int num = ran.nextInt(100)	;
			a[i] = num;	
		}
		return a;
	}

	public static void main(String[] args) {
		int[] a = mkList();
		sort(a);
		if(isSorted(a)){
			System.out.println("this array is sorted!!!");
		}else{
			System.out.println("this array is not sorted!!!");	
		}
		show(a);
	}
}


