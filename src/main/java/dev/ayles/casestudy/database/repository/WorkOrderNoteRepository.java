package dev.ayles.casestudy.database.repository;

import dev.ayles.casestudy.database.entity.WorkOrderNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderNoteRepository extends JpaRepository<WorkOrderNote, Long> {



}
