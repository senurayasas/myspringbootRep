package com.senyasas.cs.service;

import com.senyasas.cs.model.api.request.my.myRequest;
import com.senyasas.cs.model.entity.my;
import com.senyasas.cs.model.mapping.myMapper;
import com.senyasas.cs.repository.myRepository;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class myServiceTest {

    private final myRepository myRepository = mock(myRepository.class);

    private final myMapper myMapper = Mappers.getMapper(myMapper.class);

    //@Test
    void method_name__conditions__expected_outcome() {
        //Given
        final var request = myRequest.builder()
                .input("sample text")
                .build();

        when(myRepository.save(any()))
                .thenAnswer(a -> a.getArgument(0));


        //When
        //final var result = myService.example(request);

        //Then
        //assertThat(result.text()).isEqualTo("some other text");
        verify(myRepository).save(any(my.class));
    }
}
