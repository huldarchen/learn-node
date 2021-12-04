package com.huldarchen.hive;

import com.huldarchen.antlr.HiveSqlBaseVisitor;
import com.huldarchen.antlr.HiveSqlParser;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/4 15:32
 */
public class SqlTypeParser<T> extends HiveSqlBaseVisitor<T> {

  @Override
  public T visitInsert_stmt(HiveSqlParser.Insert_stmtContext ctx) {
    return super.visitInsert_stmt(ctx);
  }

  @Override
  public T visitSelect_stmt(HiveSqlParser.Select_stmtContext ctx) {
    return super.visitSelect_stmt(ctx);
  }
}
