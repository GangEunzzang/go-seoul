package seoul.go.ggo.service.trip;

import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoul.go.ggo.dto.PageRequestDTO;
import seoul.go.ggo.dto.PageResultDTO;
import seoul.go.ggo.dto.trip.TripDTO;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.entity.Trip;
import seoul.go.ggo.entity.TripImage;
import seoul.go.ggo.repository.trip.TripImageRepository;
import seoul.go.ggo.repository.trip.TripReplyRepository;
import seoul.go.ggo.repository.trip.TripRepository;

import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    private final TripImageRepository tripImageRepository;

    private final TripReplyRepository tripReplyRepository;

    //등록
    @Override
    public Long register(TripDTO tripDTO) {
        Map<String, Object> entityMap = dtoToEntity(tripDTO);
        Trip trip = (Trip) entityMap.get("trip");
        List<TripImage> tripImageList = (List<TripImage>) entityMap.get("imgList");

        tripRepository.save(trip);

        tripImageList.forEach(tripImage -> {
            tripImageRepository.save(tripImage);
        });

        return trip.getBno();
    }

    @Override
    public void imageRegister(TripDTO tripDTO) {
        Map<String, Object> entityMap = dtoToEntities(tripDTO);
        List<TripImage> tripImageList = (List<TripImage>) entityMap.get("imgList");
        System.out.println("=======imgregister========");
        System.out.println(tripImageList);
        System.out.println("=======imgregister========");
        tripImageList.forEach(tripImage -> {
            tripImageRepository.save(tripImage);
        });
    }


    @Transactional
    @Override
    public PageResultDTO<TripDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());

        Page<Object[]> result = tripRepository.getListPage(pageable);

        System.out.println("-------------------TripServiceImpl ----------------");
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });


        Function<Object[], TripDTO> fn = (arr -> entitiesToDTO(
                (Trip) arr[0],
                (List<Member>) (Arrays.asList((Member) arr[1])),
                (List<TripImage>) (Arrays.asList((TripImage) arr[2])),
                (Double) arr[3],
                (Long)arr[4]
        ));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<TripDTO, Object[]> getSortList(PageRequestDTO requestDTO , String place) {

            Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());

            Page<Object[]> result = tripRepository.getListSortPage(pageable, place);

        System.out.println("-----------------여행게시판 여행지별 정렬 --------------------");
        result.getContent().forEach(arr -> {
            log.info("----------------asdfasdfafsd===============");
            log.info(Arrays.toString(arr));
        });

        Function<Object[], TripDTO> fn = (arr -> entitiesToDTO(
                (Trip) arr[0],
                (List<Member>) (Arrays.asList((Member) arr[1])),
                (List<TripImage>) (Arrays.asList((TripImage) arr[2])),
                (Double) arr[3],
                (Long)arr[4]
        ));

        return new PageResultDTO<>(result, fn);
    }

    //검색
    @Override
    public PageResultDTO<TripDTO, Object[]> getSearchList(PageRequestDTO requestDTO, String keyword) {

        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());

        Page<Object[]> result = tripRepository.getSearch(pageable,keyword);

        System.out.println("-----------------검색 --------------------");
        result.getContent().forEach(arr -> {
            log.info("----------------검색===============");
            log.info(Arrays.toString(arr));
        });

        Function<Object[], TripDTO> fn = (arr -> entitiesToDTO(
                (Trip) arr[0],
                (List<Member>) (Arrays.asList((Member) arr[1])),
                (List<TripImage>) (Arrays.asList((TripImage) arr[2])),
                (Double) arr[3],
                (Long)arr[4]
        ));

        return new PageResultDTO<>(result, fn);
    }

    //내가쓴글 보기
    @Override
    public PageResultDTO<TripDTO, Object[]> getMySearchList(PageRequestDTO requestDTO, String Myid) {

        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());

        Page<Object[]> result = tripRepository.getMySearch(pageable,Myid);

        System.out.println("-----------------검색 --------------------");
        result.getContent().forEach(arr -> {
            log.info("----------------검색===============");
            log.info(Arrays.toString(arr));
        });

        Function<Object[], TripDTO> fn = (arr -> entitiesToDTO(
                (Trip) arr[0],
                (List<Member>) (Arrays.asList((Member) arr[1])),
                (List<TripImage>) (Arrays.asList((TripImage) arr[2])),
                (Double) arr[3],
                (Long)arr[4]
        ));

        return new PageResultDTO<>(result, fn);
    }

    /* ------------------아래부터 성구님 작성 부분 ---------------------------*/

    //상세보기
    @Override
    public TripDTO getTrip(Long bno) {
        List<Object[]> result=tripRepository.getTripWithAll(bno);

        Trip trip=(Trip) result.get(0)[0];

        List<TripImage> tripImageList = new ArrayList<>();
        List<Member> tripMemberList = new ArrayList<>();

        result.forEach(arr->{
            TripImage tripImage=(TripImage) arr[1];
            tripImageList.add(tripImage);

            Member member = (Member) arr[2];
            tripMemberList.add(member);
        });

        Double avg=(Double) result.get(0)[3];
        Long replyCount=(Long) result.get(0)[4];

        return entitiesToDTO(trip,tripMemberList,tripImageList,avg,replyCount);
    }

    //수정하기
    @Override
    public void modify(TripDTO tripDTO) {
        Trip trip=tripRepository.getOne(tripDTO.getBno());

        trip.changeTitle(tripDTO.getTitle());
        trip.changeContent(tripDTO.getContent());
        trip.changePlace(tripDTO.getPlace());

        tripRepository.save(trip);
    }


    @Override
    public void removeTrip(Long bno) {
        tripRepository.deleteById(bno);
    }


}

