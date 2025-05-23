package dasturlash.uz.Repository;


import dasturlash.uz.Entity.RegionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer> {


    Optional<RegionEntity> findByIdAndVisibleIsTrue(Integer id);

    @Transactional
    @Modifying
    @Query("update RegionEntity set visible = false where id = ?1")
    int updateVisibleById(Integer id);

    @Query("from RegionEntity where visible = true order by orderNumber")
    List<RegionEntity> findAllByOrderNumberSorted();

    Optional<RegionEntity> findByRegionKeyAndVisibleIsTrue(String key);

}