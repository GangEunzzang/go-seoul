package seoul.go.ggo.repository.trip;

import groovy.util.logging.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import seoul.go.ggo.dto.trip.TripDTO;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.entity.Trip;
import seoul.go.ggo.entity.TripImage;
import seoul.go.ggo.repository.trip.TripImageRepository;
import seoul.go.ggo.repository.trip.TripRepository;

import java.util.*;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TripRepositoryTests {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    TripImageRepository tripImageRepository;

    @Commit
    @Transactional
    @Test
    @DisplayName("여행게시판 더미데이터 입력")
    public void insertTrip() {
        IntStream.rangeClosed(1,30).forEach(i -> {
            Member member = Member.builder().id("sg07731@gmail.com").build();

            Trip trip = Trip.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .place("강남구")
                    .writer(member)
                    .build();

            tripRepository.save(trip);
            System.out.println(trip);
            System.out.println("----------------------------");

            TripImage tripImage = TripImage.builder()
                    .imgName("이미지이름" + i + "jpg")
                    .path("c/upload")
                    .uuid(UUID.randomUUID().toString()+i)
                    .trip(trip)
                    .build();

            tripImageRepository.save(tripImage);
            System.out.println("-----------------------TripImage--------------");
            System.out.println(tripImage);

        });
    }

    @Test
    @Transactional
    @DisplayName("여행게시판 목록 테스트")
    public void join(){
        PageRequest pageRequest = PageRequest.of(0,30);

        Page<Object[]> result = tripRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    @Transactional
    @DisplayName("여행 게시판 여행지별 정렬 테스트")
    public void sortList(){
        PageRequest pageRequest = PageRequest.of(0,30);

        Page<Object[]> result = tripRepository.getListSortPage(pageRequest, "강남구");

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

}


