package com.guest.guestbookservice.controllers;

import com.guest.guestbookservice.models.Visitor;
import com.guest.guestbookservice.models.VisitorDto;
import com.guest.guestbookservice.services.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("guest")
public class GuestController {

    GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping("visitor")
    @ResponseStatus(HttpStatus.CREATED)
    public VisitorDto addVisitor(@RequestBody Visitor visitor) {
        return guestService.addVisitor(visitor);
    }

    @GetMapping("visitors")
    @ResponseStatus(HttpStatus.OK)
    public List<VisitorDto> getAllVisitors() {
        return guestService.getAllVisitors();
    }
}
