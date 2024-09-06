package net.mamoe.mirai.simpleloader

class MoneyNotEnoughException(val money:Int,val targetMoney:Int):Exception() {
}