package seoul.go.ggo.dto.trip;

import lombok.*;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO implements Serializable {

    private String fileName;
    private String uuid;
    private String folderPath;

    public String getImageURL(){
        try {
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
