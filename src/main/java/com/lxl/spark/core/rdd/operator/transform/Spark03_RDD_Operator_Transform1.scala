package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 案例：获取数据的分区号
 * 根据mapPartitionsWithIndex方法多了分区号的特性
 * 遍历数据时获取它的分区号*/

object Spark03_RDD_Operator_Transform1 {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - mapPartitions
        val rdd = sc.makeRDD(List(1,2,3,4))

        val mpiRDD = rdd.mapPartitionsWithIndex(
            (index, iter) => {
                // 1,   2,    3,   4
                //(0,1)(2,2),(4,3),(6,4)
                iter.map(
                    num => {
                        (index, num)
                    }
                )
            }
        )

        mpiRDD.collect().foreach(println)


        sc.stop()

    }
}
