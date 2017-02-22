abstract class StatefulComponent extends CircuitComponent {
    StatefulComponent(int nin, int nout) {
        super(nin, nout);
    }

    @Override
    final protected void propagateStateChange() {
        propagateChange();
    }
}
