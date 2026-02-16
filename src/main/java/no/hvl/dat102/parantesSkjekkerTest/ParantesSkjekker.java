package no.hvl.dat102.parantesSkjekkerTest;

public class ParantesSkjekker {
    public static boolean skjekkParanteser(String s) {
        String[] chars = s.split("");
        Stabel<String> stabel = new Stabel<>();

        outer:
        for (String c : chars) {
            switch (c) {
                case "(":
                case "[":
                case "{": {
                    stabel.push(c);
                    break;
                }
                case ")":
                case "]":
                case "}": {
                    if (stabel.isEmpty()) {
                        return false;
                    }
                    String last = stabel.pop();
                    if (!matcher(last , c)) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    static boolean matcher(String x,String y) {
        if (x.equals("(") && y.equals(")")) {
            return true;
        }
        if (x.equals("[") && y.equals("]")) {
            return true;
        }
        if (x.equals("{") && y.equals("}")) {
            return true;
        }
        return false;
    }
}

interface StabelADT<T> {
    void push(T element);
    T pop();
    boolean isEmpty();
}

class Stabel<T> implements StabelADT<T> {
    T[] array  = (T[]) new Object [100000];
    int count = 0;

    @Override
    public void push(T element) {
        array[count++] = element;
    }

    @Override
    public T pop() {
        return array[--count];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}
