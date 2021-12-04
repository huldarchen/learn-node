package com.huldarchen;

import com.huldarchen.enums.SqlEngineEnum;
import com.huldarchen.hive.MyHiveSqlParser;
import com.huldarchen.mysql.MysqlSqlParser;
import com.huldarchen.presto.MyPresoSqlParser;
import com.huldarchen.spark.MySparkSqlParser;

/**
 * @ClassName SqlParserFactory
 * @description:
 * @author: again
 * @Date: 2021/3/10 4:21 下午
 */
public class SqlParserFactory {

    public static SqlParserService getParser(SqlEngineEnum sqlEngineEnum) {
        if (SqlEngineEnum.HIVE.equals(sqlEngineEnum)) {
            return new MyHiveSqlParser();
        } else if (SqlEngineEnum.MYSQL.equals(sqlEngineEnum)) {
            return new MysqlSqlParser();
        } else if (SqlEngineEnum.PRESTO.equals(sqlEngineEnum)) {
            return new MyPresoSqlParser();
        } else if (SqlEngineEnum.SPARK.equals(sqlEngineEnum)) {
            return new MySparkSqlParser();
        }
        throw new RuntimeException("db type is not support");
    }
}
