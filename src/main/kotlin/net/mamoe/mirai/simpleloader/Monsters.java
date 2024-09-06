package net.mamoe.mirai.simpleloader;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Monsters {

    static Item[] LootList = {
            new Item("FireBallScroll","fireBall"),
            new Item("HealthPotion","gainHP"),
            new Item("ManaPotion","gainMP"),
            new Item("MagicSword","equip"),
            new Item("OakScepter","equip"),
            new Item("ElfBow","equip"),
            new Item("ElfChainMail","equip"),
            new Item("FairyTear","fairyTear"),
            new Item("MoonDust","moonDust"),
            new Item("SilverHolyGrail","equip","SilverHolyGrail"),
            new Item("RyantarOdes","equip","RyantarOdes"),
            new Item("SacrificeSword","equip","SacrificeSword"),
            new Item("RolandsBone","equip","RolandsBone"),
            new Item("RyantarKnightSword","equip","RyantarKnightSword"),
            new Item("RyantarKnightArmor","equip","RyantarKnightArmor"),
            new Item("MithrilBow","equip","MithrilBow"),
            new Item("GreyFogCloak","equip","GreyFogCloak"),
            new Item("FairySpellBook","equip","FairySpellBook"),
            new Item("BlueTearRing","equip","BlueTearRing"),
    };

    static Item[] SpecialLootList = {

    };

    public static Item randomLoot(String monster){
        RandomCollection<Item> loot = new RandomCollection<>();
        int[] weights;
        switch(monster){
            case "Slime":
            case "Goblin":
                weights = new int[]{30, 0, 0, 20, 20, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                break;
            case "Orc":
                weights = new int[]{15, 0, 0, 20, 20, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                break;
            case "Troll":
                weights = new int[]{10, 0, 0, 20, 20, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                break;
            case "Nymph":
                weights = new int[]{0, 0, 0, 0, 0, 0, 0, 50, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                break;
            case "WinterWolf":
                weights = new int[]{10, 0, 0, 20, 20, 20, 20, 0, 0, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};
                break;
            case "Lemure":
                weights = new int[]{20, 0, 0, 20, 20, 20, 20, 0, 0, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11};
                break;
            case "SilentPhantom":
            case "CloudFairy":
            case "WereWolf":
                weights = new int[]{5, 0, 0, 2, 2, 2, 2, 0, 0, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
                break;
            default:
                weights = new int[]{0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        }
        for(int i=0;i<LootList.length;i++){
            loot = loot.add(weights[i],LootList[i]);
        }
        return loot.next();
    }

    public static Character createSlime(){
        Character Slime;
        Slime = new Character();
        Slime.Atk = ThreadLocalRandom.current().nextInt(10, 15);
        Slime.Def = ThreadLocalRandom.current().nextInt(1, 3);
        Slime.MgAtk = 0;
        Slime.MgDef = ThreadLocalRandom.current().nextInt(8, 13);
        Slime.MAX_HP = ThreadLocalRandom.current().nextInt(60, 71);
        Slime.MAX_MP = 0;
        Slime.HP = Slime.MAX_HP;
        Slime.MP = Slime.MAX_MP;
        Slime.exp = ThreadLocalRandom.current().nextInt(35, 66);
        Slime.level = 1;
        Slime.money = ThreadLocalRandom.current().nextInt(0, 10);

        //add loot
        RandomCollection<Integer> LootNum = new RandomCollection<>();
        LootNum.add(50,0).add(40,1).add(10,2);
        int ln = LootNum.next();
        switch(ln){
            case 0:
                break;
            case 1:
                Item loot1 = randomLoot("Slime");
                Slime.items.add(loot1);
                break;
            case 2:
                Item loot2 = randomLoot("Slime");
                Item loot3 = randomLoot("Slime");
                Slime.items.add(loot2);
                Slime.items.add(loot3);
                break;
        }
        return Slime;
    }

    public static Character createGoblin(){
        Character Goblin;
        Goblin = new Character();
        Goblin.Atk = ThreadLocalRandom.current().nextInt(8, 13);
        Goblin.Def = ThreadLocalRandom.current().nextInt(4, 8);
        Goblin.MgAtk = 0;
        Goblin.MgDef = ThreadLocalRandom.current().nextInt(0, 4);
        Goblin.MAX_HP = ThreadLocalRandom.current().nextInt(75, 101);
        Goblin.MAX_MP = 0;
        Goblin.HP = Goblin.MAX_HP;
        Goblin.MP = Goblin.MAX_MP;
        Goblin.exp = ThreadLocalRandom.current().nextInt(35, 56);
        Goblin.level = 1;
        Goblin.money = ThreadLocalRandom.current().nextInt(5, 26);

        //add loot
        RandomCollection<Integer> LootNum = new RandomCollection<>();
        LootNum.add(50,0).add(40,1).add(10,2);
        int ln = LootNum.next();
        switch(ln){
            case 0:
                break;
            case 1:
                Item loot1 = randomLoot("Goblin");
                Goblin.items.add(loot1);
                break;
            case 2:
                Item loot2 = randomLoot("Goblin");
                Item loot3 = randomLoot("Goblin");
                Goblin.items.add(loot2);
                Goblin.items.add(loot3);
                break;
        }
        return Goblin;
    }

    public static Character createOrc(){
        Character Orc;
        Orc = new Character();
        Orc.Atk = ThreadLocalRandom.current().nextInt(25, 36);
        Orc.Def = ThreadLocalRandom.current().nextInt(8, 11);
        Orc.MgAtk = 0;
        Orc.MgDef = ThreadLocalRandom.current().nextInt(0, 6);
        Orc.MAX_HP = ThreadLocalRandom.current().nextInt(180, 231);
        Orc.MAX_MP = 0;
        Orc.HP = Orc.MAX_HP;
        Orc.MP = Orc.MAX_MP;
        Orc.exp = ThreadLocalRandom.current().nextInt(70, 121);
        Orc.level = 1;
        Orc.money = ThreadLocalRandom.current().nextInt(30, 51);

        //add loot
        RandomCollection<Integer> LootNum = new RandomCollection<>();
        LootNum.add(50,0).add(40,1).add(10,2);
        int ln = LootNum.next();
        switch(ln){
            case 0:
                break;
            case 1:
                Item loot1 = randomLoot("Orc");
                Orc.items.add(loot1);
                break;
            case 2:
                Item loot2 = randomLoot("Orc");
                Item loot3 = randomLoot("Orc");
                Orc.items.add(loot2);
                Orc.items.add(loot3);
                break;
        }

        return Orc;
    }

    public static Character createTroll(){
        Character Troll;
        Troll = new Character();
        Troll.Atk = ThreadLocalRandom.current().nextInt(25, 36);
        Troll.Def = ThreadLocalRandom.current().nextInt(10, 16);
        Troll.MgAtk = 0;
        Troll.MgDef = 0;
        Troll.MAX_HP = ThreadLocalRandom.current().nextInt(230, 261);
        Troll.MAX_MP = 0;
        Troll.HP = Troll.MAX_HP;
        Troll.MP = Troll.MAX_MP;
        Troll.exp = ThreadLocalRandom.current().nextInt(100, 141);
        Troll.level = 1;
        Troll.money = ThreadLocalRandom.current().nextInt(50, 101);

        //add loot
        RandomCollection<Integer> LootNum = new RandomCollection<>();
        LootNum.add(40,0).add(50,1).add(10,2);
        int ln = LootNum.next();
        switch(ln){
            case 0:
                break;
            case 1:
                Item loot1 = randomLoot("Troll");
                Troll.items.add(loot1);
                break;
            case 2:
                Item loot2 = randomLoot("Troll");
                Item loot3 = randomLoot("Troll");
                Troll.items.add(loot2);
                Troll.items.add(loot3);
                break;
        }

        return Troll;
    }

    public static Character createNymph(){ //夺目绝色 *3
        Character Nymph;
        Nymph = new Character();
        Nymph.CharClass = "Nymph";
        Nymph.Atk = ThreadLocalRandom.current().nextInt(35, 51);
        Nymph.Def = ThreadLocalRandom.current().nextInt(20, 26);
        Nymph.MgAtk = ThreadLocalRandom.current().nextInt(30, 46);
        Nymph.MgDef = ThreadLocalRandom.current().nextInt(20, 26);
        Nymph.MAX_HP = ThreadLocalRandom.current().nextInt(230, 261);
        Nymph.MAX_MP = ThreadLocalRandom.current().nextInt(100, 151);;
        Nymph.HP = Nymph.MAX_HP;
        Nymph.MP = Nymph.MAX_MP;
        Nymph.exp = ThreadLocalRandom.current().nextInt(130, 151);
        Nymph.level = 3;
        Nymph.money = ThreadLocalRandom.current().nextInt(120, 171);


        //add loot
        Nymph.items.add(randomLoot("Nymph"));

        return Nymph;
    }

    public static Character createWinterWolf(){ //可以吐息、霜之牙
        Character WinterWolf;
        WinterWolf = new Character();
        WinterWolf.CharClass = "WinterWolf";
        WinterWolf.Atk = ThreadLocalRandom.current().nextInt(40, 46);
        WinterWolf.Def = ThreadLocalRandom.current().nextInt(20, 31);
        WinterWolf.MgAtk = ThreadLocalRandom.current().nextInt(20, 26);
        WinterWolf.MgDef = ThreadLocalRandom.current().nextInt(15, 26);
        WinterWolf.MAX_HP = ThreadLocalRandom.current().nextInt(265, 296);
        WinterWolf.MAX_MP = ThreadLocalRandom.current().nextInt(20, 26);;
        WinterWolf.HP = WinterWolf.MAX_HP;
        WinterWolf.MP = WinterWolf.MAX_MP;
        WinterWolf.exp = ThreadLocalRandom.current().nextInt(140, 161);
        WinterWolf.level = 3;
        WinterWolf.money = ThreadLocalRandom.current().nextInt(100, 151);

        //add loot
        RandomCollection<Integer> LootNum = new RandomCollection<>();
        LootNum.add(40,0).add(50,1).add(10,2);
        int ln = LootNum.next();
        switch(ln){
            case 0:
                break;
            case 1:
                Item loot1 = randomLoot("WinterWolf");
                WinterWolf.items.add(loot1);
                break;
            case 2:
                Item loot2 = randomLoot("WinterWolf");
                Item loot3 = randomLoot("WinterWolf");
                WinterWolf.items.add(loot2);
                WinterWolf.items.add(loot3);
                break;
        }

        return WinterWolf;
    }

    public static Character createLemure(){
        Character Lemure;
        Lemure = new Character();
        Lemure.Atk = ThreadLocalRandom.current().nextInt(50, 71);
        Lemure.Def = ThreadLocalRandom.current().nextInt(20, 36);
        Lemure.MgAtk = 0;
        Lemure.MgDef = ThreadLocalRandom.current().nextInt(30, 41);
        Lemure.MAX_HP = ThreadLocalRandom.current().nextInt(155, 206);
        Lemure.MAX_MP = 0;
        Lemure.HP = Lemure.MAX_HP;
        Lemure.MP = Lemure.MAX_MP;
        Lemure.exp = ThreadLocalRandom.current().nextInt(130, 181);
        Lemure.level = 3;
        Lemure.money = ThreadLocalRandom.current().nextInt(100, 141);

        //add loot
        RandomCollection<Integer> LootNum = new RandomCollection<>();
        LootNum.add(40,0).add(50,1).add(10,2);
        int ln = LootNum.next();
        switch(ln){
            case 0:
                break;
            case 1:
                Item loot1 = randomLoot("Lemure");
                Lemure.items.add(loot1);
                break;
            case 2:
                Item loot2 = randomLoot("Lemure");
                Item loot3 = randomLoot("Lemure");
                Lemure.items.add(loot2);
                Lemure.items.add(loot3);
                break;
        }

        return Lemure;
    }

    public static Character createSilentPhantom(){ // 这个怪物只要魔力不空就一定会使用黑弥撒;黑弥撒可以秒杀对手;使用月之尘可以阻止他使用技能;此外会弱化版灵魂激流
        Character SilentPhantom;
        SilentPhantom = new Character();
        SilentPhantom.CharClass = "SilentPhantom";
        SilentPhantom.Atk = 0;
        SilentPhantom.Def = ThreadLocalRandom.current().nextInt(50, 56);
        SilentPhantom.MgAtk = ThreadLocalRandom.current().nextInt(70, 101);
        SilentPhantom.MgDef = ThreadLocalRandom.current().nextInt(45, 51);
        SilentPhantom.MAX_HP = ThreadLocalRandom.current().nextInt(401, 451);
        SilentPhantom.MAX_MP = ThreadLocalRandom.current().nextInt(100, 151);
        SilentPhantom.HP = SilentPhantom.MAX_HP;
        SilentPhantom.MP = SilentPhantom.MAX_MP;
        SilentPhantom.exp = ThreadLocalRandom.current().nextInt(200, 251);
        SilentPhantom.level = 5;
        SilentPhantom.money = 0;

        //add loot
        RandomCollection<Integer> LootNum = new RandomCollection<>();
        LootNum.add(10,0).add(70,1).add(20,2);
        int ln = LootNum.next();
        switch(ln){
            case 0:
                break;
            case 1:
                Item loot1 = randomLoot("SilentPhantom");
                SilentPhantom.items.add(loot1);
                break;
            case 2:
                Item loot2 = randomLoot("SilentPhantom");
                Item loot3 = randomLoot("SilentPhantom");
                SilentPhantom.items.add(loot2);
                SilentPhantom.items.add(loot3);
                break;
        }

        return SilentPhantom;
    }

    public static Character createCloudFairy(){
        // 每回合除攻击外自动使用：莱恩塔幻景术——使一个玩家无法行动（即 hasAttacked = true ）
        //只要能使用魔法就会使用魔法(castMagic)
        Character CloudFairy;
        CloudFairy = new Character();
        CloudFairy.CharClass = "CloudFairy";
        CloudFairy.Atk = 0;
        CloudFairy.Def = ThreadLocalRandom.current().nextInt(50, 56);
        CloudFairy.MgAtk = ThreadLocalRandom.current().nextInt(150, 161);
        CloudFairy.MgDef = ThreadLocalRandom.current().nextInt(45, 51);
        CloudFairy.MAX_HP = ThreadLocalRandom.current().nextInt(401, 451);
        CloudFairy.MAX_MP = ThreadLocalRandom.current().nextInt(100, 151);
        CloudFairy.HP = CloudFairy.MAX_HP;
        CloudFairy.MP = CloudFairy.MAX_MP;
        CloudFairy.exp = ThreadLocalRandom.current().nextInt(250, 271);
        CloudFairy.level = 5;
        CloudFairy.money = ThreadLocalRandom.current().nextInt(200, 251);

        //add loot
        RandomCollection<Integer> LootNum = new RandomCollection<>();
        LootNum.add(10,0).add(70,1).add(20,2);
        int ln = LootNum.next();
        switch(ln){
            case 0:
                break;
            case 1:
                Item loot1 = randomLoot("CloudFairy");
                CloudFairy.items.add(loot1);
                break;
            case 2:
                Item loot2 = randomLoot("CloudFairy");
                Item loot3 = randomLoot("CloudFairy");
                CloudFairy.items.add(loot2);
                CloudFairy.items.add(loot3);
                break;
        }

        return CloudFairy;
    }

    public static Character createWereWolf(){
        // 撕咬——可以吸血
        Character WereWolf;
        WereWolf = new Character();
        WereWolf.CharClass = "WereWolf";
        WereWolf.Atk = ThreadLocalRandom.current().nextInt(140, 171);;
        WereWolf.Def = ThreadLocalRandom.current().nextInt(101, 131);
        WereWolf.MgAtk = 0;
        WereWolf.MgDef = ThreadLocalRandom.current().nextInt(240, 271);
        WereWolf.MAX_HP = ThreadLocalRandom.current().nextInt(370, 421);
        WereWolf.MAX_MP = ThreadLocalRandom.current().nextInt(100, 151);
        WereWolf.HP = WereWolf.MAX_HP;
        WereWolf.MP = WereWolf.MAX_MP;
        WereWolf.exp = ThreadLocalRandom.current().nextInt(300, 351);
        WereWolf.level = 5;
        WereWolf.money = ThreadLocalRandom.current().nextInt(300, 351);

        //add loot
        RandomCollection<Integer> LootNum = new RandomCollection<>();
        LootNum.add(10,0).add(70,1).add(20,2);
        int ln = LootNum.next();
        switch(ln){
            case 0:
                break;
            case 1:
                Item loot1 = randomLoot("WereWolf");
                WereWolf.items.add(loot1);
                break;
            case 2:
                Item loot2 = randomLoot("WereWolf");
                Item loot3 = randomLoot("WereWolf");
                WereWolf.items.add(loot2);
                WereWolf.items.add(loot3);
                break;
        }

        return WereWolf;
    }

    //public static Character createJuvenileRedDragon(){}
    //public static Character createFrostGiant(){}
}
