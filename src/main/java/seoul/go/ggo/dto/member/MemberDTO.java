package seoul.go.ggo.dto.member;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String id;

    private String password;

    private Boolean fromSocial;

}
