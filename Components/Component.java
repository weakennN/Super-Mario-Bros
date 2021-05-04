package Components;

public abstract class Component {

    private String tag;

    protected Component(String tag) {

        this.tag = tag;
    }

    public abstract void update();

    public String getTag() {

        return this.tag;
    }
}
