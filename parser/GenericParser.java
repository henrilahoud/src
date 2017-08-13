package parser;

import exceptionHandler.NullValueRunTimeException;

public interface GenericParser<T,S> {

    //boolean supports(String[] row);
    T parse(S input) throws NullValueRunTimeException;
}
