package net.mamoe.mirai.simpleloader

import java.util.concurrent.ThreadLocalRandom

@Throws(SameBuffNameException::class)
fun equipBuff(source:Character,buffname:String,target:Character){
    if(!target.curEnemy.isEmpty()){
        var buff:Buff
        when(buffname){
            "anShouLi" -> {
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == "按手礼"){
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            val rdm = ThreadLocalRandom.current().nextInt(0, 2)
                            buff = Buff("按手礼",rdm,3,source)
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    val rdm = ThreadLocalRandom.current().nextInt(0, 2)
                    buff = Buff("按手礼",rdm,3,source)
                    target.buff.add(buff)
                }
            }

            "anShouLiTeammate" -> {
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == "队友按手礼"){
                            println("SameBuffDetected,sending exception...")
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            val rdm = ThreadLocalRandom.current().nextInt(0, 2)
                            buff = Buff("队友按手礼",0,3,source)
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    val rdm = ThreadLocalRandom.current().nextInt(0, 2)
                    buff = Buff("队友按手礼",0,3,source)
                    target.buff.add(buff)
                }
            }

            "goldMoonOde" ->{
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == "金月颂诗"){
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                            buff = Buff("金月颂诗",rdm,3,source)
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                    buff = Buff("金月颂诗",rdm,3,source)
                    target.buff.add(buff)
                }
            }

            "silverStarOde" ->{
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == "银星颂诗"){
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                            buff = Buff("银星颂诗",rdm,3,source)
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                    buff = Buff("银星颂诗",rdm,3,source)
                    target.buff.add(buff)
                }
            }

            "holyBloodCastBlade" ->{
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == "圣血铸锋"){
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                            buff = Buff("圣血铸锋",rdm,3,source,"holyBloodCastBlade")
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                    buff = Buff("圣血铸锋",rdm,3,source,"holyBloodCastBlade")
                    target.buff.add(buff)
                }
            }

            "martyrsLegacy" ->{
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == "殉道者的遗赠"){
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                            buff = Buff("殉道者的遗赠",rdm,3,source)
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                    buff = Buff("殉道者的遗赠",rdm,3,source)
                    target.buff.add(buff)
                }
            }

            "fairyBlood" ->{
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == "妖精之血"){
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                            buff = Buff("妖精之血",rdm,3,source)
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                    buff = Buff("妖精之血",rdm,3,source)
                    target.buff.add(buff)
                }
            }

            "miZongShu" ->{
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == "迷踪术"){
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                            buff = Buff("迷踪术",rdm,3,source)
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                    buff = Buff("迷踪术",rdm,3,source)
                    target.buff.add(buff)
                }
            }

            "ancientSacrifice" -> {
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == "古老献祭"){
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                            buff = Buff("古老献祭",rdm,3,source)
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                    buff = Buff("古老献祭",rdm,3,source)
                    target.buff.add(buff)
                }
            }

            "jingBiShu" ->{
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == "晶壁术"){
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                            buff = Buff("晶壁术",rdm,3,source)
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    val rdm = ThreadLocalRandom.current().nextInt(-5, 5)
                    buff = Buff("晶壁术",rdm,3,source)
                    target.buff.add(buff)
                }
            }


            "HalfFairyBloodline" ->{
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == buffname){
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            buff = Buff("HalfFairyBloodline",0,1000,source)
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    buff = Buff("HalfFairyBloodline",0,1000,source)
                    target.buff.add(buff)
                }
            }

            "FairyBloodline" -> {
                if(!target.buff.isEmpty()){
                    for(tgbf in target.buff){
                        if(tgbf.BuffName == buffname){
                            throw SameBuffNameException(buffname)
                        }else if(tgbf == target.buff.last()){
                            buff = Buff("FairyBloodline",0,1000,source)
                            target.buff.add(buff)
                            break
                        }
                    }
                }else{
                    buff = Buff("FairyBloodline",0,1000,source)
                    target.buff.add(buff)
                }
            }
            "PassedSpirit"->{target.buff.add(Buff("PassedSpirit",0,1000,source))}
        }
    }
}

fun unequipBuff(user:Character,buffname: String){
    if(!user.buff.isEmpty())
        for(b in user.buff){
            if(b.BuffName == buffname){
                when(b.BuffName){
                    "按手礼" -> {
                        user.Def -= b.BuffAmount
                        user.MgDef -= b.BuffAmount

                    }
                    "队友按手礼" -> {
                        user.Atk -= b.BuffAmount
                        println("decrease amount:"+b.BuffAmount)
                    }
                    "金月颂诗" -> {
                        user.Atk -= b.BuffAmount
                    }
                    "银星颂诗" -> {
                        user.MgAtk -= b.BuffAmount
                    }
                    "圣血铸锋" -> {
                        user.Atk -= b.BuffAmount
                    }
                    "殉道者的遗赠" -> {
                        user.Def -= b.BuffAmount
                        user.MgDef -= b.BuffAmount
                    }
                    "妖精之血" -> {
                        user.Def -= b.BuffAmount
                        user.MgDef -= b.BuffAmount
                        user.getATKCOE -= 100
                    }
                    "迷踪术" -> {
                        user.getATKCOE += 50
                    }
                    "古老献祭" -> {
                        user.MgAtk -= b.BuffAmount
                        user.getATKCOE -= 30
                    }
                    "晶壁术" -> {
                        user.Def -= b.BuffAmount
                        user.MgDef -= b.BuffAmount
                    }
                    "HalfFairyBloodline" -> {
                        user.Def -= b.BuffAmount
                    }
                    "FairyBloodline" -> {
                        user.Def -= b.BuffAmount
                        user.MgDef -= b.BuffAmount
                    }
                    "PassedSpirit" -> {

                    }
                }
                user.buff.remove(b)
                break;
            }
        }

    user.BuffTime = 0
}

fun loadBuff(user:Character,buffname: String):Double{
    var BuffAmount = 0.0
    var TranslatedName:String = ""

    //translate buffname
    when(buffname){
        "anShouLi" -> TranslatedName = "按手礼"
        "anShouLiTeammate" -> TranslatedName = "队友按手礼"
        "goldMoonOde" -> TranslatedName = "金月颂诗"
        "silverStarOde" -> TranslatedName = "银星颂诗"
        "holyBloodCastBlade" -> TranslatedName = "圣血铸锋"
        "martyrsLegacy" -> TranslatedName = "殉道者的遗赠"
        "fairyBlood" -> TranslatedName = "妖精之血"
        "miZongShu" -> TranslatedName = "迷踪术"
        "ancientSacrifice" -> TranslatedName = "古老献祭"
        "jingBiShu" -> TranslatedName = "晶壁术"
        "HalfFairyBloodline" -> TranslatedName = "HalfFairyBloodline"
        "FairyBloodline" -> TranslatedName = "FairyBloodline"
        "PassedSpirit" -> TranslatedName = "Passed Spirit"
    }

    for(b in user.buff){
        if(b.BuffName == TranslatedName){
            when(b.BuffName){
                "按手礼" -> {
                    b.BuffAmount = b.BuffSource.MgAtk*0.5+b.rdm;
                    user.Def += b.BuffAmount
                    user.MgDef += b.BuffAmount
                }
                "队友按手礼" -> {
                    b.BuffAmount = b.BuffSource.MgAtk*0.8+b.rdm
                    user.Atk += b.BuffAmount
                    println("increase amount:"+b.BuffAmount)
                }
                "金月颂诗" -> {
                    b.BuffAmount = b.BuffSource.MgAtk*1.0+b.rdm
                    user.Atk += b.BuffAmount
                }
                "银星颂诗" -> {
                    b.BuffAmount = b.BuffSource.MgAtk*1.0+b.rdm
                    user.MgAtk += b.BuffAmount
                }
                "圣血铸锋" -> {
                    b.BuffAmount = b.BuffSource.MAX_HP*0.2+b.rdm
                    user.Atk += b.BuffAmount
                }
                "殉道者的遗赠" ->{
                    b.BuffAmount = b.BuffSource.MgAtk*0.7+b.rdm;
                    user.Def += b.BuffAmount
                    user.MgDef += b.BuffAmount
                }
                "妖精之血" -> {
                    b.BuffAmount = b.BuffSource.Atk*0.4+b.rdm
                    user.Def += b.BuffAmount
                    user.MgDef += b.BuffAmount
                    user.getATKCOE += 100
                }
                "迷踪术" -> {
                    user.getATKCOE -= 50
                }
                "古老献祭" -> {
                    b.BuffAmount = b.BuffSource.MAX_MP*0.8
                    user.MgAtk += b.BuffAmount
                    user.getATKCOE += 30
                }
                "晶壁术" -> {
                    b.BuffAmount = b.BuffSource.MgAtk*0.4+b.rdm
                    user.Def += b.BuffAmount
                    user.MgDef += b.BuffAmount
                }
                "HalfFairyBloodline" -> {
                    b.BuffAmount = 160.0
                    user.Def += b.BuffAmount
                }
                "FairyBloodline" -> {
                    b.BuffAmount = 160.0
                    user.MgDef += b.BuffAmount
                    user.Def += b.BuffAmount
                }
                "PassedSpirit" ->{}
            }
            BuffAmount = b.BuffAmount
            break
        }
    }

    return BuffAmount
}

fun translateBuff(buffname: String):String{
    var TranslatedName = ""
    when(buffname){
        "anShouLi" -> TranslatedName = "按手礼"
        "anShouLiTeammate" -> TranslatedName = "队友按手礼"
        "HalfFairyBloodline" -> TranslatedName = "HalfFairyBloodline"
        "FairyBloodline" -> TranslatedName = "FairyBloodline"
        "PassedSpirit" -> TranslatedName = "Passed Spirit"
    }
    return TranslatedName
}