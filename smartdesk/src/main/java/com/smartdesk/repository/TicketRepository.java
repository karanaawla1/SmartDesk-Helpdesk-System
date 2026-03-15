package com.smartdesk.smartdesk.repository;

import com.smartdesk.smartdesk.model.Ticket;
import com.smartdesk.smartdesk.model.TicketStatus;
import com.smartdesk.smartdesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCreatedBy(User user);
    List<Ticket> findByAssignedTo(User user);
    List<Ticket> findByStatus(TicketStatus status);
}