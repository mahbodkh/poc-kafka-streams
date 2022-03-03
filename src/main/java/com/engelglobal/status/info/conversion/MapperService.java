package com.engelglobal.status.info.conversion;

import at.engel.edgedevice.ema.machineStats_agent.MachineStatus;
import com.engel.ema.model.MachineStatusData;
import com.engelglobal.mrd.EquipmentStatus;
import com.engelglobal.status.info.model.EquipmentStatusEntity;
import com.engelglobal.status.info.model.ProductDataEntity;
import com.engelglobal.status.info.model.ProductStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Arrays;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, imports = Arrays.class)
public interface MapperService
{

  ProductStatusEntity mapInputMachineStatusAvroToMachineStatusEntity(MachineStatus avro);
  ProductDataEntity mapInputMachineStatusDataAvroToMachineStatusDataEntity(MachineStatusData avro);
  EquipmentStatus mapInputAggregateToEquipmentStatusAvro(EquipmentStatusEntity entity);
}
