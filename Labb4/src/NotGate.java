//Output is set to the inverse of the input
public class NotGate extends LogicGate {

    public NotGate() {
        super(1);
    }

    protected boolean compute() {
        return !getInput(0);
    }
}