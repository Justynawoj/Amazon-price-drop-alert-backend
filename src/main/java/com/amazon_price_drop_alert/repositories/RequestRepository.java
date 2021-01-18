package com.amazon_price_drop_alert.repositories;

import com.amazon_price_drop_alert.domains.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Override
    List<Request> findAll();

    @Override
    Optional<Request> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    boolean existsById(Long id);

    @Override
    Request save(Request entity);
}
