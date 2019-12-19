package com.company.cubaoptaplanner.core;


import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

// la classe (o le classi) che Planner può modificare durante la risoluzione.
@PlanningEntity
public class PELecture {

    // Nome della lezione
    private String name;

    // id dell'aula occupata dalla lezione
    private Integer roomNumber;

    //id del periodo occupato dalla lezione
    private Integer period;

    // N.B: Costruttore senza parametri obbligatorio per le PlanningEntity
    public PELecture() {
    }

    // Costruttore
    public PELecture(String name) {
        this.name = name;
    }

    // NON è una planning variabole
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    // PlanningVariable -> indica che  la proprietà cambia durante la risoluzione.
    @PlanningVariable(valueRangeProviderRefs = {"availablePeriods"})
    public Integer getPeriod() {
        return period;
    }

    // PlanningVariable -> indica che  la proprietà cambia durante la risoluzione.
    @PlanningVariable(valueRangeProviderRefs = {"availableRooms"})
    public Integer getRoomNumber() {
        return roomNumber;
    }


    // Setter delle PlanningVariable (chiamati da OptaPlnner durante la risoluzione)
    public void setPeriod(Integer period) {
        // N.B: qui potrei applicare delle logiche per impedire al solver di MODIIFCARE
        // un valore, per esempio se è stato fissato a mano.
        this.period = period;
    }
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }


}
