package com.donzy.sort;
import java.util.Random;

public class insertion 
{
	private static boolean less(int v,int w){
		return v < w;	
	}
	
	private static void exch(int[] a,int i,int j) {
		int tmp = a[i];	
		a[i] = a[j];
		a[j] = tmp;
	}
	public static void show(int[] a) {
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
		int N = a.length;
		for (int i = 1; i < N; i++) {
			for (int j = i; j > 0 && less(a[j],a[j-1]); j--) {
					exch(a,j,j-1);	
			}	
		}
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


