package net.mamoe.mirai.simpleloader;

public class Item {
    String name;
    String effect;
    String EngName;

    public Item(String name, String effect){
        this.name = name;
        this.effect = effect;
        this.EngName = "";
    }

    //进阶物品
    public Item(String name, String effect, String EngName){
        this.name = name;
        this.effect = effect;
        this.EngName = EngName;
    }
}
