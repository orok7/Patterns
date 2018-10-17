package patterns.command;

public class FileManagerDemo {

    //ToDo not finished yet
    public static void main(String[] args) {
        FileManager fm = new FileManager();
        fm.newFile("1.txt");
        fm.newDirectory("Test1");
        fm.list();
        fm.move("1.txt", "C:/Test1");
        fm.list();
        fm.changeDir("Test1");
        fm.list();
    }
}
