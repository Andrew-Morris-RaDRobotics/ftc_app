package org.firstinspires.ftc.teamcode;

/**
 * Created by Andrew on 11/6/2017.
 */

public class turnTo {

    gyroCompass testGyro;
    int targetDegrees;

    public turnTo(int target){
        targetDegrees = target;
    }

    public double getPower(double targetDeg){
        double heading = testGyro.getHeading();
        double targetDegrees = targetDeg;
        double deadzone = 10;
        double error = targetDegrees - heading;
        double Kp = 1.0 / 150;
        double power = -Kp * error;
        double pow = (-1.0/150) * (90-heading);

        return power;
    }
}
