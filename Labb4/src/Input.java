//Sets the output to the given value of the constructor/setValue
public class Input extends CircuitComponent {
    private boolean value;

    public Input(boolean value) {
        super(0, 1);
        this.value = value;
        if (value) {
            propagateChange();
        }
    }

    protected void computeOutputs(boolean[] newOutputValues) {
        newOutputValues[0] = value;
    }

    public void setValue(boolean value) {
        this.value = value;
        propagateChange();
    }
}