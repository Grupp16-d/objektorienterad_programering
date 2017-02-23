//Take 2 inputs return true when either or both inputs are active
public class OrGate extends LogicGate {

    public OrGate() {
        super(2);
    }

    protected boolean compute() {
        return (getInput(0) || getInput(1));
    }
}
