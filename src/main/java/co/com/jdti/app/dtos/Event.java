package co.com.jdti.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private String name;
    private Date startDate;
    private Date endDate;
    private String description;
}
