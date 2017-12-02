package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "GlyphSorting", group = "testing")

public class GlyphSortingTest extends OpMode {

    private static final int BROWN = 2;
    private static final int GRAY = 1;
    private static final int EMPTY = 0;
    private int glyphState = 0;
    private int[][] cryptobox = new int[4][3];

    @Override
    public void init() {
        ColorSensor colorSensor;
        colorSensor = hardwareMap.colorSensor.get("glyphColor");
        double blue = colorSensor.blue();
        double red = colorSensor.red();
        double ratio = blue / red;
        telemetry.addData("Ratio", ratio);
    }

    @Override
    public void loop() {
        ColorSensor colorSensor = hardwareMap.colorSensor.get("glyphColor");
        double blue = colorSensor.blue();
        double red = colorSensor.red();
        double ratio = blue / red;

        while (ratio > 0.5 && ratio < 0.9) {
            glyphState = BROWN;
        }
        while (ratio > 0.8 && ratio < 0.9) {
            glyphState = GRAY;
        }

        if (glyphState == GRAY) {
            if (cryptobox[2][0] == EMPTY && cryptobox[3][0] != EMPTY) {
                cryptobox[2][0] = GRAY;
                glyphState = EMPTY;
            } else if (cryptobox[0][0] == EMPTY && cryptobox[1][0] != EMPTY) {
                cryptobox[0][0] = GRAY;
                glyphState = EMPTY;
            } else if (cryptobox[3][1] == EMPTY) {
                cryptobox[3][1] = GRAY;
                glyphState = EMPTY;
            } else if (cryptobox[1][1] == EMPTY && cryptobox[2][1] != EMPTY) {
                cryptobox[1][1] = GRAY;
                glyphState = EMPTY;
            } else if (cryptobox[2][2] == EMPTY && cryptobox[3][2] != EMPTY) {
                cryptobox[2][2] = GRAY;
                glyphState = EMPTY;
            } else if (cryptobox[0][2] == EMPTY && cryptobox[1][2] != EMPTY) {
                cryptobox[0][2] = GRAY;
                glyphState = EMPTY;
            } else {
                telemetry.addData("", "");
                glyphState = EMPTY;
            }

        }
        if (glyphState == BROWN) {
            if (cryptobox[3][0] == EMPTY) {
                cryptobox[3][0] = BROWN;
                glyphState = EMPTY;
            } else if (cryptobox[1][0] == EMPTY && cryptobox[2][0] != EMPTY) {
                cryptobox[1][0] = BROWN;
                glyphState = EMPTY;
            } else if (cryptobox[2][1] == EMPTY && cryptobox[3][1] != EMPTY) {
                cryptobox[2][1] = BROWN;
                glyphState = EMPTY;
            } else if (cryptobox[0][1] == EMPTY && cryptobox[1][1] != EMPTY) {
                cryptobox[0][1] = BROWN;
                glyphState = EMPTY;
            } else if (cryptobox[3][2] == EMPTY) {
                cryptobox[3][2] = BROWN;
                glyphState = EMPTY;
            } else if (cryptobox[1][2] == EMPTY && cryptobox[2][2] != EMPTY) {
                cryptobox[1][2] = BROWN;
                glyphState = EMPTY;
            }
        }
        telemetry.addLine()
                .addData("|", cryptobox[0][0])
                .addData("|", cryptobox[0][1])
                .addData("|", cryptobox[0][2]);
        telemetry.addLine()
                .addData("|", cryptobox[1][0])
                .addData("|", cryptobox[1][1])
                .addData("|", cryptobox[1][2]);
        telemetry.addLine()
                .addData("|", cryptobox[2][0])
                .addData("|", cryptobox[2][1])
                .addData("|", cryptobox[2][2]);
        telemetry.addLine()
                .addData("|", cryptobox[3][0])
                .addData("|", cryptobox[3][1])
                .addData("|", cryptobox[3][2]);
        telemetry.update();

    }
}
