package ee.ttu.web.engine;

import ee.ttu.web.domain.Courier;
import ee.ttu.web.repository.CouriersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouriersService {

    @Autowired
    private CouriersRepository couriersRepository;

    public List<Courier> findAll() {
        return couriersRepository.findAll();
    }

    public Courier findById(Long courierId) {
        return couriersRepository.findOne(courierId);
    }
}
