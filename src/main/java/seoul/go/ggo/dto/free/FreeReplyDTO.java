package seoul.go.ggo.dto.free;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FreeReplyDTO {

    private Long rno; //댓글번호

    private String content;

    private String id;  //id

    private Long bno;  //게시글 번호

    private LocalDateTime regDate, modDate;



}
