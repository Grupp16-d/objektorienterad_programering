import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Circuit {

    private Map<String, CircuitComponent> components;

    public Circuit() {
    }

    public Circuit(List<String> lines) {
        for (String line : lines) {
            String[] tempSplit = line.split("\\s+");
            if (tempSplit[1] == "INPUT") {
                CircuitComponent comp = new Input(false);
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1] == "ZERO") {
                CircuitComponent comp = new Constant(false);
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1] == "ONE") {
                CircuitComponent comp = new Constant(true);
                addComponent(tempSplit[0], comp);
            } else if (tempSplit[1] == "FORK") {
                CircuitComponent comp = new Fork(2);
                addComponent(tempSplit[0], comp);
            } else {
                try {
                    CircuitComponent comp = (CircuitComponent) Class.forName(tempSplit[1]).newInstance();
                } catch (Exception e) {
                    System.out.println(e);
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
        if (components.containsKey(name)) {
            return components.get(name);
        } else return null;
    }

    public Set<String> componentNames() {
        return components.keySet();
    }

    public void tick() {
        components.entrySet().forEach((component) -> components.get(component).updateState());
        components.entrySet().forEach((component) -> components.get(component).propagateStateChange());
    }
}
