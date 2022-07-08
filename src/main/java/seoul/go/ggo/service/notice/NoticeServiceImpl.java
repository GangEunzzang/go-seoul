package seoul.go.ggo.service.notice;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import seoul.go.ggo.dto.PageRequestDTO;
import seoul.go.ggo.dto.PageResultDTO;
import seoul.go.ggo.dto.notice.NoticeDTO;
import seoul.go.ggo.entity.Free;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.entity.Notice;
import seoul.go.ggo.entity.QNotice;
import seoul.go.ggo.repository.notice.NoticeRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository repository;

    //등록하기
    @Override
    public Long register(NoticeDTO dto) {
        log.info("====|| DTO ||==============================");
        log.info("dto");

        Notice notice = dtoToEntity(dto);
        log.info(notice);
        repository.save(notice);
        return notice.getBno();
    }

    //리스트 얻어오기
//    @Override
//    public PageResultDTO<NoticeDTO, Notice> getList(PageRequestDTO requestDTO){
//        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());
//        BooleanBuilder booleanBuilder = getSearch(requestDTO);
//        Page<Notice> result = repository.findAll(booleanBuilder, pageable);
//        Function<Notice, NoticeDTO> fn = (entity -> entityToDTO(entity ));
//        return new PageResultDTO<>(result, fn);
//    }
    @Override
    public NoticeDTO get (Long bno){
        Object result = repository.getNoticeByBno(bno);
        Object[] arr = (Object[]) result;

        return entityToDTO((Notice) arr[0], (Member) arr[1]);
    }

    @Override
    public void remove(Long bno) {
        repository.deleteById(bno);
    }

    @Override
    public void modify(NoticeDTO noticeDTO) {
        Notice notice = repository.getOne(noticeDTO.getBno());
        notice.changeTitle(noticeDTO.getTitle());
        notice.changeContent(noticeDTO.getContent());
        repository.save(notice);
    }



    @Override
    public PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());

        Function<Object[], NoticeDTO> fn = (en -> entityToDTO(
                (Notice) en[0],
                (Member)en[1]
        ));

        Page<Object[]> result = repository.getNoticeList(pageable);

        return new PageResultDTO<>(result, fn);

    }



}
