package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by DeathChicken on 11/16/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "LimitSwitchActivate!", group = "testing")

public class LimitSwitchServoActivator extends OpMode{

    DigitalChannel LimitSwitch;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    gyroCompass testGyro;
    Servo theServo;

    @Override
    public void init() {
        LimitSwitch = (DigitalChannel) hardwareMap.get("LimitSwitch1");
        telemetry.addData("Reading", LimitSwitch.getState());
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        theServo = hardwareMap.servo.get("servo1");

    }

    @Override
    public void loop() {
        telemetry.update();

        if (LimitSwitch.getState() == true)
        {
            fl.setPower(1);
            br.setPower(1);
        }
        else {
            telemetry.addData("False", "");
        }
    }
}
