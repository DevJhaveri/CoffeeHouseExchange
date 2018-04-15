package Models;

import java.util.ArrayList;

public class IndArray {
    public ArrayList<Individual> getList() {
        return list;
    }

    public void setList(ArrayList<Individual> list) {
        this.list = list;
    }

    ArrayList<Individual> list;

    public IndArray(ArrayList<Individual> list) {
        this.list = list;
    }
}