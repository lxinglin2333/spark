package com.lxl.spark.core.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

/**在对数据进行扁平化操作，不是所有数据都类型相同
 * 此时需要对数据进行模式配置装换类型
 * 例如：List(1,2),3,List(4,5)
 * 集合中掺杂了int类型的数据*/

object Spark04_RDD_Operator_Transform2 {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - flatMap
        val rdd = sc.makeRDD(List(List(1,2),3,List(4,5)))

        val flatRDD = rdd.flatMap(
            data => {
                //模式匹配，将不是集合的数据也转换为集合
                data match {
                    case list:List[_] => list
                    case dat => List(dat)
                }
            }
        )

        flatRDD.collect().foreach(println)



        sc.stop()

    }
}
