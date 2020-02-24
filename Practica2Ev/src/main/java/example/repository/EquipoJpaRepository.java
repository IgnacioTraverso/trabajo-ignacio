package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity.Equipo;


@Repository("equipoJpaRepository")
public interface EquipoJpaRepository extends JpaRepository<Equipo, Serializable> {

}
