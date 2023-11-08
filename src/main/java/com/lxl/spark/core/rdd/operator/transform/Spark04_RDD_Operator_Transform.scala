package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**案例：对数据进行扁平化操作，
 * 将List(1, 2), List(3, 4)
 * 拆分为1 2 3 4
 * 通过flatMap实现扁平化*/

object Spark04_RDD_Operator_Transform {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - mapPartitions
        val rdd: RDD[List[Int]] = sc.makeRDD(List(
            List(1, 2), List(3, 4)
        ))

        //注意，虽然下面的也是list，但是上面list包含的也是list，下面的是一个一个Int数据
        val flatRDD: RDD[Int] = rdd.flatMap(
            list => {
                list
            }
        )
        flatRDD.collect().foreach(println)



        sc.stop()

    }
}
