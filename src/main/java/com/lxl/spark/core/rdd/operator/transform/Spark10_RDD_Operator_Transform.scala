package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
/**
 * 视频p58
 * coalesce案例
 * 当数据经过过滤、分组的操作后，每个分区的数据量可能会变小
 * 这时候还占用一个分区会影响效率
 * 通过coalesce可以实现数据的合并
 * 默认下coalesce是合并各分区的数据，比如三个旧分区合并成两个新分区
 * 旧的1分区数据到新的1分区，旧的23分区全部到新的2分区
 * 容易产生数据倾斜
 * 要先解决这个问题需要经过shuffle操作，即coalesce第二个参数设置为true
 * shuffle后的数据是无规律的
 * 也可以通过coalesce扩展，但第二个参数要设置为true，不打乱数据，数据还在一起就无法扩大分区
 * repartition()等同于coalesce第二个参数选true*/

object Spark10_RDD_Operator_Transform {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - filter
        val rdd = sc.makeRDD(List(1,2,3,4,5,6), 3)

        // coalesce方法默认情况下不会将分区的数据打乱重新组合
        // 这种情况下的缩减分区可能会导致数据不均衡，出现数据倾斜
        // 如果想要让数据均衡，可以进行shuffle处理
        //val newRDD: RDD[Int] = rdd.coalesce(2)
        val newRDD: RDD[Int] = rdd.coalesce(2,true)

        newRDD.saveAsTextFile("output")




        sc.stop()

    }
}
