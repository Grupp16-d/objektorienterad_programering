public abstract class CircuitComponent {

    protected final int input;
    protected final int output;
    private boolean[] inputValue;
    private boolean[] outputValue;
    private boolean[] inputConnected;
    private Wire[] connections;

    public CircuitComponent(int input, int output) {
        this.input = input;
        this.output = output;
        this.inputValue = new boolean[input];
        this.outputValue = new boolean[output];
        this.inputConnected = new boolean[input];
        this.connections = new Wire[output];
    }

    public CircuitComponent() {
        this.input = 0;
        this.output = 0;
    }

    public void connect(int outputIndex, CircuitComponent receiver, int receiverInputIndex) {
        if (this.connections[outputIndex] == null && !receiver.inputConnected[receiverInputIndex]) {
            this.connections[outputIndex] = new Wire(receiver, receiverInputIndex);
            receiver.inputConnected[receiverInputIndex] = true;
            receiver.inputValue[receiverInputIndex] = outputValue[outputIndex];
            receiver.propagateChange();
        } else {
            throw new RuntimeException("Already connected");
        }
    }

    public void disconnect(int outputIndex) {
        if (this.connections[outputIndex] != null) {
            int receiverInputIndex = this.connections[outputIndex].receiverInputIndex;
            this.connections[outputIndex].receiver.inputConnected[receiverInputIndex] = false;
            this.connections[outputIndex].receiver.inputValue[receiverInputIndex] = false;
            this.connections[outputIndex] = null;
        } else {
            throw new RuntimeException("Not connected");
        }
    }

    protected void propagateChange() {
        boolean tempArray[] = new boolean[output];
        computeOutputs(tempArray);
        for (int i = 0; i < output; i++) {
            if (outputValue[i] != tempArray[i]) {
                outputValue[i] = !outputValue[i];
                if (connections[i] != null) {
                    int receiverInputIndex = this.connections[i].receiverInputIndex;
                    this.connections[i].receiver.inputValue[receiverInputIndex] = outputValue[i];
                    this.connections[i].receiver.propagateChange();
                }
            }
        }
    }

    protected abstract void computeOutputs(boolean[] newOutputValues);

    protected void updateState() {
    }

    protected void propagateStateChange() {
    }

    protected boolean getInput(int inputIndex) {
        return this.inputValue[inputIndex];
    }


    private static class Wire {
        CircuitComponent receiver;
        int receiverInputIndex;

        Wire(CircuitComponent receiver, int receiverInputIndex) {
            this.receiver = receiver;
            this.receiverInputIndex = receiverInputIndex;
        }
    }
}

