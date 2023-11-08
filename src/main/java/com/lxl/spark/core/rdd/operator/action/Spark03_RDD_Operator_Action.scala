package com.lxl.spark.core.rdd.operator.action

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 转换算子aggregateByKey与行动算子aggregate的差异
 * fold（）与aggregate（）类似，在分区内、分区间规则一样时，使用fold（）简化操作
 * */

object Spark03_RDD_Operator_Action {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        val rdd = sc.makeRDD(List(1,2,3,4),2)

        // TODO - 行动算子

        //10 + 13 + 17 = 40
        // aggregateByKey : 初始值只会参与分区内计算，是装换算子
        // aggregate : 初始值会参与分区内计算,并且和参与分区间计算，是行动算子
        //val result = rdd.aggregate(10)(_+_, _+_)
        val result = rdd.fold(10)(_+_)

        println(result)

        sc.stop()

    }
}
