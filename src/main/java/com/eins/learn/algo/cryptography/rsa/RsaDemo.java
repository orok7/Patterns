package com.eins.learn.algo.cryptography.rsa;

public class RsaDemo {

    public static void main(String[] args) {
        String msg = "Hello, Alex. I'm good. Many programming contest problems are somehow related Prime Numbers. "
                + "Either we are required to check Prime Numbers, or we are asked to perform certain functions "
                + "for all prime number between 1 to N. "
                + "Example: Calculate the sum of all prime numbers between 1 and 1000000.";

        KeyGenerator keyGenerator = new KeyGenerator(8);
        String msgEncoded = RsaCipher.encode(msg, keyGenerator.getPublicEncodedKey());
        String msgDecoded = RsaCipher.decode(msgEncoded, keyGenerator.getPrivateEncodedKey());

        System.out.println("Original msg:");
        System.out.println(msg);
        System.out.println();

        System.out.println("Encoded msg:");
        System.out.println(msgEncoded);
        System.out.println();

        System.out.println("Decoded msg:");
        System.out.println(msgDecoded);
    }

}
