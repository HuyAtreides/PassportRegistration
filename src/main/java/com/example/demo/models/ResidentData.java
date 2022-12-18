package com.example.demo.models;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResidentData extends PassportData {
    private String nation;
    private String dateOfBirth;
}
