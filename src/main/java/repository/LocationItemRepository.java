package repository;

import com.utfpr.moteloo24s.model.LocationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationItemRepository extends JpaRepository<LocationItem, UUID> {
}