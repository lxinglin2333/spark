package com.lxl.spark.core.rdd.operator.transform

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 案例：根据文件内的包含的时间信息分组，统计相同时间（按每小时来分）内访问的次数
 * 83.149.9.216 - - 17/05/2015:10:05:03 +0000 GET /prese
 * 83.149.9.216 - - 17/05/2015:10:05:43 +0000 GET /prese
 * 83.149.9.216 - - 17/05/2015:10:05:47 +0000 GET /prese
 * */

object Spark06_RDD_Operator_Transform_Test {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - groupBy
        val rdd = sc.textFile("datas/apache.log")

        val timeRDD: RDD[(String, Iterable[(String, Int)])] = rdd.map(
            line => {
                val datas = line.split(" ")
                val time = datas(3)
                //time.substring(0, )
                val sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss")

                //字符串匹配成时间
                val date: Date = sdf.parse(time)
                val sdf1 = new SimpleDateFormat("HH")

                val hour: String = sdf1.format(date)
                (hour, 1)
            }
        ).groupBy(_._1)
        timeRDD.map{
            case ( hour, iter ) => {
                (hour, iter.size)
            }
        }.collect.foreach(println)


        sc.stop()

    }
}
