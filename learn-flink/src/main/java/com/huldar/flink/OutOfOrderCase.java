package com.huldar.flink;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.flink.api.common.eventtime.Watermark;
import org.apache.flink.api.common.eventtime.WatermarkGenerator;
import org.apache.flink.api.common.eventtime.WatermarkOutput;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/9 19:53
 */
public class OutOfOrderCase {
  private static final Logger logger = LoggerFactory.getLogger(OutOfOrderCase.class);

  public static void main(String[] args) throws Exception {
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    // 1.12以后默认使用的是event time
    // env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

    env.setParallelism(1);
    logger.info("{}",1);
    env.getConfig().setAutoWatermarkInterval(1);

    // WatermarkStrategy<Tuple2<String, Long>> strategy1 =
    //     WatermarkStrategy.forGenerator(
    //         (WatermarkStrategy<Tuple2<String, Long>>)
    //             context ->
    //                 new WatermarkGenerator<Tuple2<String, Long>>() {
    //                   @Override
    //                   public void onEvent(
    //                       Tuple2<String, Long> event, long eventTimestamp, WatermarkOutput output) {
    //                     output.emitWatermark(new Watermark(event.f1 - 5000));
    //                   }
    //
    //                   @Override
    //                   public void onPeriodicEmit(WatermarkOutput output) {}
    //                 })
    //             .withTimestampAssigner((element, time) -> element.f1);

    WatermarkStrategy<Tuple2<String, Long>> strategy =
        WatermarkStrategy
            // .<Tuple2<String, Long>>forBoundedOutOfOrderness(Duration.ofSeconds(5))
            .<Tuple2<String, Long>>forBoundedOutOfOrderness(Duration.ofSeconds(5))
            // 取事件时间为stream recordTimestamp
            .withTimestampAssigner((element, recordTimestamp) -> element.f1);

    env.addSource(
            new SourceFunction<Tuple2<String, Long>>() {

              @Override
              public void run(SourceContext<Tuple2<String, Long>> ctx) throws Exception {
                ctx.collect(Tuple2.of("key", 0L));
                Thread.sleep(1000);
                ctx.collect(Tuple2.of("key", 1000L));
                Thread.sleep(1000);
                ctx.collect(Tuple2.of("key", 2000L));
                Thread.sleep(1000);
                ctx.collect(Tuple2.of("key", 3000L));
                Thread.sleep(1000);
                ctx.collect(Tuple2.of("key", 3000L));
                Thread.sleep(1000);
                ctx.collect(Tuple2.of("key", 4000L));
                Thread.sleep(1000);

                ctx.collect(Tuple2.of("key", 5000L)); // 乱序 17000
                Thread.sleep(1000);
                // out of order
                ctx.collect(Tuple2.of("key", 3000L));
                Thread.sleep(1000);
                ctx.collect(Tuple2.of("key", 6000L));
                Thread.sleep(1000);
                ctx.collect(Tuple2.of("key", 6000L));
                Thread.sleep(1000);
                ctx.collect(Tuple2.of("key", 7000L)); // 19 + 18  + 5
                Thread.sleep(1000);
                ctx.collect(Tuple2.of("key", 8000L));
                Thread.sleep(1000);
                ctx.collect(Tuple2.of("key", 10000L));
                Thread.sleep(1000);
                // out of order
                ctx.collect(Tuple2.of("key", 8000L));
                Thread.sleep(1000);
                ctx.collect(Tuple2.of("key", 9000L));
                Thread.sleep(1000);
                // source is finite, so it will have an implicit MAX watermark when it finishes
              }

              @Override
              public void cancel() {}

            })
        .assignTimestampsAndWatermarks(strategy)
        .keyBy(t -> t.f0)
        .window(TumblingEventTimeWindows.of(Time.of(5, TimeUnit.SECONDS)))
        .sum(1)
        .print();

    env.execute("Out of order");
  }
}
