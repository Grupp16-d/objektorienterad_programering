//Adds all the ones from the inputs and gives the sum as binary as outputs
public class Adder extends CircuitComponent {

    public Adder() {
        super(3, 2);
    }

    protected void computeOutputs(boolean[] newOutputValues) {
        int sum = 0;
        for (int i = 0; i < 3; i++){
            if(getInput(i)){
                sum++;
            }
        }
        if(sum > 1){
            newOutputValues[1] = true;
        }
        if(sum == 1 || sum == 3){
            newOutputValues[0] = true;
        }
    }
}
