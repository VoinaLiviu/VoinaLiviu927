package View;

import Controller.Controller;

public class RunExample extends Command{
    private Controller controller;
    public RunExample(String k, String desc, Controller ctrl) {
        super(k, desc);
        controller = ctrl;
    }

    @Override
    public void execute() {
        controller.completeExecution();
    }
}
