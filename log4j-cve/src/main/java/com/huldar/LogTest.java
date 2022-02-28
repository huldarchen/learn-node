package com.huldar;

import org.apache.hadoop.hive.ql.parse.ParseException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.tools.LineageInfo;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/13 11:09
 */
public class LogTest {
  // static Logger logger = LogManager.getLogger(LogTest.class);

  public static void main(String[] args) throws ParseException, SemanticException {
    // String username = "${java:os}";
    // logger.info("Hello {}", username);
    LineageInfo lineageInfo = new LineageInfo();
    String query = "INSERT INTO TABLE QCLOUD_DW::DWD_QD_INCOME_BASE_DI (FTIME ,OWNER_UIN ,PRODUCT_CODE ,SUB_PRODUCT_CODE ,COMPONENT_CODE ,ITEM_CODE ,PRODUCT_SOURCE ,GOODSTYPE ,GOODSTYPE_CH ,AGENT_NAME ,CONSUMEINCOME ,CONSUMEINCOME_AFTER ,SHOULDINCOME ,SHOULDINCOME_AFTER ,REALINCOME ,REALINCOME_AFTER ,MONTHACCOUNTPERIOD ,BADDEBT_30 ,SUBORDER_ID ,SUBORDER_CODE ,SUBORDER_TYPE ,ETL_TIME)\n" + "SELECT 20211203 AS FTIME ,\n" + "       OWNERUIN AS OWNER_UIN ,\n" + "       PRODUCTCODE AS PRODUCT_CODE ,\n" + "       SUBPRODUCTCODE AS SUB_PRODUCT_CODE ,\n" + "       COMPONENTCODE AS COMPONENT_CODE ,\n" + "       ITEMCODE AS ITEM_CODE ,\n" + "       SOURCE AS PRODUCT_SOURCE ,\n" + "                 GOODSTYPE ,\n" + "                 GOODSTYPE_CH ,\n" + "                 AGENTNAME AS AGENT_NAME ,\n" + "                 CONSUMEINCOME ,\n" + "                 CONSUMEINCOME_AFTER ,\n" + "                 SHOULDINCOME ,\n" + "                 SHOULDAFTER AS SHOULDINCOME_AFTER ,\n" + "                 REALINCOME ,\n" + "                 REALINCOME_AFTER ,\n" + "                 MONTHACCOUNTPERIOD ,\n" + "                 BADDEBT_30 ,\n" + "                 SUBORDER_ID ,\n" + "                 SUBORDER_CODE ,\n" + "                 SUBORDER_TYPE ,\n" + "                 '2021-12-04 02:07:52' AS ETL_TIME\n" + "FROM QCLOUD_DW::ODS_T_DW_NRT141973_DI\n" + "WHERE IMP_DATE = 20211203";
    String replace = query.replace("::", ".");
    lineageInfo.getLineageInfo(replace);
    System.out.println(lineageInfo.getInputTableList());
  }
}
