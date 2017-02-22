<<<<<<< HEAD
public class JKFlipFlop {
=======
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
>>>>>>> origin/master
}
