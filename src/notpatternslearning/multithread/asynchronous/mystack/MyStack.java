package notpatternslearning.multithread.asynchronous.mystack;

public interface MyStack<T> {

    int size();

    void push(T t);

    T pop();

}
