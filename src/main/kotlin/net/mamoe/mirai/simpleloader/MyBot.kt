package net.mamoe.mirai.simpleloader

import kotlinx.coroutines.*
import net.mamoe.mirai.Bot
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.join
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.simpleloader.IOJ.Load
import net.mamoe.mirai.simpleloader.IOJ.Save
import java.io.File
import java.lang.Exception
import java.lang.NullPointerException
import java.lang.Runnable
import java.util.concurrent.ThreadLocalRandom

var chars = mutableListOf<Character>()

class ChildThread: Runnable {
    override fun run() {
        while(true){
            val lastTime:Long = File("C:\\Users\\cheng\\IdeaProjects\\untitled1\\src\\main\\kotlin\\net\\mamoe\\mirai\\simpleloader\\Saving")
                .readLines().joinToString().toLong()
            val curTime:Long = System.nanoTime()
            if(curTime - lastTime >= 86400000000000 ){
                File("C:\\Users\\cheng\\IdeaProjects\\untitled1\\src\\main\\kotlin\\net\\mamoe\\mirai\\simpleloader\\Saving")
                    .printWriter().use{out -> out.println(curTime)}
                for(char in chars){
                    char.hasWorked = false
                }
                Save()
            }

        }

    }
}

fun death(target:Character){
    target.HP = 1.0;
    target.hasAttacked = false
    target.curEnemy[0].curEnemy.remove(target)
    target.curEnemy.clear()
    var RemoveBuff = mutableListOf<Buff>()
    for(i in target.buff){
        RemoveBuff.add(i)
    }
   for(rm in RemoveBuff){
       unequipBuff(target,rm.BuffName)
   }

    Save()
}

fun shop(user:Character,target:String){
    when(target){
        "生命药水" -> {
            if(user.money >= 30){
                user.money -= 30
                val HealthPotion = Item("生命药水","gainHP")
                user.items.add(HealthPotion)
                throw BuySuccessException(HealthPotion)
            }else{
                throw MoneyNotEnoughException(user.money,30)
            }
        }
        "魔法药水" -> {
            if(user.money >= 30){
                user.money -= 30
                val ManaPotion = Item("魔法药水","gainMP")
                user.items.add(ManaPotion)
                throw BuySuccessException(ManaPotion)
            }else{
                throw MoneyNotEnoughException(user.money,30)
            }
        }
        "大瓶生命药水" -> {
            if(user.money >= 200){
                user.money -= 200
                val HealthPotion2 = Item("大瓶生命药水","gainHP2")
                user.items.add(HealthPotion2)
                throw BuySuccessException(HealthPotion2)
            }else{
                throw MoneyNotEnoughException(user.money,200)
            }
        }
        "大瓶魔法药水" -> {
            if(user.money >= 200){
                user.money -= 200
                val ManaPotion2 = Item("大瓶魔法药水","gainMP2")
                user.items.add(ManaPotion2)
                throw BuySuccessException(ManaPotion2)
            }else{
                throw MoneyNotEnoughException(user.money,200)
            }
        }
    }
}

fun city(user:Character,btm:BattleManager,command:String,target:String){
    var efct2: Double
    if(user.curEnemy.isEmpty()){

        var SkillTarget2:Character = Character()
        for(char in chars){
            if(target == char.CharName){
                SkillTarget2 = char
                break
            }else if(target != char.CharName && char == chars.last()){
                SkillTarget2 = user
            }
        }
            when(command){
                "温暖的火" -> {
                    efct2 = btm.warmFire(user,SkillTarget2)
                    throw PartMovedWarmFireException(efct2,SkillTarget2,user)
                }
                "沐浴圣泉" -> {
                    efct2 = btm.bathHolySpring(user,SkillTarget2)
                    throw PartMovedWarmFireException(efct2,SkillTarget2,user)
                }
            }
    }else{
        throw InBattleException()
    }
}

fun battle(user:Character,btm:BattleManager,command:String,target:String){
    var efct: Double = 0.0
    var anShouLiTmmtEfct = 0.0
    var anShouLiList:DoubleArray
    var martyrsLegacyTmmtEfct = 0.0
    var martyrsLegacyList:DoubleArray
    if(!user.curEnemy.isEmpty()){
        if(!user.hasAttacked){
            user.hasAttacked = true

            var SkillTarget:Character = Character()
            for(char in chars){
                if(target == char.CharName){
                    SkillTarget = char
                    break
                }else if(target != char.CharName && char == chars.last()){
                    SkillTarget = user.curEnemy[0]
                }
            }

            //在这里更改玩家行动
            when(command){
                "攻击" -> efct = btm.attack(user,SkillTarget)
                "魔法" -> efct = btm.castMagic(user,SkillTarget)
                "狼箭步" -> efct = btm.wolfStep(user,SkillTarget)
                "狙击" -> efct = btm.sniper(user,SkillTarget)
                "按手礼" -> {
                    anShouLiList = btm.anShouLi(user)
                    efct = anShouLiList[0]
                    anShouLiTmmtEfct = anShouLiList[1]}
                "灵魂激流" -> efct = btm.soulRapid(user,SkillTarget)
                "温暖的火" -> efct = btm.warmFire(user,SkillTarget) // 应该自己选一个目标
                "殉道者的遗赠" ->{
                    martyrsLegacyList = btm.martyrsLegacy(user,SkillTarget)
                    efct = martyrsLegacyList[0]
                    martyrsLegacyTmmtEfct = martyrsLegacyList[1]
                }
                "沐浴圣泉" -> efct = btm.bathHolySpring(user,SkillTarget)
                "金月颂诗"-> efct = btm.goldMoonOde(user,SkillTarget)
                "银星颂诗" -> efct = btm.silverStarOde(user,SkillTarget)
                "圣血铸锋" -> efct = btm.holyBloodCastBlade(user)
                "焰天使之怒" -> efct = btm.flameAngelsAnger(user,SkillTarget)
                "妖精剑术" -> efct = btm.fairySwordArt(user,SkillTarget)
                "妖精之血" -> efct = btm.fairyBlood(user)
                "秘银箭" -> efct = btm.mithrilArrow(user,SkillTarget)
                "迷踪术" -> efct = btm.miZongShu(user,SkillTarget)
                "古老献祭" -> efct = btm.ancientSacrifice(user)
                "晶壁术" -> efct = btm.jingBiShu(user,SkillTarget)
            }

            if(command == "温暖的火" || command == "沐浴圣泉"){
                throw PartMovedWarmFireException(efct,SkillTarget,user)
            }else if(command == "按手礼"){
                throw PartMovedAnShouLiException(efct,anShouLiTmmtEfct,user)
            }else if(command == "金月颂诗"){
                throw GoldMoonOdeSuccess(SkillTarget,efct,user)
            }else if(command == "银星颂诗"){
                throw SilverStarOdeSuccess(SkillTarget,efct,user)
            }else if(command == "圣血铸锋"){
                throw HolyBloodCastBladeSuccess(user,efct)
            }else if(command == "殉道者的遗赠"){
                throw MartyrsLegacySuccess(SkillTarget,efct,martyrsLegacyTmmtEfct,user)
            }else if(command == "妖精之血"){
                throw FairyBloodSuccess(user,efct)
            }else if(command == "迷踪术"){
                throw MiZongShuSuccess(SkillTarget,user)
            }else if(command == "古老献祭"){
                throw AncientSacrificeSuccess(user,efct)
            }else if(command == "晶壁术"){
                throw JingBiShuSuccess(SkillTarget,efct,user)
            }else{
                throw NotAllPlayerMovedException(efct,SkillTarget,user)
            }
        }else{
            throw CantActionException()
            //reply("请等队友都行动完再攻击喔")
        }
    }else{
        throw NoEnemyException()
        //reply("这个角色在城镇中休息哦")
    }
}

fun useItem(user:Character,itm:ItemManager,item:String){
    if(!user.items.isEmpty()){
        for(i in user.items){
            if(item == i.name){
                val effect = i.effect
                val efct:Double
                when(effect){
                    "gainHP" -> {
                        efct = itm.gainHP(user)
                        user.items.remove(i)
                        throw GainHPSuccessException(efct)

                    }
                    "gainMP" -> {
                        efct = itm.gainMP(user)
                        user.items.remove(i)
                        throw GainMPSuccessException(efct)

                    }
                    "gainHP2" -> {
                        efct = itm.gainHP2(user)
                        user.items.remove(i)
                        throw GainHPSuccessException(efct)

                    }
                    "gainMP2" -> {
                        efct = itm.gainMP2(user)
                        user.items.remove(i)
                        throw GainMPSuccessException(efct)

                    }
                    "fireBall" ->{
                        if(!user.curEnemy.isEmpty()){
                            efct = itm.fireBall(user,user.curEnemy[0])
                            user.items.remove(i)
                            throw NotAllPlayerMovedException(efct,user.curEnemy[0],user)
                        }else{
                            throw NoEnemyException()
                        }
                    }
                    "equip" ->{
                        unequip(user)
                        equip(user,item)
                        loadEquiptment(user)
                        throw EquipSuccessException(item)
                    }
                    "fairyTear" ->{
                        if(!user.curEnemy.isEmpty()){
                            user.items.remove(i)
                            itm.fairyTear(user,user.curEnemy[0])
                            throw EnemyWeakenException()
                        }else{
                            throw NoEnemyException()
                        }
                    }
                    "moonDust"->{
                        if(!user.curEnemy.isEmpty()){
                            user.items.remove(i)
                            itm.moonDust(user,user.curEnemy[0])
                            throw EnemyWeakenException()
                        }else{
                            throw NoEnemyException()
                        }
                    }
                }
                break
            }else if(item != i.name && i == user.items.last()){
                throw ItemNotFoundException(item)
            }
        }
    }else{
        throw ItemNotFoundException(item)
    }

}

suspend fun main() {
    //start timing
    val ChildThread1 = ChildThread()
    val t1 = Thread(ChildThread1)
    t1.start()

    //load
    try{val load = Load()
        for(i in load){
            chars.add(i)
        }
        for(char in chars){
            char.teammate.clear()
        }
    }catch(e: NullPointerException){
        chars.clear()
    }

    //start battleManager and itemManager
    val btm = BattleManager()
    val itm = ItemManager()

    val qqId = 1303262589L//Bot的QQ号，需为Long类型，在结尾处添加大写L
    val password = "zcryjsw220010326"//Bot的密码
    val miraiBot = Bot(qqId, password).alsoLogin()//新建Bot并登录
    miraiBot.subscribeMessages {

        startsWith(".创建角色：",removePrefix = true){
            try{
                val index = it.indexOf("#")
                val CharName:String? = if(index==-1) null else it.substring(0,index)
                val CharClass:String? = if(index==-1) null else it.substring(index+1)
                var CharClassEng:String? = null
                when(CharClass){
                    "战士" -> CharClassEng = "Warrior"
                    "牧师" -> CharClassEng = "Cleric"
                    "圣骑士" -> CharClassEng = "Paladin"
                    "游侠" -> CharClassEng = "Ranger"
                    "法师" -> CharClassEng = "Mage"
                }

                for(char in chars){
                    if(CharName == char.CharName){
                        throw DuplicatedNameException()
                    }
                }
                val newChar = Character(CharName,CharClassEng)

                var CharClassOutput: String? = null
                when(newChar.CharClass){
                    "Warrior" -> CharClassOutput = "战士"
                    "Cleric" -> CharClassOutput = "牧师"
                    "Paladin" -> CharClassOutput = "圣骑士"
                    "Ranger" -> CharClassOutput = "游侠"
                    "Mage" -> CharClassOutput = "法师"
                }
                chars.add(newChar)
                Save() // Save in JSON
                reply("$it 创建成功咯")
                reply("角色名字：" + newChar.CharName + "\n" +
                        "角色职业：" + CharClassOutput + "\n" +
                        "等级：" + newChar.level + "\n" +
                        "经验值：" + newChar.exp + "\n" +
                        "攻击力：" + newChar.Atk + "\n" +
                        "防御力：" + newChar.Def + "\n" +
                        "魔法攻击：" + newChar.MgAtk + "\n" +
                        "魔法防御" + newChar.MgDef + "\n" +
                        "HP：" + newChar.HP + "/" + newChar.MAX_HP + "\n" +
                        "MP：" + newChar.MP + "/" + newChar.MAX_MP +  "\n"+
                        "钱包：" + newChar.money)
            }catch (e:DuplicatedNameException){
                reply("已经有同名的角色啦")
            }catch (f:FormatErrorException){
                reply("没有办法创建，请按照”.创建角色：名字#职业“输入")
            }
        }

        startsWith(".角色属性：",removePrefix = true){
            for(char in chars){
                if(it == char.CharName){
                    var CharClassOutput: String? = null
                    when(char.CharClass){
                        "Warrior" -> CharClassOutput = "战士"
                        "Cleric" -> CharClassOutput = "牧师"
                        "Paladin" -> CharClassOutput = "圣骑士"
                        "Ranger" -> CharClassOutput = "游侠"
                        "Mage" -> CharClassOutput = "法师"
                    }
                    reply("角色名字：" + char.CharName + "\n" +
                            "角色职业：" + CharClassOutput + "\n" +
                            "等级：" + char.level + "\n" +
                            "经验值：" + char.exp + "\n" +
                            "攻击力：" + char.Atk + "\n" +
                            "防御力：" + char.Def + "\n" +
                            "魔法攻击：" + char.MgAtk + "\n" +
                            "魔法防御" + char.MgDef + "\n" +
                            "HP：" + char.HP + "/" + char.MAX_HP + "\n" +
                            "MP：" + char.MP + "/" + char.MAX_MP +  "\n" +
                            "钱包：" + char.money)
                    break
                }else if(it != char.CharName && char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }

        startsWith(".删除角色：",removePrefix = true){
            for(char in chars){
                if(char.CharName == it){
                    chars.remove(char)
                    Save()
                    reply("已经删除了 $it 这个角色")
                    break
                }else if(char.CharName != it && char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }

        startsWith(".冒险：",removePrefix = true){
            try{
            for(char in chars){
                if(it == char.CharName){
                    if(char.HP <= 0){
                        reply("你的伤势太严重了，没有办法冒险")
                        break
                    }else{
                        if(char.curEnemy.isEmpty()){
                            var enemyChar = Character()

                            //  calculate average level
                            var avgLvl:Int = 0
                            if(char.teammate.isEmpty()){
                                avgLvl = char.level
                            }else{
                                avgLvl += char.level
                                for(t in char.teammate){
                                    avgLvl += t.level
                                }
                                avgLvl  /= char.teammate.size+1
                            }
                            val rc: RandomCollection<String> = RandomCollection<String>()
                            when(avgLvl){
                                1,2 -> rc.add(45.0, "Slime").add(35.0, "Goblin").add(15.0, "Orc").add(5.0, "Troll")
                                3,4-> rc.add(27.5,"WinterWolf").add(27.5,"Lemure").add(10.0,"Nymph").add(17.5,"Orc").add(17.5,"Troll")
                                5,6->rc.add(17.0,"SilentPhantom").add(17.0,"CloudFairy").add(21.0,"WereWolf").add(10.0,"Nymph").add(17.5,"WinterWolf").add(17.5,"Lemure")
                                else -> throw UpdatingException()
                                    //rc.add(50.0,"JuvenileRedDragon").add(50.0,"FrostGiant")
                            }
                            val enemy = rc.next()
                            val env: RandomCollection<String> = RandomCollection<String>().add(33.0, "森林").add(15.0,"沼泽").add(10.0,"火山").add(20.0,"地下").add(22.0,"荒野")
                            val nxtEnv = env.next()
                            when(enemy){
                                "Slime" -> {
                                    enemyChar = Monsters.createSlime()
                                    enemyChar.CharName = "史莱姆"
                                }
                                "Goblin" -> {
                                    enemyChar = Monsters.createGoblin()
                                    enemyChar.CharName = "哥布林"
                                }
                                "Orc" -> {
                                    enemyChar = Monsters.createOrc()
                                    enemyChar.CharName = "兽人"
                                }
                                "Troll" -> {
                                    enemyChar = Monsters.createTroll()
                                    enemyChar.CharName = "巨魔"
                                }
                                "WinterWolf" -> {
                                    enemyChar = Monsters.createWinterWolf()
                                    enemyChar.CharName = "冬狼"
                                }
                                "Lemure" -> {
                                    enemyChar = Monsters.createLemure()
                                    enemyChar.CharName = "劣魔"
                                }
                                "Nymph" -> {
                                    enemyChar = Monsters.createNymph()
                                    enemyChar.CharName = "水妖精宁芙"
                                }
                                "CloudFairy" -> {
                                    enemyChar = Monsters.createCloudFairy()
                                    enemyChar.CharName = "云妖精"
                                }
                                "WereWolf" -> {
                                    enemyChar = Monsters.createWereWolf()
                                    enemyChar.CharName = "狼人"
                                }
                                "SilentPhantom" -> {
                                    enemyChar = Monsters.createSilentPhantom()
                                    enemyChar.CharName = "无声幻影"
                                }
                                "JuvenileRedDragon" -> {}
                                "FrostGiant" -> {}
                            }
                            char.curEnemy.add(enemyChar)
                            char.curEnemy[0].curEnemy.add(char)
                            if(!char.teammate.isEmpty()){ //不能只有有队友时才加
                                for(i in char.teammate){
                                    enemyChar.curEnemy.add(i)
                                    if(i.curEnemy.isEmpty()){
                                        i.curEnemy.add(enemyChar)
                                    }else{
                                        i.curEnemy.set(0,enemyChar)
                                    }

                                }
                            }

                            //给怪物加buff
                            when(enemyChar.CharName){
                                "水妖精宁芙"->{equipBuff(enemyChar,"HalfFairyBloodline",enemyChar);loadBuff(enemyChar,"HalfFairyBloodline");}
                                "无声幻影"->{equipBuff(enemyChar,"PassedSpirit",enemyChar);loadBuff(enemyChar,"PassedSpirit");}
                                "云妖精"->{equipBuff(enemyChar,"FairyBloodline",enemyChar);loadBuff(enemyChar,"FairyBloodline");}
                            }


                            //increase monster stats when face multiple players
                            enemyChar.Atk *= 1 + 0.2*(enemyChar.curEnemy.size-1)
                            enemyChar.Def *= 1 + 0.1*(enemyChar.curEnemy.size-1)
                            enemyChar.MgAtk *= 1 + 0.2*(enemyChar.curEnemy.size-1)
                            enemyChar.MgDef *= 1 + 0.1*(enemyChar.curEnemy.size-1)
                            enemyChar.MAX_HP *= 1 + 0.4*(enemyChar.curEnemy.size-1)
                            enemyChar.MAX_MP *= 1 + 0.4*(enemyChar.curEnemy.size-1)
                            enemyChar.HP = enemyChar.MAX_HP
                            enemyChar.MP = enemyChar.MAX_MP

                            reply("在"+nxtEnv+"遭遇了"+enemyChar.CharName+"!" + "\n" +
                                    "攻击力：" + enemyChar.Atk + "\n" +
                                    "防御力：" + enemyChar.Def + "\n" +
                                    "魔法攻击：" + enemyChar.MgAtk + "\n" +
                                    "魔法防御" + enemyChar.MgDef + "\n" +
                                    "HP：" + enemyChar.HP + "/" + enemyChar.MAX_HP + "\n" +
                                    "MP：" + enemyChar.MP + "/" + enemyChar.MAX_MP)
                            break
                        }else{
                            reply("这个角色正在战斗中")
                        }

                        break
                    }
                }else if(it != char.CharName && char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }}catch(ud:UpdatingException){reply("这个部分还在更新哦")}
        }

        startsWith(".战斗：",removePrefix = true){
            val CommandIndex = it.indexOf("#")
            val TargetIndex = it.indexOf("%")
            val user:String = if(CommandIndex==-1) "" else it.substring(0,CommandIndex)
            val command:String = if(TargetIndex==-1) it.substring(CommandIndex+1) else it.substring(CommandIndex+1,TargetIndex)
            val target:String = if(TargetIndex==-1) "" else it.substring(TargetIndex+1)
            for(char in chars){
                if(user == char.CharName){
                    try{
                        try{
                            if(command == "使用物品"){
                                useItem(char,itm,target)
                            }else{
                                battle(char,btm,command,target)
                            }
                        }catch(c:CantActionException){
                            reply("请等队友行动完再行动喔")
                        }catch(d:CharacterNotFoundException){
                            battle(char,btm,command,char.curEnemy[0].CharName)
                        }catch(e:ClassMismatchException){
                            reply("${e.user.CharClass}不能使用这个技能")
                            e.user.hasAttacked = false
                        }catch(g:MpNotEnoughException){
                            reply("魔力值不足以释放这个技能。当前魔力值：${g.user.MP}")
                            g.user.hasAttacked = false
                        }catch(h:NoEnemyException){
                            reply("这个角色在城镇中休息哦")
                        }catch(i:NotAllPlayerMovedException){
                            reply("${i.monster.CharName}受到了${i.damage}点伤害")
                            throw MonsterActionException(i.player)
                        }catch(j:PartMovedAnShouLiException){
                            reply("防御力和魔法防御上升了${j.efct}，队友的物理攻击上升了${j.teammateEfct}，持续3回合")
                            throw MonsterActionException(j.player)
                        }catch(k:PartMovedWarmFireException){
                            reply("${k.target.CharName}恢复了${k.efct}点生命值")
                            throw MonsterActionException(k.player)
                        }
                        catch (n:ItemNotFoundException){
                            reply("背包里没有${n.item}")
                        }catch(aa:GainHPSuccessException){
                            reply("恢复了${aa.efct}点生命值")
                        }catch(ab:GainMPSuccessException){
                            reply("恢复了${ab.efct}点魔力值")
                        }catch (ew:EnemyWeakenException){
                            reply("怪物的特殊状态被解除了")
                        }catch(we:WrongTargetException){
                            reply("似乎没有什么效果")
                        }catch(bb:GoldMoonOdeSuccess){
                            reply("${bb.target.CharName}的物理攻击上升了${bb.efct}，持续三回合")
                            throw MonsterActionException(bb.player)
                        }catch(bb:SilverStarOdeSuccess){
                            reply("${bb.target.CharName}的魔法攻击上升了${bb.efct}，持续三回合")
                            throw MonsterActionException(bb.player)
                        }catch(bb:HolyBloodCastBladeSuccess){
                            reply("你的物理攻击上升了${bb.efct}，持续三回合。现在可以使用焰天使之怒了。")
                            throw MonsterActionException(bb.user)
                        }catch(bb:MartyrsLegacySuccess){
                            reply("${bb.target.CharName}的防御力和魔法防御上升了${bb.efct}，持续三回合；目标的生命值恢复了${bb.TmmtEfct}")
                            throw MonsterActionException(bb.player)
                        }catch(bb:FairyBloodSuccess){
                            reply("你的防御力和魔法防御上升了${bb.efct}，持续三回合。你变得易受攻击了")
                            throw MonsterActionException(bb.user)
                        }catch(bb:MiZongShuSuccess){
                            reply("${bb.target.CharName}变得不易受到攻击了，持续三回合")
                            throw MonsterActionException(bb.player)
                        }catch(bb:AncientSacrificeSuccess){
                            reply("你的物理攻击上升了${bb.efct}，持续三回合。你变得易受攻击了")
                            throw MonsterActionException(bb.target)
                        }catch(bb:JingBiShuSuccess){
                            reply("${bb.target.CharName}的防御力和魔法防御上升了${bb.efct}，持续三回合")
                            throw MonsterActionException(bb.player)
                        }catch(bb:RequirementNotReachException){
                            reply("现在没有办法使用这个技能")
                        }
                    }
                    catch(ac:MonsterActionException){
                        if(!ac.player.curEnemy.isEmpty()){
                            val AtkList: RandomCollection<Character> = RandomCollection<Character>()
                            val monster = ac.player.curEnemy[0]
                            for(i in monster.curEnemy){
                                if(!i.hasAttacked){break}
                                else if(i.hasAttacked && i == monster.curEnemy.last()){
                                    //set hasAttacked to false here
                                    for(j in monster.curEnemy){
                                        AtkList.add(j.getATKCOE,j)
                                        j.hasAttacked = false

                                        //removing buff
                                        val RemoveBuff = mutableListOf<Buff>()
                                        for(bf in j.buff){
                                            bf.BuffTime -= 1
                                            if(bf.BuffTime <= 0){
                                                RemoveBuff.add(bf)
                                            }
                                        }
                                        if(!RemoveBuff.isEmpty()){
                                            for(rbf in RemoveBuff){
                                                unequipBuff(j,rbf.BuffName)
                                            }
                                        }
                                    }
                                }
                            }

                            if(monster.HP <= 0){
                                char.hasAttacked = false
                                char.gainEXP(monster.exp)
                                char.money += monster.money
                                var lootOutput = ""
                                if(!monster.items.isEmpty()){
                                    for(loot in monster.items){
                                        char.items.add(loot)
                                        when(loot.name){
                                            "FireBallScroll" -> loot.name = "火球术卷轴"
                                            "HealthPotion" -> loot.name = "生命药水"
                                            "ManaPotion" -> loot.name = "魔法药水"
                                            "MagicSword" -> loot.name = "魔法长剑"
                                            "OakScepter" -> loot.name = "橡木法杖"
                                            "ElfBow" -> loot.name = "精灵之弓"
                                            "ElfChainMail" -> loot.name = "精灵链甲"
                                            "FairyTear" -> loot.name = "妖精之泪"
                                            "MoonDust" -> loot.name = "月之尘"
                                            "SilverHolyGrail" -> loot.name = "银圣杯"
                                            "RyantarOdes" -> loot.name = "莱恩塔颂诗集"
                                            "SacrificeSword" -> loot.name = "牺牲之剑"
                                            "RolandsBone" -> loot.name = "罗兰的圣骸"
                                            "RyantarKnightSword" -> loot.name = "莱恩塔骑士剑"
                                            "RyantarKnightArmor" -> loot.name = "莱恩塔骑士甲"
                                            "MithrilBow" -> loot.name = "秘银弓"
                                            "GreyFogCloak" -> loot.name = "灰雾斗篷"
                                            "FairySpellBook" -> loot.name = "妖精的法术书"
                                            "BlueTearRing" -> loot.name = "蓝泪石戒指"
                                        }
                                        lootOutput += loot.name+"；"
                                    }
                                }
                                char.curEnemy.clear()
                                char.debuff = null
                                var RemoveBuffs = mutableListOf<Buff>()
                                for(bf in char.buff){
                                    RemoveBuffs.add(bf)
                                }
                                for(rm in RemoveBuffs){
                                    unequipBuff(char,rm.BuffName)
                                }
                                char.BuffTime = 0
                                char.DebuffTime = 0
                                for(v in char.teammate){
                                    v.hasAttacked = false
                                    v.gainEXP(monster.exp)
                                    v.money += monster.money
                                    v.curEnemy.clear()
                                    v.debuff = null
                                    RemoveBuffs = mutableListOf()
                                    for(bf in v.buff){
                                        RemoveBuffs.add(bf)
                                    }
                                    for(rm in RemoveBuffs){
                                        unequipBuff(v,rm.BuffName)
                                    }
                                    v.BuffTime = 0
                                    v.DebuffTime = 0
                                }
                                Save()
                                reply("击败了${monster.CharName}，获得了${monster.exp}点经验，和${monster.money}枚金币!")
                                reply("获得了以下物品：${lootOutput}")

                            }
                            else{
                                try{
                                    val mstTgt = AtkList.next()

                                    //在这里更改怪物行动
                                    when(monster.CharName){
                                        //"水妖精宁芙"，"冬狼"之类，各自一种行动方式
                                        //不会放技能的小怪就放进else里
                                        "水妖精宁芙"->{
                                            val mstMove = RandomCollection<Int>().add(60.0,0).add(40.0,1)
                                            val AttackMode = mstMove.next()
                                            when(AttackMode){
                                                0->{
                                                    val playerDmg = btm.attack(monster,mstTgt)
                                                    reply("宁芙对${mstTgt.CharName}的攻击造成了${playerDmg}点伤害")
                                                }
                                                1->{
                                                    val playerDmg = btm.dazzlingCharm(monster,mstTgt)
                                                    reply("宁芙对${mstTgt.CharName}使用了夺目绝色，造成了${playerDmg}点伤害")
                                                }
                                            }
                                        }
                                        "冬狼"->{
                                            val mstMove = RandomCollection<Int>().add(40.0,0).add(30.0,1).add(30.0,2)
                                            val AttackMode = mstMove.next()
                                            when(AttackMode){
                                                0->{
                                                    val playerDmg = btm.attack(monster,mstTgt)
                                                    reply("冬狼对${mstTgt.CharName}的攻击造成了${playerDmg}点伤害")
                                                }
                                                1->{
                                                    val playerDmg = btm.breath(monster,mstTgt)
                                                    reply("冬狼对${mstTgt.CharName}使用了吐息，造成了${playerDmg}点伤害")
                                                }
                                                2->{
                                                    val playerDmg = btm.frostFang(monster,mstTgt)
                                                    reply("冬狼对${mstTgt.CharName}使用了霜之牙，造成了${playerDmg}点伤害")
                                                }
                                            }
                                        }
                                        "无声幻影"->{
                                            var playerDmg:Double = 0.0
                                            try{
                                                playerDmg = btm.blackMissa(monster,mstTgt)
                                                reply("无声幻影对${mstTgt.CharName}使用了黑弥撒，造成了${playerDmg}点伤害")
                                            }catch(nb:NoTargetBuffException){
                                                if(monster.MP>=30){
                                                    playerDmg = btm.soulLance(monster,mstTgt)
                                                    reply("无声幻影对${mstTgt.CharName}使用了灵魂枪，造成了${playerDmg}点伤害")
                                                }else{
                                                    playerDmg = btm.attack(monster,mstTgt)
                                                    reply("无声幻影对${mstTgt.CharName}的攻击造成了${playerDmg}点伤害")
                                                }
                                            }catch(mn:MpNotEnoughException){
                                                playerDmg = btm.attack(monster,mstTgt)
                                                reply("无声幻影对${mstTgt.CharName}的攻击造成了${playerDmg}点伤害")
                                            }
                                        }
                                        "云妖精"->{
                                            var playerDmg:Double = 0.0
                                            try{
                                                btm.ryantarHallucinatoryTerrain(monster,mstTgt)
                                                playerDmg = btm.castMagic(monster,mstTgt)
                                                reply("云妖精对${mstTgt.CharName}施展了魔法，造成了${playerDmg}点伤害。由于莱恩塔幻景术的效果，这个角色这回合不能行动。")
                                            }catch(mn:MpNotEnoughException){
                                                playerDmg = btm.attack(monster,mstTgt)
                                                reply("云妖精对${mstTgt.CharName}的攻击造成了${playerDmg}点伤害。由于莱恩塔幻景术的效果，这个角色这回合不能行动。")
                                            }
                                        }
                                        "狼人"->{
                                            var playerDmg:Double = 0.0
                                            try{
                                                playerDmg = btm.bite(monster,mstTgt)
                                                reply("狼人对${mstTgt.CharName}使用了撕咬，造成了${playerDmg}点伤害并回复了等量的生命值")
                                            }catch(mn:MpNotEnoughException){
                                                playerDmg = btm.attack(monster,mstTgt)
                                                reply("狼人对${mstTgt.CharName}的攻击造成了${playerDmg}点伤害")
                                            }
                                        }
                                        else->{
                                            val playerDmg = btm.attack(monster,mstTgt)
                                            reply("${monster.CharName}对${mstTgt.CharName}的攻击造成了${playerDmg}点伤害。")
                                        }
                                    }
                                    //check for death
                                    if(mstTgt.HP <= 0){
                                        death(mstTgt)
                                        reply("${mstTgt.CharName}被击败了，路过的好心人把${mstTgt.CharName}送回了城镇")

                                    }
                                }catch(np:NullPointerException){}
                            }

                        }
                    }

                    break
                }else if(user != char.CharName && char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }

        startsWith(".城镇：",removePrefix = true){
            val CommandIndex2 = it.indexOf("#")
            val TargetIndex2 = it.indexOf("%")
            val user:String = if(CommandIndex2==-1) "" else it.substring(0,CommandIndex2)
            val command:String = if(TargetIndex2==-1) it.substring(CommandIndex2+1) else it.substring(CommandIndex2+1,TargetIndex2)
            val target:String = if(TargetIndex2==-1) "" else it.substring(TargetIndex2+1)

            for(char in chars){
                if(user == char.CharName) {
                    try {
                        if(command == "使用物品"){
                            useItem(char,itm,target)
                        }else if(command == "商店"){
                            shop(char,target)
                        }else{
                            city(char, btm, command, target)
                        }

                    }catch(e:ClassMismatchException){
                        reply("${e.user.CharClass}不能使用${e.name}")
                    }catch(g:MpNotEnoughException){
                        reply("魔力值不足以释放这个技能。当前魔力值：${g.user.MP}")
                    }catch(k:PartMovedWarmFireException){
                        reply("${k.target.CharName}恢复了${k.efct}点生命值")
                        Save()
                    }catch(a:InBattleException){
                        reply("这个角色正在战斗中")
                    }catch (n:ItemNotFoundException){
                        reply("背包里没有${n.item}")
                    }catch(o:MoneyNotEnoughException){
                        reply("购买需要${o.targetMoney}枚金币，你只有${o.money}枚金币")
                    }catch(aa:GainHPSuccessException){
                        reply("恢复了${aa.efct}点生命值")
                        Save()
                    }catch(ab:GainMPSuccessException){
                        reply("恢复了${ab.efct}点魔力值")
                        Save()
                    }catch(ac:BuySuccessException){
                        reply("成功购买了${ac.item.name}")
                        Save()
                    }
                    break
                }else if(user != char.CharName && char == chars.last()){
                    reply("没有找到这个角色哦")
                }
             }
        }

        startsWith(".逃跑：",removePrefix = true){
            for(char in chars){
                if(it == char.CharName){
                    if(!char.curEnemy.isEmpty()){
                        char.hasAttacked = false
                        char.curEnemy[0].curEnemy.remove(char)
                        char.curEnemy.clear()
                        char.debuff = null
                        var RemoveBuff = mutableListOf<Buff>()
                        for(bf in char.buff){
                            RemoveBuff.add(bf)
                        }
                        for(rm in RemoveBuff){
                            unequipBuff(char,rm.BuffName)
                        }
                        char.BuffTime = 0
                        char.DebuffTime = 0
                        reply("你逃回了城镇！")
                        Save()
                    }else{
                        reply("这个角色在城镇中休息哦")
                    }
                    break
                }else if(it != char.CharName && char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
    }

        startsWith(".打工：",removePrefix = true){
            for(char in chars){
                if(it == char.CharName){
                    if(char.curEnemy.isEmpty()){
                        if(!char.hasWorked){
                            val gain: Int = ThreadLocalRandom.current().nextInt(1, 26)*char.level
                            char.money += gain
                            char.hasWorked = true
                            reply("经过一段时间的打工，你得到了" + gain + "枚金币！")
                            Save()
                        }else{
                            reply("一天只能打一次工哦")
                        }
                    }else{
                        reply("这个角色正在战斗中")
                    }
                    break
                }else if(it != char.CharName && char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }

        startsWith(".组队：",removePrefix = true){
            val index = it.indexOf("#")
            val member1: String? = if(index==-1) null else it.substring(0,index)
            val member2: String? = if(index==-1) null else it.substring(index+1)
            if(member1 == member2){
                reply("不可以和你自己组队喔")
            }else{
                for(i in chars){
                    if(member1 == i.CharName){
                        for(j in chars){
                            if(member2 == j.CharName){
                                if(!i.teammate.isEmpty()){
                                    for(tm in i.teammate){
                                        if(j.CharName == tm.CharName){
                                            break
                                        }else if(j.CharName != tm.CharName && tm == i.teammate.last()){
                                            i.teamUp(j)
                                            break
                                        }
                                    }
                                }else{i.teamUp(j)}
                                if(!j.teammate.isEmpty()){
                                    for(tm in j.teammate){
                                        if(i.CharName == tm.CharName){
                                            break
                                        }else if(i.CharName != tm.CharName && tm == j.teammate.last()){
                                            j.teamUp(i)
                                            break
                                        }
                                    }
                                }else{j.teamUp(i)}
                                reply("组队成功")
                                break
                            }else if(member2 != j.CharName && j == chars.last()){
                                reply("没有找到${member2}这个角色")
                                break
                            }
                        }
                        break
                    }else if(member1 != i.CharName && i == chars.last()){
                        reply("没有找到${member1}这个角色")
                    }
                }
            }
        }
        startsWith(".退出队伍：",removePrefix = true){
            for(char in chars){
                if(it == char.CharName){
                    for(i in char.teammate){
                        i.teammate.remove(char)
                    }
                    char.teammate.clear()
                    if(!char.curEnemy.isEmpty()){
                        char.curEnemy[0].curEnemy.remove(char)
                        char.curEnemy.clear()
                        char.debuff = null
                        var RemoveBuff = mutableListOf<Buff>()
                        for(bf in char.buff){
                            RemoveBuff.add(bf)
                        }
                        for(rm in RemoveBuff){
                            unequipBuff(char,rm.BuffName)
                        }
                        char.BuffTime = 0
                        char.DebuffTime = 0
                    }
                    reply("已经退出冒险队伍")
                    break
                }else if(it != char.CharName && char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }

        startsWith(".查看队伍：",removePrefix = true){
            var teammate = ""
            for(char in chars){
                if(it == char.CharName){
                    if(!char.teammate.isEmpty()){
                        for(i in char.teammate){
                            teammate += i.CharName + "；"
                        }
                        reply("当前的队友：$teammate")
                    }else{
                        reply("这个角色没有队友喔")
                    }
                    break
                }else if(it != char.CharName && char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }

        startsWith(".背包：",removePrefix = true){
            for(char in chars){
                if(it == char.CharName){
                    var output = ""
                    if(char.items.isEmpty()){
                        reply("背包是空的")
                    }else{
                        for(i in char.items){
                            output += i.name+"；"
                        }
                        reply("背包里有：${output}")
                    }
                    break
                }else if(it != char.CharName && char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }

        startsWith(".转赠：",removePrefix = true){
            val CommandIndex3 = it.indexOf("#")
            val TargetIndex3 = it.indexOf("%")
            val user:String = if(CommandIndex3==-1) "" else it.substring(0,CommandIndex3)
            val command:String = if(TargetIndex3==-1) it.substring(CommandIndex3+1) else it.substring(CommandIndex3+1,TargetIndex3)
            val target:String = if(TargetIndex3==-1) "" else it.substring(TargetIndex3+1)
            if(user == target){
                reply("不能转赠给你自己")
            }else{
                for(char in chars){
                    if(user == char.CharName) {
                        for(tgt in chars){
                            if(target == tgt.CharName){
                                if(!char.items.isEmpty()){
                                    for(item in char.items){
                                        if(command == item.name){
                                            tgt.items.add(item)
                                            char.items.remove(item)
                                            reply("转赠成功")
                                            Save()
                                            break
                                        }else if(command != item.name && item == char.items.last()){
                                            reply("背包里没有${command}")
                                            break
                                        }
                                    }
                                }else{
                                    reply("你的背包是空的")
                                }
                                break
                            }else if(target !=  tgt.CharName && tgt == chars.last()){
                                reply("没有找到目标哦")
                            }
                        }
                        break
                    }else if(user != char.CharName && char == chars.last()){
                        reply("没有找到这个角色哦")
                    }
                }
            }
        }

        startsWith(".装备：",removePrefix = true){
            val index = it.indexOf("#")
            val user = if(index==-1) "" else it.substring(0,index)
            val equiptment = if(index==-1) "" else it.substring(index+1)

            for(char in chars){
                if(char.CharName == user){
                    try{
                        useItem(char,itm,equiptment)
                    }catch(e:EquipSuccessException){
                        Save()
                        reply("装备上了${e.equiptment}")
                    }catch(e:ClassMismatchException){
                        reply("${e.user.CharClass}不能装备${e.name}")
                    }catch(e:ItemNotFoundException){
                        reply("背包里没有${e.item}")
                    }
                    break
                }else if(char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }

        startsWith(".查看装备：",removePrefix = true){
            for(char in chars){
                if(char.CharName == it){
                    if(char.equiptment == null){
                        reply("${char.CharName}当前没有装备")
                    }else{
                        reply("${char.CharName}的装备是：${char.equiptment.name}")
                    }
                    break
                }else if(char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }

        startsWith(".解除装备：",removePrefix = true){
            for(char in chars){
                if(it == char.CharName){
                    if(char.equiptment == null){
                        reply("没有正在使用的装备")
                    }else{
                        unequip(char)
                        if(char.equiptment == null){
                            Save()
                            reply("解除成功")
                        }
                    }
                    break
                }else if(char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }

        startsWith(".查看增幅：",removePrefix = true){
            var BuffOutput:String = ""
            for(char in chars){
                if(char.CharName == it){
                    if(char.buff == null){
                        reply("${char.CharName}当前没有增幅")
                    }else{
                        for(i in char.buff){
                            BuffOutput += i.BuffName+"；"
                        }
                        reply("${char.CharName}的buff是：${BuffOutput}")
                    }
                    break
                }else if(char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }

        startsWith(".查看怪物：",removePrefix = true){
            var enemyChar:Character
            for(char in chars){
                if(char.CharName == it){
                    if(char.curEnemy == null){
                        reply("${char.CharName}当前没有敌人")
                    }else{
                        //查看怪物
                        enemyChar = char.curEnemy[0]
                        reply(  "怪物名字" + enemyChar.CharName+"\n" +
                                "攻击力：" + enemyChar.Atk + "\n" +
                                "防御力：" + enemyChar.Def + "\n" +
                                "魔法攻击：" + enemyChar.MgAtk + "\n" +
                                "魔法防御" + enemyChar.MgDef + "\n" +
                                "HP：" + enemyChar.HP + "/" + enemyChar.MAX_HP + "\n" +
                                "MP：" + enemyChar.MP + "/" + enemyChar.MAX_MP)
                    }
                    break
                }else if(char == chars.last()){
                    reply("没有找到这个角色哦")
                }
            }
        }


        ".loadFile" {
            try{val load = Load()
                for(i in load){
                    chars.add(i)
                }
                for(char in chars){
                    char.teammate.clear() //unknown problem if remove this line, char will have teammate
                }
            }catch(e: NullPointerException){
                chars.clear()
            }
        }

        ".帮助" reply("本bot拥有如下指令："+"\n"+".创建角色：角色名#职业----创建一个新角色。目前共有五种职业：战士、法师、游侠、牧师、圣骑士"
                                       +"\n"+"\n"+".角色属性：角色名----查看该角色的属性"
                                       +"\n"+"\n"+".删除角色：角色名----可以删除该角色"
                                       +"\n"+"\n"+".组队：角色名1#角色名2----让两名角色组队冒险，让第一名角色加入第二名角色的战斗。注意，仅有输入的两名角色视为组队，若想把各自的队友加进一个队伍，请让所有人两两配对（嘤嘤嘤我找时间优化一下）"
                                       +"\n"+"\n"+".退出队伍：角色名----让该角色退出原本的队伍，但对其余的队伍成员没有影响。注意，退出队伍也会退出战斗。"
                                       +"\n"+"\n"+".查看队伍：角色名----查看与该角色组队的所有角色"
                                       +"\n"+"\n"+".冒险：角色名----使该角色与其队伍成员随机遭遇一个怪物"
                                       +"\n"+"\n"+".战斗：角色名#指令%目标----使该角色攻击目标（可以不指定目标，若不指定目标则默认为敌人，若要使用物品请输入：角色名#使用物品%物品名），指令有：攻击、魔法、使用物品、灵魂激流（法师的技能）、按手礼（圣骑士的技能）、温暖的火（牧师技能）、狙击（游侠技能）、狼箭步（战士技能）。其中除了使用物品外，所有指令在怪物攻击前只能使用一次。"
                                       +"\n"+"\n"+".逃跑：角色名----让当前角色逃回城镇，其余队伍成员依旧处于战斗状态，组队状态也不会解除"
                                       +"\n"+"\n"+".城镇：角色名#指令%目标----只有在城镇时才能使用，让当前角色在城镇活动。指令有：温暖的火（牧师技能）、按手礼（圣骑士技能）、使用物品（角色名#使用物品%物品名）、商店（角色名#商店%物品名；可购商品：生命药水，魔法药水）"
                                       +"\n"+"\n"+".打工：角色名----只有在城镇时才能使用，让角色打工。打工次数每24小时1次"
                                       +"\n"+"\n"+".背包：角色名----显示该角色所持有的物品"
                                       +"\n"+"\n"+".转增：角色名#转赠物品%目标----转赠持有的物品给目标角色。注意转赠装备前请先解除装备。"
                                       +"\n"+"\n"+".装备:角色名#装备名----装备背包中的装备（装备物品则自动使用但不会回复消息）。每个角色最多同时装备一件装备。装备仅能通过打败怪物掉落。"
                                       +"\n"+"\n"+".查看装备：角色名----查看正在装备中的物品。"
                                       +"\n"+"\n"+".解除装备：角色名----解除装备中的物品。"
                                       +"\n"+"\n"+".解除增幅：角色名----查看目前的buff。"
                                       +"\n"+"\n"+".查看怪物：角色名----查看当前面对的敌人的状态。"
                                       +"\n"+"\n"+"最后，战斗时人数多于1人时，敌人会有相应程度的加强；此外每次bot被关闭时，以下数据不会被保存：组队数据、冒险数据")
    }
    miraiBot.join() // 等待 Bot 离线, 避免主线程退出
}