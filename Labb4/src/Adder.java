//Java class that take 3 imputs and 2 outputs that ads 3 number and output a 2 bit number
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
