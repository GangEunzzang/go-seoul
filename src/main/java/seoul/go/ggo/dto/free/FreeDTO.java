package seoul.go.ggo.dto.free;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FreeDTO {
    private Long bno;
    private String title;
    private String content;
    private String writerId; // 작성자의 아이디(이메일)
    private LocalDateTime regDate; // 등록 시간(수정 불가능)
    private LocalDateTime modDate; // 수정 시간(수정 가능)
    private int replyCount ;
}
