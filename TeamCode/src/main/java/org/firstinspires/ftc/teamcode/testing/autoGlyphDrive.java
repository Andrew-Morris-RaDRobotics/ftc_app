package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.utils.motorDeclaration;
import org.firstinspires.ftc.teamcode.utils.intakeDeclaration;

@Autonomous (name= "BlueJewelLeft", group= "testing")
public class autoGlyphDrive extends LinearOpMode {

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;
    public DcMotor fwopperDrive;
    public DcMotor intakeDrive;
    public motorDeclaration motors;
    public intakeDeclaration intake;
    public ColorSensor colorSensor;

    double ratio = colorSensor.blue()/colorSensor.red();
    int glyphColor=0;

    @Override
    public void runOpMode() throws InterruptedException {
        fl=hardwareMap.dcMotor.get("fl");
        fr=hardwareMap.dcMotor.get("fr");
        bl=hardwareMap.dcMotor.get("bl");
        br=hardwareMap.dcMotor.get("br");
        intakeDrive=hardwareMap.dcMotor.get("intakeDrive");
        fwopperDrive=hardwareMap.dcMotor.get("fwopperDrive");
        colorSensor=hardwareMap.colorSensor.get("glyphColor");

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fwopperDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fwopperDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if (fr.getCurrentPosition() > -500) {
            motors.setP(-.3, 0, 0);
        } else {
            motors.setP(0,0,0);
        }

//        if (fwopperDrive.getCurrentPosition()<500) {
//            intake.driveIntake(0.3);
//        }
//        else {
//            intake.driveIntake(0);
//        }

        if (intakeDrive.getCurrentPosition()<500) {
            intake.runConveyor(1);
            if (ratio>0.5 && ratio<0.8) {
                glyphColor=2;
            } else if (ratio>0.8 && ratio<0.9) {
                glyphColor=1;
            } else {
                glyphColor=0;
            }
            telemetry.addData("Glyph Color",glyphColor);
            telemetry.addData("Ratio", ratio);
        } else {
            intake.runAll(0,0);
        }

        motors.setP(0,0,0);
        intake.driveIntake(0);
        intake.runAll(0,0);
    }
}