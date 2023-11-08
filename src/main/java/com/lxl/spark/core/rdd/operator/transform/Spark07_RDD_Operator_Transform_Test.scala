package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

/***
 * 过滤
 * filter把不符合条件的数据进行过滤
 * 过滤apache.log17/05/2015之前的数据
 */

object Spark07_RDD_Operator_Transform_Test {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - filter
        val rdd = sc.textFile("datas/apache.log")

        rdd.filter(
            line => {
                val datas = line.split(" ")
                val time = datas(3)
                time.startsWith("17/05/2015")
            }
        ).collect().foreach(println)


        sc.stop()

    }
}
