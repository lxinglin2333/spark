package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 视频p68
 * 分区内：rdd独立的一个分区内
 * 分区间：rdd不同分区之间
 * 案例：rdd两个分区最大的数求和 （之前用数据做个这个案例？）
 * 这使用aggregateByKey实现
 * aggregateByKey
 * aggregateByKey存在函数柯里化，有两个参数列表
 * 第一个参数列表,需要传递一个参数，表示为初始值
 * 主要用于当碰见第一个key的时候，和value进行分区内计算
 * 第二个参数列表需要传递2个参数
 * 第一个参数表示分区内计算规则
 * 第二个参数表示分区间计算规则*/

object Spark17_RDD_Operator_Transform1 {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - (Key - Value类型)

        val rdd = sc.makeRDD(List(
            ("a", 1), ("a", 2), ("b", 3),
            ("b", 4), ("b", 5), ("a", 6)
        ),2)
        // (a,【1,2】), (a, 【3，4】)
        // (a, 2), (a, 4)
        // (a, 6)

        // aggregateByKey存在函数柯里化，有两个参数列表
        // 第一个参数列表,需要传递一个参数，表示为初始值
        //       主要用于当碰见第一个key的时候，和value进行分区内计算
        // 第二个参数列表需要传递2个参数
        //      第一个参数表示分区内计算规则
        //      第二个参数表示分区间计算规则

        // math.min(x, y)
        // math.max(x, y)
        //这里zeroValue设置为5直接导致a的最大值不准确
        rdd.aggregateByKey(5)(
            (x, y) => math.max(x, y),
            (x, y) => x + y
        ).collect.foreach(println)
        println("-----------------")
        rdd.aggregateByKey(0)(
            (x, y) => x + y,
            (x, y) => x + y
        ).collect.foreach(println)
        println("-----------------")
        rdd.aggregateByKey(0)(_+_, _+_).collect.foreach(println)





        sc.stop()

    }
}
