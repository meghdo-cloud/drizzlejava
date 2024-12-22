package cloud.meghdo.drizzlejava.repository;

import cloud.meghdo.drizzlejava.entity.tablea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tablearepository extends JpaRepository<tablea, Long> {
}
