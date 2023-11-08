package com.lxl.spark.core.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * 视频p168
 * udf函数，实现一些原来的sql实现不了的功能
 * 案例：在名字前加前缀*/

object Spark02_SparkSQL_UDF {

    def main(args: Array[String]): Unit = {

        // TODO 创建SparkSQL的运行环境
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        val spark = SparkSession.builder().config(sparkConf).getOrCreate()

        val df = spark.read.json("datas/user.json")
        df.createOrReplaceTempView("user")

        spark.udf.register("prefixName", (name:String) => {
            "Name: " + name
        })

        spark.sql("select age, prefixName(username) from user").show


        // TODO 关闭环境
        spark.close()
    }
}
