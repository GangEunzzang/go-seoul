package seoul.go.ggo.repository.notice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.entity.Notice;

import java.util.stream.IntStream;

@SpringBootTest
public class NoticeRepositoryTests {

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    @DisplayName("공지사항 게시판 더미데이터 입력")
    public void insertNotice() {
        IntStream.rangeClosed(1,30).forEach(i -> {
            Member member = Member.builder().id("user"+i+"google.com").build();

            Notice notice = Notice.builder()
                    .title("공지사항 제목" + i)
                    .content("공지사항 내용" + i)
                    .writer(member)
                    .build();
            noticeRepository.save(notice);
            System.out.println(notice);
        });
    }
}