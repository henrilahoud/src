package exceptionHandler;

public interface OperationResult<T> {
    public T handle(T value);
}
