//Output is set to true if only 1 input is true
public class XorGate extends LogicGate{

    public XorGate() {
        super(2);
    }

    protected boolean compute() {
        return getInput(0) ^ getInput(1);
    }
}
