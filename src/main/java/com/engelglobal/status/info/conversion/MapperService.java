package com.engelglobal.status.info.conversion;

import com.engelglobal.status.info.model.EquipmentStatusEntity;
import com.engelglobal.status.info.model.ProductDataEntity;
import com.engelglobal.status.info.model.ProductStatusEntity;
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
