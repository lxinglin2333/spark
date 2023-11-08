package com.lxl.spark.core.rdd.operator.action

import org.apache.spark.{SparkConf, SparkContext}

/**
 * saveAsTextFile、saveAsTextFile、saveAsTextFile包装的存储方式不同
 * 一般第一个saveAsTextFile的使用频率比较多*/

object Spark05_RDD_Operator_Action {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        //val rdd = sc.makeRDD(List(1,1,1,4),2)
        val rdd = sc.makeRDD(List(
            ("a", 1),("a", 2),("a", 3)
        ))

        // TODO - 行动算子
        rdd.saveAsTextFile("output")
        rdd.saveAsTextFile("output1")
        // saveAsSequenceFile方法要求数据的格式必须为K-V类型
        rdd.saveAsTextFile("output2")

        sc.stop()

    }
}
