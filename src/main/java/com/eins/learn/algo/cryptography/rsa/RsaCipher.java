package com.eins.learn.algo.cryptography.rsa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigInteger;

import static com.eins.learn.algo.cryptography.rsa.KeyStringUtils.decodeKey;
import static com.eins.learn.algo.cryptography.rsa.KeyStringUtils.decodeStr;
import static com.eins.learn.algo.cryptography.rsa.KeyStringUtils.encodeStr;
import static com.eins.learn.algo.cryptography.rsa.KeyStringUtils.isEmptyString;

public class RsaCipher {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String encode(String msg, String publicKey) {
        if (isEmptyString(msg)) {
            throw new IllegalArgumentException("Empty message.");
        }
        Key key = decodeKey(publicKey);
        BigInteger n = new BigInteger(String.valueOf(key.getFirstParam()));
        BigInteger e = new BigInteger(String.valueOf(key.getSecondParam()));
        int msgLength = msg.length();
        long[] result = new long[msgLength];
        for (int i = 0; i < msgLength; i++) {
            char c = msg.charAt(i);
            BigInteger m = new BigInteger(Integer.valueOf(c).toString());
            result[i] = m.modPow(e, n).longValue();
        }
        try {
            return encodeStr(mapper.writeValueAsString(result));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decode(String msg, String privateKey) {
        if (isEmptyString(msg)) {
            throw new IllegalArgumentException("Empty message.");
        }
        Key key = decodeKey(privateKey);
        BigInteger n = new BigInteger(String.valueOf(key.getFirstParam()));
        BigInteger d = new BigInteger(String.valueOf(key.getSecondParam()));
        long[] encoded;
        try {
            encoded = mapper.readValue(decodeStr(msg), long[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (long l : encoded) {
            BigInteger c = new BigInteger(Long.valueOf(l).toString());
            result.append((char) c.modPow(d, n).intValue());
        }
        return result.toString();
    }

}
