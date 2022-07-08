package seoul.go.ggo.repository.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import seoul.go.ggo.entity.Trip;
import seoul.go.ggo.entity.TripReply;

import java.util.List;

public interface TripReplyRepository extends JpaRepository<TripReply,Long> {
//    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<TripReply> findByTrip(Trip trip);

//    @Query(value="select * from trip_reply where trip_reply.trip_bno=bno",nativeQuery = true)
//    Trip findByBno(Long bno);

    //@Modifying
    //@Query("delete from trip_reply tr where tr.trip.bno=:bno;")
    //void deleteByBno(Long bno);

//    @Modifying
//    @Query(value="delete from trip_reply where trip_reply.trip=:bno;",nativeQuery = true)
//    void deleteByBno(Long bno);
}
