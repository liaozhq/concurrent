package com.qcloud.concurrent.forkjoin;

public class Sum {
	
	public static void main(String[] args) {
		long result = 0;
		
		for(long i = 1 ; i <= 100000000; i++) {
			result += i;
		}
		System.out.println(result);
	}

}
