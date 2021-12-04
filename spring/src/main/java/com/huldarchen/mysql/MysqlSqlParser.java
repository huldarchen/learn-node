package com.huldarchen.mysql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.huldarchen.SqlParserAbstract;
import com.huldarchen.antlr.MySqlLexer;
import com.huldarchen.antlr.MySqlParser;
import com.huldarchen.enums.SqlTypeEnum;

/**
 * @ClassName MysqlSqlParser
 * @description:
 * @author: again
 * @Date: 2021/3/10 4:26 下午
 */
public class MysqlSqlParser extends SqlParserAbstract {

    private ParseTree getParseTree(String sql) {
        sql = sql.toUpperCase();
        CharStream input = CharStreams.fromString(sql);
        MySqlLexer mySqlLexer = new MySqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(mySqlLexer);
        MySqlParser parser = new MySqlParser(tokens);
        return parser.root();
    }

    @Override
    public SqlTypeEnum parseSqlType(String sql) {
        MysqlSqlTypeParser visitor = new MysqlSqlTypeParser();
        visitor.visit(getParseTree(sql));
        return visitor.getSqlType();
    }
}
