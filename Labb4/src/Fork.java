class Fork extends CircuitComponent {
    public Fork(int nOutput) {
        super(1, nOutput);
    }

    protected void computeOutputs(boolean[] newOutputValues) {
        boolean val = getInput(0);

        for (int i = 0; i < output; i++) {
            newOutputValues[i] = val;
        }
    }
}