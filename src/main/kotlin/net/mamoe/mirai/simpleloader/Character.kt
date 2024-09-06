//package net.mamoe.mirai.simpleloader
//
//import java.util.*
//import java.util.concurrent.ThreadLocalRandom
//
//class Character {
//    var CharName: String? = null
//    var CharClass: String? = null
//    var Atk = 0.0
//    var Def = 0.0
//    var MgAtk = 0.0
//    var MgDef = 0.0
//    var MAX_HP = 0.0
//    var MAX_MP = 0.0
//    var HP = 0.0
//    var MP = 0.0
//    var exp = 0
//    var level = 0
//    var money = 0
//    var getATKCOE = 0.0
//    var equiptment: Item? = null
//    var buff: Buff? = null
//    var debuff: Buff? = null
//    var BuffTime = 0
//    var DebuffTime = 0
//
//    @Transient
//    var curEnemy: ArrayList<Character>
//    var hasWorked: Boolean
//    var hasAttacked = false
//    var items: ArrayList<Item>
//
//    @Transient
//    var teammate: ArrayList<Character>
//
//    constructor() {
//        hasWorked = false
//        val hasAttacked = false
//        curEnemy = ArrayList()
//        teammate = ArrayList()
//        val equiptment: Item? = null
//        items = ArrayList()
//    }
//
//    constructor(name: String?, Class: String?) {
//        CharName = name
//        CharClass = Class
//        hasWorked = false
//        val hasAttacked = false
//        curEnemy = ArrayList()
//        teammate = ArrayList()
//        val equiptment: Item? = null
//        items = ArrayList()
//        try {
//            when (CharClass) {
//                "Warrior" -> {
//                    Atk = ThreadLocalRandom.current().nextInt(20, 31).toDouble()
//                    Def = ThreadLocalRandom.current().nextInt(6, 11).toDouble()
//                    MgAtk = 0.0
//                    MgDef = ThreadLocalRandom.current().nextInt(0, 7).toDouble()
//                    MAX_HP = ThreadLocalRandom.current().nextInt(90, 131).toDouble()
//                    MAX_MP = ThreadLocalRandom.current().nextInt(6, 11).toDouble()
//                    HP = MAX_HP
//                    MP = MAX_MP
//                    exp = 0
//                    level = 1
//                    money = 0
//                    getATKCOE = ThreadLocalRandom.current().nextInt(35, 46).toDouble()
//                }
//                "Cleric" -> {
//                    Atk = ThreadLocalRandom.current().nextInt(0, 6).toDouble()
//                    Def = ThreadLocalRandom.current().nextInt(0, 8).toDouble()
//                    MgAtk = ThreadLocalRandom.current().nextInt(20, 31).toDouble()
//                    MgDef = ThreadLocalRandom.current().nextInt(2, 9).toDouble()
//                    MAX_HP = ThreadLocalRandom.current().nextInt(50, 101).toDouble()
//                    MAX_MP = ThreadLocalRandom.current().nextInt(20, 29).toDouble()
//                    HP = MAX_HP
//                    MP = MAX_MP
//                    exp = 0
//                    level = 1
//                    money = 0
//                    getATKCOE = ThreadLocalRandom.current().nextInt(15, 26).toDouble()
//                }
//                "Mage" -> {
//                    Atk = ThreadLocalRandom.current().nextInt(0, 6).toDouble()
//                    Def = ThreadLocalRandom.current().nextInt(0, 4).toDouble()
//                    MgAtk = ThreadLocalRandom.current().nextInt(20, 31).toDouble()
//                    MgDef = ThreadLocalRandom.current().nextInt(0, 3).toDouble()
//                    MAX_HP = ThreadLocalRandom.current().nextInt(50, 71).toDouble()
//                    MAX_MP = ThreadLocalRandom.current().nextInt(16, 36).toDouble()
//                    HP = MAX_HP
//                    MP = MAX_MP
//                    exp = 0
//                    level = 1
//                    money = 0
//                    getATKCOE = ThreadLocalRandom.current().nextInt(10, 21).toDouble()
//                }
//                "Ranger" -> {
//                    Atk = ThreadLocalRandom.current().nextInt(25, 41).toDouble()
//                    Def = ThreadLocalRandom.current().nextInt(3, 9).toDouble()
//                    MgAtk = 0.0
//                    MgDef = ThreadLocalRandom.current().nextInt(0, 5).toDouble()
//                    MAX_HP = ThreadLocalRandom.current().nextInt(60, 101).toDouble()
//                    MAX_MP = ThreadLocalRandom.current().nextInt(15, 22).toDouble()
//                    HP = MAX_HP
//                    MP = MAX_MP
//                    exp = 0
//                    level = 1
//                    money = 0
//                    getATKCOE = ThreadLocalRandom.current().nextInt(15, 26).toDouble()
//                }
//                "Paladin" -> {
//                    Atk = ThreadLocalRandom.current().nextInt(15, 26).toDouble()
//                    Def = ThreadLocalRandom.current().nextInt(8, 13).toDouble()
//                    MgAtk = ThreadLocalRandom.current().nextInt(5, 11).toDouble()
//                    MgDef = ThreadLocalRandom.current().nextInt(5, 10).toDouble()
//                    MAX_HP = ThreadLocalRandom.current().nextInt(110, 151).toDouble()
//                    MAX_MP = ThreadLocalRandom.current().nextInt(18, 25).toDouble()
//                    HP = MAX_HP
//                    MP = MAX_MP
//                    exp = 0
//                    level = 1
//                    money = 0
//                    getATKCOE = ThreadLocalRandom.current().nextInt(45, 56).toDouble()
//                }
//                else -> throw FormatErrorException()
//            }
//        } catch (e: NullPointerException) {
//            throw FormatErrorException()
//        }
//    }
//
//    fun takeDamage(amount: Double) {
//        HP -= amount
//    }
//
//    fun gainHP(amount: Double) {
//        HP += amount
//        if (HP >= MAX_HP) {
//            HP = MAX_HP
//        }
//    }
//
//    fun attack(target: Character): Double {
//        var damage = 0.0
//        val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
//        if (Atk + rdm - target.Def >= 0) {
//            damage = Atk + rdm - target.Def
//        }
//        target.takeDamage(damage)
//        return damage
//    }
//
//    fun MPDamage(amount: Int): Double {
//        MP -= amount.toDouble()
//        return MP
//    }
//
//    //    public double castMagic(Character target)throws MpNotEnoughException{
//    //        this.MPDamage(3);
//    //        if(this.MP <= 0){
//    //            throw new MpNotEnoughException(user);
//    //        }
//    //        int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
//    //        double damage = 0;
//    //        if(MgAtk + rdm - target.MgDef >= 0){damage = MgAtk + rdm - target.MgDef;}
//    //        target.takeDamage(damage);
//    //        return damage;
//    //    }
//    fun gainEXP(amount: Int) {
//        exp += amount
//
//        //check levelling up
//        while (true) { //multiple checking enable levelling up more than 1 time
//            if (exp <= EXP_MILESTONE[level + 1]) {
//                break
//            } else {
//                level += 1
//                when (CharClass) {
//                    "Warrior" -> {
//                        Atk += ThreadLocalRandom.current().nextInt(5, 9).toDouble()
//                        Def += ThreadLocalRandom.current().nextInt(1, 3).toDouble()
//                        MgDef += ThreadLocalRandom.current().nextInt(0, 2).toDouble()
//                        MAX_HP += ThreadLocalRandom.current().nextInt(22, 33).toDouble()
//                        MAX_MP += ThreadLocalRandom.current().nextInt(2, 3).toDouble()
//                        HP = MAX_HP
//                        MP = MAX_MP
//                    }
//                    "Cleric" -> {
//                        Atk += ThreadLocalRandom.current().nextInt(0, 2).toDouble()
//                        Def += ThreadLocalRandom.current().nextInt(0, 2).toDouble()
//                        MgAtk += ThreadLocalRandom.current().nextInt(5, 8).toDouble()
//                        MgDef += ThreadLocalRandom.current().nextInt(1, 3).toDouble()
//                        MAX_HP += ThreadLocalRandom.current().nextInt(12, 26).toDouble()
//                        MAX_MP += ThreadLocalRandom.current().nextInt(5, 7).toDouble()
//                        HP = MAX_HP
//                        MP = MAX_MP
//                    }
//                    "Mage" -> {
//                        Atk += ThreadLocalRandom.current().nextInt(0, 2).toDouble()
//                        Def += ThreadLocalRandom.current().nextInt(0, 1).toDouble()
//                        MgAtk += ThreadLocalRandom.current().nextInt(5, 8).toDouble()
//                        MgDef += ThreadLocalRandom.current().nextInt(0, 2).toDouble()
//                        MAX_HP += ThreadLocalRandom.current().nextInt(12, 18).toDouble()
//                        MAX_MP += ThreadLocalRandom.current().nextInt(4, 9).toDouble()
//                        HP = MAX_HP
//                        MP = MAX_MP
//                    }
//                    "Ranger" -> {
//                        Atk += ThreadLocalRandom.current().nextInt(6, 10).toDouble()
//                        Def += ThreadLocalRandom.current().nextInt(1, 2).toDouble()
//                        MgDef += ThreadLocalRandom.current().nextInt(0, 1).toDouble()
//                        MAX_HP += ThreadLocalRandom.current().nextInt(15, 25).toDouble()
//                        MAX_MP += ThreadLocalRandom.current().nextInt(3, 6).toDouble()
//                        HP = MAX_HP
//                        MP = MAX_MP
//                    }
//                    "Paladin" -> {
//                        Atk += ThreadLocalRandom.current().nextInt(3, 7).toDouble()
//                        Def += ThreadLocalRandom.current().nextInt(2, 5).toDouble()
//                        MgAtk += ThreadLocalRandom.current().nextInt(1, 3).toDouble()
//                        MgDef += ThreadLocalRandom.current().nextInt(1, 3).toDouble()
//                        MAX_HP += ThreadLocalRandom.current().nextInt(28, 38).toDouble()
//                        MAX_MP += ThreadLocalRandom.current().nextInt(5, 8).toDouble()
//                        HP = MAX_HP
//                        MP = MAX_MP
//                        exp = 0
//                        level = 1
//                    }
//                }
//            }
//        }
//    }
//
//    fun teamUp(tmmt: Character) {
//        teammate.add(tmmt)
//        if (!curEnemy.isEmpty()) {
//            curEnemy[0].curEnemy.remove(this)
//        }
//        if (!tmmt.curEnemy.isEmpty()) {
//            if (curEnemy.isEmpty()) {
//                curEnemy.add(tmmt.curEnemy[0])
//            } else {
//                curEnemy[0] = tmmt.curEnemy[0]
//            }
//            for (i in tmmt.curEnemy[0].curEnemy) {
//                if (i.CharName == CharName) {
//                    break
//                } else if (i === tmmt.curEnemy[0].curEnemy[tmmt.curEnemy[0].curEnemy.size - 1]) {
//                    tmmt.curEnemy[0].curEnemy.add(this)
//                    break
//                }
//            }
//        }
//    }
//
//    override fun toString(): String {
//        return "Character{" +
//                "CharName='" + CharName + '\'' +
//                ", CharClass='" + CharClass + '\'' +
//                ", Atk=" + Atk +
//                ", Def=" + Def +
//                ", MgAtk=" + MgAtk +
//                ", MgDef=" + MgDef +
//                ", MAX_HP=" + MAX_HP +
//                ", MAX_MP=" + MAX_MP +
//                ", HP=" + HP +
//                ", MP=" + MP +
//                ", EXP_MILESTONE=" + Arrays.toString(EXP_MILESTONE) +
//                ", exp=" + exp +
//                ", level=" + level +
//                '}'
//    }
//
//    companion object {
//        var EXP_MILESTONE = intArrayOf(0, 0, 500, 1500, 3000, 5000, 7500, 10500, 14000, 18000, 22500, 27500, 33000, 39000, 45500, 52500, 60000, 68000, 76500, 85500)
//    }
//}