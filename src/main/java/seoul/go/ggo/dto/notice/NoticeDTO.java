package seoul.go.ggo.dto.notice;

import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.lang.reflect.Member;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class NoticeDTO {

    private Long bno;
    private String title;
    private String content;

    private String writerId;

    private LocalDateTime regDate, modDate;


}
