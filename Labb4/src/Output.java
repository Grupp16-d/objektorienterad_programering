<<<<<<< HEAD
public class Output {
    public boolean getValue(){
        
=======
public class Output extends CircuitComponent{

    public Output(){
        super(1,0);
    }

    public boolean getValue(){
        return getInput(0);
    }

    protected void computeOutputs(boolean[] newOutputValues) {
>>>>>>> origin/master
    }
}
