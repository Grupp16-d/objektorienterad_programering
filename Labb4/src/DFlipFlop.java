//Output is set to the input when the tick method is run.
public class DFlipFlop extends StatefulComponent {

    private boolean state = false;

    public DFlipFlop() {
        super(1, 1);
    }

    @Override
    protected void updateState() {
        state = getInput(0);
    }

    protected void computeOutputs(boolean[] newOutputValues) {
        newOutputValues[0] = state;
    }
}
