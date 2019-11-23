package com.eins.learn.solid.interfacesegregation;

import java.util.List;

public class FixedExample {
    public static void main(String[] args) {

    }

    interface Product {
        String getName();

        String getDescription();
    }

    interface HasImage {
        String getImageUrl();
    }

    interface ColorVaried {
        List<String> getColors();
    }

    interface HasPrice {
        float getPrice();
    }
}
