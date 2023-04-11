package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.TacoUser;

public interface UserRepository extends CrudRepository<TacoUser, Long> {

    TacoUser findByUsername(String username);
}
