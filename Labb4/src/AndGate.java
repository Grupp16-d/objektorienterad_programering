//Output is set to true if both inputs are true
public class AndGate extends LogicGate {

    public AndGate() {
        super(2);
    }

    protected boolean compute() {
        return getInput(0) && getInput(1);
    }
}
