package com.szsc.customermanagement.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocationCountData {
    private int total;
    private int mainland;
    private int hongkong;
    private int overseas;
}
