package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.utils.GlyphVision;

@Autonomous(name="AutonomousTest")

public class AutonomousTest extends OpMode{

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotor bl;
    public GlyphVision test;
    @Override
    public void init() {
        test = new GlyphVision(hardwareMap);
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("bl");
        bl = hardwareMap.dcMotor.get("br");

        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);

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

        //br.setTargetPosition(10/2);
        //br.setPower(0.25);

        //bl.setTargetPosition(-10/2);
        //bl.setPower(-0.25);
        test.getGlyph();
        telemetry.addLine(test.getGlyph().toString());
        telemetry.addLine(test.getPos());
        telemetry.update();
    }
}
