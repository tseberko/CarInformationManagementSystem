package edu.tseberko.carinformation.ports.domains.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    @Query("select m from Model m left join fetch m.bodyType")
    List<Model> findAllWithBodyType();

    @Query("select m from Model m join fetch m.bodyType where m.id = ?1")
    Optional<Model> findByIdWithBodyType(long id);

    int countByBodyType_Id(long bodyTypeId);

}
