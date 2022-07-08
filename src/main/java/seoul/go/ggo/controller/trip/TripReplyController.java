package seoul.go.ggo.controller.trip;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seoul.go.ggo.dto.trip.TripReplyDTO;
import seoul.go.ggo.service.trip.TripReplyService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/reply")
@Log4j2
@RequiredArgsConstructor
public class TripReplyController {

    private final TripReplyService tripReplyService;

    @GetMapping("/{bno}/all")
    public ResponseEntity<List<TripReplyDTO>> getList(@PathVariable("bno") Long bno){
        System.out.println("=============ALL========");
        System.out.println(bno);
        System.out.println("=============ALL========");
        List<TripReplyDTO> tripReplyDTOList=tripReplyService.getListOfTrip(bno);

        System.out.println("=============ALL2========");
        System.out.println(tripReplyDTOList);
        System.out.println("=============ALL2========");

        return new ResponseEntity<>(tripReplyDTOList, HttpStatus.OK);
    }

    @PostMapping("/{bno}")
    public ResponseEntity<Long> addTripReply(@RequestBody TripReplyDTO tripReplyDTO, Principal principal){
        tripReplyDTO.setWriter(principal.getName());
        System.out.println("=============controller========");
        System.out.println(tripReplyDTO);
        System.out.println("=============controller========");
        Long rno=tripReplyService.register(tripReplyDTO);
        return new ResponseEntity<>(rno,HttpStatus.OK);
    }

    @PutMapping("/{bno}/{rno}")
    public ResponseEntity<Long> modifyTripReply(@PathVariable Long rno,
                                                @RequestBody TripReplyDTO tripReplyDTO){
        System.out.println("=============RModiy========");
        System.out.println(tripReplyDTO);
        System.out.println("=============RModiy========");
        tripReplyService.modify(tripReplyDTO);
        return new ResponseEntity<>(rno,HttpStatus.OK);
    }

    @DeleteMapping("/{bno}/{rno}")
    public ResponseEntity<Long> removeTripReply(@PathVariable Long rno){
        tripReplyService.remove(rno);
        return new ResponseEntity<>(rno,HttpStatus.OK);
    }
}