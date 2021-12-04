package com.huldarchen.hive;

import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.huldarchen.SqlParserAbstract;
import com.huldarchen.antlr.HiveSqlLexer;
import com.huldarchen.antlr.HiveSqlParser;
import com.huldarchen.enums.SqlTypeEnum;
import com.huldarchen.model.lineage.FieldLineageModel;
import com.huldarchen.model.lineage.TableLineageModel;
import com.huldarchen.model.metadata.TableMetadataModel;

/**
 * @ClassName HiveSqlParser
 * @description:
 * @author: again
 * @Date: 2021/3/10 4:21 下午
 */
public class MyHiveSqlParser extends SqlParserAbstract {

    private ParseTree getParseTree(String sql) {
        CharStream input = CharStreams.fromString(sql);
        HiveSqlLexer lexer = new HiveSqlLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        HiveSqlParser parser = new HiveSqlParser(tokenStream);
        return parser.program();
    }

    @Override
    public SqlTypeEnum parseSqlType(String sql) {
        HiveSqlTypeParser visitor = new HiveSqlTypeParser();
        visitor.visit(getParseTree(sql));
        return visitor.getSqlType();
    }

    @Override
    public TableMetadataModel parseSqlMetadata(String sql) {
        HiveSqlMetadataParser visitor = new HiveSqlMetadataParser(sql);
        visitor.visit(getParseTree(sql));
        return visitor.getTableMetadata();
    }

    @Override
    public String parseSqlFormatter(String sql) {
        HiveSqlFormatterParser visitor = new HiveSqlFormatterParser(sql);
        visitor.visit(getParseTree(sql));
        return visitor.getFormattedSQL();
    }

    @Override
    public TableLineageModel parseSqlTableLineage(String sql) {
        HiveSqlTableLineageParser visitor = new HiveSqlTableLineageParser();
        visitor.visit(getParseTree(sql));
        return visitor.getTableLineage();
    }

    @Override
    public List<FieldLineageModel> parseSqlFieldLineage(String sql) {
        HiveSqlFieldLineageParser visitor = new HiveSqlFieldLineageParser(sql);
        visitor.visit(getParseTree(sql));
        return visitor.getHiveFieldLineage();
    }
}
