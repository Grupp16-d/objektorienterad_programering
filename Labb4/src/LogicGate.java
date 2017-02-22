public abstract class LogicGate extends CircuitComponent {

    public LogicGate(int inputs) {
        super(inputs, 1);
    }

    protected void computeOutputs(boolean[] newOutputValues) {
        for (int i = 0; i < newOutputValues.length; i++) {
            newOutputValues[i] = compute();
        }
    }

    protected abstract boolean compute();
}
