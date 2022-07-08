package seoul.go.ggo.service.trip;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import seoul.go.ggo.repository.trip.TripImageRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class TripImageServiceImpl implements TripImageService{

    private final TripImageRepository tripImageRepository;

    @Override
    public void remove(Long bno) {
        Long inum=tripImageRepository.findByBno(bno);
        tripImageRepository.deleteById(inum);
    }
}
