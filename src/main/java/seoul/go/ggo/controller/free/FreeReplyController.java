package seoul.go.ggo.controller.free;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seoul.go.ggo.dto.free.FreeReplyDTO;
import seoul.go.ggo.service.free.FreeReplyService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class FreeReplyController {

    private final FreeReplyService freeReplyService;

    @GetMapping(value ="/free/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FreeReplyDTO>> getListByFree(@PathVariable("bno") Long bno){
        return new ResponseEntity<>( freeReplyService.getList(bno), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody FreeReplyDTO replyDTO, Principal principal){

        String LoginId= principal.getName();
        replyDTO.setId(LoginId);
        log.info(replyDTO);
        log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Long rno = freeReplyService.register(replyDTO);

        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    @DeleteMapping("/{bno}/{rno}")
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {

        System.out.println("========remove=========");
        System.out.println(rno);
        System.out.println("========remove=========");

        freeReplyService.remove(rno);

        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    @PutMapping("/{rno}")
    public ResponseEntity<String> modify(@RequestBody FreeReplyDTO replyDTO) {

        log.info(replyDTO);

        freeReplyService.modify(replyDTO);

        return new ResponseEntity<>("success", HttpStatus.OK);

    }

}

