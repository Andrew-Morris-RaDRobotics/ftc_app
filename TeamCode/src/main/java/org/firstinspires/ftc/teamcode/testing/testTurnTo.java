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


    public gyroCompass testGyro;
    public turnTo turn;
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        testGyro = new gyroCompass(hardwareMap);
        turn = new turnTo(hardwareMap,testGyro);

        while (opModeIsActive()) {
        telemetry.addData("turnP: ",
            turn.turnT(10.0,1/40,1/1000,1));
            telemetry.update();
            //turn.turnT(10.0,1/40,1/1000,1);
        }

    }
}