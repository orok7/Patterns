package patterns.composite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DemoComposite {
    public static void main(String[] args) {
        ToDo one = new ToDo("One");
        ToDo two = new ToDo("Two");
        ToDo three = new ToDo("Three");
        ToDo four = new ToDo("Four");
        ToDo five = new ToDo("Five");
        ToDo six = new ToDo("Six");
        ToDo seven = new ToDo("Seven");
        ToDo eight = new ToDo("Eight");

        Project main = new Project("Main");
        Project sub1 = new Project("Sub1");
        Project sub2 = new Project("Sub2");

        sub1.add(four);
        sub1.add(five);
        sub1.add(six);

        sub2.add(seven);
        sub2.add(eight);

        main.add(one);
        main.add(sub1);
        main.add(two);
        main.add(sub2);
        main.add(three);

        saveToFile(main, "tstMain.html");
        saveToFile(sub1, "tstSub1.html");
        saveToFile(sub2, "tstSub2.html");
    }

    private static void saveToFile(Project main, String fileName) {
        StringBuilder htmlFile = new StringBuilder("<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n" + "    <title>Title</title>\n" + "</head>\n" + "<body>");
        htmlFile.append(main.getHtml()).append("</body>\n" + "</html>");
        try {
            Files.write(Paths.get(fileName), htmlFile.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
