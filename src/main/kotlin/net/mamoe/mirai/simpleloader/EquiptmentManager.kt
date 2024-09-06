package net.mamoe.mirai.simpleloader

fun loadEquiptment(user: Character) {
    if (user.equiptment != null) {
        val equiptment = user.equiptment
        when (equiptment.name) {
            "魔法长剑" -> user.Atk += 20.0
            "橡木法杖" -> user.MgAtk += 20.0
            "精灵之弓" -> user.Atk += 30.0
            "精灵链甲" -> { user.Def += 20.0;user.MgDef += 20.0 }
            "银圣杯" -> {user.MgAtk += 40;user.MAX_HP += 50}
            "莱恩塔颂诗集" -> {user.MgAtk += 30;user.Def += 30;user.MAX_HP += 70;}
            "牺牲之剑" -> {user.Atk += 60;user.Def -= 10;user.MgDef -= 10;user.getATKCOE -= 10;}
            "罗兰的圣骸" -> {user.MgDef += 30;user.Def += 30;user.MgAtk += 20;user.MAX_HP += 100;}
            "莱恩塔骑士剑" -> {user.Atk += 40;user.Def += 25;user.MgDef += 20;user.MAX_HP += 50;}
            "莱恩塔骑士甲" -> {user.MgDef += 40;user.Def += 40;user.MAX_HP += 90;}
            "秘银弓" -> {user.Atk += 50;user.Def += 10;user.MAX_HP += 40;}
            "灰雾斗篷" -> {user.Atk += 30;user.Def += 30;user.MgDef += 10;user.MAX_HP += 40}
            "妖精的法术书" -> {user.MgAtk += 45;user.Def += 10;user.MAX_HP += 40;}
            "蓝泪石戒指" -> {user.MgAtk += 30;user.Def += 10;user.MgDef += 30;user.MAX_HP += 40}
        }
    }
}

fun unequip(user: Character) {
    if (user.equiptment != null) {
        val equiptment = user.equiptment
        when (equiptment.name) {
            "魔法长剑" -> user.Atk -= 20.0
            "橡木法杖" -> user.MgAtk -= 20.0
            "精灵之弓" -> user.Atk -= 30.0
            "精灵链甲" -> { user.Def -= 20.0;user.MgDef -= 20.0 }
            "银圣杯" -> {user.MgAtk -= 40;user.MAX_HP -= 50}
            "莱恩塔颂诗集" -> {user.MgAtk -= 30;user.Def -= 20;user.MAX_HP -= 50;}
            "牺牲之剑" -> {user.Atk -= 60;user.Def += 10;user.MgDef += 10;user.getATKCOE += 10;}
            "罗兰的圣骸" -> {user.MgDef -= 30;user.Def -= 30;user.MgAtk -= 20;user.MAX_HP -= 100;}
            "莱恩塔骑士剑" -> {user.Atk -= 40;user.Def -= 25;user.MgDef -= 20;user.MAX_HP -= 50;}
            "莱恩塔骑士甲" -> {user.MgDef -= 40;user.Def -= 40;user.MAX_HP -= 90;}
            "秘银弓" -> {user.Atk -= 50;user.Def -= 10;user.MAX_HP -= 40;}
            "灰雾斗篷" -> {user.Atk -= 30;user.Def -= 20;user.MgDef -= 10;user.MAX_HP -= 40}
            "妖精的法术书" -> {user.MgAtk -= 45;user.Def -= 10;user.MAX_HP -= 40;}
            "蓝泪石戒指" -> {user.MgAtk -= 20;user.Def -= 10;user.MgDef -= 20;user.MAX_HP -= 40}
        }
        user.equiptment = null
    }
}

@Throws(ItemNotFoundException::class, ClassMismatchException::class)
fun equip(user: Character, equiptment: String) {
    if (!user.items.isEmpty()) {
        for (i in user.items) {
            if (i.name == equiptment) {
                when (i.name) {
                    "魔法长剑", "精灵链甲" -> if (user.CharClass == "Warrior" || user.CharClass == "Paladin") {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "橡木法杖" -> if (user.CharClass == "Mage" || user.CharClass == "Cleric") {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "精灵之弓" -> if (user.CharClass == "Ranger" ) {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "银圣杯" -> if (user.CharClass == "Cleric" && user.level >= 4) {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "莱恩塔颂诗集" -> if (user.CharClass == "Cleric" && user.level >= 4) {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "牺牲之剑" -> if (user.CharClass == "Paladin" && user.level >= 4) {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "罗兰的圣骸" -> if (user.CharClass == "Paladin" && user.level >= 4) {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "莱恩塔骑士剑" -> if (user.CharClass == "Warrior" && user.level >= 4) {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "莱恩塔骑士甲" -> if (user.CharClass == "Warrior" && user.level >= 4) {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "秘银弓" -> if (user.CharClass == "Ranger" && user.level >= 4) {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "灰雾斗篷" -> if (user.CharClass == "Ranger" && user.level >= 4) {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "妖精的法术书" -> if (user.CharClass == "Mage" && user.level >= 4) {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                    "蓝泪石戒指" -> if (user.CharClass == "Mage" && user.level >= 4) {
                        user.equiptment = i
                    } else {
                        throw ClassMismatchException(user, equiptment)
                    }
                }
                break
            } else if (i === user.items[user.items.size - 1]) {
                throw ItemNotFoundException(equiptment)
            }
        }
    } else {
        throw ItemNotFoundException(equiptment)
    }
}