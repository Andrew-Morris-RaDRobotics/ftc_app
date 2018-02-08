package org.firstinspires.ftc.teamcode.ChallengeFile;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utils.gyroCompass;
import org.firstinspires.ftc.teamcode.utils.motorDeclaration;
import org.firstinspires.ftc.teamcode.utils.turnTo;

@Autonomous(name = "BlueAUTOTest", group = "testing")

public class BlueAUTOTest extends LinearOpMode {

    public Servo jewelStick;
    public ColorSensor leftJewel;
    public ColorSensor rightJewel;
    public DcMotor fl;
    public DcMotor fr;
    DcMotor conveyor;
    public DcMotor br;
    public DcMotor bl;
    public motorDeclaration Motors;
    public gyroCompass testGyro;
    public turnTo turn;

    @Override
    public void runOpMode() throws InterruptedException {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        fr.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);
        testGyro = new gyroCompass(hardwareMap);
        turn = new turnTo(hardwareMap, testGyro);
        Motors = new motorDeclaration(hardwareMap);

        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        jewelStick = hardwareMap.servo.get("jewelStick");
        leftJewel = hardwareMap.colorSensor.get("leftJewel");
        rightJewel = hardwareMap.colorSensor.get("rightJewel");
        double target = 0;
        waitForStart();

        jewelStick.setPosition(1);
        int color = 0; //1 is red 2 is blu (left side)
        double curr = 0.0;
        ElapsedTime runtime = new ElapsedTime();
        runtime.reset();
        while (opModeIsActive()) {
        }
        while (color == 0) {
            if (runtime.seconds() > 7.0) {
                target = 0;
                color = 3;
            } else if (leftJewel.red() - 5 > rightJewel.red() && rightJewel.blue() - 5 > leftJewel.blue() && runtime.seconds() > 2) {
                telemetry.addData("left side is red", "right side is blue");
                color = 2;
                curr = testGyro.getHeading();
                target = -25;
                // sleep(1000);
            } else if (leftJewel.red() + 5 < rightJewel.red() && rightJewel.blue() + 5 < leftJewel.blue() && runtime.seconds() > 2) {
                telemetry.addData("right side is red", "left side is blue");
                color = 1;
                curr = testGyro.getHeading();
                target = 25;
                //sleep(1000);

            }
        }
        while (color > 0) {
            turn.turnT(target, 0.005, 0.0004, 0.0, 3);
            jewelStick.setPosition(0);
            if (testGyro.getHeading() == target) {
                fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                fr.setTargetPosition(1100);
                fr.setPower(0.25);

                fl.setTargetPosition(-1100);
                fl.setPower(0.25);

                br.setTargetPosition(1100);
                br.setPower(0.25);

                bl.setTargetPosition(-1100);
                bl.setPower(0.25);

                color = 1;
            }
            if (runtime.seconds() > 5) {
                runtime.reset();
            }
        }

    }
}