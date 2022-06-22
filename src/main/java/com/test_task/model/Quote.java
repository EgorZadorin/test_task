package com.test_task.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ISIN")
    private String isin;

    @Column(name = "price")
    private double price;

    @Column(name = "type")
    private String type;

    @Column(name = "timestamp")
    private Timestamp ts;
}
