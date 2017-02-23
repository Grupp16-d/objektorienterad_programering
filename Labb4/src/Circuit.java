import java.util.*;

public class Circuit {

    private Map<String, CircuitComponent> components = new HashMap<>();

    public Circuit() {
    }

    public Circuit(List<String> lines) {
        List<CircuitComponent> inputs = new ArrayList<CircuitComponent>();
        for (String line : lines) {
            String[] tempSplit = line.split("\\s+");
            if (tempSplit[1].equals("INPUT")) {
                CircuitComponent comp = new Input(false);
                addComponent(tempSplit[0], comp);
                inputs.add(comp);
            } else if (tempSplit[1].equals("ZERO")) {
                CircuitComponent comp = new Constant(false);
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1].equals("ONE")) {
                CircuitComponent comp = new Constant(true);
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1].equals("FORK")) {
                CircuitComponent comp = new Fork(2);
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1].equals("AND")) {
                CircuitComponent comp = new AndGate();
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1].equals("OR")) {
                CircuitComponent comp = new OrGate();
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1].equals("XOR")) {
                CircuitComponent comp = new XorGate();
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1].equals("NOT")) {
                CircuitComponent comp = new NotGate();
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1].equals("ADDER")) {
                CircuitComponent comp = new Adder();
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1].equals("OUTPUT")) {
                CircuitComponent comp = new Output();
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1].equals("DFLIPFLOP")) {
                CircuitComponent comp = new DFlipFlop();
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1].equals("JKFLIPFLOP")) {
                CircuitComponent comp = new JKFlipFlop();
                addComponent(tempSplit[0], comp);
            }
        }
        for (String line : lines) {
            String[] tempSplit = line.split("\\s+");
            if (tempSplit.length > 2) {
                for (int i = 2; i < tempSplit.length; i = i + 3) {
                    int outputIndex = Integer.parseInt(tempSplit[i]);
                    CircuitComponent receiver = getComponent(tempSplit[i + 1]);
                    int receiverIndex = Integer.parseInt(tempSplit[i + 2]);

                    getComponent(tempSplit[0]).connect(outputIndex, receiver, receiverIndex);
                }
            }
        }
        tick();
    }


    public void addComponent(String name, CircuitComponent component) {
        if (!components.containsKey(name)) {
            components.put(name, component);
        } else {
            throw new RuntimeException("That key already exists");
        }

    }

    public CircuitComponent getComponent(String name) {
        if (components.get(name) != null) {
            return components.get(name);
        } else {
            return null;
        }
    }

    public Set<String> componentNames() {
        return components.keySet();
    }

    public void tick() {
        components.entrySet().forEach((entry) -> entry.getValue().updateState());
        components.entrySet().forEach((entry) -> entry.getValue().propagateStateChange());
    }
}
