//An abstract class for all the components
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

    //Connects the current component from one output to an input on the given component and sets all the correct
    //values in the arrays. Also runs propagateChange to make sure the connected component gets the outputs.
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

    //Disconnects the given output on the current component and sets all the correct values in the arrays of the current
    //and connected component and runs propagateChange to update the previously connected component
    public void disconnect(int outputIndex) {
        if (this.connections[outputIndex] != null) {
            int receiverInputIndex = this.connections[outputIndex].receiverInputIndex;
            CircuitComponent receiver = this.connections[outputIndex].receiver;
            this.connections[outputIndex].receiver.inputConnected[receiverInputIndex] = false;
            this.connections[outputIndex].receiver.inputValue[receiverInputIndex] = false;
            this.connections[outputIndex] = null;
            receiver.propagateChange();
        } else {
            throw new RuntimeException("Not connected");
        }
    }

    //Changes outputs on the current component according the its inputs and its specific computeOutputs method.
    //If the outputs change the method runs again on the connected components.
    protected void propagateChange() {
        boolean tempArray[] = new boolean[output];
        computeOutputs(tempArray);
        for (int i = 0; i < this.output; i++) {
            if (this.outputValue[i] != tempArray[i]) {
                this.outputValue[i] = !this.outputValue[i];
                if (this.connections[i] != null) {
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

