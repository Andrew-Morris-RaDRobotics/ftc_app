package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utils.motorDeclaration;

public class CryptoboxAlignment extends LinearOpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    DistanceSensor distanceSensor;
    DistanceSensor distanceSensor2;


    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "blDistance");
        distanceSensor2 = hardwareMap.get(DistanceSensor.class, "brDistance");

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (distanceSensor2.getDistance(DistanceUnit.INCH) > 10) {
            fl.setPower(-0.2);
            fr.setPower(-0.2);
            bl.setPower(0.2);
            br.setPower(0.2);
        }
        fl.setTargetPosition(-210);
        fr.setTargetPosition(-210);
        bl.setTargetPosition(210);
        bl.setTargetPosition(210);

        while (distanceSensor.getDistance(DistanceUnit.INCH) > distanceSensor2.getDistance(DistanceUnit.INCH)) {
            fl.setPower(-0.2);
            fr.setPower(-0.2);
            bl.setPower(-0.2);
            br.setPower(-0.2);
        }
        while (distanceSensor.getDistance(DistanceUnit.INCH) < distanceSensor2.getDistance(DistanceUnit.INCH)) {
            fl.setPower(0.2);
            fr.setPower(0.2);
            bl.setPower(0.2);
            br.setPower(0.2);
        }

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }
}
