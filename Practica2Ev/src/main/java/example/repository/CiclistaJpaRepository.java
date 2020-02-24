package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity.Ciclista;


@Repository("ciclistaJpaRepository")
public interface CiclistaJpaRepository extends JpaRepository<Ciclista, Serializable> {

}
