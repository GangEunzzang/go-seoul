package seoul.go.ggo.controller.notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import seoul.go.ggo.dto.PageRequestDTO;
import seoul.go.ggo.dto.notice.NoticeDTO;
import seoul.go.ggo.service.notice.NoticeService;

import java.security.Principal;


@Controller
@RequestMapping("/notice")
@Log4j2
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;


    @GetMapping("/")
    public String index(){

        return "redirect:/notice/list";
    }


    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("공지사항 게시판 목록"+ pageRequestDTO);

        model.addAttribute("result", noticeService.getList(pageRequestDTO));

    }

    @GetMapping("/register")
    public void register(){
        log.info("register get...");

    }

    @PostMapping("/register")
    public String registerPost (NoticeDTO dto, Principal principal, RedirectAttributes redirectAttributes){
        String admin=principal.getName();
        dto.setWriterId(admin);
        log.info("dto......" + dto);

        //새로 추가된 엔티티의 번호
        Long bno = noticeService.register(dto);
        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/notice/list";
    }


    @GetMapping({"/read", "/modify"})
    public void read( @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model,Long bno){
        log.info("bno: " + bno);
        NoticeDTO noticeDTO = noticeService.get(bno);
        model.addAttribute("dto", noticeDTO);
    }


    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes){
        log.info("bno: " + bno);
        noticeService.remove(bno);
        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/notice/list";
    }


    @PostMapping("/modify")
    public String modify(NoticeDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes){
        log.info("post modify .......................");
        log.info("dto: " + dto);

        noticeService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("bno", dto.getBno());

        return "redirect:/notice/read";
    }
}
