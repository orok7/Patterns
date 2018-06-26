package notpatternslearning.multithread.try2;

import java.io.FileReader;
import java.io.IOException;

public class MyFileReader extends Thread {

    private MyDataSource dataSource;

    public MyFileReader(MyDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        try (FileReader in = new FileReader(dataSource.file)) {
            int ch;
            while ( (ch = in.read()) != -1) {
                dataSource.setNextData(ch);
            }
            dataSource.setNextData(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printIt(char[] data) {
        if (data != null) {
            for (char datum : data) {
                System.out.print(datum);
            }
        }
    }
}
