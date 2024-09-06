package net.mamoe.mirai.simpleloader;

import java.util.concurrent.ThreadLocalRandom;

public class ItemManager {
    public double gainHP(Character target){
        double heal;
        if(target.HP + 35 <= target.MAX_HP){
            heal = 35;
            target.HP += 35;
        }else{
            heal = target.MAX_HP - target.HP;
            target.HP = target.MAX_HP;
        }
        return heal;
    }

    public double gainMP(Character target){
        double heal;
        if(target.MP + 15 <= target.MAX_MP){
            heal = 15;
            target.MP += 15;
        }else{
            heal = target.MAX_MP - target.MP;
            target.MP = target.MAX_MP;
        }
        return heal;
    }

    public double gainHP2(Character target){
        double heal;
        if(target.HP + 100 <= target.MAX_HP){
            heal = 100;
            target.HP += 100;
        }else{
            heal = target.MAX_HP - target.HP;
            target.HP = target.MAX_HP;
        }
        return heal;
    }

    public double gainMP2(Character target){
        double heal;
        if(target.MP + 50 <= target.MAX_MP){
            heal = 50;
            target.MP += 50;
        }else{
            heal = target.MAX_MP - target.MP;
            target.MP = target.MAX_MP;
        }
        return heal;
    }

    public double fireBall(Character user,Character target) throws ClassMismatchException {
        double damage = 0;
        if(user.CharClass.equals("Mage")||user.CharClass.equals("Cleric")){
            int rdm = ThreadLocalRandom.current().nextInt(-5, 5);
            if(50 + rdm - target.MgDef >= 0){damage = 50 + rdm - target.MgDef;}
            target.takeDamage(damage);
        }else{throw new ClassMismatchException(user,"火球术卷轴");}

        return damage;
    }

    public void fairyTear(Character user,Character target) throws WrongTargetException{
        if(!target.CharClass.equals("Nymph") && !target.CharClass.equals("CloudFairy")){
            throw new WrongTargetException();
        }else{
            if(!target.buff.isEmpty()){
                for(Buff i:target.buff){
                    if(i.getBuffName().equals("HalfFairyBloodline") || i.getBuffName().equals("FairyBloodline")){
                        BuffManagerKt.unequipBuff(target,i.getBuffName());
                        break;
                    }else if(i == target.buff.get(target.buff.size()-1)){
                        throw new WrongTargetException();
                    }
                }
            }else{throw new WrongTargetException();}
        }
    }

    public void moonDust(Character user,Character target) throws WrongTargetException {
        if(!target.CharClass.equals("SilentPhantom")){
            throw new WrongTargetException();
        }else{
            if(!target.buff.isEmpty()){
                for(Buff i:target.buff){
                    if(i.getBuffName().equals("PassedSpirit")){
                        BuffManagerKt.unequipBuff(target,i.getBuffName());
                        break;
                    }else if(i == target.buff.get(target.buff.size()-1)){
                        throw new WrongTargetException();
                    }
                }
            }else{throw new WrongTargetException();}
        }
    }



}
