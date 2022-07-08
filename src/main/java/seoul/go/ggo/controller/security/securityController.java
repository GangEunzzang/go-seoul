package seoul.go.ggo.controller.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import seoul.go.ggo.security.service.adminService;


@Controller
@Log4j2
@RequestMapping("/security/")
@RequiredArgsConstructor
public class securityController {

//    private final adminService adminService;
    // 모든 사용자 접근 가능
    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public void exAll() {
        log.info("exAll.........");
    }

    // 로그인한 사용자(USER권한) 접근 가능

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/member")
    public void exMember() {
        log.info("exMember.................");

    }

    // 관리자 접근 가능
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void exAdmin() {
        log.info("exAdmin............");
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/main")
    public void main() {
        log.info("exAdmin............");
    }
//    @PreAuthorize("#clubAuthMember != null && #clubAuthMember.username eq\"user95@zerock.org\"")
//    @GetMapping("/exOnly")
//    public String exMemberOnly(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO) {
//        return "/sample/admin";
//    }

    @GetMapping("/loginCustom")
    public void loginCustom(String id,String password){
//        boolean adminOk=adminService.getAdmin(id,password);
    }


    }


//    @GetMapping("/ajax")
//    public void ajax(){}




