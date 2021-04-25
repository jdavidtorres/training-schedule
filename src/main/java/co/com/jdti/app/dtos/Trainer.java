package co.com.jdti.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {

    private String name;
    private String lastname;
    private Date birthday;
}
