package seoul.go.ggo.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.entity.MemberRole;
import seoul.go.ggo.repository.member.MemberRepository;
import seoul.go.ggo.security.dto.GgoAuthMemberDTO;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class GgoOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("--------------------------------------");
        log.info("userRequest:" + userRequest);

        //클라이언트 이름구해서 출력
        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName: " + clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("==============================");
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + ":" + v);
        });

        String id = null;

        if (clientName.equals("Google")) {
            id = oAuth2User.getAttribute("email");
        } else if (clientName.equals("Naver")) {
            Map<String, Object> attributes=oAuth2User.getAttributes();
            Map<String, Object> attributesResponse = (Map<String, Object>) attributes.get("response");
            id = attributesResponse.get("email").toString();

//            byte[] a = decoder.decode(id);

            System.out.println("=-========naver===========");
            System.out.println(id);
            System.out.println("=-========naver===========");
        }
        log.info("EMAIL: " + id);




//        Member member = saveSocialMember(id);
//
//        return oAuth2User;

        Member member = saveSocialMember(id);
        GgoAuthMemberDTO authMember = new GgoAuthMemberDTO(
                member.getId(),
                member.getPassword(),
                true,
                member.getRoleSet().stream().map(
                                role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );
        return authMember;

    }


    private Member saveSocialMember (String id){

        //기존에 동일한 이메일로 가입한 회원이 있는 경우에는 그대로 조회만
        Optional<Member> result = memberRepository.findById(id, true);

        if (result.isPresent()) {
            return result.get();
        }

        //없다면 회원 추가 패스워드는 1111 이름은 그냥 이메일 주소로
        Member member = Member.builder().id(id)
                .id(id)
                .password(passwordEncoder.encode("1234"))
                .fromSocial(true)
                .build();

        member.addMemberRole(MemberRole.USER);


        memberRepository.save(member);

        return member;
    }




}