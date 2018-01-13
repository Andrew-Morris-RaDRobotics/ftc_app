package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class glyphSort extends OpMode {
    public ColorSensor colorSensor;
    static final int BROWN = 2;
    static final int GRAY = 1;
    static final int EMPTY = 0;
    boolean glyphLoaded = false;
    double blue = colorSensor.blue();
    double red = colorSensor.red();
    double ratio = blue / red;
    int glyphColor = EMPTY;
    int[][] cryptobox = new int[4][3];

    public glyphSort(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.colorSensor.get("glyphColor");
    }

    public void glyphSort(boolean activated) {
        if (activated) {
            if (glyphLoaded = false) {
                if (ratio > 0.5 && ratio < 0.8) {
                    glyphColor = BROWN;
                    glyphLoaded = true;
                } else if (ratio > 0.8 && ratio < 0.9) {
                    glyphColor = GRAY;
                    glyphLoaded = true;
                } else {
                    glyphLoaded = false;
                    glyphColor = EMPTY;
                }

                if (glyphColor == GRAY) {
                    if (cryptobox[2][0] == EMPTY && cryptobox[3][0] != EMPTY) {
                        cryptobox[2][0] = GRAY;
                        ;
                    } else if (cryptobox[0][0] == EMPTY && cryptobox[1][0] != EMPTY) {
                        cryptobox[0][0] = GRAY;
                        ;
                    } else if (cryptobox[3][1] == EMPTY) {
                        cryptobox[3][1] = GRAY;
                        ;
                    } else if (cryptobox[1][1] == EMPTY && cryptobox[2][1] != EMPTY) {
                        cryptobox[1][1] = GRAY;
                        ;
                    } else if (cryptobox[2][2] == EMPTY && cryptobox[3][2] != EMPTY) {
                        cryptobox[2][2] = GRAY;
                        ;
                    } else if (cryptobox[0][2] == EMPTY && cryptobox[1][2] != EMPTY) {
                        cryptobox[0][2] = GRAY;
                        ;
                    } else {
                        ;
                    }

                }
                if (glyphColor == BROWN) {
                    if (cryptobox[3][0] == EMPTY) {
                        cryptobox[3][0] = BROWN;
                        ;
                    } else if (cryptobox[1][0] == EMPTY && cryptobox[2][0] != EMPTY) {
                        cryptobox[1][0] = BROWN;
                        ;
                    } else if (cryptobox[2][1] == EMPTY && cryptobox[3][1] != EMPTY) {
                        cryptobox[2][1] = BROWN;
                        ;
                    } else if (cryptobox[0][1] == EMPTY && cryptobox[1][1] != EMPTY) {
                        cryptobox[0][1] = BROWN;
                        ;
                    } else if (cryptobox[3][2] == EMPTY) {
                        cryptobox[3][2] = BROWN;
                        ;
                    } else if (cryptobox[1][2] == EMPTY && cryptobox[2][2] != EMPTY) {
                        cryptobox[1][2] = BROWN;
                    }
                }
            } else {
                if (ratio > 0.5 && ratio < 0.8) {
                    glyphColor = BROWN;
                    glyphLoaded = true;
                } else if (ratio > 0.8 && ratio < 0.9) {
                    glyphColor = GRAY;
                    glyphLoaded = true;
                } else {
                    glyphLoaded = false;
                    glyphColor = EMPTY;
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
        }
    }
}
