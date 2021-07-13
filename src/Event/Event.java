package Event;

import java.util.ArrayList;
import java.util.List;

public class Event<A> {

    private List<EventListener<A>> listeners;

    public Event() {
        this.listeners = new ArrayList<>();
    }

    public void invokeAll(A arg) {
        for (EventListener<A> eventListener : this.listeners) {
            if (eventListener != null) {
                eventListener.invoke(arg);
            }
        }
    }

    public void subscribe(EventListener<A> listener) {
        this.listeners.add(listener);
    }

    public void unsubscribe(EventListener<A> listener) {
        this.listeners.remove(listener);
    }
}