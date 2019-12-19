package com.company.cubaoptaplanner.core;


import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import java.util.HashSet;

public class SCScoreCalculator implements EasyScoreCalculator<PSCourseSchedule> {

    @Override
    public Score calculateScore(PSCourseSchedule courseSchedule) {
        int hardScore = 0;
        int softScore = 0;

        HashSet<String> occupiedRooms = new HashSet<>();
        for (PELecture lecture : courseSchedule.getLectureList()) {
            if(lecture.getPeriod() != null && lecture.getRoomNumber() != null) {
                String roomInUse = lecture.getPeriod().toString() + ":" + lecture.getRoomNumber().toString();
                if (occupiedRooms.contains(roomInUse)) {
                    hardScore += -1;
                } else {
                    occupiedRooms.add(roomInUse);
                }
            } else {
                hardScore += -1;
            }
        }
        //System.out.println("hardScore:" + hardScore + " - softScore:"+softScore);
        return HardSoftScore.of(hardScore, softScore);
    }
}
