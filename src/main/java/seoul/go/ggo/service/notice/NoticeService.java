package seoul.go.ggo.service.notice;

import seoul.go.ggo.dto.PageRequestDTO;
import seoul.go.ggo.dto.PageResultDTO;
import seoul.go.ggo.dto.notice.NoticeDTO;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.entity.Notice;


public interface NoticeService {

    // 등록하기
    Long register(NoticeDTO dto);

    //상세보기
    NoticeDTO get(Long bno);

    //삭제하기
    void remove(Long bno);

    //수정하기
    void modify(NoticeDTO dto);

    //목록처리
    PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO requestDTO);

    default Notice dtoToEntity(NoticeDTO dto){
        Member member = Member.builder().id(dto.getWriterId()).build();

        Notice notice = Notice.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return notice;
    }

    default NoticeDTO entityToDTO(Notice entity, Member member){

        NoticeDTO dto = NoticeDTO.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writerId(member.getId()) // 이부분도 dtoToEntity와  같이 변경해줬습니다.
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }

}
