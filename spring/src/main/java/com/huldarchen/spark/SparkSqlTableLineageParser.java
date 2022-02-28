package com.huldarchen.spark;

import java.util.Optional;

import com.huldarchen.antlr.SparkSqlBaseListener;
import com.huldarchen.antlr.SparkSqlBaseVisitor;
import com.huldarchen.antlr.SparkSqlParser;
import com.huldarchen.model.lineage.TableNameModel;
import org.antlr.v4.runtime.RuleContext;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/4 15:36
 */
public class SparkSqlTableLineageParser<T> extends SparkSqlBaseVisitor<T> {

  @Override
  public T visitInsertOverwriteTable(SparkSqlParser.InsertOverwriteTableContext ctx) {
    // Optional.ofNullable(ctx)
    //         .map(SparkSqlParser.InsertOverwriteTableContext::TABLE)
    //         .map(RuleContext::getText)
    //         .map(TableNameModel::parseTableName)
    //         .orElse(null);
    return super.visitInsertOverwriteTable(ctx);
  }

  @Override
  public T visitInsertIntoTable(SparkSqlParser.InsertIntoTableContext ctx) {
    return super.visitInsertIntoTable(ctx);
  }

  @Override
  public T visitInsertOverwriteHiveDir(SparkSqlParser.InsertOverwriteHiveDirContext ctx) {
    return super.visitInsertOverwriteHiveDir(ctx);
  }

  @Override
  public T visitInsertOverwriteDir(SparkSqlParser.InsertOverwriteDirContext ctx) {
    return super.visitInsertOverwriteDir(ctx);
  }
}
