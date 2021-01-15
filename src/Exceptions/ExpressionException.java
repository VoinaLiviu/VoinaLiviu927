package Exceptions;

public class ExpressionException extends RuntimeException{
    String message;

    public ExpressionException(String msg){
        message = msg;
    }

    public String toString(){
        return "ExpressionException Occurred: " + message;
    }
}
