package dasturlash.uz.Repository;

import dasturlash.uz.Entity.RegionEntity;
import dasturlash.uz.Entity.SectionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SectionRepository extends CrudRepository<SectionEntity, Integer> {
    List<SectionEntity> findAllByVisibleTrueOrderByOrderNumberAsc();

    @Query("from SectionEntity where visible = true order by orderNumber")
    List<SectionEntity> findAllByOrder_numberSorted();
}