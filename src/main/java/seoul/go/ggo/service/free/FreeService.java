package seoul.go.ggo.service.free;

import seoul.go.ggo.dto.PageRequestDTO;
import seoul.go.ggo.dto.PageResultDTO;
import seoul.go.ggo.dto.free.FreeDTO;
import seoul.go.ggo.entity.Free;
import seoul.go.ggo.entity.Member;


public interface FreeService {
    Long register(FreeDTO dto); //등록
    void modify(FreeDTO freeDTO); //수정
    void removeWithReplies(Long bno); //삭제

    PageResultDTO<FreeDTO, Object[]> getList(PageRequestDTO pageRequestDTO); //목록


    default Free dtoToEntity(FreeDTO dto) {
        Member member = Member.builder().id(dto.getWriterId()).build();
        Free free = Free.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return free;
    }

    default FreeDTO entityToDTO(Free free, Member member, Long replyCount) {
        FreeDTO freeDTO = FreeDTO.builder()
                .bno(free.getBno())
                .title(free.getTitle())
                .content(free.getContent())
                .writerId(member.getId())
                .regDate(free.getRegDate())
                .modDate(free.getModDate())
                .replyCount(replyCount.intValue())
                .build();
        return freeDTO;
    }



    FreeDTO get(Long bno);
}