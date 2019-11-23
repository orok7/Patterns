package com.eins.learn.algo.cryptography.rsa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Key {
    private long firstParam;
    private long secondParam;

    @Override
    public String toString() {
        return "Key{" + "firstParam=" + firstParam + ", secondParam=" + secondParam + '}';
    }
}
