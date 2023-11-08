package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**获取目标文件中的一段数据，即截取
 * * 原来的rdd功能：1.读取
 * * 现在的rdd功能：1.读取 2.从每一行中截取一段*/
object Spark01_RDD_Operator_Transform_Test {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - map
        val rdd = sc.textFile("datas/apache.log")

        // 长的字符串
        // 短的字符串
        val mapRDD: RDD[String] = rdd.map(
            line => {
                val datas = line.split(" ")
                datas(6)
            }
        )
        mapRDD.collect().foreach(println)

        sc.stop()

    }
}
