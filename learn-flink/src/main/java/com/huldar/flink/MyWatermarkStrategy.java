package com.huldar.flink;

import scala.Tuple2;

import org.apache.flink.api.common.eventtime.Watermark;
import org.apache.flink.api.common.eventtime.WatermarkGenerator;
import org.apache.flink.api.common.eventtime.WatermarkGeneratorSupplier;
import org.apache.flink.api.common.eventtime.WatermarkOutput;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/9 23:30
 */
public class MyWatermarkStrategy implements WatermarkStrategy<Tuple2<String,Long>> {

  private long maxOutOfOrderness;

  public MyWatermarkStrategy(long maxOutOfOrderness) {
    this.maxOutOfOrderness = maxOutOfOrderness;
  }

  @Override
  public WatermarkGenerator<Tuple2<String, Long>> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
    return new WatermarkGenerator<Tuple2<String, Long>>() {
      private long timestamp = Long.MIN_VALUE;

      @Override
      public void onEvent(Tuple2<String, Long> event, long eventTimestamp, WatermarkOutput output) {
        timestamp = Math.max(eventTimestamp, event._2);
      }

      @Override
      public void onPeriodicEmit(WatermarkOutput output) {
        System.out.println(timestamp - maxOutOfOrderness);
        output.emitWatermark(new Watermark(timestamp - maxOutOfOrderness));
      }
    };
  }
}
