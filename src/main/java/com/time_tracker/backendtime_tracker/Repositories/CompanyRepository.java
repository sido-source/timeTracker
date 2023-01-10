package com.time_tracker.backendtime_tracker.Repositories;

import com.time_tracker.backendtime_tracker.Entities.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public interface CompanyRepository extends CrudRepository<Company, Integer> {

    @Override
    Iterable<Company> findAll();

    @Override
    Optional<Company> findById(Integer integer);
    Optional<Company> findById(String string);

    @Override
    void deleteById(Integer integer);

    @Override
    <S extends Company> S save(S entity);

//    @Query("SELECT c FROM Company c WHERE c.name = :name")
//    Optional<Company> findByName(@Param("name") String name);
    Optional<Company> findByName(String name);
}
