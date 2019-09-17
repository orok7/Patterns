package solid.interfacesegregation;

import java.util.List;

public class WrongExample {
    public static void main(String[] args) {

    }

    interface Product {
        String getName();

        String getDescription();

        String getImageUrl();

        List<String> getColors();

        float getPrice();
    }
}
