package dasturlash.uz.Repository;


import dasturlash.uz.Entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer> {
    @Query("from RegionEntity where visible = true order by order_number")
    List<RegionEntity> findAllByOrder_numberSorted();
}