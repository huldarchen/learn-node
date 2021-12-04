package com.huldarchen.spark;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.huldarchen.SqlParserAbstract;
import com.huldarchen.antlr.SparkSqlLexer;
import com.huldarchen.antlr.SparkSqlParser;
import com.huldarchen.enums.SqlTypeEnum;

/**
 * @ClassName MySparkSqlParser
 * @description:
 * @author: again
 * @Date: 2021/3/11 8:08 下午
 */
public class MySparkSqlParser extends SqlParserAbstract {

    private ParseTree getParseTree(String sql) {
        sql = sql.toUpperCase();
        CharStream input = CharStreams.fromString(sql);
        SparkSqlLexer mySqlLexer = new SparkSqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(mySqlLexer);
        SparkSqlParser parser = new SparkSqlParser(tokens);
        return parser.statement();
    }

    @Override
    public SqlTypeEnum parseSqlType(String sql) {
        SparkSqlTypeParser visitor = new SparkSqlTypeParser();
        visitor.visit(getParseTree(sql));
        return visitor.getSqlType();
    }



}
