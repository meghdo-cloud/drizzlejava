package cloud.meghdo.drizzle.repository;

import cloud.meghdo.drizzle.entity.tablea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tablearepository extends JpaRepository<tablea, Long> {
}
