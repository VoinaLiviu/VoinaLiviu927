package Exceptions;

public class MyStackException extends RuntimeException{
    String message;

    public MyStackException(String msg){
        message = msg;
    }

    public String toString(){
        return "MyStackException Occurred: " + message;
    }
}
