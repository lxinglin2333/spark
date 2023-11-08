package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**案例：分区最大数求和
 *如分区1包含【1,2】分区2包含【3,4】
 * 最大值求和就是2+4=6
 * glom()将数据转换成数组
 * 数组提供了很多方法*/

object Spark05_RDD_Operator_Transform_Test {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - glom
        val rdd : RDD[Int] = sc.makeRDD(List(1,2,3,4), 2)

        // 【1，2】，【3，4】
        // 【2】，【4】
        // 【6】
        val glomRDD: RDD[Array[Int]] = rdd.glom()

        //将分区的数据转换成一个数组，并输出最大值
        val maxRDD: RDD[Int] = glomRDD.map(
            array => {
                array.max
            }
        )
        println(maxRDD.collect().sum)





        sc.stop()

    }
}
