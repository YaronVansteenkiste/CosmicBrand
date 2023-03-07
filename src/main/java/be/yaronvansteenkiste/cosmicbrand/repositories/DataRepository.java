package be.yaronvansteenkiste.cosmicbrand.repositories;

import be.yaronvansteenkiste.cosmicbrand.model.Datahandler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface DataRepository extends JpaRepository<Datahandler, Long> {
}
