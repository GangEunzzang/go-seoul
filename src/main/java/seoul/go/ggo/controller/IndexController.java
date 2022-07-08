package seoul.go.ggo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class IndexController {

    // 메인페이지만
    @GetMapping({"/index","/","main"})
    public void index(){}

}
