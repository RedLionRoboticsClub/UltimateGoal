package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="AutoBasic", group="Test")


public class BasicAuto extends LinearOpMode {

    //variables
    BasicHardwareMap2021 robot = new BasicHardwareMap2021();
    private ElapsedTime runtime = new ElapsedTime();

    double func = 0;

    @Override

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();
        sleep(20);
        strafeLeft(25, 0.5, 2);
        sleep(200);
        moveForward(50, 0.7, 3);
        ShootOnion(1000);
        sleep(20);
        strafeRight(2,0.2,2);
        ShootOnion(1000);
        strafeRight(2,0.2,2);
        ShootOnion(1000);
        moveForward(5,.5,2);





    }

    public void moveForward(int tick, double power, int timeout) throws InterruptedException {

        //Set encoder values to 0
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.motorFR.setTargetPosition(tick);
        robot.motorBR.setTargetPosition(tick);
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

    public void moveBackward(int tick, double power, int timeout) throws InterruptedException {

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

    public void strafeRight(int tick, double power, int timeout) throws InterruptedException {

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

    public void strafeLeft(int tick, double power, int timeout) throws InterruptedException {

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


    public void stopMotors() throws InterruptedException {
        robot.motorBR.setPower(0);
        robot.motorBL.setPower(0);
        robot.motorFR.setPower(0);
        robot.motorFL.setPower(0);
    }

    public void ShootOnion(int time) throws InterruptedException{
        robot.fastBoi.setPower(1);
        sleep(time);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(0.5);
        sleep(800);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.9);
        sleep(time);
        robot.fastBoi.setPower(0);


    }

    public void TripleOnion(int time) throws InterruptedException{
        robot.fastBoi.setPower(1);
        sleep(time);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(0.5);
        sleep(800);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.9);
        sleep(1000);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(0.5);
        sleep(800);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.9);
        sleep(1000);
        robot.finger.setDirection(Servo.Direction.REVERSE);
        robot.finger.setPosition(0.5);
        sleep(800);
        robot.finger.setDirection(Servo.Direction.FORWARD);
        robot.finger.setPosition(0.9);
        sleep(time);
        robot.fastBoi.setPower(0);
    }
    public void OnionEat(int time) throws InterruptedException{
        robot.hoppy.setPower(1);
        sleep(time);
    }

    public void OnionStopEating(int time) throws InterruptedException{
        robot.hoppy.setPower(0);
    }
}

