package seoul.go.ggo.service.free;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import seoul.go.ggo.dto.PageRequestDTO;
import seoul.go.ggo.dto.PageResultDTO;
import seoul.go.ggo.dto.free.FreeDTO;
import seoul.go.ggo.entity.Free;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.repository.free.FreeReplyRepository;
import seoul.go.ggo.repository.free.FreeRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class FreeServiceImpl implements FreeService {

    private final FreeRepository repository;

    private final FreeReplyRepository replyRepository;

    //자유게시판 등록
    @Override
    public Long register(FreeDTO dto) {
        Free free = dtoToEntity(dto);
        repository.save(free);
        log.info("되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐");
        return free.getBno();


    }


    @Override
    public void removeWithReplies(Long bno) {
        repository.deleteById(bno);

    }

    //자유게시판 목록보기
    @Override
    public PageResultDTO<FreeDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        Function<Object[], FreeDTO> fn = (en -> entityToDTO((Free)
                en[0], (Member) en[1], (Long) en[2]));

        Page<Object[]> result = repository.getFreeWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public FreeDTO get(Long bno) {
        Object result=repository.getFreeByBno(bno);
        Object[] arr=(Object[])result;

        return entityToDTO((Free)arr[0],(Member)arr[1],(Long)arr[2]);
    }

    // 자유게시판 수정
    @Override
    public void modify(FreeDTO freeDTO) {
        Free free = repository.getOne(freeDTO.getBno());
        free.changeTitle(freeDTO.getTitle());
        free.changeContent(freeDTO.getContent());
        repository.save(free);
    }


/*
    // 자유게시판 삭제 //여기 구현할때 빈에러남
    @Transactional
    @Override
    public void removeWithReplies(Long bno) { //삭제 기능 구현, 트랜잭션 추가
        replyRepository.deleteByBno(bno);
        repository.deleteById(bno);
    }
*/





}


