package seoul.go.ggo.repository.member;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import seoul.go.ggo.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.fromSocial = :social and m.id =:id")
    Optional<Member> findById(@Param("id")String id, @Param("social") boolean social);

//    @Query("select m from Member m where m.id=:id and m.password=:password")
//    boolean findByAdmin(@Param("id") String id, @Param("password") String password);
}

