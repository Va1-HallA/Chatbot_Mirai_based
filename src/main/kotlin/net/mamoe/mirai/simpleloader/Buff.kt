package net.mamoe.mirai.simpleloader

class Buff constructor(name:String,random:Int,bufftime:Int,source:Character,EngBuffName:String = "") {
    var BuffName:String = name
    var rdm:Int = random
    var BuffTime = bufftime
    var BuffSource = source
    var BuffAmount:Double = 0.0
    var EngBuffName:String = EngBuffName
}