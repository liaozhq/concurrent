
package com.qcloud.concurrent.forkjoin;

import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FileSearchCurrent {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool();
		SearchTask task = new SearchTask(new File("C:\\"), "FXSOCM.dll");
		pool.execute(task);
		task.join();
		System.out.println(System.currentTimeMillis()-start);
	}

	public static class SearchTask extends RecursiveAction {

		private File f;

		private String name;

		public SearchTask(File f, String name) {
			this.f = f;
			this.name = name;
		}

		@Override
		protected void compute() {
			if (this.f != null && this.f.listFiles() != null) {
				for (File file : this.f.listFiles()) {
					if (file.isDirectory()) {
//						System.out.println("dictionary==="+file.getName());
						invokeAll(new SearchTask(file, this.name));

					} else {
						if (file != null) {
							if (name.equals(file.getName())) {
								System.out.println(file.getPath());
							}
						}
					}
				}
			}
		}
	}
}