class Constant extends CircuitComponent {
    private final boolean value;

    public Constant(boolean value) {
        super(0, 1);
        this.value = value;
        if (value = true) {
            propagateChange();
        }
    }

    protected void computeOutputs(boolean[] newOutputValues) {
        newOutputValues[0] = value;
    }
}
