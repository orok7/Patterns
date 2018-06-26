package notpatternslearning.multithread.try2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter extends Thread {

    private File fileToWrite;
    private MyDataSource dataSource;
    private Integer dataSourceKey;

    public MyFileWriter(File fileToWrite, MyDataSource dataSource) {
        this.fileToWrite = fileToWrite;
        this.dataSource = dataSource;
        this.dataSourceKey = dataSource.registerNewConsumer();
    }

    @Override
    public void run() {
        try (FileWriter out = new FileWriter(fileToWrite); BufferedWriter bufferedWriter = new BufferedWriter(out)) {
            while (true) {
                Integer nextData = dataSource.getNextData(dataSourceKey);
                if (nextData == null || nextData == -1) {
                    break;
                }
                bufferedWriter.write(nextData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
