package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.AcaoModel;

@Repository
public interface AcaoRepository extends JpaRepository<AcaoModel, Integer>{

}
