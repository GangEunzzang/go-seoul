//package seoul.go.ggo.controller.trip;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import seoul.go.ggo.dto.PageRequestDTO;
//import seoul.go.ggo.dto.PageResultDTO;
//import seoul.go.ggo.dto.trip.TripDTO;
//import seoul.go.ggo.service.trip.TripService;
//
//import java.util.List;
//
//@RestController
//@Log4j2
//@RequiredArgsConstructor
//@RequestMapping("/trip")
//public class TripSortController {
//
//    private final TripService tripService;
//
//    @GetMapping("/{place}/list")
//    public ResponseEntity<List<TripDTO>> PlaceList(@PathVariable("place") String place, Model model, PageRequestDTO pageRequestDTO){
//        log.info("-----여행 게시판 여행지별 정렬 목록보기 -----");
//        log.info("정렬 place 값은 :" + pageRequestDTO);
//
//        // model.addAttribute("result", tripService.getSortList(pageRequestDTO, place));
//        return null;
//        return new ResponseEntity<>( tripService.getSortList(pageRequestDTO, place),HttpStatus.OK);
//    };
//
//    @GetMapping("/{place}/list")
//    public String haha(@PathVariable("place") String place) {
//
//        return place + "입니다";
//
//    }
//}



