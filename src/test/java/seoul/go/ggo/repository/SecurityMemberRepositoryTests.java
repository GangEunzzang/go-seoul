package seoul.go.ggo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.entity.MemberRole;
import seoul.go.ggo.repository.member.MemberRepository;

import java.util.stream.IntStream;

@SpringBootTest
public class SecurityMemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 더미데이터 입력")
    // 패스워드 인코딩 처리는 x
    public void insertMember(){
        IntStream.rangeClosed(1,30).forEach(i -> {
            Member member = Member.builder()
                    .id("user"+i+"google.com")
                    .password("1111")
                    .fromSocial(false)
                    .build();

            member.addMemberRole(MemberRole.USER); //기본은 유저

            if(i == 1){ // user1번에게는 모든권한이 있음
                member.addMemberRole(MemberRole.ADMIN);
                member.addMemberRole(MemberRole.MANAGER);
            }
            memberRepository.save(member);
        });
    }
}
