package com.lxl.spark.core.rdd.builder

import org.apache.spark.{SparkConf, SparkContext}

/**
 * rdd 从文件中读取数据的分区规律
 * 源码
 * 分区是根据数组长度根据，把不同下标的数据分到不同的分区*/

object Spark02_RDD_File_Par {

    def main(args: Array[String]): Unit = {

        // TODO 准备环境
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
        val sc = new SparkContext(sparkConf)

        // TODO 创建RDD
        // textFile可以将文件作为数据处理的数据源，默认也可以设定分区。
        //     minPartitions : 最小分区数量
        //     math.min(defaultParallelism, 2)
        //val rdd = sc.textFile("datas/1.txt")
        // 如果不想使用默认的分区数量，可以通过第二个参数指定分区数
        // Spark读取文件，底层其实使用的就是Hadoop的读取方式
        // 分区数量的计算方式：
        //    totalSize = 7
        //    goalSize =  7 / 2 = 3（byte）

        //    7 / 3 = 2...1 (1.1) + 1 = 3(分区)

        //
        val rdd = sc.textFile("datas/1.txt", 2)

        rdd.saveAsTextFile("output")


        // TODO 关闭环境
        sc.stop()
    }
}
