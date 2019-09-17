package security;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MDExample {
    private final String[] ALGO = { "MD5", "SHA-1", "SHA-256" };
    private final MessageDigest[] MD = { null, null, null };

    public static void main(String[] args) {
        MDExample app = new MDExample();

        byte[] mdbytes = app.getMessageDigest("SHA-256", "length");
        StringBuilder sb = new StringBuilder();
        // convert the byte to hex format
        for (byte mdbyte : mdbytes) {
            String s = Integer.toHexString(0xff & mdbyte);
            s = (s.length() == 1) ? "0" + s : s;
            sb.append(s);
        }
        System.out.println(String.format("%s", sb.toString()));
        System.out.println("\n");

        app.MessageDidgestTest();

        System.exit(0);
    }

    private void MessageDidgestTest() {
        try {
            // Инициализация объектов MessageDigest
            for (int i = 0; i < ALGO.length; i++)
                MD[i] = MessageDigest.getInstance(ALGO[i]);
            // Чтение файла
            FileInputStream fis = new FileInputStream("report.txt");
            // Массив данных
            byte[] dataBytes = new byte[fis.available()];

            int nread = 0;
            while ((nread = fis.read(dataBytes)) != -1) {
                MD[0].update(dataBytes, 0, nread);
                MD[1].update(dataBytes, 0, nread);
                MD[2].update(dataBytes, 0, nread);
            }
            fis.close();

            for (MessageDigest messageDigest : MD) {
                byte[] mdbytes = messageDigest.digest();
                StringBuffer sb = new StringBuffer();
                // convert the byte to hex format
                for (byte mdbyte : mdbytes) {
                    String s = Integer.toHexString(0xff & mdbyte);
                    s = (s.length() == 1) ? "0" + s : s;
                    sb.append(s);
                }
                System.out.println(String.format("  %s", sb.toString()));
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private byte[] getMessageDigest (final String algo, final String text)
    {
        if ((algo == null) || (algo.trim().length() == 0) || (text == null))
            return null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algo);
            md.update(text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (md != null)
            return md.digest();
        else
            return null;
    }
}
