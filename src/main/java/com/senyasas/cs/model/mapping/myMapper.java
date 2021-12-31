package com.senyasas.cs.model.mapping;

import com.senyasas.cs.model.entity.my;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface myMapper {

    //myResponse toResponse(my my);

    my fromText(String text);

}
