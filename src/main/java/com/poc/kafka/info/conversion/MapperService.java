package com.poc.kafka.info.conversion;

import com.poc.kafka.info.model.EquipmentStatusEntity;
import com.poc.kafka.info.model.ProductDataEntity;
import com.poc.kafka.info.model.ProductStatusEntity;
import com.global.ema.model.ProductData;
import com.global.mrd.EquipmentStatus;
import com.global.productStats.ProductStatus;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Arrays;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, imports = Arrays.class)
public interface MapperService
{

  ProductStatusEntity mapInputMachineStatusAvroToMachineStatusEntity(ProductStatus avro);
  ProductDataEntity mapInputMachineStatusDataAvroToMachineStatusDataEntity(ProductData avro);
  EquipmentStatus mapInputAggregateToEquipmentStatusAvro(EquipmentStatusEntity entity);
}
