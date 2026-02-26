package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByNome(String nome);

    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(Integer anoNascimento, Integer anoFalecimento);

    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoIsNull(Integer anoNascimento);
}