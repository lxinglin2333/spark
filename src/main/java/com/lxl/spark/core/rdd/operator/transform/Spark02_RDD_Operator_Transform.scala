package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 分区内一个数据一个数据依次执行逻辑太慢了
 * rdd中提供了一个方法mapPartitions，能以分区为单位，把一个分区内的数据都拿到再进行逻辑操作，类似与io中的缓冲流
 * 但是有一个缺点：会将整个分区的数据加载到内存进行引用
 * 如果处理完的数据是不会被释放掉，存在对象的引用
 * 在内存较小，数据量较大的场合下，容易出现内存溢出*/

object Spark02_RDD_Operator_Transform {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - mapPartitions
        val rdd = sc.makeRDD(List(1,2,3,4), 2)

        // mapPartitions : 可以以分区为单位进行数据转换操作
        //                 但是会将整个分区的数据加载到内存进行引用
        //                 如果处理完的数据是不会被释放掉，存在对象的引用。
        //                 在内存较小，数据量较大的场合下，容易出现内存溢出。
        val mpRDD: RDD[Int] = rdd.mapPartitions(
            iter => {
                println(">>>>>>>>>>")
                iter.map(_ * 2)
            }
        )
        mpRDD.collect().foreach(println)

        sc.stop()

    }
}
