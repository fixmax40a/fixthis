package task2;

public class TranslateException extends Exception {

    public TranslateException(Throwable e) {
        initCause(e);
    }

    public TranslateException(String message ,Throwable e) {
        super(message);
        initCause(e);
    }
}