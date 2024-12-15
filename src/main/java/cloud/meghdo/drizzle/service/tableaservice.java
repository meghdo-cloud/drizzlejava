package cloud.meghdo.drizzle.service;

import cloud.meghdo.drizzle.entity.tablea;
import cloud.meghdo.drizzle.repository.tablearepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class tableaservice {

    private final tablearepository tablearepository;

    @Autowired
    public tableaservice(tablearepository tablearepository) {
        this.tablearepository = tablearepository;
    }

    public tablea savetablea(tablea tablea) {
        return tablearepository.save(tablea);
    }
}
