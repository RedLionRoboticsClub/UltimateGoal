package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

@TeleOp(name="ColinWillBeMad", group="Test")

public class ColinWillBeMad extends LinearOpMode {

    //Connect Hardware Maps
    BasicHardwareMap2021 robot = new BasicHardwareMap2021();

    private ElapsedTime runtime = new ElapsedTime();

    //Run LinearOpMode
    @Override
    public void runOpMode() {

        //Initiate Hardware Map
        robot.init(hardwareMap);

        robot.finger.setPosition(.47);

        //Add Telemetry to confirm Robot Start. Recommended to name her here
        double motorPower = 1;
        double strafePower = 1;


        // Wait for the game to start (driver presses PLAY)

        waitForStart();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()){

            //---------------------
            //------GAMEPAD 1------
            //---------------------

            //-------WHEELS--------
            double yLValue = gamepad1.left_stick_y;
            double yRValue = gamepad1.right_stick_y;
            double xLValue = gamepad1.left_stick_x;
            double xRValue = gamepad1.right_stick_x;


            boolean changed = false;
            boolean changed0 = false;
            boolean changed1 = false;
            boolean changed2 = false;

            if(gamepad1.b && !changed){
                if(motorPower == 1){
                    while (gamepad1.b) {
                        motorPower = .55;
                        strafePower = .65;
                        changed = true;
                        telemetry.addData("Current Speed:", " Slow");
                        telemetry.addData("Motor Power", motorPower);
                        telemetry.update();
                    }
                }
                else if (motorPower == .55){
                    while (gamepad1.b) {
                        motorPower = 1;
                        strafePower = 1;
                        changed = true;
                        telemetry.addData("Current Speed:", " Fast");
                        telemetry.addData("Motor Power", motorPower);
                        telemetry.update();
                    }
                }
            }
            else {
                changed = true;
            }

            double radL = Math.sqrt(Math.pow(xLValue,2)+Math.pow(yLValue,2));

            double radR = Math.sqrt(Math.pow(xRValue,2)+Math.pow(yRValue,2));




            //Movement
            yLValue = Range.clip(yLValue, -1, 1);
            yRValue = Range.clip(yRValue, -1, 1);
            xLValue = Range.clip(xLValue, -1, 1);
            xRValue = Range.clip(xRValue, -1, 1);

            if (yLValue > 0 && radL > .1) {
                robot.motorFL.setPower(motorPower);
                robot.motorBL.setPower(motorPower);
            }   if (yLValue < 0 && radL > .1) {
                robot.motorFL.setPower(-motorPower);
                robot.motorBL.setPower(-motorPower);
            }   if (yRValue > 0 && radR > .1){
                robot.motorFR.setPower(motorPower);
                robot.motorBR.setPower(motorPower);
            }   if (yRValue < 0 && radR > .1){
                robot.motorFR.setPower(-motorPower);
                robot.motorBR.setPower(-motorPower);
            }


            if (gamepad1.dpad_down &&gamepad1.dpad_left){

                robot.motorFL.setPower(strafePower);
                robot.motorBR.setPower(strafePower);
                robot.motorBL.setPower(0);
                robot.motorFR.setPower(0);

            }
            else if(gamepad1.dpad_up &&gamepad1.dpad_right){

                robot.motorFL.setPower(-strafePower);
                robot.motorBR.setPower(-strafePower);
                robot.motorBL.setPower(0);
                robot.motorFR.setPower(0);

            }else if (gamepad1.dpad_down &&gamepad1.dpad_right){

                robot.motorFR.setPower(strafePower);
                robot.motorBL.setPower(strafePower);
                robot.motorFL.setPower(0);
                robot.motorBR.setPower(0);

            }else if (gamepad1.dpad_up &&gamepad1.dpad_left){

                robot.motorFR.setPower(-strafePower);
                robot.motorBL.setPower(-strafePower);
                robot.motorFL.setPower(0);
                robot.motorBR.setPower(0);

            }else if (gamepad1.dpad_right){

                robot.motorFL.setPower(-strafePower);
                robot.motorBR.setPower(-strafePower);
                robot.motorBL.setPower(strafePower);
                robot.motorFR.setPower(strafePower);

            }else if (gamepad1.dpad_left){

                robot.motorFL.setPower(strafePower);
                robot.motorBR.setPower(strafePower);
                robot.motorBL.setPower(-strafePower);
                robot.motorFR.setPower(-strafePower);

            }else if (gamepad1.dpad_up){

                robot.motorFL.setPower(-motorPower);
                robot.motorBR.setPower(-motorPower);
                robot.motorBL.setPower(-motorPower);
                robot.motorFR.setPower(-motorPower);

            }else if (gamepad1.dpad_down){

                robot.motorFL.setPower(motorPower);
                robot.motorBR.setPower(motorPower);
                robot.motorBL.setPower(motorPower);
                robot.motorFR.setPower(motorPower);

            } else {

                robot.motorFL.setPower(0);
                robot.motorBL.setPower(0);
                robot.motorBR.setPower(0);
                robot.motorFR.setPower(0);
            }

            if(gamepad1.x && !changed1){
                if(robot.fastBoi.getPower() == 1){
                    while (gamepad1.x) {

                        robot.fastBoi.setPower(0);
                        changed1 = true;

                    }
                }
                else if (robot.fastBoi.getPower() == 0){
                    while (gamepad1.x) {

                        robot.fastBoi.setPower(1);
                        changed1 = true;

                    }
                }
            }
            else {
                changed1 = true;
            }

            if(gamepad1.y && !changed2){
                if(robot.hoppy.getPower() == 1){
                    while (gamepad1.y) {

                        robot.hoppy.setPower(0);
                        changed2 = true;

                    }
                }
                else if (robot.hoppy.getPower() == 0){
                    while (gamepad1.y) {

                        robot.hoppy.setPower(1);
                        changed2 = true;

                    }
                }
            }
            else {
                changed2 = true;
            }

            if(gamepad1.right_trigger > .3 && !changed0){

                while (gamepad1.right_trigger > .3 ) {
                    robot.finger.setDirection(Servo.Direction.FORWARD);
                    robot.finger.setPosition(.25);
                }
                robot.finger.setDirection(Servo.Direction.FORWARD);
                robot.finger.setPosition(.49);




            }



        }

    }
}





