import java.util.*;

//Builds a circuit out of CircuitComponents and their connections
public class Circuit {

    private Map<String, CircuitComponent> components = new HashMap<>();

    public Circuit() {
    }

    public Circuit(List<String> lines) {
        //Loops through each line of the given list
        for (String line : lines) {
            //Splits the current line of the list, which gives each "argument" of the line as a separate index.
            //For example, index 0 is the name, index 1 the types and so on.
            String[] tempSplit = line.split("\\s+");
            //Creates a CircuitComponent depending on the type given in the current line.
            //Adds this component to the Map that contains the Circuit
            switch (tempSplit[1]) {
                case "INPUT": {
                    CircuitComponent comp = new Input(false);
                    addComponent(tempSplit[0], comp);
                    break;
                }
                case "ZERO": {
                    CircuitComponent comp = new Constant(false);
                    addComponent(tempSplit[0], comp);
                    break;
                }
                case "ONE": {
                    CircuitComponent comp = new Constant(true);
                    addComponent(tempSplit[0], comp);
                    break;
                }
                case "FORK": {
                    CircuitComponent comp = new Fork(2);
                    addComponent(tempSplit[0], comp);
                    break;
                }
                case "AND": {
                    CircuitComponent comp = new AndGate();
                    addComponent(tempSplit[0], comp);
                    break;
                }
                case "OR": {
                    CircuitComponent comp = new OrGate();
                    addComponent(tempSplit[0], comp);
                    break;
                }
                case "XOR": {
                    CircuitComponent comp = new XorGate();
                    addComponent(tempSplit[0], comp);
                    break;
                }
                case "NOT": {
                    CircuitComponent comp = new NotGate();
                    addComponent(tempSplit[0], comp);
                    break;
                }
                case "ADDER": {
                    CircuitComponent comp = new Adder();
                    addComponent(tempSplit[0], comp);
                    break;
                }
                case "OUTPUT": {
                    CircuitComponent comp = new Output();
                    addComponent(tempSplit[0], comp);
                    break;
                }
                case "DFLIPFLOP": {
                    CircuitComponent comp = new DFlipFlop();
                    addComponent(tempSplit[0], comp);
                    break;
                }
                case "JKFLIPFLOP": {
                    CircuitComponent comp = new JKFlipFlop();
                    addComponent(tempSplit[0], comp);
                    break;
                }
            }
        }

        //Loops through each line again
        for (String line : lines) {
            //Splits again
            String[] tempSplit = line.split("\\s+");
            //Checks the connections that come in groups of 3 starting at index 2. Creates a connection with the
            //given parameters for the current component.
            for (int i = 2; i < tempSplit.length; i = i + 3) {
                int outputIndex = Integer.parseInt(tempSplit[i]);
                CircuitComponent receiver = getComponent(tempSplit[i + 1]);
                int receiverIndex = Integer.parseInt(tempSplit[i + 2]);

                getComponent(tempSplit[0]).connect(outputIndex, receiver, receiverIndex);
            }

        }
    }


    //Adds the given component with the given name to the circuit if the name does not already exist
    public void addComponent(String name, CircuitComponent component) {
        if (!components.containsKey(name)) {
            components.put(name, component);
        } else {
            throw new RuntimeException("That key already exists");
        }

    }

    //Returns the component with the given name, returns null otherwise.
    public CircuitComponent getComponent(String name) {
        return components.get(name);
    }

    public Set<String> componentNames() {
        return components.keySet();
    }

    //Runs updateState on all components with an internal values, then runs propagateChange in the same components
    public void tick() {
        components.entrySet().forEach((entry) -> entry.getValue().updateState());
        components.entrySet().forEach((entry) -> entry.getValue().propagateStateChange());
    }
}
