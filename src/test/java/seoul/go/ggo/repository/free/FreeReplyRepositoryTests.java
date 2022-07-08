package seoul.go.ggo.repository.free;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seoul.go.ggo.entity.Free;
import seoul.go.ggo.entity.FreeReply;
import seoul.go.ggo.entity.Member;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class FreeReplyRepositoryTests {

    @Autowired
    private FreeReplyRepository freeReplyRepository;


    @Test
    public void insertFree_reply(){
        IntStream.rangeClosed(1,30).forEach(i->{
            long bno=(long)(Math.random()*10)+1;
            Free free = Free.builder().bno(bno).build();
            Member member=Member.builder().id("user"+i+"@google.com").build();
            FreeReply freeReply = FreeReply.builder()
                    .content("댓글...."+i)
                    .free(free)
                    .writer(member)
                    .build();
            freeReplyRepository.save(freeReply);
        });
    }

    @Transactional
    @Test
    public void Reply01(){
        Optional<FreeReply> result = freeReplyRepository.findById(1L);

        FreeReply freeReply = result.get();

        System.out.println(freeReply);
        System.out.println(freeReply.getFree());

    }


}
