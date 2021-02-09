package com.guest.guestbookservice.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VisitorDto {
    private String name;
    private String comment;

}
