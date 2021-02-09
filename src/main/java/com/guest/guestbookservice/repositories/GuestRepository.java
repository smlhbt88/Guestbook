package com.guest.guestbookservice.repositories;

import com.guest.guestbookservice.models.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Visitor, Long> {
}
