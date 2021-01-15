package Exceptions;

public class ExecutionStackException extends RuntimeException{
    String message;

    public ExecutionStackException(String msg){
        message = msg;
    }

    public String toString(){
        return "ExecutionStackException Occurred: " + message;
    }
}
