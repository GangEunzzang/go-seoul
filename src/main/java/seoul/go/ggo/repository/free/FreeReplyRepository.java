package seoul.go.ggo.repository.free;

import org.springframework.data.jpa.repository.JpaRepository;
import seoul.go.ggo.entity.Free;
import seoul.go.ggo.entity.FreeReply;

import java.util.List;

public interface FreeReplyRepository extends JpaRepository<FreeReply, Long> {
//    @Modifying
//    @Query("delete from Reply r where r.board.bno =:bno ")
//    void deleteByBno(Long bno);

    List<FreeReply> getRepliesByFreeOrderByRno(Free build);
}
