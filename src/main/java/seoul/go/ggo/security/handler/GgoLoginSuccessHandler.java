package seoul.go.ggo.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import seoul.go.ggo.security.dto.GgoAuthMemberDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class GgoLoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private PasswordEncoder passwordEncoder;

    public GgoLoginSuccessHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("----------------------------------------------------");
        log.info("어센티케이션 성공");


        GgoAuthMemberDTO ggoAuthMemberDTO = (GgoAuthMemberDTO) authentication.getPrincipal();

        boolean fromSocial = ggoAuthMemberDTO.isFromSocial();

        log.info("수정이 필요한 멤버 "+fromSocial);

        boolean passwordResult = passwordEncoder.matches("1234",ggoAuthMemberDTO.getPassword());

            if (fromSocial && passwordResult){
                redirectStrategy.sendRedirect(request,response,"/index");
            }


        log.warn("Login Success");

        List<String> roleNames = new ArrayList<>();
        authentication.getAuthorities().forEach(authority->{
            roleNames.add(authority.getAuthority());
        });
        log.warn("ROLE NAMES : "+roleNames);
//        if(roleNames.contains("ROLE_ADMIN")) {
//            response.sendRedirect("/security/member");
//            return;
//        }
//        if(roleNames.contains("ROLE_MEMBER")) {
//            response.sendRedirect("/index");
//            return;
//        }
//        response.sendRedirect("/index");



    }

}

