package com.senyasas.cs.repository;

import com.senyasas.cs.model.entity.my;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface myRepository extends PagingAndSortingRepository<my, Long> {
    Optional<my> findByText(String text);

    // Some examples:
    //findByLastnameAndFirstname … where x.lastname = ?1 and x.firstname = ?2
    //findByActiveTrue() … where x.active = true
    //findByStartDateBefore … where x.startDate < ?1
}
