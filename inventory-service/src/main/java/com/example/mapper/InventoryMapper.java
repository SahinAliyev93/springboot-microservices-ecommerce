package com.example.mapper;

import com.example.dto.InventoryRequest;
import com.example.dto.InventoryResponse;
import com.example.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DateMapper.class)
public interface InventoryMapper {


    Inventory toEntity(InventoryRequest request);

    InventoryResponse toResponse(Inventory inventory);
}
