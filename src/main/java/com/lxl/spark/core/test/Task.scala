package com.lxl.spark.core.test

//继承以序列化对象进行网络传输
class Task extends Serializable {


    val datas = List(1,2,3,4)

    //val logic = ( num:Int )=>{ num * 2 }
    val logic : (Int)=>Int = _ * 2


}
