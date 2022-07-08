package seoul.go.ggo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "writer")
@Builder
@Getter
public class Trip extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String place;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }

    public void changePlace(String place){
        this.place = place;
    }
}
