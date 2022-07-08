package seoul.go.ggo.repository.free;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import seoul.go.ggo.entity.Free;

import java.util.List;

public interface FreeRepository extends JpaRepository<Free, Long>{

//    @Query("select b, w " +
//            " from Free b " +
//            " left join b.writer w" +
//            " where b.bno = :bno")
//    Object getBoardWithWriter(@Param("bno") Long bno);
//
//    @Query("SELECT b, r " +
//            " FROM Free b " +
//            " LEFT JOIN Reply r " +
//            " ON r.board = b WHERE b.bno = :bno")
//    List<Object[]> getBoardWithReply(@Param("bno")Long bno);



    @Query(value = "SELECT f.bno, r.* " +
            " FROM Free f LEFT JOIN Free_reply r " +
            " ON r.free_bno = f.bno " +
            " WHERE f.bno = :bno",nativeQuery = true)
    List<Object[]> getFreeWithReply(@Param("bno") Long bno);

//    @Query(value ="SELECT f, w, count(r) " +
//            " FROM Free f " +
//            " LEFT JOIN f.writer_id w " +
//            " LEFT JOIN free_replt r ON r.free = f " +
//            " GROUP BY f",
//            countQuery ="SELECT count(f) FROM Free f")
//    Page<Object[]> getFreeWithReplyCount(Pageable pageable);

    @Query(value ="SELECT b, w, count(r)" +
            " FROM Free b " +
            " LEFT JOIN b.writer w" +
            " LEFT JOIN FreeReply r ON r.free = b " +
            " GROUP BY b",
            countQuery = "SELECT count(b) FROM Free b")
    Page<Object[]> getFreeWithReplyCount(Pageable pageable);

    @Query(value = "SELECT b, w, count(r)" +
            " FROM Free b LEFT JOIN b.writer w " +
            " LEFT OUTER JOIN FreeReply r ON r.free = b" +
            " WHERE b.bno = :bno")
    Object getFreeByBno(@Param("bno") Long bno);


}
