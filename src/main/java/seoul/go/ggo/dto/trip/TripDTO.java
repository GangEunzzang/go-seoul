package seoul.go.ggo.dto.trip;


import lombok.*;
import seoul.go.ggo.dto.member.MemberDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {
    private Long bno;
    private String title;
    private String content;
    private String place;
    private String writerId; // 작성자 Id
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private int replyCount; // 리뷰 수 jpa(count) 사용
    private double avg; // 별점 평균

    @Builder.Default
    private List<TripImageDTO> imageDTOList = new ArrayList<>();

    @Builder.Default
    private List<MemberDTO> memberDTOList = new ArrayList<>();
}
