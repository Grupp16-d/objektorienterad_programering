//take 1 input and invert the input and return the opiset
public class NotGate extends LogicGate {

    public NotGate() {
        super(1);
    }

    protected boolean compute() {
        return !(getInput(0));
    }
}