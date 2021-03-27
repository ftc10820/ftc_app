/*
Copyright 2020 FIRST Tech Challenge Team FTC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@TeleOp

public class Qualifier2DriverOpFinal extends LinearOpMode {
    
    //motors
    private DcMotor drive1;
    private DcMotor drive2;
    private DcMotor drive3;
    private DcMotor drive4;
    private DcMotor intake;
    private DcMotor shooter1;
    private DcMotor wafflemotor;
    private DcMotor intakemotor;

    
    //servos
    private Servo waffle1;
    private Servo waffle2;
    private Servo liftgate1;
    private Servo liftgate2;
    private Servo liftgate3;
    
    //sensors
    /*
    private DistanceSensor distanceRight;
    private DistanceSensor distanceFront;
    private DistanceSensor distanceLeftFront;
    private DistanceSensor distanceLeftBack;
    private DistanceSensor distanceBack;
    private ColorSensor launchLineRight;
    private ColorSensor launchLineLeft;
    */
    @Override
    public void runOpMode() {
        
        
        initializeRobot();
        liftgate3.setPosition(0.5);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            
            
               
            wafflemotor.setPower(gamepad2.right_stick_y * 0.5); 
            if(gamepad1.a){
                drive1.setPower(-1);
                drive2.setPower(-1);
                drive3.setPower(1);
                drive4.setPower(1);
                sleep(100);
                drive1.setPower(0);
                drive2.setPower(0);
                drive3.setPower(0);
                drive4.setPower(0);
                
            }
            
             
            if (gamepad2.dpad_up){
                
            intakemotor.setPower(-1);
            
           } else if (gamepad2.dpad_left){
               
                intakemotor.setPower(0);
                
           } 
        
            if (gamepad2.dpad_right) {
                
                liftgate1.setPosition(0.5);
                liftgate2.setPosition(0.5);
                
            } else if (gamepad2.dpad_down) {
                
                liftgate1.setPosition(0.75);
                liftgate2.setPosition(0.75);
                
            } 
            
            if(gamepad2.start) {
                //liftgate3.setPosition(1);
                intake.setPower(1);
                intakemotor.setPower(0.75);
                liftgate1.setPosition(0.75);
                liftgate2.setPosition(0.75);

            } else if (gamepad2.back) {
                intake.setPower(0);
                intakemotor.setPower(0);
                liftgate1.setPosition(0.5);
                liftgate2.setPosition(0.5);
                //liftgate3.setPosition(0.5);
                


            }
            
            if (gamepad2.x) {
                
                intake.setPower(1);

            } else if (gamepad2.y && liftgate3.getPosition() == 0.5) {
                liftgate3.setPosition(1);
                shooter1.setPower(-0.9);

            } else if(gamepad2.y && liftgate3.getPosition() == 1){
                liftgate3.setPosition(0.5);
                shooter1.setPower(0);
                
            }
            else if (gamepad2.b){
                shooter1.setPower(0);

            } else if (gamepad2.a) {
                
                intake.setPower(0);
                
            }
            
            
            if (gamepad2.left_bumper) {
                
                waffle1.setPosition(1);
                waffle2.setPosition(-1);
                telemetry.addData("wafflestatus", "closed");
                telemetry.update();
                
            } else if (gamepad2.right_bumper) {
                
                waffle1.setPosition(-1);
                waffle2.setPosition(1);

            } else {
                
                waffle1.setPosition(0.5);
                waffle2.setPosition(0.5);

            }
            
            
            if (gamepad1.right_bumper) {
                
            // go right
            drive1.setPower(-1);
            drive2.setPower(1);
            drive3.setPower(-1);
            drive4.setPower(1);
                
            } else if (gamepad1.left_bumper) {
                
            // go left
            drive1.setPower(1);
            drive2.setPower(-1);
            drive3.setPower(1);
            drive4.setPower(-1);
                
            } else {
                    
            drive1.setPower(-gamepad1.right_stick_y);
            drive2.setPower(-gamepad1.right_stick_y);
            drive3.setPower(-gamepad1.left_stick_y);
            drive4.setPower(-gamepad1.left_stick_y);
                
            }
            
            //telemetry.addData("alpha:", launchLine.alpha());
            
            
            telemetry.addData("Status", "Running");
            telemetry.update();
            
        }
    }
    
       
        private void initializeRobot() {
    
        //hardware maps of motors
        drive1 = hardwareMap.dcMotor.get("drive1");
        drive2 = hardwareMap.dcMotor.get("drive2");
        drive3 = hardwareMap.dcMotor.get("drive3");
        drive4 = hardwareMap.dcMotor.get("drive4");
        intake = hardwareMap.dcMotor.get("intake");
        shooter1 = hardwareMap.dcMotor.get("shooter1");
        wafflemotor = hardwareMap.dcMotor.get("wafflemotor");
        intakemotor = hardwareMap.get(DcMotor.class, "intakemotor");

    
        //hardware maps of servos

        waffle1 = hardwareMap.servo.get("waffle1");
        waffle2 = hardwareMap.servo.get("waffle2");
        liftgate1 = hardwareMap.get(Servo.class, "liftgate1");
        liftgate2 = hardwareMap.get(Servo.class, "liftgate2");
        liftgate3 = hardwareMap.get(Servo.class, "liftgate3");

        
        // sensors
        //launchLine = hardwareMap.get(ColorSensor.class, "color");
        /*
        launchLineLeft = hardwareMap.get(ColorSensor.class, "colorLeft");
        launchLineRight = hardwareMap.get(ColorSensor.class, "colorRight");
        distanceLeftFront = hardwareMap.get(DistanceSensor.class, "distanceLeftFront");
        distanceLeftBack = hardwareMap.get(DistanceSensor.class, "distanceLeftBack");
        distanceFrontRight = hardwareMap.get(DistanceSensor.class, "distanceFront" );
        distanceRight = hardwareMap.get(DistanceSensor.class, "distanceRight" );
        distanceBack = hardwareMap.get(DistanceSensor.class, "distanceBack");
        */
        //directions
        drive1.setDirection(DcMotor.Direction.REVERSE);
        drive2.setDirection(DcMotor.Direction.FORWARD);
        drive3.setDirection(DcMotor.Direction.REVERSE);
        drive4.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.FORWARD);
        shooter1.setDirection(DcMotor.Direction.REVERSE);
        wafflemotor.setDirection(DcMotor.Direction.FORWARD);
        intakemotor.setDirection(DcMotor.Direction.FORWARD);

        wafflemotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();

    }
}

