package seoul.go.ggo.service.trip;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import seoul.go.ggo.dto.trip.TripReplyDTO;
import seoul.go.ggo.entity.Trip;
import seoul.go.ggo.entity.TripReply;
import seoul.go.ggo.repository.trip.TripReplyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TripReplyServiceImpl implements TripReplyService{

    private final TripReplyRepository tripReplyRepository;

    @Override
    public List<TripReplyDTO> getListOfTrip(Long bno) {
        Trip trip=Trip.builder().bno(bno).build();

        List<TripReply> result=tripReplyRepository.findByTrip(trip);

        return result.stream().map(tripReply ->
            entityToDto(tripReply)).collect(Collectors.toList());
    }

    @Override
    public Long register(TripReplyDTO tripReplyDTO) {
        System.out.println("===========register===================");
        System.out.println(tripReplyDTO);
        System.out.println("===========register===================");
        TripReply tripReply=dtoToEntity(tripReplyDTO);
        System.out.println("===========register===================");
        System.out.println(tripReply);
        System.out.println("===========register===================");
        tripReplyRepository.save(tripReply);
        return tripReply.getRno();
    }

    @Override
    public void modify(TripReplyDTO tripReplyDTO) {
        Optional<TripReply> result=tripReplyRepository
                .findById(tripReplyDTO.getRno());

        if(result.isPresent()){
            TripReply tripReply=result.get();
            tripReply.changeStar(tripReplyDTO.getStar());
            tripReply.changeContent(tripReplyDTO.getContent());

            tripReplyRepository.save(tripReply);
        }
    }

    @Override
    public void remove(Long rno) {
        tripReplyRepository.deleteById(rno);
    }


}
