package Models;

import java.util.ArrayList;

public class EntArray {

    public ArrayList<Entrepreneur> getList() {
        return list;
    }

    public void setList(ArrayList<Entrepreneur> list) {
        this.list = list;
    }

    ArrayList<Entrepreneur> list;

    public EntArray(ArrayList<Entrepreneur> list) {
        this.list = list;
    }
}
