package seoul.go.ggo.service.free;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seoul.go.ggo.dto.free.FreeReplyDTO;
import seoul.go.ggo.entity.Free;
import seoul.go.ggo.entity.FreeReply;
import seoul.go.ggo.repository.free.FreeReplyRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FreeReplyServiceImpl implements FreeReplyService {

    private final FreeReplyRepository freeReplyRepository;

    @Override
    public Long register(FreeReplyDTO freeReplyDTO) {

        FreeReply freeReply = dtoToEntity(freeReplyDTO);

        freeReplyRepository.save(freeReply);

        return freeReply.getRno();

    }

    @Override
    public List<FreeReplyDTO> getList(Long bno) {

        List<FreeReply> result = freeReplyRepository
                .getRepliesByFreeOrderByRno(Free.builder().bno(bno).build());

        return result.stream().map(freeReply -> entityToDTO(freeReply)).collect(Collectors.toList());

    }

    @Override
    public void modify(FreeReplyDTO replyDTO) {
        FreeReply freeReply = dtoToEntity(replyDTO);

        freeReplyRepository.save(freeReply);
    }

    @Override
    public void remove(Long rno){

        freeReplyRepository.deleteById(rno);

    }


}
