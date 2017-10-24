package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="AutonomousTest")

public class AutonomousTest extends OpMode{

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");



//        fl.setDirection(DcMotor.Direction.FORWARD);
//        fr.setDirection(DcMotor.Direction.REVERSE);
//        bl.setDirection(DcMotor.Direction.REVERSE);
//        br.setDirection(DcMotor.Direction.FORWARD);


        waitForStart();    
    }

    private void waitForStart() {
    }

    @Override
    public void loop() {
//
//        fr.setPower(1.1*5);
//        fl.setPower(-0.1*5);
//        bl.setPower(-0.8*5);
//        br.setPower(1.15*5);

//        fr.setPower(-.25);
//        fl.setPower(.25);
//        bl.setPower(.25);
//        br.setPower(-.25);
        fr.setTargetPosition(1);
    }





}
