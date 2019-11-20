package algo.cryptography.rsa;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyStringUtils {

    private static final Pattern KEY_PATTERN = Pattern.compile("\\{(\\d+);(\\d+)}");

    public static String encodeKey(Key key) {
        String keyStr = String.format("{%d;%d}", key.getFirstParam(), key.getSecondParam());
        return encodeStr(keyStr);
    }

    public static Key decodeKey(String key) {
        if (isEmptyString(key)) {
            throw new IllegalArgumentException("Incorrect key.");
        }
        Matcher matcher = KEY_PATTERN.matcher(decodeStr(key));
        if (matcher.find()) {
            long firstParam = Long.parseLong(matcher.group(1));
            long secondParam = Long.parseLong(matcher.group(2));
            return new Key(firstParam, secondParam);
        }
        throw new IllegalArgumentException("Incorrect key.");
    }

    public static String encodeStr(String keyStr) {
        return new String(Base64.getEncoder().encode(keyStr.getBytes()));
    }

    public static String decodeStr(String key) {
        return new String(Base64.getDecoder().decode(key.getBytes()));
    }

    public static boolean isEmptyString(String msg) {
        return msg == null || msg.isEmpty();
    }

}
