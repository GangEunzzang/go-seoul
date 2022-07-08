package seoul.go.ggo.repository.trip;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import seoul.go.ggo.entity.TripImage;

public interface TripImageRepository extends JpaRepository<TripImage, Long> {

    @Query("select ti.inum from TripImage ti left outer join Trip t on ti.trip=t where t.bno=:bno")
    Long findByBno(Long bno);
}
