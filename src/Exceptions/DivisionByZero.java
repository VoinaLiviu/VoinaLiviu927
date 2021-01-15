package Exceptions;

public class DivisionByZero extends RuntimeException{
    String message;

    public DivisionByZero(String msg){
        message = msg;
    }

    public String toString(){
        return "DivisionByZeroException Occurred: " + message;
    }
}
