package Event;

public abstract class EventListener<A> {

    public abstract void invoke(A arg);
}
