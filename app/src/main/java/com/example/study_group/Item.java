package com.example.study_group;

public class Item {
    public String name;   //"루피"
    public String msg;    //"해적단 선장"
    public int icon;      //R.drawable.img01
    public int img;       //R.drawable.img02

    public Item() {
    }

    public Item(String name, String msg, int icon, int img) {
        this.name = name;
        this.msg = msg;
        this.icon = icon;
        this.img = img;
    }
}
