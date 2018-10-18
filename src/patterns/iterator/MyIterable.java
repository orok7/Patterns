package patterns.iterator;

public interface MyIterable<T> {
    MyIterator<T> getIterator();
}
