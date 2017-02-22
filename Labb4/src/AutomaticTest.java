import java.util.ArrayList;
import java.util.Arrays;

public class AutomaticTest {

    static String[] testNames = {
            "AndGate",
            "OrGate",
            "XorGate",
            "NotGate",
            "Adder",
            "Constant",
            "Fork",
            "DFlipFlop",
            "JKFLipFlop",
            "EightBitAdder",
            "EightBitCounter"
    };
    static int indexEightBitAdder = 9;
    static int indexEightBitCounter = 10;
    static String[][] circuits = {
            {  // AndGate
                    "i1 INPUT 0 c 0",
                    "i2 INPUT 0 c 1",
                    "c AND 0 o 0",
                    "o OUTPUT"
            },
            {  // OrGate
                    "i1 INPUT 0 c 0",
                    "i2 INPUT 0 c 1",
                    "c OR 0 o 0",
                    "o OUTPUT"
            },
            {  // XorGate
                    "i1 INPUT 0 c 0",
                    "i2 INPUT 0 c 1",
                    "c XOR 0 o 0",
                    "o OUTPUT"
            },
            {  // NotGate
                    "i INPUT 0 c 0",
                    "c NOT 0 o 0",
                    "o OUTPUT"
            },
            {  // Adder
                    "i1 INPUT 0 c 0",
                    "i2 INPUT 0 c 1",
                    "i3 INPUT 0 c 2",
                    "c ADDER 0 sum 0 1 carry 0",
                    "sum OUTPUT",
                    "carry OUTPUT"
            },
            {  // Constant
                    "c1 ZERO 0 o1 0",
                    "c2 ONE 0 o2 0",
                    "o1 OUTPUT",
                    "o2 OUTPUT"
            },
            {  // Fork
                    "i INPUT 0 c 0",
                    "c FORK 0 o1 0 1 o2 0",
                    "o1 OUTPUT",
                    "o2 OUTPUT"
            },
            {  // DFlipFlop
                    "i INPUT 0 c 0",
                    "c DFLIPFLOP 0 o 0",
                    "o OUTPUT"
            },
            {  // JKFLipFlop
                    "j INPUT 0 c 0",
                    "k INPUT 0 c 1",
                    "c JKFLIPFLOP 0 q 0 1 nq 0",
                    "q OUTPUT",
                    "nq OUTPUT"
            },
            null,  // EightBitAdder
            null  // EightBitCounter
    };

    static String[][] sequences = {
            {  // AndGate
                    "o 0",
                    "i1 1",
                    "o 0",
                    "t",
                    "o 0",
                    "i2 1",
                    "o 1",
                    "t",
                    "o 1",
                    "i1 0",
                    "o 0"
            },
            {  // OrGate
                    "o 0",
                    "i1 1",
                    "o 1",
                    "t",
                    "o 1",
                    "i2 1",
                    "o 1",
                    "t",
                    "o 1",
                    "i1 0",
                    "o 1"
            },
            {  // OrGate
                    "o 0",
                    "i1 1",
                    "o 1",
                    "t",
                    "o 1",
                    "i2 1",
                    "o 0",
                    "t",
                    "o 0",
                    "i1 0",
                    "o 1"
            },
            {  // NotGate
                    "o 1",
                    "i 1",
                    "o 0"
            },
            {  // Adder
                    "sum 0",
                    "carry 0",
                    "i1 1",
                    "sum 1",
                    "carry 0",
                    "i2 1",
                    "sum 0",
                    "carry 1",
                    "i1 0",
                    "sum 1",
                    "carry 0",
                    "i3 1",
                    "sum 0",
                    "carry 1",
                    "i2 0",
                    "sum 1",
                    "carry 0",
                    "i1 1",
                    "sum 0",
                    "carry 1",
                    "i2 1",
                    "sum 1",
                    "carry 1"
            },
            {  // Constant
                    "o1 0",
                    "o2 1"
            },
            {  // Fork
                    "o1 0",
                    "o2 0",
                    "i 1",
                    "o1 1",
                    "o2 1"
            },
            {  // DFlipFlop
                    "o 0",
                    "i 1",
                    "o 0",
                    "t",
                    "o 1",
                    "t",
                    "o 1",
                    "i 0",
                    "o 1",
                    "t",
                    "o 0"
            },
            {  // JKFlipFlop
                    "j 1",
                    "k 0",
                    "t",
                    "q 1",
                    "nq 0",
                    "k 1",
                    "t",
                    "q 0",
                    "nq 1",
                    "k 0",
                    "q 0",
                    "t",
                    "q 1",
                    "j 0",
                    "t",
                    "q 1",
                    "k 1",
                    "t",
                    "q 0"
            },
            {  // EightBitAdder
                    "x0 0",
                    "x1 1",
                    "x2 0",
                    "x3 1",
                    "x4 1",
                    "x5 1",
                    "x6 1",
                    "x7 0",

                    "y0 1",
                    "y1 1",
                    "y2 0",
                    "y3 0",
                    "y4 1",
                    "y5 1",
                    "y6 0",
                    "y7 0",

                    "z0 1",
                    "z1 0",
                    "z2 1",
                    "z3 1",
                    "z4 0",
                    "z5 1",
                    "z6 0",
                    "z7 1"
            },
            {  // EightBitCounter
                    "x0 0",
                    "x1 0",
                    "x2 0",
                    "x3 0",
                    "x4 0",
                    "x5 0",
                    "x6 0",
                    "x7 0",
                    "t",
                    "x0 1",
                    "x1 0",
                    "x2 0",
                    "x3 0",
                    "x4 0",
                    "x5 0",
                    "x6 0",
                    "x7 0",
                    "t",
                    "x0 0",
                    "x1 1",
                    "x2 0",
                    "x3 0",
                    "x4 0",
                    "x5 0",
                    "x6 0",
                    "x7 0",
                    "t",
                    "t",
                    "t",
                    "t",
                    "t",
                    "t",
                    "t",
                    "t",
                    "t",
                    "t",
                    "t",
                    "x0 1",
                    "x1 0",
                    "x2 1",
                    "x3 1",
                    "x4 0",
                    "x5 0",
                    "x6 0",
                    "x7 0"
            }
    };

    static void dumpState(String name, String[] circuit, String[] sequence, Circuit c, int bugLine) {
        System.out.println("Bug detected in test " + name);
        System.out.println("");
        System.out.println("Circuit:");
        for (String line : circuit) {
            System.out.println(line);
        }
        System.out.println("");
        System.out.println("Sequence:");
        for (int i = 0; i < bugLine; i++) {
            String[] words = sequence[i].split(" ");
            if (words[0].equals("t")) {
                System.out.println("tick");
            } else {
                CircuitComponent comp = c.getComponent(words[0]);
                boolean value = words[1].equals("1");
                if (comp instanceof Input) {
                    System.out.println("set input " + words[0] + " to " + value);
                }
            }
        }
        String[] words = sequence[bugLine].split(" ");
        System.out.println("");
        System.out.println("After this output " + words[0] + " should have value " + words[1].equals("1") + ", but has not.");
        System.exit(1);
    }

    static void runTest(String name, String[] circuit, String[] sequence) {
        try {
            Circuit c = new Circuit(Arrays.asList(circuit));
            for (int i = 0; i < sequence.length; i++) {
                String[] words = sequence[i].split(" ");
                if (words[0].equals("t")) {
                    c.tick();
                } else {
                    CircuitComponent comp = c.getComponent(words[0]);
                    boolean value = words[1].equals("1");
                    if (comp instanceof Input) {
                        ((Input)comp).setValue(value);
                    } else if (comp instanceof Output) {
                        boolean actualValue = ((Output)comp).getValue();
                        if (actualValue != value) {
                            dumpState(name, circuit, sequence, c, i);
                        }
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Bug detected in test " + name);
            System.out.println("Exception was thrown but this should not happen.");
            System.out.println("Exception message: " + e.getMessage());
            System.exit(1);
        }
    }

    static void runBadTests() {
        {
            Circuit c = new Circuit();
            c.addComponent("x", new AndGate());
            try {
                c.addComponent("x", new AndGate());
                System.out.println("Bug found: Added two components with same name to a circuit but no exception was thrown.");
                System.exit(1);
            } catch (RuntimeException e) {
            }
        }

        {
            Circuit c = new Circuit();
            c.addComponent("x", new AndGate());
            c.addComponent("y", new AndGate());
            c.addComponent("z", new AndGate());
            c.getComponent("x").connect(0, c.getComponent("y"), 0);
            try {
                c.getComponent("x").connect(0, c.getComponent("z"), 0);
                System.out.println("Bug found: Connected same output twice but no exception was thrown.");
                System.exit(1);
            } catch (RuntimeException e) {
            }
        }

        {
            Circuit c = new Circuit();
            c.addComponent("x", new AndGate());
            c.addComponent("y", new AndGate());
            c.addComponent("z", new AndGate());
            c.getComponent("x").connect(0, c.getComponent("y"), 0);
            try {
                c.getComponent("z").connect(0, c.getComponent("y"), 0);
                System.out.println("Bug found: Connected same input twice but no exception was thrown.");
                System.exit(1);
            } catch (RuntimeException e) {
            }
        }
    }

    public static void main(String[] argv) {
        constructNBitAdderCircuit(8, indexEightBitAdder);
        constructNBitCounterCircuit(8, indexEightBitCounter);

        for (int i = 0; i < circuits.length; i++) {
            runTest(testNames[i], circuits[i], sequences[i]);
        }
        runBadTests();
        System.out.println(circuits.length + " circuits and some illegal usage tested without any bugs found.");
    }

    static void constructNBitAdderCircuit(int nbits, int testidx) {
        ArrayList<String> c = new ArrayList<>();
        for (int i = 0; i < nbits; i++) {
            c.add("x" + i + " INPUT 0 f1" + i + " 0");
            c.add("y" + i + " INPUT 0 f2" + i + " 0");
            if (i == 0) {
                c.add("zero ZERO 0 f40 0");
            }
            c.add("f1" + i + " FORK 0 xor1" + i + " 0 1 and2" + i + " 0");
            c.add("f2" + i + " FORK 0 xor1" + i + " 1 1 and2" + i + " 1");
            c.add("xor1" + i + " XOR 0 f3" + i + " 0");
            c.add("and2" + i + " AND 0 or" + i + " 1");
            c.add("f3" + i + " FORK 0 xor2" + i + " 0 1 and1" + i + " 0");
            c.add("f4" + i + " FORK 0 xor2" + i + " 1 1 and1" + i + " 1");
            c.add("and1" + i + " AND 0 or" + i + " 0");
            c.add("xor2" + i + " XOR 0 z" + i + " 0");
            c.add("z" + i + " OUTPUT");
            if (i == nbits - 1) {
                c.add("or" + i + " OR");
            } else {
                c.add("or" + i + " OR 0 f4" + (i + 1) + " 0");
            }
        }
        circuits[testidx] = new String[c.size()];
        c.toArray(circuits[testidx]);
    }

    static void constructNBitCounterCircuit(int nbits, int testidx) {
        ArrayList<String> c = new ArrayList<>();
        c.add("one1 ONE 0 jk0 0");
        c.add("one2 ONE 0 jk0 1");
        c.add("one3 ONE 0 and0 0");
        for (int i = 0; i < nbits; i++) {
            c.add("jk" + i + " JKFLIPFLOP 0 f1" + i + " 0");
            c.add("f1" + i + " FORK 0 x" + i + " 0 1 and" + i + " 1");
            c.add("and" + i + " AND 0 f2" + i + " 0");
            if (i == nbits - 1) {
                c.add("f2" + i + " FORK");
            } else {
                c.add("f2" + i + " FORK 0 f3" + i + " 0 1 jk" + (i + 1) + " 1");
                c.add("f3" + i + " FORK 0 and" + (i + 1) + " 0 1 jk" + (i + 1) + " 0");
            }
            c.add("x" + i + " OUTPUT");
        }
        circuits[testidx] = new String[c.size()];
        c.toArray(circuits[testidx]);
    }
}