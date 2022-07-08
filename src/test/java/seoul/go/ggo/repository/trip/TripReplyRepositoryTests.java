package seoul.go.ggo.repository.trip;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.entity.Trip;
import seoul.go.ggo.entity.TripReply;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class TripReplyRepositoryTests {
    @Autowired
    private TripReplyRepository tripReplyRepository;

    @Autowired
    private TripImageRepository tripImageRepository;



    @Test
    public void insertTripReply(){
        IntStream.rangeClosed(1,20).forEach(i->{
            Long bno=(long)(Math.random()*30)+2;

            Trip trip=Trip.builder().bno(bno).build();
            Member member=Member.builder().id("user"+i+"google.com").build();

            TripReply tripReply=TripReply.builder()
                    .writer(member)
                    .trip(trip)
                    .star((int)(Math.random()*5)+1)
                    .content("content"+i)
                    .build();
            tripReplyRepository.save(tripReply);
        });
    }

    @Test
    public void testGetTripReply(){
        Trip trip=Trip.builder().bno(2L).build();
        List<TripReply> result=tripReplyRepository.findByTrip(trip);

        result.forEach(tripReply -> {
            System.out.println("========================");
            System.out.println(tripReply.getRno());
            System.out.println(tripReply.getStar());
            System.out.println(tripReply.getContent());
            System.out.println(tripReply.getWriter().getId());
            System.out.println("========================");
        });
    }

    @Test
    public void testFindByBno(){
        Long inum=tripImageRepository.findByBno(63L);
        System.out.println("==========================");
        System.out.println(tripImageRepository.findById(inum));
        System.out.println("==========================");
    }

}
