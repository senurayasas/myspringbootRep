package com.senyasas.cs.model.mapping;

import com.senyasas.cs.model.api.request.my.CustomerRequest;
import com.senyasas.cs.model.entity.CustomerModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
componentModel = "spring")
public interface CustomerMapper {

    CustomerModel fromRequest(CustomerRequest request);

}
