//The output is set depending on the combination of the 2 inputs once tick is run
public class JKFlipFlop extends StatefulComponent {

    private boolean state = false;

    public JKFlipFlop() {
        super(2, 2);
    }

    @Override
    protected void updateState() {
        if(getInput(0) && !getInput(1)){
            state = true;
        }else if(!getInput(0) && getInput(1)){
            state = false;
        }else if(getInput(0) && getInput(1)){
            state = !state;
        }
    }

    protected void computeOutputs(boolean[] newOutputValues) {
        newOutputValues[0] = state;
        newOutputValues[1] = !state;
    }
}
