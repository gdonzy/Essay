package com.donzy.sort;
import java.util.Random;

public class Merge 
{
	
	private static int[] aux;

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
		aux = new int[a.length];		
		sort(a,0,a.length-1);
	}

	public static void sort(int[] a,int lo,int hi) {
		if (hi<=lo) {
			return ;
		}
		int mid = lo + (hi-lo)/2;
		sort(a,lo,mid);
		sort(a,mid+1,hi);
		merge(a,lo,mid,hi);
	}	

	public static void merge(int[] a,int lo,int mid,int hi) {
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		for (int k = lo; k <= hi; k++) {
			if (i>mid) {
				a[k] = aux[j++];
			}
			else if (j>hi) {
				a[k] = aux[i++];	
			}
			else if (less(aux[i],aux[j])){
				a[k] = aux[i++];
			}
			else{
				a[k] = aux[j++];	
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


