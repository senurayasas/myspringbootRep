package com.senyasas.cs.repository;

import com.senyasas.cs.model.entity.my;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class myRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private myRepository repository;

    @Test
    void method_name__conditions__expected_outcome() {
        //Given
        final var my = new my()
                .withText("example text");

        testEntityManager.persist(my);
        testEntityManager.flush();

        //When
        Optional<my> byText = repository.findByText("example text");

        //Then
        assertThat(byText).isPresent().contains(my);
    }
}
