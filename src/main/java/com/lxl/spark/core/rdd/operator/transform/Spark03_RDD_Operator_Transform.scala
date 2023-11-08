package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 案例：只要第二个分区的数据，第一个去掉
 * mapPartitionsWithIndex方法相对于mapPartitions多了分区号
 * 能对不分区的数据进行不同的操作*/

object Spark03_RDD_Operator_Transform {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - mapPartitions
        val rdd = sc.makeRDD(List(1,2,3,4), 2)
        // 【1，2】，【3，4】
        val mpiRDD = rdd.mapPartitionsWithIndex(
            (index, iter) => {
                if ( index == 1 ) {
                    iter
                } else {
                    //给一个空集的迭代器
                    Nil.iterator
                }
            }
        )
        mpiRDD.collect().foreach(println)


        sc.stop()

    }
}
