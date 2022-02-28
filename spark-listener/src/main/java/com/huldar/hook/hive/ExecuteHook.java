package com.huldar.hook.hive;

import org.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext;
import org.apache.hadoop.hive.ql.hooks.HookContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/30 00:12
 */
public class ExecuteHook implements ExecuteWithHookContext {
  private static final Logger logger = LoggerFactory.getLogger(ExecuteHook.class);
  @Override
  public void run(HookContext hookContext) throws Exception {

    logger.info("-------------------------" + hookContext.getQueryPlan().getQueryString());
  }
}
