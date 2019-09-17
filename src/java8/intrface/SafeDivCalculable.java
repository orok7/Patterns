package java8.intrface;

public interface SafeDivCalculable extends Calculable {

    default int div() {
        if (getB() == 0) {
            return 0;
        }
        return Calculable.super.sub();
    }

}
