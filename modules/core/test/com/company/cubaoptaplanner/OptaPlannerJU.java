package com.company.cubaoptaplanner;

import com.company.cubaoptaplanner.core.PELecture;
import com.company.cubaoptaplanner.core.PSCourseSchedule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.util.Arrays;

public class OptaPlannerJU {

    @ClassRule
    public static CubaoptaplannerTestContainer cont = CubaoptaplannerTestContainer.Common.INSTANCE;

    // ----------------------------------------------------------------------------
    //
    // CONST
    //
    // ----------------------------------------------------------------------------


    static PSCourseSchedule unsolvedCourseSchedule;

    @Before
    public void setUp() {

        unsolvedCourseSchedule = new PSCourseSchedule();

        for(int i = 0; i < 4; i++){
            unsolvedCourseSchedule.getLectureList().add(new PELecture("Lezione_"+i));
        }

        unsolvedCourseSchedule.getPeriodList().addAll(Arrays.asList(new Integer[] { 1, 2 }));
        unsolvedCourseSchedule.getRoomList().addAll(Arrays.asList(new Integer[] { 1, 2 }));
    }


    @Test
    public void test_whenDroolsSolver() {
        SolverFactory<PSCourseSchedule> solverFactory = SolverFactory.createFromXmlResource("com/company/cubaoptaplanner/core/courseScheduleSolverConfigDrools.xml",
                Thread.currentThread().getContextClassLoader());
        Solver<PSCourseSchedule> solver = solverFactory.buildSolver();
        PSCourseSchedule solvedCourseSchedule = solver.solve(unsolvedCourseSchedule);

        solvedCourseSchedule.printCourseSchedule();

        Assert.assertNotNull(solvedCourseSchedule.getScore());
        Assert.assertEquals(0, solvedCourseSchedule.getScore().getHardScore());
    }


}
