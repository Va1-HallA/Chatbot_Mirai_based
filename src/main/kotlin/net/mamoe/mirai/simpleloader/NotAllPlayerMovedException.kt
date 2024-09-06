package net.mamoe.mirai.simpleloader

class NotAllPlayerMovedException(val damage:Double,val monster:Character,val player:Character):Exception() {
}