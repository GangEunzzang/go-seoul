package seoul.go.ggo.service.trip;

import seoul.go.ggo.dto.trip.TripReplyDTO;
import seoul.go.ggo.entity.Member;
import seoul.go.ggo.entity.Trip;
import seoul.go.ggo.entity.TripReply;

import java.util.List;

public interface TripReplyService {
    List<TripReplyDTO> getListOfTrip(Long bno);

    Long register(TripReplyDTO tripReplyDTO);

    void modify(TripReplyDTO tripReplyDTO);

    void remove(Long rno);

    default TripReply dtoToEntity(TripReplyDTO tripReplyDTO){
        TripReply tripReply=TripReply.builder()
                .rno(tripReplyDTO.getRno())
                .content(tripReplyDTO.getContent())
                .trip(Trip.builder().bno(tripReplyDTO.getTrip()).build())
                .star(tripReplyDTO.getStar())
                .writer(Member.builder().id(tripReplyDTO.getWriter()).build())
                .build();
        System.out.println("======================================");
        System.out.println(tripReply);
        System.out.println("======================================");
        return tripReply;
    }

    default TripReplyDTO entityToDto(TripReply tripReply){
        TripReplyDTO tripReplyDTO=TripReplyDTO.builder()
                .rno(tripReply.getRno())
                .trip(tripReply.getTrip().getBno())
                .writer(tripReply.getWriter().getId())
                .star(tripReply.getStar())
                .content(tripReply.getContent())
                .modDate(tripReply.getModDate())
                .regDate(tripReply.getRegDate())
                .build();
        return tripReplyDTO;
    }
}
