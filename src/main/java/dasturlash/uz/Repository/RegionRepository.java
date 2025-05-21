package dasturlash.uz.Repository;


import dasturlash.uz.Entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
    List<RegionEntity> findAllByVisibleTrueOrderByOrderNumberAsc();
}