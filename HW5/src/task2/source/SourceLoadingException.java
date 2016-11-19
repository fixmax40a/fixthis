package task2.source;

public class SourceLoadingException extends Exception {

    public SourceLoadingException(String message ,Throwable throwable) {
        super(message);
        initCause(throwable);
    }
}
