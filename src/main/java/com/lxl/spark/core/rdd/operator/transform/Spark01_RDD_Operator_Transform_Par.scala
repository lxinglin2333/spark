package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

/**案例：对读取的数据进行处理，观察数据在rdd内是怎样处理的
 * 单个分区：
 * rdd的计算一个分区内的数据是一个一个执行逻辑
 * 只有前面一个数据【全部的逻辑】执行完毕后，才会执行下一个数据，前一个数据走完所有rdd，才到下一个数据走rdd。
 * 分区内数据的执行是有序的
 * 多个分区：
 * 不同分区数据计算是无序的
 * 但是不同分区之前互不影响，相当多个分区并行，每个分区内数据是一个一个执行逻辑
 * */

object Spark01_RDD_Operator_Transform_Par {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - map

        // 1. rdd的计算一个分区内的数据是一个一个执行逻辑
        //    只有前面一个数据全部的逻辑执行完毕后，才会执行下一个数据。
        //    分区内数据的执行是有序的。
        // 2. 不同分区数据计算是无序的。
        val rdd = sc.makeRDD(List(1,2,3,4),2)

        val mapRDD = rdd.map(
            num => {
                println(">>>>>>>> " + num)
                num
            }
        )
        val mapRDD1 = mapRDD.map(
            num => {
                println("######" + num)
                num
            }
        )

        mapRDD1.collect()

        sc.stop()

    }
}
