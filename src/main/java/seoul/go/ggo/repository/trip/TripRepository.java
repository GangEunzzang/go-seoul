package seoul.go.ggo.repository.trip;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.entity.Trip;
import seoul.go.ggo.entity.TripImage;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
//    @Query(value = "select a.bno b.img_name, b.path, b.uuid" +
//           "from Trip a" +
//            "right outer join trip_image b on a.bno = b.trip_bno",nativeQuery = true)
//        @Query(value = "select a.bno, b.img_name, b.path, b.uuid" +
//                        " from trip a " +
//                        " LEFT OUTER JOIN trip_image b " +
//                        " ON a.bno = b.trip_bno", nativeQuery = true)

    @Query("select a , m , b, avg(coalesce(r.star,0)), count(distinct r)" +
            " from Trip a " +
            " Left OUTER JOIN TripImage b ON b.trip = a " +
            " Left OUTER JOIN Member m ON m.id = a.writer.id" +
            " left outer join TripReply r on r.trip = a group by a")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select a , m , b, avg(coalesce(r.star,0)), count(distinct r) " +
            " from Trip a " +
            " Left OUTER JOIN TripImage b ON b.trip = a " +
            " Left OUTER JOIN Member m ON m.id = a.writer.id" +
            " left outer join TripReply r on r.trip = a " +
            " where a.place = :place " +
            " group by a")
    Page<Object[]> getListSortPage(Pageable pageable,@Param("place") String place) ;
    // -----------------a file list 없다고 오류 뜸--------------------------------
//    @Query(value = "SELECT  b.trip_bno, a.writer_id, b.img_name, b.path, b.uuid" +
//            " from trip a" +
//            " LEFT OUTER JOIN trip_image b ON a.bno = b.trip_bno" +
//            " LEFT OUTER JOIN member m ON a.writer_id = m.id" ,nativeQuery = true)
//    Page<Object[]> getListPage(Pageable pageable);

    @Query("select a.bno , m.id  ,b.imgName, b.path, b.uuid"  +
            " from Trip a " +
            " Left OUTER JOIN TripImage b ON b.trip = a " +
            " Left OUTER JOIN Member m ON m.id = a.writer")
    List<Trip> getList();

    @Query("select a,mi, m,avg(coalesce(r.star,0)),  count(distinct r)"+
            " from Trip a " +
            " left outer join TripImage mi on mi.trip = a " +
            " left outer join TripReply r on r.trip=a" +
            " left outer join Member m on m.id = a.writer.id"+
            " where a.bno = :bno group by mi")
    List<Object[]> getTripWithAll(@Param("bno")Long bno);

    @Modifying
    @Query("delete from Trip where bno=:bno")
    void deleteMovieById(Long bno);

    @Query("select a , m , b, avg(coalesce(r.star,0)), count(distinct r) " +
            " from Trip a " +
            " Left OUTER JOIN TripImage b ON b.trip = a " +
            " Left OUTER JOIN Member m ON m.id = a.writer.id" +
            " Left OUTER JOIN TripReply r on r.trip = a " +
            " where a.content like %:keyword% or a.title like %:keyword% or a.place like %:keyword% or a.writer.id like %:keyword%" +
            " group by a")
    Page<Object[]> getSearch(Pageable pageable,@Param("keyword") String keyword);

    @Query("select a , m , b, avg(coalesce(r.star,0)), count(distinct r) " +
            " from Trip a " +
            " Left OUTER JOIN TripImage b ON b.trip = a " +
            " Left OUTER JOIN Member m ON m.id = a.writer.id" +
            " Left OUTER JOIN TripReply r on r.trip = a " +
            " where a.writer.id like %:Myid%" +
            " group by a")
    Page<Object[]> getMySearch(Pageable pageable,@Param("Myid") String Myid);


}
