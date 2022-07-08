package seoul.go.ggo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"free","writer"})
@Builder
@Getter
public class FreeReply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @Column(length = 500, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Free free;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    public void changeContent(String content){
        this.content = content;
    }
}
