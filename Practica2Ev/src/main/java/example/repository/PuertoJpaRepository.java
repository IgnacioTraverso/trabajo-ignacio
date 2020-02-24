package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity.Puerto;


@Repository("puertoJpaRepository")
public interface PuertoJpaRepository extends JpaRepository<Puerto, Serializable> {

}
