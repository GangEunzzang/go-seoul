package seoul.go.ggo.controller.free;

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
import seoul.go.ggo.dto.free.FreeDTO;
import seoul.go.ggo.service.free.FreeService;

import java.security.Principal;

@Controller
@RequestMapping("/free")
@Log4j2
@RequiredArgsConstructor
public class FreeController {
    private final FreeService freeService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("result", freeService.getList(pageRequestDTO));
        log.info("자유게시판 목록보기 : "+pageRequestDTO);
    }

    @GetMapping("/autoQuestion")
    public void autoQuestion(){}

    @GetMapping("/register")
    public void register(){
        log.info("register get...");

    }
    @PostMapping("/register")
    public String registerPost(FreeDTO dto, Principal principal, RedirectAttributes redirectAttributes){
        String freeId= principal.getName();
        dto.setWriterId(freeId);
        Long bno = freeService.register(dto);
        redirectAttributes.addFlashAttribute("msg",bno);
        log.info("되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐되냐안되냐");
        return "redirect:/free/list";

    }

    @GetMapping("/read")
    public void read(@ModelAttribute("requestDTO")PageRequestDTO pageRequestDTO, Long bno,Model model){
        FreeDTO freeDTO = freeService.get(bno);
        model.addAttribute("dto", freeDTO);
    }

    @GetMapping("/modify")
    public void modify(@ModelAttribute("requestDTO")PageRequestDTO pageRequestDTO, Long bno,Model model,Principal principal, RedirectAttributes redirectAttributes){
        FreeDTO freeDTO = freeService.get(bno);

        String userId= principal.getName();
        String FWriter=freeDTO.getWriterId();

        System.out.println("====FModify========");
        System.out.println(userId);
        System.out.println(FWriter);
        System.out.println("====FModify========");

        if(userId.equals(FWriter)){
            model.addAttribute("dto", freeDTO);
        }else{
            model.addAttribute("dto", freeDTO);
            model.addAttribute("msg","내가 쓴 글이 아닙니다.");
        }

    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes){
        freeService.removeWithReplies(bno);
        redirectAttributes.addFlashAttribute("msg,bno");
        return "redirect:/free/list";
    }

    @PostMapping("/modify")
    public String modify(FreeDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes){
        freeService.modify(dto);

        redirectAttributes.addAttribute("page",requestDTO.getPage());
        redirectAttributes.addAttribute("type",requestDTO.getType());
        redirectAttributes.addAttribute("keyword",requestDTO.getKeyword());
        redirectAttributes.addAttribute("bno",dto.getBno());
        return "redirect:/free/read";
    }




}