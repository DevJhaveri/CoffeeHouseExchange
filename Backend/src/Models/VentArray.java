package Models;

import java.util.ArrayList;

public class VentArray {
    public ArrayList<Venture> getList() {
        return list;
    }

    public void setList(ArrayList<Venture> list) {
        this.list = list;
    }

    ArrayList<Venture> list;

    public VentArray(ArrayList<Venture> list) {
        this.list = list;
    }
}