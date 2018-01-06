package org.firstinspires.ftc.teamcode.subsystem;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.drive_at_angle_psudo;
import org.firstinspires.ftc.teamcode.utils.gyroCompass;
import org.firstinspires.ftc.teamcode.utils.turnTo;

@Autonomous (name= "testTurnTo0o", group= "testing")

public class testTurnTo0o extends LinearOpMode {

    public DcMotor fr;
    public DcMotor fl;
    public DcMotor bl;
    public DcMotor br;
    public gyroCompass testGyro;
    public turnTo turn;
    ElapsedTime runtime = new ElapsedTime();
    drive_at_angle_psudo thing;

    @Override
    public void runOpMode() throws InterruptedException {
        testGyro = new gyroCompass(hardwareMap);
        turn = new turnTo(hardwareMap,testGyro);
       boolean test = false;
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        thing = new drive_at_angle_psudo(hardwareMap);
        //testGyro.angle(10.0,0.3);
        while (opModeIsActive()) {
            //thing.angle(90.0,0.3);
            //turn.turnT(10.0,1/40,1/1000,1);
        }

    }
}