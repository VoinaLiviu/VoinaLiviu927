package Exceptions;

public class MyListException extends RuntimeException{
    String message;

    public MyListException(String msg){
        message = msg;
    }

    public String toString(){
        return "MyListException Occured: " + message;
    }
}
