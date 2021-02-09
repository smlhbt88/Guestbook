package com.guest.guestbookservice;


import com.guest.guestbookservice.repositories.GuestRepository;
import com.guest.guestbookservice.services.GuestService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
public class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestService guestService;

}
