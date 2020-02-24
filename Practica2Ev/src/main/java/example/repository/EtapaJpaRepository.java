package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity.Etapa;


@Repository("etapaJpaRepository")
public interface EtapaJpaRepository extends JpaRepository<Etapa, Serializable> {

}
