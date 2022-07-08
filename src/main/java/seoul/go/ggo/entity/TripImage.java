package seoul.go.ggo.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "trip")
@Builder
@Getter
public class TripImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    @Column(length = 100, nullable = false)
    private String imgName;

    @Column(length = 200, nullable = false)
    private String path;

    @Column(length = 200, nullable = false)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trip trip;

    public void ChangeImgName(String imgName){
        this.imgName=imgName;
    }

    public void ChangePath(String path){
        this.path=path;
    }

    public void ChangeUuid(String uuid){
        this.uuid=uuid;
    }
}
