package org.apache.spark.sql.util

import org.apache.spark.internal.Logging
import org.apache.spark.scheduler._
import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.QueryExecution
import org.apache.spark.sql.execution.ui.SparkListenerSQLExecutionStart

class MySparkAppListener extends SparkListener with QueryExecutionListener with Logging {
  override def onApplicationStart(applicationStart: SparkListenerApplicationStart): Unit = {
    val appId = applicationStart.appId
    logInfo("***************************************************" + appId.get)
  }

  override def onStageCompleted(stageCompleted: SparkListenerStageCompleted): Unit = {
    val stageName = stageCompleted.stageInfo.name
    logInfo("stage completed ***********************************" + stageName)
  }

  override def onStageSubmitted(stageSubmitted: SparkListenerStageSubmitted): Unit = {
    val properties = stageSubmitted.properties
    logInfo("stageSubmitted ***********************************" + properties)
  }

  override def onTaskStart(taskStart: SparkListenerTaskStart): Unit = {

    logInfo("taskStart ***********************************" + taskStart.stageId)
  }

  override def onTaskGettingResult(taskGettingResult: SparkListenerTaskGettingResult): Unit = {
    logInfo("taskGettingResult ***********************************" + taskGettingResult.taskInfo.gettingResultTime)
  }

  override def onTaskEnd(taskEnd: SparkListenerTaskEnd): Unit = {
    logInfo("taskEnd ***********************************" + taskEnd.reason)
  }

  override def onJobStart(jobStart: SparkListenerJobStart): Unit = {
    logInfo("jobStart ***********************************" + jobStart.jobId)
  }

  override def onJobEnd(jobEnd: SparkListenerJobEnd): Unit = {
    logInfo("jobEnd ***********************************" + jobEnd.jobResult)
  }

  override def onEnvironmentUpdate(environmentUpdate: SparkListenerEnvironmentUpdate): Unit = {
    logInfo("environmentUpdate ***********************************" + environmentUpdate.environmentDetails)
  }

  override def onBlockManagerAdded(blockManagerAdded: SparkListenerBlockManagerAdded): Unit = {
    logInfo("blockManagerAdded ***********************************" + blockManagerAdded.blockManagerId)
  }

  override def onBlockManagerRemoved(blockManagerRemoved: SparkListenerBlockManagerRemoved): Unit = {
    logInfo("blockManagerRemoved ***********************************" + blockManagerRemoved.blockManagerId)
  }

  override def onUnpersistRDD(unpersistRDD: SparkListenerUnpersistRDD): Unit = {
    logInfo("onUnpersistRDD ***********************************" + unpersistRDD.rddId)
  }

  override def onApplicationEnd(applicationEnd: SparkListenerApplicationEnd): Unit = {
    logInfo("applicationEnd ***********************************" + applicationEnd.time)
  }

  override def onExecutorMetricsUpdate(executorMetricsUpdate: SparkListenerExecutorMetricsUpdate): Unit = {
    logInfo("executorMetricsUpdate ***********************************" + executorMetricsUpdate.execId)
  }

  override def onExecutorAdded(executorAdded: SparkListenerExecutorAdded): Unit = {
    logInfo("executorAdded ***********************************" + executorAdded.executorId)
  }

  override def onExecutorRemoved(executorRemoved: SparkListenerExecutorRemoved): Unit = {
    logInfo("executorRemoved ***********************************" + executorRemoved.executorId)
  }

  override def onExecutorBlacklisted(executorBlacklisted: SparkListenerExecutorBlacklisted): Unit = {
    logInfo("executorBlacklisted ***********************************" + executorBlacklisted)
  }

  override def onExecutorUnblacklisted(executorUnblacklisted: SparkListenerExecutorUnblacklisted): Unit = {
    logInfo("executorUnblacklisted ***********************************" + executorUnblacklisted)
  }

  override def onNodeBlacklisted(nodeBlacklisted: SparkListenerNodeBlacklisted): Unit = {
    logInfo("onNodeBlacklisted ***********************************" + nodeBlacklisted.hostId)
  }

  override def onNodeUnblacklisted(nodeUnblacklisted: SparkListenerNodeUnblacklisted): Unit = {
    logInfo("nodeUnblacklisted ***********************************" + nodeUnblacklisted.hostId)
  }

  override def onBlockUpdated(blockUpdated: SparkListenerBlockUpdated): Unit = {
    logInfo("blockUpdated ***********************************" + blockUpdated.blockUpdatedInfo)
  }

  override def onSpeculativeTaskSubmitted(speculativeTask: SparkListenerSpeculativeTaskSubmitted): Unit = {
    logInfo("speculativeTask ***********************************" + speculativeTask)
  }

  override def onOtherEvent(event: SparkListenerEvent): Unit = {
    logInfo("onOtherEvent ***********************************" + event)

    event match {
      case sles: SparkListenerSQLExecutionStart =>
        val traces = Thread.currentThread().getStackTrace
        traces.foreach(tre =>
          logInfo(tre.getClassName + "-------------------------" +  tre.getMethodName)
        )
        logInfo(sles.details)
        logInfo(sles.sparkPlanInfo.simpleString)
        logInfo(sles.description)
        logInfo(sles.physicalPlanDescription)
        val ss = SparkSession.getDefaultSession.get
        val state = ss.sessionState
        val queryStr = ss.conf.get("hive.query.string")
        logInfo(queryStr)
        logInfo("************" + state.getClass)
        val parser = state.sqlParser
        logInfo("************" + parser.getClass)

        val logicalPlan = parser.parsePlan(queryStr)
        logInfo(logicalPlan.toString())
//        val queryExecution = state.executePlan(logicalPlan)
//        logInfo(queryExecution.toString())
      case _ =>
        logInfo("********************" + event)
    }
  }

  override def onSuccess(funcName: String, qe: QueryExecution, durationNs: Long): Unit = {}

  override def onFailure(funcName: String, qe: QueryExecution, exception: Exception): Unit = {

  }
}
