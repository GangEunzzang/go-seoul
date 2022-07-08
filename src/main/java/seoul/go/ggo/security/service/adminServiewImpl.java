//package seoul.go.ggo.security.service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Service;
//import seoul.go.ggo.entity.Member;
//import seoul.go.ggo.repository.member.MemberRepository;
//
//@Service
//@RequiredArgsConstructor
//@Log4j2
//public class adminServiewImpl implements adminService{
//
//    private final MemberRepository memberRepository;
//    @Override
//    public boolean getAdmin(String id,String password) {
//        boolean admin=memberRepository.findByAdmin(id,password);
//        return admin;
//    }
//}
