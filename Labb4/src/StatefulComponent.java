//Abstract class for the FlipFlop components
public abstract class StatefulComponent extends CircuitComponent {
    StatefulComponent(int nin, int nout) {
        super(nin, nout);
    }

    @Override
    final protected void propagateStateChange() {
        propagateChange();
    }
}