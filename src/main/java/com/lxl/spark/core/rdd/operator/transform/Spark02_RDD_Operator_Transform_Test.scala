package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 案例：获取每个分区数中的最大值
 * 因为mapPartitions数据分区处理的特性，可以实现一些特殊的功能
 * mapPartitions自带查询分区最大值的方法
 * */

object Spark02_RDD_Operator_Transform_Test {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - mapPartitions
        val rdd = sc.makeRDD(List(1,2,3,4), 2)

        // 【1，2】，【3，4】
        // 【2】，【4】
        val mpRDD = rdd.mapPartitions(
            iter => {
                //因为只要输出一个最大值，不是迭代器，所以需要进行转换
                List(iter.max).iterator
            }
        )
        mpRDD.collect().foreach(println)

        sc.stop()

    }
}
