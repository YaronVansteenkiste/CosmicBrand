package be.yaronvansteenkiste.cosmicbrand.repositories;

import be.yaronvansteenkiste.cosmicbrand.model.Product;
import org.springframework.data.repository.CrudRepository;
import be.yaronvansteenkiste.cosmicbrand.repositories.ProductRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface ProductRepository extends CrudRepository<Product, Integer>{
}
