package med.voll.api.domain.repository;

import med.voll.api.domain.models.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            select p.ativo from Paciente p
            where
            p.id = :idPaciente
            """)
    Boolean findAtivoById(Long idPaciente);
}
