package seoul.go.ggo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"trip","writer"})
@Builder
@Getter
public class TripReply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @Column(length = 500, nullable = false)
    private String content;

    @Column(nullable = false)
    private int star;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trip trip ;

    @ManyToOne(fetch = FetchType.LAZY)
    private  Member writer;

    public void changeContent(String content){
        this.content = content;
    }

    public void changeStar(int star){
        this.star = star;
    }
}
