package net.mamoe.mirai.simpleloader;

import java.util.concurrent.ThreadLocalRandom;

public class BattleManager {
    public BattleManager(){}

    public double attack(Character user, Character target){
        double damage = 0;
        int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
        if(user.Atk + rdm - target.Def >= 0){damage = user.Atk + rdm - target.Def;}
        target.takeDamage(damage);
        return damage;
    }

    public double castMagic(Character user, Character target)throws MpNotEnoughException{
        int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
        double damage = 0;
        if(user.MP-3 < 0){
            throw new MpNotEnoughException(user);
        }else{
            user.MPDamage(3);
            if(user.MgAtk + rdm - target.MgDef >= 0){damage = user.MgAtk + rdm - target.MgDef;}
            target.takeDamage(damage);
        }
        return damage;
    }

    public double wolfStep(Character user, Character target)throws ClassMismatchException,MpNotEnoughException{
        if(!user.CharClass.equals("Warrior")){ // check class
            throw new ClassMismatchException(user,"狼箭步");
        }else{
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;

            if(user.MP-3 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(3);
                if(user.Atk*1.5 + rdm - target.Def >= 0){damage = user.Atk*1.5 + rdm - target.Def;}
                target.takeDamage(damage);
            }
            return damage;
        }
    }

    public double warmFire(Character user, Character target)throws ClassMismatchException,MpNotEnoughException{
        if(!user.CharClass.equals("Cleric")){ // check class
            throw new ClassMismatchException(user,"温暖的火");
        }else{
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double heal;
            if(user.MP-5 < 0){
                throw new MpNotEnoughException(user);
            }else{
                if(target.HP+user.MgAtk*1.3+rdm <= target.MAX_HP){
                    user.MPDamage(5);
                    heal = user.MgAtk*1.3+rdm;
                    target.gainHP(heal);
                }else{
                    heal = target.MAX_HP - target.HP;
                    target.HP = target.MAX_HP;
                }
            }
            return heal;
        }
    }

    public double sniper(Character user, Character target)throws ClassMismatchException,MpNotEnoughException{
        if(!user.CharClass.equals("Ranger")){ // check class
            throw new ClassMismatchException(user,"狙击");
        }else{
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;
            if(user.MP-5 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(5);
                if(user.Atk*1.7 + rdm - target.Def >= 0){damage = user.Atk*1.7 + rdm - target.Def;}
                target.takeDamage(damage);
            }
            return damage;
        }
    }

    public double soulRapid(Character user, Character target)throws ClassMismatchException,MpNotEnoughException{
        if(!user.CharClass.equals("Mage")){ // check class
            throw new ClassMismatchException(user,"灵魂激流");
        }else{
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;
            if(user.MP-8 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(8);
                if(user.MgAtk*3 + rdm - target.MgDef >= 0){damage = user.MgAtk*3 + rdm - target.MgDef;}
                target.takeDamage(damage);
            }
            return damage;
        }
    }

    public double[] anShouLi(Character user)throws ClassMismatchException,MpNotEnoughException{
        double TeammateAmount = 0.0;
        double anshouliAmmt = 0.0;
        if(!user.CharClass.equals("Paladin")){
            throw new ClassMismatchException(user,"按手礼");
        }else{
            if(user.MP - 5 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(5);
                try{
                BuffManagerKt.equipBuff(user,"anShouLi",user);
                anshouliAmmt = BuffManagerKt.loadBuff(user,"anShouLi");}catch(SameBuffNameException e){System.out.println("SameBuffDetected");}
                for(Character t:user.teammate){
                    if(!t.curEnemy.isEmpty()){
                        try {
                            BuffManagerKt.equipBuff(user, "anShouLiTeammate", t);
                            TeammateAmount = BuffManagerKt.loadBuff(t, "anShouLiTeammate");
                        }catch(SameBuffNameException e){System.out.println("SameBuffDetected");}
                    }
                }
                return new double[]{anshouliAmmt,TeammateAmount};


            }
        }
    }

    public double bathHolySpring(Character user, Character target) throws ClassMismatchException, MpNotEnoughException, RequirementNotReachException {
        if(!user.CharClass.equals("Cleric")){ // check class
            throw new ClassMismatchException(user,"沐浴圣泉");
        }else{
            if(!user.equiptment.EngName.equals("SilverHolyGrail")){
                throw new RequirementNotReachException();
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double heal;
            if(user.MP-8 < 0){
                throw new MpNotEnoughException(user);
            }else{
                if(target.HP+user.MgAtk*2.2+rdm <= target.MAX_HP){
                    user.MPDamage(8);
                    heal = user.MgAtk*2.2+rdm;
                    target.gainHP(heal);
                }else{
                    heal = target.MAX_HP - target.HP;
                    target.HP = target.MAX_HP;
                }
            }
            return heal;
        }
    }

    public double goldMoonOde(Character user, Character target) throws ClassMismatchException, MpNotEnoughException, RequirementNotReachException {
        double efct = 0.0;
        if(!user.CharClass.equals("Cleric")){ // check class
            throw new ClassMismatchException(user,"金月颂诗");
        }else{
            if(!user.equiptment.EngName.equals("RyantarOdes")){
                throw new RequirementNotReachException();
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            if(user.MP-8 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(8);
                try {
                    BuffManagerKt.equipBuff(user, "goldMoonOde", target);
                    efct = BuffManagerKt.loadBuff(target, "goldMoonOde");
                }catch (SameBuffNameException e){}
            }
            return efct;
        }
    }

    public double silverStarOde(Character user, Character target) throws ClassMismatchException, MpNotEnoughException, RequirementNotReachException {
        double efct = 0.0;
        if(!user.CharClass.equals("Cleric")){ // check class
            throw new ClassMismatchException(user,"银星颂诗");
        }else{
            if(!user.equiptment.EngName.equals("RyantarOdes")){
                throw new RequirementNotReachException();
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            if(user.MP-8 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(8);
                try {
                    BuffManagerKt.equipBuff(user, "silverStarOde", target);
                    efct = BuffManagerKt.loadBuff(target, "silverStarOde");
                }catch (SameBuffNameException e){}
            }
            return efct;
        }
    }

    public double holyBloodCastBlade(Character user) throws ClassMismatchException, MpNotEnoughException, RequirementNotReachException {
        double efct = 0.0;
        if(!user.CharClass.equals("Paladin")){ // check class
            throw new ClassMismatchException(user,"圣血铸锋");
        }else{
            if(!user.equiptment.EngName.equals("SacrificeSword")){
                throw new RequirementNotReachException();
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            if(user.HP - user.MAX_HP*0.2 < 0){
                throw new RequirementNotReachException();
            }else{
                try{
                user.HP -= user.MAX_HP*0.2;
                BuffManagerKt.equipBuff(user,"holyBloodCastBlade",user);
                efct = BuffManagerKt.loadBuff(user,"holyBloodCastBlade");}catch(SameBuffNameException e){}
            }
            return efct;
        }
    }

    public double flameAngelsAnger(Character user, Character target)throws ClassMismatchException,MpNotEnoughException,RequirementNotReachException{
        if(!user.CharClass.equals("Paladin")){ // check class
            throw new ClassMismatchException(user,"焰天使之怒");
        }else{
            if(!user.equiptment.EngName.equals("SacrificeSword")){
                throw new RequirementNotReachException();
            }else{
                if(user.buff.isEmpty()){throw new RequirementNotReachException();}else{
                    for(Buff i:user.buff){
                        if (i.getEngBuffName().equals("holyBloodCastBlade")) {
                            break;
                        }else if(i == user.buff.get(user.buff.size()-1)){throw new RequirementNotReachException();}
                        }
                }
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;
            if(user.HP - user.MAX_HP*0.2 < 1){
                throw new MpNotEnoughException(user);
            }else{
                user.HP -= user.MAX_HP*0.2;
                if(user.Atk*2.4 + rdm - target.Def >= 0){damage = user.Atk*2.4 + rdm - target.Def;}
                target.takeDamage(damage);
            }
            return damage;
        }
    }

    public double[] martyrsLegacy(Character user, Character target) throws ClassMismatchException, MpNotEnoughException, RequirementNotReachException {
        double heal = 0;
        double efct = 0.0;
        if(!user.CharClass.equals("Paladin")){ // check class
            throw new ClassMismatchException(user,"殉道者的遗赠");
        }else{
            if(!user.equiptment.EngName.equals("RolandsBone")){
                throw new RequirementNotReachException();
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            if(user.MP - 5 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(5);
                if(target.HP+user.MgAtk*1.5+rdm <= target.MAX_HP){
                    heal = user.MgAtk*1.5+rdm;
                    target.gainHP(heal);
                }else{
                    heal = target.MAX_HP - target.HP;
                    target.HP = target.MAX_HP;
                }
                try{
                BuffManagerKt.equipBuff(user,"martyrsLegacy",target);
                efct = BuffManagerKt.loadBuff(target,"martyrsLegacy");}catch (SameBuffNameException e){}
            }
            return new double[]{efct,heal};
        }
    }

    public double fairySwordArt(Character user, Character target)throws ClassMismatchException,MpNotEnoughException,RequirementNotReachException{
        if(!user.CharClass.equals("Warrior")){ // check class
            throw new ClassMismatchException(user,"妖精剑术");
        }else{
            if(!user.equiptment.EngName.equals("RyantarKnightSword")){
                throw new RequirementNotReachException();
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;
            if(user.MP-3 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(3);
                if(user.Atk*1.7 + rdm - target.Def >= 0){damage = user.Atk*1.7 + rdm - target.Def;}
                target.takeDamage(damage);
            }
            return damage;
        }
    }

    public double fairyBlood(Character user) throws ClassMismatchException, MpNotEnoughException, RequirementNotReachException {
        if(!user.CharClass.equals("Warrior")){ // check class
            throw new ClassMismatchException(user,"妖精之血");
        }else{
            if(!user.equiptment.EngName.equals("RyantarKnightArmor")){
                throw new RequirementNotReachException();
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            if(user.MP - 6 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(6);
                try{
                BuffManagerKt.equipBuff(user,"fairyBlood",user);}catch(SameBuffNameException e){return 0.0;}
            }
            return BuffManagerKt.loadBuff(user,"fairyBlood");
        }
    }

    public double mithrilArrow(Character user, Character target)throws ClassMismatchException,MpNotEnoughException,RequirementNotReachException{
        if(!user.CharClass.equals("Ranger")){ // check class
            throw new ClassMismatchException(user,"秘银箭");
        }else{
            if(!user.equiptment.EngName.equals("MithrilBow")){
                throw new RequirementNotReachException();
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;
            if(user.MP-5 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(5);
                if(user.Atk*2.5 + rdm - target.Def >= 0){damage = user.Atk*2.5 + rdm - target.Def;}
                target.takeDamage(damage);
            }
            return damage;
        }
    }

    public double miZongShu(Character user, Character target) throws ClassMismatchException, MpNotEnoughException, RequirementNotReachException {
        double efct = 0.0;
        if(!user.CharClass.equals("Ranger")){ // check class
            throw new ClassMismatchException(user,"迷踪术");
        }else{
            if(!user.equiptment.EngName.equals("GreyFogCloak")){
                throw new RequirementNotReachException();
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            if(user.MP-5 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(5);
                try {
                    BuffManagerKt.equipBuff(user, "miZongShu", target);
                    efct = BuffManagerKt.loadBuff(target, "miZongShu");
                }catch (SameBuffNameException e){}
            }
            return efct;
        }
    }

    public double ancientSacrifice(Character user) throws ClassMismatchException, MpNotEnoughException, RequirementNotReachException {
        double efct = 0.0;
        if(!user.CharClass.equals("Mage")){ // check class
            throw new ClassMismatchException(user,"古老献祭");
        }else{
            if(!user.equiptment.EngName.equals("FairySpellBook")){
                throw new RequirementNotReachException();
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            if(user.HP - user.MAX_HP*0.3 < 0){
                throw new RequirementNotReachException();
            }else{
                user.HP -= user.MAX_HP*0.3;
                try {
                    BuffManagerKt.equipBuff(user, "ancientSacrifice", user);
                    efct = BuffManagerKt.loadBuff(user, "ancientSacrifice");
                }catch (SameBuffNameException e){}
            }
            return efct;
        }
    }

    public double jingBiShu(Character user, Character target) throws ClassMismatchException, MpNotEnoughException, RequirementNotReachException {
        double efct = 0.0;
        if(!user.CharClass.equals("Mage")){ // check class
            throw new ClassMismatchException(user,"晶壁术");
        }else{
            if(!user.equiptment.EngName.equals("BlueTearRing")){
                throw new RequirementNotReachException();
            }
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            if(user.MP-10 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(10);
                try {
                    BuffManagerKt.equipBuff(user, "jingBiShu", target);
                    efct = BuffManagerKt.loadBuff(target, "jingBiShu");
                }catch (SameBuffNameException e){}
            }
            return efct;
        }
    }

    // 怪物技能
    public double dazzlingCharm(Character user,Character target)throws ClassMismatchException,MpNotEnoughException{
        if(!user.CharClass.equals("Nymph")){ // check class
            throw new ClassMismatchException(user,"夺目绝色");
        }else{
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;
            if(user.MP-30 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(30);
                if(user.MgAtk*3 + rdm - target.MgDef >= 0){damage = user.MgAtk*3 + rdm - target.MgDef;}
                target.takeDamage(damage);
            }
            return damage;
        }
    }

    public double breath(Character user,Character target)throws ClassMismatchException,MpNotEnoughException{
        if(!user.CharClass.equals("WinterWolf") && !user.CharClass.equals("Dragon")){ // check class
            throw new ClassMismatchException(user,"吐息");
        }else{
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;
            if(user.MP-5 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(5);
                if(user.MgAtk*4 + rdm - target.MgDef >= 0){damage = user.MgAtk*4 + rdm - target.MgDef;}
                target.takeDamage(damage);
            }
            return damage;
        }
    }

    public double frostFang(Character user,Character target)throws ClassMismatchException,MpNotEnoughException{
        if(!user.CharClass.equals("WinterWolf")){ // check class
            throw new ClassMismatchException(user,"霜之牙");
        }else{
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;
            if(user.MP-5 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(5);
                if(user.Atk*1.7 + rdm - target.Def >= 0){damage = user.Atk*1.7 + rdm - target.Def;}
                target.takeDamage(damage);
            }
            return damage;
        }
    }

    public double blackMissa(Character user,Character target)throws ClassMismatchException,MpNotEnoughException,NoTargetBuffException{
        if(!user.CharClass.equals("SilentPhantom")){ // check class
            throw new ClassMismatchException(user,"黑弥撒");
        }else{
            double damage = 0;
            for(Buff i: user.buff){
                if(i.getBuffName().equals("PassedSpirit")){
                    int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
                    if(user.MP-30 < 0){
                        throw new MpNotEnoughException(user);
                    }else{
                        user.MPDamage(30);
                        damage = target.MAX_HP;
                        target.takeDamage(damage);
                    }
                }else if(i == user.buff.get(user.buff.size()-1)){
                    throw new NoTargetBuffException();
                }
            }
            return damage;
        }
    }

    public double soulLance(Character user,Character target)throws ClassMismatchException,MpNotEnoughException{
        if(!user.CharClass.equals("SilentPhantom")){ // check class
            throw new ClassMismatchException(user,"灵魂枪");
        }else{
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;
            if(user.MP-30 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(30);
                if(user.MgAtk*1.7 + rdm - target.MgDef >= 0){damage = user.MgAtk*1.7 + rdm - target.MgDef;}
                target.takeDamage(damage);
            }
            return damage;
        }
    }

    public double ryantarHallucinatoryTerrain(Character user,Character target)throws ClassMismatchException,MpNotEnoughException{
        if(!user.CharClass.equals("CloudFairy")){ // check class
            throw new ClassMismatchException(user,"莱恩塔幻景术");
        }else{
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;
            if(user.MP-0 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(0);
                target.hasAttacked = true;
            }
            return damage;
        }
    }

    public double bite(Character user,Character target)throws ClassMismatchException,MpNotEnoughException{
        if(!user.CharClass.equals("WereWolf")){ // check class
            throw new ClassMismatchException(user,"撕咬");
        }else{
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            double damage = 0;
            if(user.MP-20 < 0){
                throw new MpNotEnoughException(user);
            }else{
                user.MPDamage(20);
                if(user.Atk*1.7 + rdm - target.Def >= 0){damage = user.Atk*1.7 + rdm - target.Def;}
                target.takeDamage(damage);
                user.HP += damage;
                if(user.HP>=user.MAX_HP){user.HP = user.MAX_HP;}
            }
            return damage;
        }
    }
}
