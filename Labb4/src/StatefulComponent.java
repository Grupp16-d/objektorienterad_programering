abstract class StatefulComponent extends CircuitComponent {
    StatefulComponent(int nin, int nout) {
        super(nin, nout);
    }
    final protected void propagateStateChange() {
        propagateChange();
    }
}