package com.lxl.spark.core.rdd.builder

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
/**
 * 从内存中创建rdd
 * 两个方法
 * 1.parallelize
 * 2.makeRDD
 * 其实makeRDD还是parallelize，只是多套了一层*/

object Spark01_RDD_Memory {

    def main(args: Array[String]): Unit = {

        // TODO 准备环境
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
        val sc = new SparkContext(sparkConf)

        // TODO 创建RDD
        // 从内存中创建RDD，将内存中集合的数据作为处理的数据源
        val seq = Seq[Int](1,2,3,4)

        // parallelize : 并行
        //val rdd: RDD[Int] = sc.parallelize(seq)
        // makeRDD方法在底层实现时其实就是调用了rdd对象的parallelize方法。
        val rdd: RDD[Int] = sc.makeRDD(seq)

        rdd.collect().foreach(println)

        // TODO 关闭环境
        sc.stop()
    }
}
