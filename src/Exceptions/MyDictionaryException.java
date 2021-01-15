package Exceptions;

public class MyDictionaryException extends RuntimeException{
    String message;

    public MyDictionaryException(String msg){
        message = msg;
    }

    public String toString(){
        return "MyDictionaryException Occurred: " + message;
    }
}
