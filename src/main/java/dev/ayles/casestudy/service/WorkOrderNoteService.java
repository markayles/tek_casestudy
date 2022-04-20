package dev.ayles.casestudy.service;

import dev.ayles.casestudy.database.repository.WorkOrderNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderNoteService {

    @Autowired
    private WorkOrderNoteRepository workOrderNoteRepository;

}
