package seoul.go.ggo.service.free;

import seoul.go.ggo.dto.free.FreeReplyDTO;
import seoul.go.ggo.entity.Free;
import seoul.go.ggo.entity.FreeReply;
import seoul.go.ggo.entity.Member;

import java.util.List;

public interface FreeReplyService {

    Long register(FreeReplyDTO freeReplyDTO);
    List<FreeReplyDTO> getList(Long bno);

    default FreeReply dtoToEntity(FreeReplyDTO freeReplyDTO){
        Free free = Free.builder().bno(freeReplyDTO.getBno()).build();
        Member member = Member.builder().id(freeReplyDTO.getId()).build();

        FreeReply reply = FreeReply.builder()
                .rno(freeReplyDTO.getRno())
                .content(freeReplyDTO.getContent())
                .writer(member)
                .free(free)
                .build();
        return reply;
    }

    default FreeReplyDTO entityToDTO(FreeReply freeReply) {

        FreeReplyDTO dto = FreeReplyDTO.builder()
                .rno(freeReply.getRno())
                .content(freeReply.getContent())
                .id(freeReply.getWriter().getId())
                .regDate(freeReply.getRegDate())
                .modDate(freeReply.getModDate())
                .build();

        return dto;

    }

    void modify(FreeReplyDTO replyDTO);

    void remove(Long rno);
}
