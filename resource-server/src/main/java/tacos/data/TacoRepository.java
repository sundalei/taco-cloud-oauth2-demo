package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import tacos.domain.Taco;

@RepositoryRestResource(collectionResourceRel = "tacos", path = "tacos", collectionResourceDescription = @Description("tacos"))
public interface TacoRepository extends JpaRepository<Taco, Long> {

}
