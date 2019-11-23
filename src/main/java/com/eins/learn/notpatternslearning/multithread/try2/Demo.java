package com.eins.learn.notpatternslearning.multithread.try2;

import com.eins.learn.notpatternslearning.multithread.try1.MyThread;

import java.io.File;

public class Demo {

    public static void main(String[] args) {
        MyDataSource dataSource = new MyDataSourceImpl(new File("input.txt"));
        new MyFileWriter(new File("input(copy1).txt"), dataSource).start();
        new MyFileWriter(new File("input(copy2).txt"), dataSource).start();
        new MyFileWriter(new File("input(copy3).txt"), dataSource).start();
        new MyFileWriter(new File("input(copy4).txt"), dataSource).start();
        new MyFileWriter(new File("input(copy5).txt"), dataSource).start();
        new MyFileWriter(new File("input(copy6).txt"), dataSource).start();
        new MyFileWriter(new File("input(copy7).txt"), dataSource).start();
        new MyFileWriter(new File("input(copy8).txt"), dataSource).start();
        new MyFileWriter(new File("input(copy9).txt"), dataSource).start();
        MyThread.sleepAndWait(1000);
        new MyFileReader(dataSource).start();
    }

}
