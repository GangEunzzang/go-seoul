package seoul.go.ggo.dto.trip;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripReplyDTO {

    private Long rno;

    private String writer;

    private Long trip;

    private  String content;

    private int star;

    private LocalDateTime regDate, modDate;

}
