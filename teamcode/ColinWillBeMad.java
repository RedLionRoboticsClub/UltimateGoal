//import all the stuff again
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

//define and name your teleop (per tradition its the presidents name then will be mad because the prez hates funny things apparently) 
@TeleOp(name="ColinWillBeMad", group="Test")

//where the class starts 
public class ColinWillBeMad extends LinearOpMode {

    //Connect Hardware Maps
    BasicHardwareMap2021 robot = new BasicHardwareMap2021();

    private ElapsedTime runtime = new ElapsedTime();

    //Run LinearOpMode
    @Override
    public void runOpMode() {

        //Initiate Hardware Map / do anything you need to do for set up
        robot.init(hardwareMap);

        robot.finger.setPosition(.47);

        //Add Telemetry to confirm Robot Start. Recommended to name her here
        double motorPower = 1;
        double strafePower = 1;


        // Wait for the game to start (driver presses PLAY)

        waitForStart();

        robot.grabby.setPosition(.5);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()){

            //---------------------
            //------GAMEPAD 1------
            //---------------------
            
            //everything below here is stuff that can be left the same, deleted, or very slightly modified depending on what you want to do. Honestly I dont know
            //what the game is or what you'll need, so for now i'll just leave this as it is. Over the weekend I'll get some more stuff together and annotate this
            //further, I just dont have the time right now

            //-------WHEELS--------
            
            //i believe this is for the tank control robot, which is 1 stick per wheel. Personally I think thats best, but driver gets to choose
            
            double yLValue = gamepad1.left_stick_y;
            double yRValue = gamepad1.right_stick_y;
            double xLValue = gamepad1.left_stick_x;
            double xRValue = gamepad1.right_stick_x;


            boolean changed = false;
            boolean changed0 = false;
            boolean changed1 = false;
            boolean changed2 = false;
            boolean changed3 = false;

            //if whatever button is pressed, then do thing/move is the entire basis of all of this section
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
            
//Dont worry about the math right now I think, thats a later issue
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
            
//Moving from the movement section to the extras, fastboi was still a motor but it was for shooting rings, not moving
            
            if(gamepad1.x && !changed1){
                if(robot.fastBoi.getPower() > 0){
                    while (gamepad1.x) {

                        robot.fastBoi.setPower(0);
                        changed1 = true;

                    }
                }
                else if (robot.fastBoi.getPower() == 0){
                    while (gamepad1.x) {

                        robot.fastBoi.setPower(.85);
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

            if(gamepad1.left_trigger > .3 && !changed3){
                if(robot.grabby.getPosition() == .50){
                    while (gamepad1.left_trigger > .3) {

                        robot.grabby.setDirection(Servo.Direction.REVERSE);
                        robot.grabby.setPosition(.2);
                        changed3 = true;

                    }
                }
                else if (robot.grabby.getPosition() != .5) {
                    while (gamepad1.left_trigger > .3) {

                        robot.grabby.setDirection(Servo.Direction.FORWARD);
                        robot.grabby.setPosition(.5);
                        changed3 = true;

                    }
                }
            }
            else {
                changed3 = true;
            }

            if ( runtime.seconds() >= 90) {
                if (gamepad1.right_bumper ) {
                    while (gamepad1.right_bumper) {
                        robot.lifty.setPower(.5);
                    }

                } if (gamepad1.left_bumper){
                    while (gamepad1.left_bumper) {
                        robot.lifty.setPower(-.5);
                    }
                }
                else {
                robot.lifty.setPower(0);
    }


}
        }

    }
}

//this is stuff I'll have to do more research on myself and explain in person probably, as I really have never done teleop because I was the auto coder. I dont
//believe its super necessary to understand how all of this works, you just need to know where to replace values and stuff but I'll assist on that



