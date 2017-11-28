package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class CryptoboxAlignment extends LinearOpMode {
    private static final double RUN_POWER = 0.2;
    private static final double STOP = 0.0;

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    DistanceSensor distanceSensor1;
    DistanceSensor distanceSensor2;

    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        distanceSensor1 = hardwareMap.get(DistanceSensor.class, "blDistance");
        distanceSensor2 = hardwareMap.get(DistanceSensor.class, "brDistance");

        while (distanceSensor2.getDistance(DistanceUnit.INCH) > 10) {
            fl.setPower(-RUN_POWER);
            fr.setPower(-RUN_POWER);
            bl.setPower(RUN_POWER);
            br.setPower(RUN_POWER);
        }

        while (distanceSensor1.getDistance(DistanceUnit.INCH) > distanceSensor2.getDistance(DistanceUnit.INCH)) {
            fl.setPower(-RUN_POWER);
            fr.setPower(-RUN_POWER);
            bl.setPower(-RUN_POWER);
            br.setPower(-RUN_POWER);
        }
        while (distanceSensor1.getDistance(DistanceUnit.INCH) < distanceSensor2.getDistance(DistanceUnit.INCH)) {
            fl.setPower(RUN_POWER);
            fr.setPower(RUN_POWER);
            bl.setPower(RUN_POWER);
            br.setPower(RUN_POWER);
        }

        fl.setPower(STOP);
        fr.setPower(STOP);
        bl.setPower(STOP);
        br.setPower(STOP);
    }
}
