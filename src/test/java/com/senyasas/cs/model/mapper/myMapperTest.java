package com.senyasas.cs.model.mapper;

import com.senyasas.cs.model.mapping.myMapper;
import com.senyasas.cs.model.entity.my;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class myMapperTest {

    private static final myMapper MAPPER = Mappers.getMapper(myMapper.class);

    //@Test
    void method_name__conditions__expected_outcome() {
        //Given
        final var my = new my().withText("example");

        //When
        //final var myResponse = MAPPER.toResponse(my);

        //Then
        //assertThat(myResponse.text()).isEqualTo("example");
    }
}
