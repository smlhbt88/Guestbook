package com.guest.guestbookservice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guest.guestbookservice.models.Visitor;
import com.guest.guestbookservice.models.VisitorDto;
import com.guest.guestbookservice.repositories.GuestRepository;
import com.guest.guestbookservice.services.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
public class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestService guestService;

    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void addVisitor() {
        Visitor visitor = new Visitor("Eric","Very good");

        when(guestRepository.save(visitor)).thenReturn(visitor);

        VisitorDto actual = guestService.addVisitor(visitor);

        verify(guestRepository, times(1)).save(visitor);

        assertEquals(visitor.getName(), actual.getName());
        assertEquals(visitor.getComment(), actual.getComment());
    }

    @Test
    public void getAllVisitors() throws JsonProcessingException {
        Visitor visitor1 = new Visitor("Eric","Very good");
        Visitor visitor2 = new Visitor("Nityam","Awesome");
        Visitor visitor3 = new Visitor("Sam","Great");
        List<Visitor> expectedVisitorList = new ArrayList<>();
        expectedVisitorList.add(visitor1);
        expectedVisitorList.add(visitor2);
        expectedVisitorList.add(visitor3);

        guestRepository.save(visitor1);
        guestRepository.save(visitor2);
        guestRepository.save(visitor3);

        when(guestRepository.findAll()).thenReturn(expectedVisitorList);

        List<VisitorDto> allActualVisitors = guestService.getAllVisitors();

        verify(guestRepository, times(1)).findAll();

        assertEquals(objectMapper.writeValueAsString(expectedVisitorList),objectMapper.writeValueAsString(allActualVisitors));
    }
}
