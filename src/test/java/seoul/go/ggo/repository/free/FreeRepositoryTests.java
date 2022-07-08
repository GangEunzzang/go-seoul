package seoul.go.ggo.repository.free;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import seoul.go.ggo.entity.Free;
import seoul.go.ggo.entity.Member;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class FreeRepositoryTests {

    @Autowired
    private FreeRepository freeRepository;

    @Test
    public void insertFree(){

        IntStream.rangeClosed(1,30).forEach(i->{

            Member member = Member.builder().id("user"+i+"google.com").build();

            Free free = Free.builder()
                    .title("제목,,,,,"+i)
                    .content("컨텐츠,,,,,"+i)
                    .writer(member)
                    .build();
            freeRepository.save(free);
        });
    }

    @Transactional
    @Test
    public void testRead01(){
        Optional<Free> result = freeRepository.findById(10L);

        Free free = result.get();

        System.out.println(free);
        System.out.println(free.getWriter());
    }


//    @Test
//    public void testReadWithWriter(){ //안됨ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
//        Object result = freeRepository.getFreeWithWriter(10L);
//
//        Object[] arr = (Object[])result;
//        System.out.println(Arrays.toString(arr));
//    }

    @Test
    public void testGetFreeWithReply(){
        List<Object[]> result = freeRepository.getFreeWithReply(10L);

        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testWithReplyCount() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Object[]> result = freeRepository.getFreeWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[])row;

            System.out.println(Arrays.toString(arr));
        });
    }
}
