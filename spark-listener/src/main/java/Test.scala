import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.util.MyListener

object Test {

  def main(args: Array[String]): Unit = {
    // 连接hive数据仓库
    val sparkSession = SparkSession.builder()
//        .config("hive.metastore.uris", "thrift://localhost:9083")
        .appName("HiveCaseJob")
        .master("local[*]")
        .enableHiveSupport()
        .getOrCreate()

    val listenV3 = new MyListener()
    sparkSession.listenerManager.register(listenV3)
//    sparkSession.sessionState.
    sparkSession.sql(" select cast(date_add(date_format(add_months(to_date('20211201', 'yyyymmdd'),1), 'yyyymmdd'), -1 ) AS bigint) ").show()
    sparkSession.sql("create database if not EXISTS test")
    sparkSession.sql("create table  if not EXISTS test.test_orc(id int ,num int)")
    sparkSession.sql("create table if not EXISTS test.test02(id int, name string)")
//    sparkSession.sql("set spark.sql.queryExecutionListeners = org.apache.spark.sql.util.MyListener")
    //sparkSession.sql("show databases").show()
    sparkSession.sql("insert into test.test_orc select id,count(name) as num from test.test02 group by id").show()
    //val user_log = sparkSession.sql("select * from dbtaobao.user_log").collect()
    //val test = user_log.map(row => "user_id"+row(0))
    //test.map(row => println(row))

    print("******" + null)

  }

}
