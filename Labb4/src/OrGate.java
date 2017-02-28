//Output is set to true aslong as one input is true
public class OrGate extends LogicGate {

    public OrGate() {
        super(2);
    }

    protected boolean compute() {
        return (getInput(0) || getInput(1));
    }
}
