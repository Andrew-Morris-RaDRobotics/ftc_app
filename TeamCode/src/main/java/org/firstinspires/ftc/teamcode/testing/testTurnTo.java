package org.firstinspires.ftc.teamcode.testing;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utils.gyroCompass;
import org.firstinspires.ftc.teamcode.utils.motorDeclaration;
import org.firstinspires.ftc.teamcode.utils.turnTo;

@Autonomous (name= "testTurnTo", group= "testing")

public class testTurnTo extends LinearOpMode {

    public DcMotor fr;
    public DcMotor fl;
    public DcMotor bl;
    public DcMotor br;
    public gyroCompass testGyro;
    public turnTo turn;
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        testGyro = new gyroCompass(hardwareMap);
        turn = new turnTo(hardwareMap,testGyro);
       boolean test = false;
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        while (opModeIsActive()) {
            if(!test) {
                //test = turn.turnT(-20.0, 0.015, .00005, .00, 1);
               // test = turn.turnT(-18.0, 0.0125, 0.0005, 0.0, 1);

            }
            else{
                telemetry.addData("done!","yeet");
            }

        telemetry.addData("turnP: ",test);
            telemetry.addData("gyro ",testGyro.getHeading());
            telemetry.update();
            //turn.turnT(10.0,1/40,1/1000,1);
        }

    }
}