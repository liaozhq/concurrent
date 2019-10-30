package com.qcloud.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class SumSplit {
	
	private static final long length = 100000000;
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		long[] lists = new long[Integer.parseInt(String.valueOf(length))];
		
		ForkJoinPool pool = new ForkJoinPool();
		
		for(long i = 1; i <= length ; i++) {
			lists[Integer.parseInt(String.valueOf(i))-1] = i; 
		}
		
		ForkJoinTask<Long> f = pool.submit(new SqlitTask(lists, 0, length-1));
		
		System.out.println(f.get());
	
	}
	
	public static class SqlitTask extends RecursiveTask<Long>{
		
		long[] lists;
		
		long start;
		
		long end;
		
		public SqlitTask (long[] lists, long start, long end){
			
			this.lists = lists;
			
			this.start = start;
			
			this.end = end;
		}
		
		@Override
		protected Long compute() {
			
			if(this.end - this.start <= 10) {
				long result = 0;
				for(long i = this.start; i <= this.end ; i++) {
					result += this.lists[Integer.parseInt(String.valueOf(i))];
				}
				return result;
			}else {
				long middle = (this.start+this.end)/2;
				SqlitTask leftTask = new SqlitTask(this.lists, this.start, middle);
				SqlitTask rightTask = new SqlitTask(this.lists, middle+1, this.end);
				leftTask.fork(); //开启一个线程
			    rightTask.fork(); //开启一个线程
			    return leftTask.join() + rightTask.join(); //等待两线程执行完成返回结果相加
			}
		}
	}
}
