//take 2 inputs if either one of them are active but not if both are active return true
public class XorGate extends LogicGate{

    public XorGate() {
        super(2);
    }

    protected boolean compute() {
        return getInput(0) ^ getInput(1);
    }
}
