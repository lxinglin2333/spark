package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

/**两个rdd之间分区数是一致的，且rdd1分区1的数据会到rdd1，rdd2的数据回到rdd2
 *下面案例为测试证明
 * 分区1的数据【1，2】经过rdd2的乘2操作后还是会到分区1
 * 分区2的数据【3，4】经过rdd2的乘2操作后还是会到分区2
 * 特殊情况下：分组操作会影响前后rdd分区的数据*/
object Spark01_RDD_Operator_Transform_Part {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - map
        val rdd = sc.makeRDD(List(1,2,3,4),2)
        // 【1，2】，【3，4】
        rdd.saveAsTextFile("output")
        val mapRDD = rdd.map(_*2)
        // 【2，4】，【6，8】
        mapRDD.saveAsTextFile("output1")

        sc.stop()

    }
}
