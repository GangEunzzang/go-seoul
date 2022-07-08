package seoul.go.ggo.repository.trip;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import seoul.go.ggo.repository.member.MemberRepository;

import javax.transaction.Transactional;

@SpringBootTest
public class MemberRepositoyTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TripReplyRepository tripReplyRepository;

    @Autowired
    private TripRepository tripRepository;

    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){

        Long rno=(long)74;
        tripReplyRepository.deleteById(rno);
    }
}
