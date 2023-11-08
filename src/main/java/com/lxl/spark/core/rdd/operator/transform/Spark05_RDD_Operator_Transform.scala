package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**案例：将单独的数据合并成一个数组
 *如将List(1,2,3,4)合并成-->[1,2,3,4] */

object Spark05_RDD_Operator_Transform {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - glom
        val rdd : RDD[Int] = sc.makeRDD(List(1,2,3,4), 2)

        // List => Int
        // Int => Array
        //注意返回的是一个Array[Int]数组，所以打印的时候需要取出里面的元素
        //不然直接打印的是数组的引用
        val glomRDD: RDD[Array[Int]] = rdd.glom()

        glomRDD.collect().foreach(data=> println(data.mkString(",")))





        sc.stop()

    }
}
