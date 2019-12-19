package com.company.cubaoptaplanner.core;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// E' la classe che rappresenta una soluzione al problema.
// Questa classe deve rappresentare l'intero set di dati e contenere tutte le entità di pianificazione.
@PlanningSolution
public class PSCourseSchedule {

    Logger logger = LoggerFactory.getLogger("CourseSchedule");

    // Lista delle stanze
    private List<Integer> roomList;

    // Lista dei periodi
    private List<Integer> periodList;

    // Lista delle lezioni
    // (al cui interno ci sono i riferiementi ad aulte / periodi)
    private List<PELecture> lectureList;

    // Punteggio della soluzione
    private HardSoftScore score;


    // Costruttore
    public PSCourseSchedule(){
        roomList = new ArrayList<>();
        periodList = new ArrayList<>();
        lectureList = new ArrayList<>();
    }

    // Indica che questo metodo restituisce la collezione delle PlanningEntity
    // che rappresentano la migliore soluzione al problema
    // Quindi la lista delle lezioni con assegnate stanze e periodi
    @PlanningEntityCollectionProperty
    public List<PELecture> getLectureList() {
        return lectureList;
    }


    // Indica che questo metodo fornisce la lista dei valori possibili da utilizzare durante la risoluzione
    // per l'assegnazione del valore della proprietà nella PlanningEntity che usa il ref availableRooms
    @ValueRangeProvider(id = "availableRooms")
    // Indica che questa proprietà può essere usata nei contraint
    @ProblemFactCollectionProperty
    public List<Integer> getRoomList() {
        return roomList;
    }

    @ValueRangeProvider(id = "availablePeriods")
    @ProblemFactCollectionProperty
    public List<Integer> getPeriodList() {
        /*
        List<Integer> list = new ArrayList<>();
        list.add(1);
        return list;
        */
        return periodList;
    }




    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }




    public void printCourseSchedule() {
        System.out.println("Solution score HS: " + score.getHardScore()  + " SS: " + score.getSoftScore());
        lectureList.stream()
                .map(c -> c.getName() + " in Room " + c.getRoomNumber().toString()
                        + " during Period " + c.getPeriod().toString()

                )
                .forEach(k -> System.out.println(k));
    }

}
