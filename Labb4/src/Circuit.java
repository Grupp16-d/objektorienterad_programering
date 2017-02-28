import java.util.*;

public class Circuit {

    private Map<String, CircuitComponent> components = new HashMap<>();

    public Circuit() {
    }

    public Circuit(List<String> lines) {
        for (String line : lines) {
            String[] tempSplit = line.split("\\s+");
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
        for (String line : lines) {
            String[] tempSplit = line.split("\\s+");
            for (int i = 2; i < tempSplit.length; i = i + 3) {
                int outputIndex = Integer.parseInt(tempSplit[i]);
                CircuitComponent receiver = getComponent(tempSplit[i + 1]);
                int receiverIndex = Integer.parseInt(tempSplit[i + 2]);

                getComponent(tempSplit[0]).connect(outputIndex, receiver, receiverIndex);
            }

        }
    }


    public void addComponent(String name, CircuitComponent component) {
        if (!components.containsKey(name)) {
            components.put(name, component);
        } else {
            throw new RuntimeException("That key already exists");
        }

    }

    public CircuitComponent getComponent(String name) {
        return components.get(name);
    }

    public Set<String> componentNames() {
        return components.keySet();
    }

    public void tick() {
        components.entrySet().forEach((entry) -> entry.getValue().updateState());
        components.entrySet().forEach((entry) -> entry.getValue().propagateStateChange());
    }
}
