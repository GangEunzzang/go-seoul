package seoul.go.ggo.repository.notice;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import seoul.go.ggo.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>, QuerydslPredicateExecutor<Notice> {
    // 위와같이 조인해서 repository 생성
    @Query("select n, w " +
            " from Notice n " +
            " left join n.writer w " +
            " on n.writer = w.id")
    Page<Object[]> getNoticeList(Pageable pageable);

    @Query("select n, w " +
            "from Notice n " +
            "left join n.writer w " +
            "where n.bno = :bno")
    Object getNoticeByBno(@Param("bno")Long bno);



}
