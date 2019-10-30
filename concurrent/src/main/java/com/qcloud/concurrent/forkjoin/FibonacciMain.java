package com.qcloud.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		
		Fibonacci f = new Fibonacci(11);
		System.out.println(pool.submit(f).get());
		pool.shutdown();
	}
	
	public static class Fibonacci extends RecursiveTask<Integer> {
	
		int n;
	    
		public Fibonacci(int n) {
	    	this.n = n;
	    }	

		@Override
		protected Integer compute() {
			if(n < 2) {
				return n;
			}else {
//				System.out.println(n-1);
				System.out.println("����"+String.valueOf(n-1));
			Fibonacci f1 = new Fibonacci(n - 1);
			f1.fork();
//			
			return n+f1.join();
			}
		}
	}

}
