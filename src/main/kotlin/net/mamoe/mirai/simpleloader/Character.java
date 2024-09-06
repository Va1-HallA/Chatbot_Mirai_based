package net.mamoe.mirai.simpleloader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Character {
    String CharName;
    String CharClass;
    double Atk;
    double Def;
    double MgAtk;
    double MgDef;
    double MAX_HP;
    double MAX_MP;
    double HP;
    double MP;
    static int[] EXP_MILESTONE ={0,0,500,1500,3000,5000,7500,10500,14000,18000,22500,27500,33000,39000,45500,52500,60000,68000,76500,85500};
    int exp;
    int level;
    int money;
    double getATKCOE;
    Item equiptment = null;
    ArrayList<Buff> buff;
    Buff debuff;
    int BuffTime;
    int DebuffTime;
    transient ArrayList<Character> curEnemy;
    boolean hasWorked;
    boolean hasAttacked;
    ArrayList<Item> items;

    transient ArrayList<Character> teammate;

    public Character(){
        hasWorked = false;
        boolean hasAttacked = false;
        curEnemy = new ArrayList<>();
        teammate = new ArrayList<>();
        Item equiptment = null;
        buff = new ArrayList<>();
        debuff = null;
        BuffTime = 0;
        DebuffTime = 0;
        items = new ArrayList<>();
    }
    public Character(String name, String Class) throws Exception{
        CharName = name;
        CharClass = Class;
        hasWorked = false;
        boolean hasAttacked = false;
        curEnemy = new ArrayList<>();
        teammate = new ArrayList<>();
        Item equiptment = null;
        buff = new ArrayList<Buff>();
        debuff = null;
        BuffTime = 0;
        DebuffTime = 0;
        items = new ArrayList<>();
        try{
            switch(CharClass){
                case "Warrior":
                    Atk = ThreadLocalRandom.current().nextInt(20, 31);
                    Def = ThreadLocalRandom.current().nextInt(6, 11);
                    MgAtk = 0;
                    MgDef = ThreadLocalRandom.current().nextInt(0, 7);
                    MAX_HP = ThreadLocalRandom.current().nextInt(90, 131);
                    MAX_MP = ThreadLocalRandom.current().nextInt(6, 11);
                    HP = MAX_HP;
                    MP = MAX_MP;
                    exp = 0;
                    level = 1;
                    money = 0;
                    getATKCOE = ThreadLocalRandom.current().nextInt(35, 46);
                    break;

                case "Cleric":
                    Atk = ThreadLocalRandom.current().nextInt(0, 6);
                    Def = ThreadLocalRandom.current().nextInt(0, 8);
                    MgAtk = ThreadLocalRandom.current().nextInt(20, 31);
                    MgDef = ThreadLocalRandom.current().nextInt(2, 9);
                    MAX_HP = ThreadLocalRandom.current().nextInt(50, 101);
                    MAX_MP = ThreadLocalRandom.current().nextInt(20, 29);
                    HP = MAX_HP;
                    MP = MAX_MP;
                    exp = 0;
                    level = 1;
                    money = 0;
                    getATKCOE = ThreadLocalRandom.current().nextInt(15, 26);
                    break;

                case "Mage":
                    Atk = ThreadLocalRandom.current().nextInt(0, 6);
                    Def = ThreadLocalRandom.current().nextInt(0, 4);
                    MgAtk = ThreadLocalRandom.current().nextInt(20, 31);
                    MgDef = ThreadLocalRandom.current().nextInt(0, 3);
                    MAX_HP = ThreadLocalRandom.current().nextInt(50, 71);
                    MAX_MP = ThreadLocalRandom.current().nextInt(16, 36);
                    HP = MAX_HP;
                    MP = MAX_MP;
                    exp = 0;
                    level = 1;
                    money = 0;
                    getATKCOE = ThreadLocalRandom.current().nextInt(10, 21);
                    break;

                case "Ranger":
                    Atk = ThreadLocalRandom.current().nextInt(25, 41);
                    Def = ThreadLocalRandom.current().nextInt(3, 9);
                    MgAtk = 0;
                    MgDef = ThreadLocalRandom.current().nextInt(0, 5);
                    MAX_HP = ThreadLocalRandom.current().nextInt(60, 101);
                    MAX_MP = ThreadLocalRandom.current().nextInt(15, 22);
                    HP = MAX_HP;
                    MP = MAX_MP;
                    exp = 0;
                    level = 1;
                    money = 0;
                    getATKCOE = ThreadLocalRandom.current().nextInt(15, 26);
                    break;

                case "Paladin":
                    Atk = ThreadLocalRandom.current().nextInt(15, 26);
                    Def = ThreadLocalRandom.current().nextInt(8, 13);
                    MgAtk = ThreadLocalRandom.current().nextInt(10, 18);
                    MgDef = ThreadLocalRandom.current().nextInt(5, 10);
                    MAX_HP = ThreadLocalRandom.current().nextInt(110, 151);
                    MAX_MP = ThreadLocalRandom.current().nextInt(18, 25);
                    HP = MAX_HP;
                    MP = MAX_MP;
                    exp = 0;
                    level = 1;
                    money = 0;
                    getATKCOE = ThreadLocalRandom.current().nextInt(45, 56);
                    break;

                default:
                    throw new FormatErrorException();
        }

        }catch(NullPointerException e){
            throw new FormatErrorException();
        }
    }

    public void takeDamage(double amount){
        HP -= amount;
    }

    public void gainHP(double amount){
        this.HP += amount;
        if(this.HP >= this.MAX_HP){
            this.HP = this.MAX_HP;
        }
    }

    public double attack(Character target){
        double damage = 0;
        int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
        if(Atk + rdm - target.Def >= 0){damage = Atk + rdm - target.Def;}
        target.takeDamage(damage);
        return damage;
    }

    public double MPDamage(int amount){
        MP -= amount;
        return MP;
    }

//    public double castMagic(Character target)throws MpNotEnoughException{
//        this.MPDamage(3);
//        if(this.MP <= 0){
//            throw new MpNotEnoughException(user);
//        }
//        int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
//        double damage = 0;
//        if(MgAtk + rdm - target.MgDef >= 0){damage = MgAtk + rdm - target.MgDef;}
//        target.takeDamage(damage);
//        return damage;
//    }

    public void gainEXP(int amount){
        exp += amount;

        //check levelling up
        while(true){ //multiple checking enable levelling up more than 1 time
            if(exp <= EXP_MILESTONE[level+1]){
                break;
            }else{
                level += 1;
                switch(CharClass){
                    case "Warrior":
                        Atk += ThreadLocalRandom.current().nextInt(5, 9);
                        Def += ThreadLocalRandom.current().nextInt(1, 3);
                        MgDef += ThreadLocalRandom.current().nextInt(0, 2);
                        MAX_HP += ThreadLocalRandom.current().nextInt(22, 33);
                        MAX_MP += ThreadLocalRandom.current().nextInt(2, 3);
                        HP = MAX_HP;
                        MP = MAX_MP;
                        break;
                    case "Cleric":
                        Atk += ThreadLocalRandom.current().nextInt(0, 2);
                        Def += ThreadLocalRandom.current().nextInt(0, 2);
                        MgAtk += ThreadLocalRandom.current().nextInt(5, 8);
                        MgDef += ThreadLocalRandom.current().nextInt(1, 3);
                        MAX_HP += ThreadLocalRandom.current().nextInt(12, 26);
                        MAX_MP += ThreadLocalRandom.current().nextInt(5, 7);
                        HP = MAX_HP;
                        MP = MAX_MP;
                        break;

                    case "Mage":
                        Atk += ThreadLocalRandom.current().nextInt(0, 2);
                        Def += ThreadLocalRandom.current().nextInt(0, 1);
                        MgAtk += ThreadLocalRandom.current().nextInt(5, 8);
                        MgDef += ThreadLocalRandom.current().nextInt(0, 2);
                        MAX_HP += ThreadLocalRandom.current().nextInt(12, 18);
                        MAX_MP += ThreadLocalRandom.current().nextInt(4, 9);
                        HP = MAX_HP;
                        MP = MAX_MP;
                        break;

                    case "Ranger":
                        Atk += ThreadLocalRandom.current().nextInt(6, 10);
                        Def += ThreadLocalRandom.current().nextInt(1, 2);
                        MgDef += ThreadLocalRandom.current().nextInt(0, 1);
                        MAX_HP += ThreadLocalRandom.current().nextInt(15, 25);
                        MAX_MP += ThreadLocalRandom.current().nextInt(3, 6);
                        HP = MAX_HP;
                        MP = MAX_MP;
                        break;

                    case "Paladin":
                        Atk += ThreadLocalRandom.current().nextInt(3, 7);
                        Def += ThreadLocalRandom.current().nextInt(2, 5);
                        MgAtk += ThreadLocalRandom.current().nextInt(3, 5);
                        MgDef += ThreadLocalRandom.current().nextInt(1, 3);
                        MAX_HP += ThreadLocalRandom.current().nextInt(28, 38);
                        MAX_MP += ThreadLocalRandom.current().nextInt(5, 8);
                        HP = MAX_HP;
                        MP = MAX_MP;
                        break;

                }
            }
        }
    }

    public void teamUp(Character tmmt){
        this.teammate.add(tmmt);

        if(!this.curEnemy.isEmpty()){
            this.curEnemy.get(0).curEnemy.remove(this);
        }

        if(!tmmt.curEnemy.isEmpty()){
            if(this.curEnemy.isEmpty()){
                this.curEnemy.add(tmmt.curEnemy.get(0));
            }else{
                this.curEnemy.set(0,tmmt.curEnemy.get(0));
            }
            for(Character i: tmmt.curEnemy.get(0).curEnemy){
                if(i.CharName.equals(this.CharName)){
                    break;
                }else if(i == tmmt.curEnemy.get(0).curEnemy.get(tmmt.curEnemy.get(0).curEnemy.size() - 1)){
                    tmmt.curEnemy.get(0).curEnemy.add(this);
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Character{" +
                "CharName='" + CharName + '\'' +
                ", CharClass='" + CharClass + '\'' +
                ", Atk=" + Atk +
                ", Def=" + Def +
                ", MgAtk=" + MgAtk +
                ", MgDef=" + MgDef +
                ", MAX_HP=" + MAX_HP +
                ", MAX_MP=" + MAX_MP +
                ", HP=" + HP +
                ", MP=" + MP +
                ", EXP_MILESTONE=" + Arrays.toString(EXP_MILESTONE) +
                ", exp=" + exp +
                ", level=" + level +
                '}';
    }
}
