package seoul.go.ggo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class Member extends BaseEntity {
    @Id
    @Column(length = 50)
    private String id;

    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private boolean fromSocial;
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();
    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }
}
