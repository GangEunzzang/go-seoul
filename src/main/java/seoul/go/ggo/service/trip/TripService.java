package seoul.go.ggo.service.trip;

import net.bytebuddy.description.type.TypeList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoul.go.ggo.dto.PageRequestDTO;
import seoul.go.ggo.dto.PageResultDTO;
import seoul.go.ggo.dto.member.MemberDTO;
import seoul.go.ggo.dto.trip.TripDTO;
import seoul.go.ggo.dto.trip.TripImageDTO;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.entity.Trip;
import seoul.go.ggo.entity.TripImage;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface TripService {

    //등록
    Long register(TripDTO tripDTO);

    //목록 처리
    PageResultDTO<TripDTO, Object[]> getList(PageRequestDTO requestDTO);

    //구 별로 목록 정렬
    PageResultDTO<TripDTO, Object[]> getSortList(PageRequestDTO requestDTO, String place);

    //검색 목록 정렬
    PageResultDTO<TripDTO, Object[]> getSearchList(PageRequestDTO requestDTO,String keyword);

    //내가쓴글 보기
    PageResultDTO<TripDTO, Object[]> getMySearchList(PageRequestDTO requestDTO,String Myid);


    //상세보기
    TripDTO getTrip(Long bno);

    //수정
    void modify(TripDTO tripDTO);

    void removeTrip(Long bno);

    void imageRegister(TripDTO tripDTO);


    default  TripDTO entitiesToDTO(Trip trip, List<Member> member, List<TripImage> tripImage, Double avg, Long replyCount){
        TripDTO tripDTO = TripDTO.builder()
                .bno(trip.getBno())
                .title(trip.getTitle())  // 제목
                .content(trip.getContent()) // 내용
                .place(trip.getPlace())  // 구
//                .writerId(member.getId())   // 작성자의 id
                .regDate(trip.getRegDate()) // 글쓴시간
                .modDate(trip.getModDate()) // 수정시간
                .build();

        List<MemberDTO> memberDTOList = member.stream().map(m -> {
            return MemberDTO.builder()
                    .id(m.getId())
                    .password(m.getPassword())
                    .fromSocial(m.isFromSocial())
                    .build();
        }).collect(Collectors.toList());


        List<TripImageDTO> tripImageDTOList = tripImage.stream().map(image -> {
           return TripImageDTO.builder()
                   .imgName(image.getImgName())
                   .path(image.getPath())
                   .uuid(image.getUuid())
                   .build();
        }).collect(Collectors.toList());

        tripDTO.setWriterId(member.get(0).getId());
        tripDTO.setAvg(avg);
        tripDTO.setReplyCount(replyCount.intValue());
        tripDTO.setMemberDTOList(memberDTOList);

        // tripDTO 안에 있는 List에 tripImageDTOList 저장
        // tripImageDTOList는 TripImage를 DTO로 변형한것
        tripDTO.setImageDTOList(tripImageDTOList);

        return tripDTO;
    }
    //수정용------------------------------------------
    default Map<String, Object> dtoToEntities(TripDTO tripDTO) {

        Map<String, Object> entityMap = new HashMap<>();
        Member member = Member.builder().id(tripDTO.getWriterId()).build();

        Trip trip = Trip.builder()
                .bno(tripDTO.getBno())
                .title(tripDTO.getTitle())
                .content(tripDTO.getContent())
                .place(tripDTO.getPlace())
                .writer(member)
                .build();

        List<TripImageDTO> imageDTOList = tripDTO.getImageDTOList();

        if (imageDTOList != null && imageDTOList.size() > 0) { //TripImageDTO 처리

            List<TripImage> tripImageList = imageDTOList.stream().map(tripImageDTO -> {

                TripImage tripImage = TripImage.builder()
                        .path(tripImageDTO.getPath())
                        .imgName(tripImageDTO.getImgName())
                        .uuid(tripImageDTO.getUuid())
                        .trip(trip)
                        .build();
                return tripImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", tripImageList);
        }

        return entityMap;
    }
    //수정용------------------------------------------------

    default Map<String, Object> dtoToEntity(TripDTO tripDTO) {

        Map<String, Object> entityMap = new HashMap<>();
        Member member = Member.builder().id(tripDTO.getWriterId()).build();

        Trip trip = Trip.builder()
                .bno(tripDTO.getBno())
                .title(tripDTO.getTitle())
                .content(tripDTO.getContent())
                .place(tripDTO.getPlace())
                .writer(member)
                .build();

        entityMap.put("trip", trip);

        List<TripImageDTO> imageDTOList = tripDTO.getImageDTOList();

        if (imageDTOList != null && imageDTOList.size() > 0) { //TripImageDTO 처리

            List<TripImage> tripImageList = imageDTOList.stream().map(tripImageDTO -> {

                TripImage tripImage = TripImage.builder()
                        .path(tripImageDTO.getPath())
                        .imgName(tripImageDTO.getImgName())
                        .uuid(tripImageDTO.getUuid())
                        .trip(trip)
                        .build();
                return tripImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", tripImageList);
        }

        return entityMap;
    }

    //------------------------- ------성구님 댓글 처리 부분 -------------------------------------------
    default TripDTO entityToDTO(Trip trip, List<TripImage> tripImage,Double avg, Long replyCount){
        TripDTO tripDTO = TripDTO.builder()
                .bno(trip.getBno())   // 게시물번호
                .title(trip.getTitle())  // 제목
                .content(trip.getContent()) // 내용
                .place(trip.getPlace())  // 구
                .regDate(trip.getRegDate()) // 글쓴시간
                .modDate(trip.getModDate()) // 수정시간
                .build();

        List<TripImageDTO> tripImageDTOList = tripImage.stream().map(Image -> {
            return TripImageDTO.builder().imgName(Image.getImgName())
                    .path(Image.getPath())
                    .uuid(Image.getUuid())
                    .build();
        }).collect(Collectors.toList());

        tripDTO.setImageDTOList(tripImageDTOList);
        tripDTO.setAvg(avg);
        tripDTO.setReplyCount(replyCount.intValue());

        return tripDTO;
    }
}

