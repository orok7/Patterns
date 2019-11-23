package com.eins.learn.algo.cryptography.rsa;

import lombok.Getter;

import java.math.BigInteger;
import java.util.Random;

import static com.eins.learn.algo.cryptography.rsa.KeyStringUtils.encodeKey;

@Getter
public class KeyGenerator {

    private final String publicEncodedKey;
    private final String privateEncodedKey;

    public KeyGenerator(int bitLength) {
        int p = getPrime(bitLength, 0);
        int q = getPrime(bitLength, p);

        long n = p * q;
        long fi = (p - 1) * (q - 1);

        int e = calculateE(fi, 3);
        long d = calculateD(fi, e);
        while (d <= 0) {
            e = calculateE(fi, e + 1);
            d = calculateD(fi, e);
            if (e > 100) {
                p = getPrime(bitLength, 0);
                q = getPrime(bitLength, p);
                n = p * q;
                fi = (p - 1) * (q - 1);
            }
        }
        Key publicKey = new Key(n, e);
        Key privateKey = new Key(n, d);
        publicEncodedKey = encodeKey(publicKey);
        privateEncodedKey = encodeKey(privateKey);
    }

    private static int getPrime(int bitLength, int p) {
        int res = p;
        while (res == p) {
            res = new BigInteger(bitLength, 1, new Random()).intValue();
        }
        return res;
    }

    private static int calculateE(long fi, int start) {
        for (int i = start; i < fi; i++) {
            if (i % 2 == 0) {
                continue;
            }
            if ((fi % i != 0) && new BigInteger("" + i).isProbablePrime(1)) {
                return i;
            }
        }
        return -1;
    }

    private static long calculateD(long fi, int e) {
        double d;
        for (int k = 2; k < 100; k++) {
            d = (k * fi + 1) / (double) e;
            if ((d * 10 % 10) == 0) {
                return (long) d;
            }
        }
        return -1;
    }

}
