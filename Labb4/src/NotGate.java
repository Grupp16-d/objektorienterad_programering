public class NotGate extends LogicGate {

    public NotGate() {
        super(2);
    }

    protected boolean compute() {
        return !(getInput(0) && getInput(1));
    }
}
