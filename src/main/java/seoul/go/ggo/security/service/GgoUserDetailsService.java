package seoul.go.ggo.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.repository.member.MemberRepository;
import seoul.go.ggo.security.dto.GgoAuthMemberDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class GgoUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ClubUserDetailsService loadUserByUsername " + username);

        Optional<Member> result = memberRepository.findById(username, false);

        if (result.isEmpty()){
        }
        Member member = result.get();

        log.info("------------------------");
        log.info(member);

        GgoAuthMemberDTO ggoAuthMemberDTO = new GgoAuthMemberDTO(
                member.getId(),
                member.getPassword(),
                member.isFromSocial(),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toList())
        );
        ggoAuthMemberDTO.setName(ggoAuthMemberDTO.getName());
        ggoAuthMemberDTO.setFromSocial(member.isFromSocial());

        return ggoAuthMemberDTO;

    }
}


