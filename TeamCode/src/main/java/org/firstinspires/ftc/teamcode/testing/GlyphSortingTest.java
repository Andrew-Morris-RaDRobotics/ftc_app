package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import java.util.concurrent.TimeUnit;


public class GlyphSortingTest extends OpMode {

    ColorSensor colorSensor;

    @Override
    public void init() {
        ColorSensor colorSensor;
        colorSensor = hardwareMap.colorSensor.get("colordistance1");
        double ratio = colorSensor.blue() / colorSensor.red();
        telemetry.addData("Ratio", ratio);
    }

    @Override
    public void loop() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        colorSensor = hardwareMap.colorSensor.get("colordistance1");
        double ratio = colorSensor.blue() / colorSensor.red();
        int[][] cryptobox = new int[4][3];

        if (ratio > 0.8) {
            if (cryptobox[2][0] == 0) {
                telemetry.addData("(0,2) Gray", "");
                cryptobox[2][0] = 1;
            } else if (cryptobox[0][0] == 0) {
                telemetry.addData("(0,4) Gray", "");
                cryptobox[0][0] = 1;
            } else if (cryptobox[3][1] == 0) {
                telemetry.addData("(1,1) Gray", "");
                cryptobox[3][1] = 1;
            } else if (cryptobox[1][1] == 0) {
                telemetry.addData("(1,3) Gray", "");
                cryptobox[1][1] = 1;
            } else if (cryptobox[2][2] == 0) {
                telemetry.addData("(2,2) Gray", "");
                cryptobox[2][2] = 1;
            } else if (cryptobox[0][2] == 0) {
                telemetry.addData("(2,4) Gray", "");
                cryptobox[0][2] = 1;
            } else {
                telemetry.addData("", "");
            }
        } else if (ratio > 0.5 && ratio < 0.8) {
            if (cryptobox[3][0] == 0) {
                telemetry.addData("(0,0 Brown", "");
                cryptobox[3][0] = 2;
            } else if (cryptobox[1][0] == 0) {
                telemetry.addData("(0,2) Brown", "");
                cryptobox[1][0] = 2;
            } else if (cryptobox[2][1] == 0) {
                telemetry.addData("(1,2) Brown", "");
                cryptobox[2][1] = 2;
            } else if (cryptobox[0][1] == 0) {
                telemetry.addData("(1,4) Brown", "");
                cryptobox[0][1] = 2;
            } else if (cryptobox[3][2] == 0) {
                telemetry.addData("(2,0) Brown", "");
                cryptobox[3][2] = 2;
            } else if (cryptobox[1][2] == 0) {
                telemetry.addData("(2,2) Brown", "");
                cryptobox[1][2] = 2;
            }
        }

    }
}
