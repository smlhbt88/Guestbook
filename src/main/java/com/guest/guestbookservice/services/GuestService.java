package com.guest.guestbookservice.services;

import com.guest.guestbookservice.models.Visitor;
import com.guest.guestbookservice.models.VisitorDto;
import com.guest.guestbookservice.repositories.GuestRepository;
import org.springframework.stereotype.Service;

@Service
public class GuestService {

    GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public VisitorDto addVisitor(Visitor visitor) {
        Visitor visitor1 = guestRepository.save(visitor);
        VisitorDto visitorDto = new VisitorDto();
        visitorDto.setName(visitor1.getName());
        visitorDto.setComment(visitor1.getComment());
        return visitorDto;
    }
}
