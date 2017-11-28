package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.utils.motorDeclaration;

/**
 * Created by DeathChicken on 11/24/2017.
 */
@Autonomous(name = "encoderTest", group = "testing")
public class encoderTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        double countF;
        double countS;
        double countT;
        countF = 0;
        countS = 0;
        countT = 0;
        telemetry.addData("countF", countF);
        telemetry.addData("countS", countS);
        telemetry.addData("countT", countT);
        motorDeclaration test = new motorDeclaration(hardwareMap);
        waitForStart();


        while (opModeIsActive()) {

/*            while (countF < 5) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.setP(1, 0, 0);
                ++countF;
                telemetry.addData("countF", countF);
                telemetry.update();
            }
            test.setP(0, 0, 0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (countS < 5) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.setP(0, 1, 0);
                ++countS;
                telemetry.addData("countS", countS);
                telemetry.update();
            }
            test.setP(0, 0, 0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }   while (countT < 5) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } test.setP(0, 0, 1);
                ++countT;
                telemetry.addData("countT", countT);
            telemetry.update();


        }

    }
*/




        }

/* test.setP(1,0,0) goes forward
   test.setP(-1,0,0) goes backward
   test.setP(0,1,0) goes left
   test.setP(0,-1,0) goes right
   test.setP(0,0,1) turns left
   test.setP(0,0,-1) turns right
 */
    }
}