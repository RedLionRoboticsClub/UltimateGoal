//delivery for robot phone:
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//always name your auto code funny stuff, its the rule. easily being able to find files is for nerds

//also always name everything (adjective)boi because club culture is amazing

@Autonomous(name="AutoBasicer", group="Test")


public class BasicerAuto extends LinearOpMode {

    //variables
    BasicHardwareMap2021 robot = new BasicHardwareMap2021();
    private ElapsedTime runtime = new ElapsedTime();

//i have no idea what func does or why its here but i guess its important?
    double func = 0;

    @Override

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        
//okay, this is the important part. How this section works is basically it calls on a ton of functions you define below to do a certain thing for a certain amount
        //of distance or time by itself. It's the fun part but also is a lot of trial and error but thats fun too so its all good. Once you finish the basic 
        //functions, you will only need to modify code up here which is awesome and not complicated. this was our plan c autonomous if we couldnt get the other one
        //working by competition time, so its very very simple to follow
        
        
        waitForStart();
       sleep(20);
       moveForward(100, .75, 3);
        //tripleonion shoots 3 rings into a goal
       TripleOnion(1000);
       strafeLeft(400, .75,3);
       moveForward(2650, 1, 3);




    }
//now all of the code that makes that stuff above work
    //basically this is structured name(distance, power, timeout), so whenever you type in a function up there ^^ you will need to provide the distance you want the
    //motor to turn (tick is a unit of measurement, dont ask me how much it is i dont know), speed at which it does it, and then a sort of big number but not too
    //big so that when your code inevitably doesnt do what its supposed to do (its the rarbots curse) it doesnt get stuck forever
    public void moveBackward(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0 so that it can count ticks accurately
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        //call for variable you put in for ticks above
        robot.motorFR.setTargetPosition(tick);
        robot.motorBR.setTargetPosition(tick);
        robot.motorFL.setTargetPosition(tick);
        robot.motorBL.setTargetPosition(tick);
        
       //motor move to position
        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //uhhhhh call on the power but i dont know why its down here? just go with it
        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }
        //stop motors when thing done
        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }

    //ALRIGHT! thats one function down! now do the same for all of the different movement directions
    public void moveForward(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(-tick);
        robot.motorBR.setTargetPosition(-tick);
        robot.motorFL.setTargetPosition(-tick);
        robot.motorBL.setTargetPosition(-tick);

        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }

        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }

    public void turnRight(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(-tick);
        robot.motorBR.setTargetPosition(-tick);
        robot.motorFL.setTargetPosition(tick);
        robot.motorBL.setTargetPosition(tick);

        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }

        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }

    public void turnLeft(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(tick);
        robot.motorBR.setTargetPosition(tick);
        robot.motorFL.setTargetPosition(-tick);
        robot.motorBL.setTargetPosition(-tick);

        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }

        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }
    
    //strafing just means like sliding without actually having to turn, you do it by making the motors diagonal from eachother go the same way and the other ones
    //go the other way, just look at this function to figure out which way it moves
    public void strafeLeft(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(-tick);
        robot.motorBR.setTargetPosition(tick);
        robot.motorFL.setTargetPosition(tick);
        robot.motorBL.setTargetPosition(-tick);

        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }

        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }

    public void strafeRight(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(tick);
        robot.motorBR.setTargetPosition(-tick);
        robot.motorFL.setTargetPosition(-tick);
        robot.motorBL.setTargetPosition(tick);

        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        robot.motorFR.setPower(power);
        robot.motorBR.setPower(power);
        robot.motorFL.setPower(power);
        robot.motorBL.setPower(power);

        while (opModeIsActive() &&
                (runtime.seconds() < timeout) &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

        }

        robot.motorFR.setPower(0);
        robot.motorBR.setPower(0);
        robot.motorFL.setPower(0);
        robot.motorBL.setPower(0);

    }

//stop everything in case you need to do that, i dont know what use we had for this but okay
    public void stopMotors() throws InterruptedException {
        robot.motorBR.setPower(0);
        robot.motorBL.setPower(0);
        robot.motorFR.setPower(0);
        robot.motorFL.setPower(0);
    }
//this is a bunch of tiny functions called from the autohardwaremap that i forgot existed that was made to shoot rings. as you can see here, its possible to 
    //put a function within a function, you just need to keep that timeout in case something goes wrong
    public void ShootOnion(int time) throws InterruptedException{
        robot.fastBoi.setPower(1);
        //this is also a good time to talk about sleep things. sleep is where the robot just kind of stops everything for however long you tell it. another good
        //thing to do if ur code isnt working is just throw a bunch of sleeps in and usually that fixes it for whatever reason
        sleep(time);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(0.5);
        sleep(800);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.9);
        sleep(time);
        robot.fastBoi.setPower(0);


    }
//onion^3
    public void TripleOnion(int time) throws InterruptedException{
        robot.fastBoi.setPower(1);
        sleep(time);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(.49);
        sleep(1000);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.25);
        sleep(1000);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(.49);
        sleep(1000);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.25);
        sleep(1000);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(.49);
        sleep(1500);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.25);
        sleep(1500);
        robot.fastBoi.setPower(0);
    }
    //ask barrett to show you the moncher if he hasnt already
    public void OnionEat(int time) throws InterruptedException{
        robot.hoppy.setPower(1);
        sleep(time);
    }

    public void OnionStopEating(int time) throws InterruptedException{
        robot.hoppy.setPower(0);
    }
}

//so yeah, very very basic auto code. If you go and look in basicauto, you can see where things start to get a little more complicated. All of the bottom functions
//in that code are the same as the ones here (i think), its just more of them are used because the code was trying to do more. but yeah, for now thats code until
//you get a robot you can practice on, so go bother the people building or build it yourself so then I can actually teach teach instead of this. have fun!
