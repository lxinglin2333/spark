package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
 * leftJoinRDD
 * rightJoinRDD
 * rdd的join
 * 两个rdd之间的join，类似与左右连接
 * */

object Spark22_RDD_Operator_Transform {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - (Key - Value类型)

        val rdd1 = sc.makeRDD(List(
            ("a", 1), ("b", 2)//, ("c", 3)
        ))

        val rdd2 = sc.makeRDD(List(
            ("a", 4), ("b", 5),("c", 6)
        ))
        //val leftJoinRDD = rdd1.leftOuterJoin(rdd2)
        val rightJoinRDD = rdd1.rightOuterJoin(rdd2)

        //leftJoinRDD.collect().foreach(println)
        rightJoinRDD.collect().foreach(println)



        sc.stop()

    }
}
