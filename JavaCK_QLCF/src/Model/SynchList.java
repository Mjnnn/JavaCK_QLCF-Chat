package Model;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SynchList {
    private ArrayList<ObjectOutputStream> list;

    public SynchList() {
        list = new ArrayList<ObjectOutputStream>();
    }

    synchronized ObjectOutputStream get(int i) {
        return list.get(i);
    }

    synchronized void add(ObjectOutputStream o) {
        list.add(o);
    }

    public synchronized int size() {
        return list.size();
    }

    synchronized void remove(ObjectOutputStream o) {
        list.remove(o);
    }
}
