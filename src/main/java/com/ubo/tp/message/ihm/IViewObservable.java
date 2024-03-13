package main.java.com.ubo.tp.message.ihm;

public interface IViewObservable<T> {
    void addObserver(T observer);
    void deleteObserver(T observer);
}
