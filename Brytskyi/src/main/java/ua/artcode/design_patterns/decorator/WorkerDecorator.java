package ua.artcode.design_patterns.decorator;

/**
 * Created by serhii on 10/2/15.
 */
public class WorkerDecorator implements Decorator {

    private String position;
    private Decorator next;

    public WorkerDecorator(String position, WorkerDecorator next) {
        this.position = position;
        this.next = next;
    }

    public String workerDecAction(){
        return position + " report";
    }

    public String action(){
        return  workerDecAction() + "," + safeAction();
    }

    public String safeAction(){
        return next != null ? next.action() : "";
    }


}
