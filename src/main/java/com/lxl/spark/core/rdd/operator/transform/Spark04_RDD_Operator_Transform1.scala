package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
/**案例：对数据进行扁平化操作，
 * 将"Hello Scala", "Hello Spark"
 * 拆分为"Hello" ,"Scala", "Hello" ,"Spark"
 * 通过flatMap实现扁平化*/

object Spark04_RDD_Operator_Transform1 {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - flatMap
        val rdd: RDD[String] = sc.makeRDD(List(
            "Hello Scala", "Hello Spark"
        ))

        val flatRDD: RDD[String] = rdd.flatMap(
            s => {
                s.split(" ")
            }
        )
        flatRDD.collect().foreach(println)



        sc.stop()

    }
}
