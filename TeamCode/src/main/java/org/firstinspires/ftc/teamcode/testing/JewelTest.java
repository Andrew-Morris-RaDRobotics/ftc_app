package org.firstinspires.ftc.teamcode.testing;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.motorDeclaration;

import java.util.concurrent.TimeUnit;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "JewelTest", group = "testing")


public class JewelTest extends LinearOpMode{


    ColorSensor colorSensorRight;
    ColorSensor colorSensorLeft;
    Servo jewelextender;
    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;

    @Override
    public void runOpMode() throws InterruptedException {
        colorSensorLeft = hardwareMap.colorSensor.get("leftJewel");
        colorSensorRight = hardwareMap.colorSensor.get("rightJewel");
        jewelextender = hardwareMap.servo.get("jewelStick");
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");



        jewelextender.setPosition(0);
        if (colorSensorRight.red() > colorSensorRight.blue() && colorSensorRight.red()>colorSensorLeft.red()) {
            telemetry.addData("Right jewel red","");
            TimeUnit.SECONDS.sleep(2);
            fr.setTargetPosition(fr.getCurrentPosition()-30);
            br.setTargetPosition(br.getCurrentPosition()+30);

        } else if (colorSensorRight.blue()>colorSensorRight.red() && colorSensorRight.blue()>colorSensorLeft.blue()) {
            telemetry.addData("Left jewel red", "");
            TimeUnit.SECONDS.sleep(2);
            fl.setTargetPosition(fl.getCurrentPosition()+30);
            bl.setTargetPosition(bl.getCurrentPosition()-30);
        } else {
            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
        }

        jewelextender.setPosition(0);
    }
}
