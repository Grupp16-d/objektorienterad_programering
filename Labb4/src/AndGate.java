//And gate take 2 in and if they are the same return 1
public class AndGate extends LogicGate {

    public AndGate() {
        super(2);
    }

    protected boolean compute() {
        return getInput(0) && getInput(1);
    }
}
