package com.guest.guestbookservice;


import com.guest.guestbookservice.models.Visitor;
import com.guest.guestbookservice.models.VisitorDto;
import com.guest.guestbookservice.repositories.GuestRepository;
import com.guest.guestbookservice.services.GuestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
public class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestService guestService;

    @Test
    public void addVisitor() {
        Visitor visitor = new Visitor("Eric","Very good");

        when(guestRepository.save(visitor)).thenReturn(visitor);

        VisitorDto actual = guestService.addVisitor(visitor);

        verify(guestRepository, times(1)).save(visitor);

        assertEquals(visitor.getName(), actual.getName());
        assertEquals(visitor.getComment(), actual.getComment());
    }

}
