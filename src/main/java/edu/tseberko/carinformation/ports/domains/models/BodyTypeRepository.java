package edu.tseberko.carinformation.ports.domains.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BodyTypeRepository extends JpaRepository<BodyType, Long> {

    @Query("select bd from BodyType bd left join fetch bd.parent")
    List<BodyType> findAllWithParent();

    @Query("select bd from BodyType bd left join fetch bd.parent where bd.id = ?1 ")
    Optional<BodyType> findByIdFetchParent(Long id);

    int countByParent_Id(Long parentId);

    @Query(
            nativeQuery = true,
            value = """
                    select * from body_type
                        where id not in (
                            with recursive cte (id, parent_id) as (
                              select id, parent_id from body_type where parent_id = ?1
                              union all
                              select bt.id, bt.parent_id from body_type bt inner join cte on bt.parent_id = cte.id
                            ) select id from cte)
                        and id <> ?1
                        and id <> (select parent_id from body_type where id = ?1)
                    """
    )
    List<BodyType> getAllParentCandidatesForBodyTypeWithId(long bodyTypeId);

}
