import java.util.List;
import java.util.Map;
import java.util.Set;

public class Circuit{

    private Map<String, CircuitComponent> components;

    public Circuit() {}

    public Circuit(List<String> lines){
        split()
    }


    public void addComponent(String name, CircuitComponent component) {
        if (!components.containsKey(name)) {
            components.put(name, component);
        }else{
            throw new RuntimeException("That key already exists");
        }

    }

    public CircuitComponent getComponent(String name){
        if(components.containsKey(name)){
            return components.get(name);
        }else return null;
    }

    public Set<String> componentNames() {
        return components.keySet();
    }

    public void tick(){
        components.entrySet().forEach((component) -> components.get(component).updateState());
        components.entrySet().forEach((component) -> components.get(component).propagateStateChange());
    }

}
