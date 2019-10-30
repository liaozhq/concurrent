package com.qcloud.concurrent.forkjoin;

import java.io.File;

public class FileSearch {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		search(new File("C:\\"), "FXSOCM.dll");
		System.out.println(System.currentTimeMillis()-start);
	}

	public static void search(File file, String filename) {
		if (file != null && file.listFiles() != null) {
			for (File f : file.listFiles()) {
				if (f.isDirectory()) {
					search(f, filename);
				} else {
					if (filename.equals(f.getName())) {
						System.out.println(file.getAbsolutePath()+File.separator+filename);
					}
				}
			}
		}
	}

}
