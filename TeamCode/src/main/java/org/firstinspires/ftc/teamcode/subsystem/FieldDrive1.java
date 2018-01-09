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
    double p;
    double i;
    double speed;
    boolean dontDoIt;
    @Override
    public void init() {
dontDoIt=false;
        testGyro = new gyroCompass(hardwareMap);
        turn = new turnTo(hardwareMap, testGyro);

        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        thing = new drive_at_angle_psudo(hardwareMap);
        p=.0016;
        i=.0002;
        speed=0.3;
    }

    public void loop(){
        speed=0.22;
        speed += gamepad1.right_trigger*.78;
dontDoIt=false;
                //test = turn.turnT(-20.0, 0.015, .00005, .00, 1);



        double angle2 = 0;
        double x2 = gamepad1.left_stick_x;
        double y2 = -1*gamepad1.left_stick_y;
        if(x2>0&&y2>0){
            angle2=Math.toDegrees(Math.atan(x2/y2));
        }
        else if(x2>0&&y2<=0){
            angle2=90+-1*Math.toDegrees(Math.atan(y2/x2));
        }
        else if(x2<0&&y2<=0){
            angle2=-90+-1*Math.toDegrees(Math.atan(y2/x2));
        }
        else if(x2<0&&y2>0){
            angle2=Math.toDegrees(Math.atan(x2/y2));
        }
        else if(x2==0&&y2<0.15){
            angle2=180.0;
        }
        else if(x2==0&&y2>0.15){
            angle2=0.0;
        }

        telemetry.addData("gyro",testGyro.getHeading());
//        if(((Math.abs(x)>.08)||(Math.abs(y)>.08))){
//                turn.turnT(angle, p, i, 0.0, 1);
//            dontDoIt=true;
//            }

         if(((Math.abs(x2)>.08)||(Math.abs(y2)>.08))) {
            thing.angle(angle2-(-1*testGyro.getHeading()), speed,-1*gamepad1.right_stick_x*(speed+.03));
            telemetry.addData("angle",angle2);
            telemetry.addData("target angle",angle2-(-1*testGyro.getHeading()));
        }

        else if(Math.abs(gamepad1.right_stick_x)>.05){
             fr.setPower(-1*gamepad1.right_stick_x*speed);
             fl.setPower(-1*gamepad1.right_stick_x*speed);
             br.setPower(-1*gamepad1.right_stick_x*speed);
             bl.setPower(-1*gamepad1.right_stick_x*speed);
         }
          else if(gamepad1.dpad_up){
                turn.turnT(0, 0.005, 0.0005, 0.0, 1);
            }

       else if(gamepad1.dpad_down){
            turn.turnT(179.9,0.005, 0.0005, 0.0, 1);
        }

        else if(gamepad1.dpad_right){
            turn.turnT(90, 0.005, 0.0005, 0.0, 1);
        }

        else if(gamepad1.dpad_left){
            turn.turnT(-90, 0.005, 0.0005, 0.0, 1);
        }


        else if(!dontDoIt){
            fr.setPower(0);
            fl.setPower(0);
            br.setPower(0);
            bl.setPower(0);
        }
       // angle=Math.toDegrees(Math.atan(x/y));
               // turn.turnT(-18.0, 0.0125, 0.0005, 0.0, 1);




            telemetry.addData("angle: ",angle2);
           // telemetry.addData("gyro ",testGyro.getHeading());
            telemetry.update();
            //turn.turnT(10.0,1/40,1/1000,1);
        }

    }
