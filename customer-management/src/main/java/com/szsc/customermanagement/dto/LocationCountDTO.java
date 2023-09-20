package com.szsc.customermanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocationCountDTO {
    private int total;
    private int mainland;
    private int hongkong;
    private int overseas;
}
