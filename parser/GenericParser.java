package parser;

public interface GenericParser<T,S> {
    
    T parse(S input) throws Exception;
}
