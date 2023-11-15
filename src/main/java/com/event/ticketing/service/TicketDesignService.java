package com.event.ticketing.service;

import java.util.List;
import com.event.ticketing.entity.TicketDesign;

public interface TicketDesignService {
    List<TicketDesign> getAllDesigns();
    TicketDesign getDesignById(Long id);
}
