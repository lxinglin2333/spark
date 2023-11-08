package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/***
 * 过滤
 * filter把不符合条件的数据进行过滤
 * 过滤偶数 */

object Spark07_RDD_Operator_Transform {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - filter
        val rdd = sc.makeRDD(List(1,2,3,4))

        val filterRDD: RDD[Int] = rdd.filter(num=>num%2!=0)

        filterRDD.collect().foreach(println)


        sc.stop()

    }
}
