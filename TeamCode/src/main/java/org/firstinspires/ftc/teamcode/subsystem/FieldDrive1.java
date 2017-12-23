package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.utils.gyroCompass;
import org.firstinspires.ftc.teamcode.utils.turnTo;
import org.firstinspires.ftc.teamcode.subsystem.drive_at_angle_psudo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "FieldDrive1", group = "testing")

public class FieldDrive1 extends OpMode {

    public DcMotor fr;
    public DcMotor fl;
    public DcMotor bl;
    public DcMotor br;
    public gyroCompass testGyro;
    public turnTo turn;
    drive_at_angle_psudo thing;
    ElapsedTime runtime = new ElapsedTime();
    boolean test=false;
    @Override
    public void init() {
        testGyro = new gyroCompass(hardwareMap);
        turn = new turnTo(hardwareMap, testGyro);

        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        thing = new drive_at_angle_psudo(hardwareMap);
    }

    public void loop(){

                //test = turn.turnT(-20.0, 0.015, .00005, .00, 1);

            double angle = 0;
            double x = gamepad1.right_stick_x;
            double y = -1*gamepad1.right_stick_y;
            if(x>0&&y>0){
                angle=Math.toDegrees(Math.atan(x/y));
            }
            else if(x>0&&y<=0){
                angle=90+-1*Math.toDegrees(Math.atan(y/x));
            }
           else if(x<0&&y<=0){
                angle=-90+-1*Math.toDegrees(Math.atan(y/x));
            }
            else if(x<0&&y>0){
                angle=Math.toDegrees(Math.atan(x/y));
            }
            else if(x==0&&y<0.15){
                angle=180.0;
            }
            else if(x==0&&y>0.15){
                angle=0.0;
            }
            if((Math.abs(x)>.08)&&(Math.abs(y)>.08)&&gamepad1.a){
                turn.turnT(angle, 0.003, 0.0005, 0.0, 1);
            }
        if((Math.abs(x)>.08)&&(Math.abs(y)>.08)&&gamepad1.b) {
            thing.angle(angle, 0.3);
        }
        if(gamepad1.y){
            thing.angle(90,0.25);
        }
        if(gamepad1.x){
            thing.angle(angle,0.25);
        }
            if(gamepad1.dpad_up){
                turn.turnT(0, 0.001, 0.0005, 0.0, 1);
            }

        if(gamepad1.dpad_down){
            turn.turnT(179.9,0.0001, 0.0005, 0.0, 1);
        }

        if(gamepad1.dpad_right){
            turn.turnT(90, 0.001, 0.0005, 0.0, 1);
        }

        if(gamepad1.dpad_left){
            turn.turnT(-90, 0.001, 0.0005, 0.0, 1);
        }

       // angle=Math.toDegrees(Math.atan(x/y));
               // turn.turnT(-18.0, 0.0125, 0.0005, 0.0, 1);




            telemetry.addData("angle: ",angle);
           // telemetry.addData("gyro ",testGyro.getHeading());
            telemetry.update();
            //turn.turnT(10.0,1/40,1/1000,1);
        }

    }
