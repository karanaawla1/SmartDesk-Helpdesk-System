package com.smartdesk.smartdesk.service;

import com.smartdesk.smartdesk.dto.TicketRequest;
import com.smartdesk.smartdesk.dto.TicketResponse;
import com.smartdesk.smartdesk.model.*;
import com.smartdesk.smartdesk.repository.TicketRepository;
import com.smartdesk.smartdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketResponse createTicket(TicketRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Ticket ticket = Ticket.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(Priority.valueOf(request.getPriority().toUpperCase()))
                .status(TicketStatus.OPEN)
                .createdBy(user)
                .build();

        return mapToResponse(ticketRepository.save(ticket));
    }

    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll()
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<TicketResponse> getMyTickets(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return ticketRepository.findByCreatedBy(user)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TicketResponse updateStatus(Long id, String status) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found!"));
        ticket.setStatus(TicketStatus.valueOf(status.toUpperCase()));
        return mapToResponse(ticketRepository.save(ticket));
    }

    public TicketResponse assignTicket(Long id, Long agentId) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found!"));
        User agent = userRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found!"));
        ticket.setAssignedTo(agent);
        ticket.setStatus(TicketStatus.IN_PROGRESS);
        return mapToResponse(ticketRepository.save(ticket));
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    private TicketResponse mapToResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus().name())
                .priority(ticket.getPriority().name())
                .createdBy(ticket.getCreatedBy().getName())
                .assignedTo(ticket.getAssignedTo() != null ?
                        ticket.getAssignedTo().getName() : "Unassigned")
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .build();
    }
}