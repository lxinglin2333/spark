package com.lxl.spark.core.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

/**
 * 视频p167
 * 创建对象，初始化环境
 * 案例：rdd、df、ds三者的互相转换*/

object Spark01_SparkSQL_Basic {

    def main(args: Array[String]): Unit = {

        // TODO 创建SparkSQL的运行环境
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        val spark = SparkSession.builder().config(sparkConf).getOrCreate()
        //引入对象实例中的隐式转换
        import spark.implicits._


        // TODO 执行逻辑操作

        // TODO DataFrame
        //文件中读取数据创建df
        //val df: DataFrame = spark.read.json("datas/user.json")
        //df.show()
        //模拟一些在黑窗口上的df操作
        // DataFrame => SQL
//        df.createOrReplaceTempView("user")
//
//        spark.sql("select * from user").show
//        spark.sql("select age, username from user").show
//        spark.sql("select avg(age) from user").show

        // DataFrame => DSL
        // 在使用DataFrame时，如果涉及到转换操作，需要引入转换规则

        //df.select("age", "username").show
        //df.select($"age" + 1).show
        //df.select('age + 1).show

        // TODO DataSet
        // DataFrame其实是特定泛型的DataSet
        //val seq = Seq(1,2,3,4)
        //val ds: Dataset[Int] = seq.toDS()
        //ds.show()

        // RDD <=> DataFrame
        val rdd = spark.sparkContext.makeRDD(List((1, "zhangsan", 30), (2, "lisi", 40)))
        val df: DataFrame = rdd.toDF("id", "name", "age")
        val rowRDD: RDD[Row] = df.rdd

        // DataFrame <=> DataSet
        val ds: Dataset[User] = df.as[User]
        val df1: DataFrame = ds.toDF()

        // RDD <=> DataSet
        val ds1: Dataset[User] = rdd.map {
            case (id, name, age) => {
                User(id, name, age)
            }
        }.toDS()
        val userRDD: RDD[User] = ds1.rdd


        // TODO 关闭环境
        spark.close()
    }
    case class User( id:Int, name:String, age:Int )
}
