package com.poc.kafka.info.service;

import com.poc.kafka.info.conversion.MapperService;
import com.poc.kafka.info.model.EquipmentStatusEntity;
import com.global.ema.model.ProductData;
import com.global.productStats.ProductStatus;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.BiFunction;

@Service
@Slf4j
@RequiredArgsConstructor
public class GlobalAggregationService
{

  private final MapperService mapperService;

    @Bean
    public BiFunction<KStream<String, SpecificRecord>, KStream<String, SpecificRecord>, KStream<String, SpecificRecord>> processMachineStatus() {
      return (machineStatusStream, machineStatusDataStream) -> machineStatusStream
          .join(machineStatusDataStream
              , joiner()
              , JoinWindows.of(Duration.ofSeconds(100))
              , StreamJoined.with(Serdes.String(), new SpecificAvroSerde<>(), new SpecificAvroSerde<>())
          )
          .peek((k, v) -> log.info("Done -> {}", v))
          .filterNot((k, v) -> v == null);
    }

  private ValueJoiner<SpecificRecord, SpecificRecord, SpecificRecord> joiner() {
    return (machineStatus, machineStatusData) -> {
      if (machineStatusData == null) {
        return null;
      }
      final var avroAgg =
          new EquipmentStatusEntity().toBuilder(); // todo: make the aggregation object
      final var machineStatusEntity =
          mapperService.mapInputProductStatusAvroToProductStatusEntity((ProductStatus) machineStatus);
      final var machineStatusDataEntity =
          mapperService.mapInputProductDataAvroToProductDataEntity((ProductData) machineStatusData);
      avroAgg
          .productId(machineStatusDataEntity.getProductId())
          .status(EquipmentStatusEntity.StatusTypes.ONLINE);

      return mapperService.mapInputAggregateToEquipmentStatusAvro(avroAgg.build());
    };
  }

  //  @Bean
  //  public BiConsumer<KStream<String, SpecificRecord>, KStream<String, SpecificRecord>> processMachineStatus() {
  //    return (machineStatusStream, machineStatusDataStream) -> machineStatusStream
  //        //        .peek((k, v) -> log.info("Consuming {}: {}", k, v));
  //
  //        .join(machineStatusDataStream
  //            , joiner()
  //            , JoinWindows.of(Duration.ofSeconds(100))
  //              //                , StreamJoined.with(Serdes.String(), new SpecificAvroSerde<>(), new SpecificAvroSerde<>())
  //        )
  //        .peek((k, v) -> log.info("Done -> {}", v));
  //    //            .filterNot((k, v) -> v == null);
  //
  //
  //  }
}
