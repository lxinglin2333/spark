package com.lxl.spark.core.rdd.persist

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 持久化章节
 * 视频p99
 * rdd是不保存数据的，虽然对象还在，但是要想获得多份数据，还是要从头跑一遍流程 */

object Spark02_RDD_Persist {

    def main(args: Array[String]): Unit = {
        val sparConf = new SparkConf().setMaster("local").setAppName("Persist")
        val sc = new SparkContext(sparConf)

        val list = List("Hello Scala", "Hello Spark")

        val rdd = sc.makeRDD(list)

        val flatRDD = rdd.flatMap(_.split(" "))

        val mapRDD = flatRDD.map(word=>{
            println("@@@@@@@@@@@@")
            (word,1)
        })

        //mapRDD数据已经传给reduceRDD，里面已经没有数据了
        val reduceRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_+_)
        reduceRDD.collect().foreach(println)
        println("**************************************")
        //虽然mapRDD对象还存在，但是里面的数据已经传给reduceRDD，再次使用mapRDD会把前面的rdd流程全跑一遍
        //结果@@@@@@@@@@@@多次出现证明flatRDD跑了多次
        val groupRDD = mapRDD.groupByKey()
        groupRDD.collect().foreach(println)


        sc.stop()
    }
}
