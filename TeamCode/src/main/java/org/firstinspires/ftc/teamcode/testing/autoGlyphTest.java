package org.firstinspires.ftc.teamcode.testing;


import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utils.GlyphVision;
import org.firstinspires.ftc.teamcode.utils.gyroCompass;
import org.firstinspires.ftc.teamcode.utils.motorDeclaration;

@Autonomous(name= "autoGlyphTest", group= "testing")
public class autoGlyphTest extends LinearOpMode {

    public GlyphVision glyph;
    public Servo leftIntakeFlipper;
    public Servo rightIntakeFlipper;
    public DcMotor fwoppers;
    public DcMotor intakeDrive;
    public gyroCompass testGyro;
    public Servo intakeBucket;
    public ColorSensor glyphColor;
    public DistanceSensor glyphDistance;
    public DcMotor conveyor;
    public Servo conveyorTop;

    @Override
    public void runOpMode() throws InterruptedException {
        fwoppers = hardwareMap.dcMotor.get("fwopperDrive");
        intakeBucket = hardwareMap.servo.get("intakeBucket");
        leftIntakeFlipper = hardwareMap.servo.get("leftIntakeFlipper");
        rightIntakeFlipper = hardwareMap.servo.get("rightIntakeFlipper");
        glyphColor = hardwareMap.colorSensor.get("glyphColor");
        glyphDistance = hardwareMap.get(DistanceSensor.class, "glyphColor");
        conveyor=hardwareMap.dcMotor.get("conveyor");
        conveyorTop=hardwareMap.servo.get("conveyorTop");

        float red = glyphColor.red();
        float blue = glyphColor.blue();
        float green = glyphColor.green();
        double ratio = blue / red;
        double ratio2 = blue / green;
        double distance=glyphDistance.getDistance(DistanceUnit.CM);

        final int NO_GLYPH=0;
        final int STUCK_GLYPH=1;
        final int GLYPH=2;
        int glyphState=0;

        ElapsedTime driveTime=new ElapsedTime();

        driveTime.reset();

        while (distance>10 && driveTime.time()<10) {
            intakeDrive.setPower(.8);
            intakeBucket.setPosition(0.62);
        }

        double time = driveTime.time();
        telemetry.addData("Drive Time", time);
        telemetry.addData("intakeDrive Position", intakeDrive.getCurrentPosition());
        telemetry.update();

        ElapsedTime intakeTime=new ElapsedTime();

        while (distance>5 && intakeTime.time()<10) {
            intakeDrive.setPower(0);
            fwoppers.setPower(1);
        }

        double intakeTimer=intakeTime.time();

        if (distance<2) {
            telemetry.addData("Glyph State", "Loaded");
            telemetry.addData("Intake Time", intakeTimer);
            if (ratio>0.64 && ratio<0.7 && ratio2>0.8 && ratio<0.83) {
                telemetry.addData("Glyph Color", "Brown");
            } else if (ratio>0.86 && ratio<0.89 && ratio2>0.86 && ratio2<0.88) {
                telemetry.addData("Glyph Color", "Gray");
            } else {
                telemetry.addData("Glyph Color", "Unknown");
            }
        } else if (distance<5) {
            telemetry.addData("Glyph State", "Stuck");
            telemetry.addData("Intake Time", intakeTimer);
            if (ratio>0.64 && ratio<0.7) {
                telemetry.addData("Glyph Color", "Brown");
            } else if (ratio>0.86 && ratio<0.89) {
                telemetry.addData("Glyph Color", "Gray");
            } else {
                telemetry.addData("Glyph Color", "Unknown");
            }
        } else {
            telemetry.addData("Loading", "Unsuccessful");
        }

        telemetry.update();

        driveTime.reset();

        while (time>driveTime.time()) {
            intakeDrive.setPower(-.8);
        }

        ElapsedTime conveyorTime=new ElapsedTime();

        while (conveyorTime.time()<0.62) {
            conveyor.setPower (1);
        }

        conveyorTop.setPosition(1);

        intakeBucket.setPosition(0.15);

        conveyor.setPower(-0.45);

        while (conveyorTime.time() < 1) {
        }

        telemetry.addData("Conveyor Position", conveyor.getCurrentPosition());
        telemetry.update();
    }
}
