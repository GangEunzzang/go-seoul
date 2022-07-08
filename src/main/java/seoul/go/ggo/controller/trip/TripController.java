package seoul.go.ggo.controller.trip;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import seoul.go.ggo.dto.PageRequestDTO;
import seoul.go.ggo.dto.member.MemberDTO;
import seoul.go.ggo.dto.trip.TripDTO;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.service.trip.TripImageService;
import seoul.go.ggo.service.trip.TripService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    private final TripImageService tripImageService;

    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String register(TripDTO tripDTO, RedirectAttributes redirectAttributes) {
        log.info("-------여행 게시판 등록 처리 DTO ---------------");
        log.info("tripDTO 값은" + tripDTO);

        Long bno = tripService.register(tripDTO);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/trip/list";
    }

    ;

//    @GetMapping("/list")
//    public void list(PageRequestDTO pageRequestDTO, Model model){
//        log.info("-----여행 게시판 목록 보기 ----------");
//        model.addAttribute("result", tripService.getList(pageRequestDTO));
//    };

    @GetMapping("/list")
    public void PlaceList(String keyword, String place,String Mytrip ,Principal principal,Model model, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("-----여행 게시판 여행지별 정렬 목록보기 -----");
        log.info("정렬 place 값은 :" + place);
        String Myid=null;
        if(Mytrip!=null){
            Myid= principal.getName();
        }

        if (place != null) {
            model.addAttribute("result", tripService.getSortList(pageRequestDTO, place));
            model.addAttribute("place", place);

        } else if (keyword != null) {
            model.addAttribute("result", tripService.getSearchList(pageRequestDTO, keyword));

        } else if (Myid!=null) {
            model.addAttribute("result", tripService.getMySearchList(pageRequestDTO, Myid));
        } else {
            model.addAttribute("result", tripService.getList(pageRequestDTO));
        }
    }


    @GetMapping("/read")
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, long bno, Model model, Principal principal) {
        TripDTO tripDTO = tripService.getTrip(bno);
        model.addAttribute("dto", tripDTO);
        log.info("여행게시판 상세보기" + tripDTO);
    }


    @GetMapping("/modify")
    public void modify(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, long bno, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        TripDTO tripDTO = tripService.getTrip(bno);

        String userId = principal.getName();
        String writerId = tripDTO.getWriterId();

        log.info("로그인한 유저 ID :" + userId);
        log.info("게시자 ID : " + writerId);

        if (userId.equals(writerId)) {
            model.addAttribute("dto", tripDTO);
            log.info("여행게시판 수정하기" + tripDTO);
        } else {
            model.addAttribute("dto", tripDTO);
            model.addAttribute("msg","내가 쓴 글이 아닙니다.");
        }
    }


    @PostMapping("/modify")
    public String modify(TripDTO tripDTO, RedirectAttributes redirectAttributes){
        System.out.println("------modify---------");
        System.out.println(tripDTO);
        System.out.println("------modify---------");

        tripService.modify(tripDTO);
        redirectAttributes.addAttribute("bno",tripDTO.getBno());
        return "redirect:/trip/read?bno="+tripDTO.getBno();
    }

    @GetMapping("/imgmodify")
    public void imgmodify(){};

    @PostMapping("/imgmodify")
    public String imgmodify(TripDTO tripDTO,RedirectAttributes redirectAttributes){
        System.out.println("------imgmodify---------");
        System.out.println(tripDTO);
        System.out.println("------imgmodify---------");

        tripService.modify(tripDTO);
        tripImageService.remove(tripDTO.getBno());
        tripService.imageRegister(tripDTO);
        redirectAttributes.addAttribute("bno",tripDTO.getBno());
        return "redirect:/trip/read?bno="+tripDTO.getBno();
    }

    @PostMapping("/remove")
    public String remove(long bno,RedirectAttributes redirectAttributes){
        tripService.removeTrip(bno);
        redirectAttributes.addFlashAttribute("msg",bno);
        return "redirect:/trip/list";
    }
}
