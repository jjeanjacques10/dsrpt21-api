package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.ExecucaoModel;

@Repository
public interface ExecucaoRepository extends JpaRepository<ExecucaoModel, Integer>{

}
