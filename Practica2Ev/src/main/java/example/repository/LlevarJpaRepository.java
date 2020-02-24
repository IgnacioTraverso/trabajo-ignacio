package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity.Llevar;


@Repository("llevarJpaRepository")
public interface LlevarJpaRepository extends JpaRepository<Llevar, Serializable> {

}
