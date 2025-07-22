package com.levelup.erp.material.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class FinishedMaterial extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer plant;
    private String headerMaterial;
    private String subMaterial;
    private String materialDescription;
    private String materialTypeDescription;
    private String materialGroup;
    private String baseUnitOfMeasure;

    @Enumerated(EnumType.STRING)
    private MRPType mrpType;

    private String currency;
    private String nlp;
    private String artworkNumber;
    private String artworkRevisionNo;
    private String technicalSpecNo;
    private String technicalSpecRevisionNo;
    private String purchasingGroup;
    private String valuationClass;
    private String gtinNumber;

}