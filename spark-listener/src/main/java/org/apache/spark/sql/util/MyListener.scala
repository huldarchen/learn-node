package org.apache.spark.sql.util

import org.apache.spark.internal.Logging
import org.apache.spark.sql.execution.QueryExecution

class MyListener extends QueryExecutionListener with Logging{

  logInfo("初始化 queryExecutionListener")

  override def onSuccess(funcName: String, qe: QueryExecution, durationNs: Long): Unit = {
    logInfo("======================================================")
    logInfo("测试")
    logInfo("======================================================")
  }

  override def onFailure(funcName: String, qe: QueryExecution, exception: Exception): Unit = {
    logInfo("======================================================")
    logInfo("测试")
    logInfo("======================================================")
  }
}
