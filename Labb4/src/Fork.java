class Fork extends CircuitComponent {
    public Fork(int nOutput) {
        super(1, nOutput);
    }

    protected void computeOutputs(boolean[] newOutputValues) {
        boolean val = getInput(0);
<<<<<<< HEAD
        for (int i = 0; i < nOutput; i++) {
=======
        for (int i = 0; i < output; i++) {
>>>>>>> origin/master
            newOutputValues[i] = val;
        }
    }
}