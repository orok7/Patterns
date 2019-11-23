package com.eins.learn.notpatternslearning.multithread.try2;

import java.io.File;

public abstract class MyDataSource {

    protected final File file;

    public MyDataSource(File file) {
        this.file = file;
    }

    abstract public Integer getNextData(Integer consumerKey);

    abstract public void setNextData(Integer data);

    abstract public Integer registerNewConsumer();

}