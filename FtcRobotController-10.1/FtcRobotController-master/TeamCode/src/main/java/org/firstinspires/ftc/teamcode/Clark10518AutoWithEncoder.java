package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "Clark15024n Auto By Encoder", group = "auto")
public class Clark10518AutoWithEncoder extends LinearOpMode {

        Clark10518HWMap robot = new Clark10518HWMap();
        ElapsedTime runtime = new ElapsedTime();

        // Calculate the COUNTS_PER_INCH for your specific drive train.
        // Go to your motor vendor website to determine your motor's COUNTS_PER_MOTOR_REV
        // For external drive gearing, set DRIVE_GEAR_REDUCTION as needed.
        // For example, use a value of 2.0 for a 12-tooth spur gear driving a 24-tooth spur gear.
        // This is gearing DOWN for less speed and more torque.
        // For gearing UP, use a gear ratio less than 1.0. Note this will affect the direction of wheel rotation.
        ///For drive train motors
        static final double     COUNTS_PER_MOTOR_REV    = 28 ;    // gear ratio * tick per = 20 * 28 = 560
        static final double     DRIVE_GEAR_REDUCTION    = 20 ;     //  External Gearing.
        static final double     WHEEL_DIAMETER_INCHES   = 3.0 ;     // For figuring circumference
        static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                (WHEEL_DIAMETER_INCHES * 3.1415);
        static final double CORRECTION = 0.83333;
        static final double     DRIVE_SPEED             = 1;
        static final double     TURN_SPEED              = 0.5;

        static final double LIFT_GEAR_RATIO = 13.7;
        static final double LIFT_COUNTS_PER_INCH = (438 * LIFT_GEAR_RATIO) / (Math.PI * 2) * 2; //* 28;

        // The values for the claw
        double clawOpenPos = 1.0;
        double clawClosePos = 0;
        double currentClawPos = 1;

        // The values for the WristY    `
        double clawWristYUp = 0.5;
        double clawWristYDown = 0.0;
        double currentWristYPos = 0.5;

        // The values for the WristX
        double clawWristXVertical = 1.0;
        double clawWristXHorizontal = 0.5;
        double currentWristXPos = 1.0;


    @Override
        public void runOpMode(){
            robot.Map(hardwareMap);
            telemetry.addData("Time", runtime.time());
            telemetry.addData("Starting at",  "%7d :%7d",
                    robot.frontLeftChassis.getCurrentPosition(),
                    robot.frontRightChassis.getCurrentPosition());
            telemetry.update();

            robot.frontRightChassis.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontLeftChassis.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backRightChassis.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backLeftChassis.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            //robot.LiftA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //robot.LiftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            waitForStart();

            // Step through each leg of the path,

            // Initialize the claw
            robot.wristX.setPosition(clawWristXVertical);`i
            robot.wristY.setPosition(clawWristYUp);
            robot.claw.setPosition(clawClosePos);

            // Move the extender up
            robot.armRotator.setPower(-0.4);
            robot.armExtender.setPower(0.6);
            sleep(1650);
            robot.armExtender.setPower(0.2); // holds the extender in position

            encoderDrive(DRIVE_SPEED,-29,-29,50000);
            sleep(500);

            robot.armExtender.setPower(-0.7);
            sleep(1000);
            robot.armExtender.setPower(0.2);
            robot.claw.setPosition(clawOpenPos);
            sleep(100);
            robot.armExtender.setPower(-0.3);
            sleep(2000);

           encoderDrive(DRIVE_SPEED,5,5,10000);
           rightTurn();
           encoderDrive(DRIVE_SPEED,-40,-40,10000);
           leftTurn();

           encoderDrive(0.8,-30,-30,10000);
           rightTurn();
           encoderDrive(0.8,-12, -12, 10000);
           leftTurn();
           encoderDrive(0.8, 46,46,10000);
           encoderDrive(0.8, -46,-46,10000);
           rightTurn();
           encoderDrive(0.8,-10,-10,10000);
           leftTurn();
           encoderDrive(0.8, 46,46,10000);
           encoderDrive(0.8, -12,-12,10000);
           rightTurn();
           rightTurn();
           encoderDrive(DRIVE_SPEED, -24,-24,10000);
           robot.claw.setPosition(clawClosePos);
           encoderDrive(DRIVE_SPEED,18,18,10000);
           rightTurn();
           encoderDrive(DRIVE_SPEED,-40,-40,10000);
           rightTurn();

           // Scoring Another Specimen
          robot.armRotator.setPower(-0.4);
          robot.armExtender.setPower(0.6);
          sleep(1650);
          robot.armExtender.setPower(0.2); // holds the extender in position
          encoderDrive(DRIVE_SPEED,-29,-29,50000);
          robot.armExtender.setPower(-0.7);
          sleep(1000);
          robot.armExtender.setPower(0.2);
          robot.claw.setPosition(clawOpenPos);
          robot.armExtender.setPower(-0.3);
          sleep(2000);




            //encoderDrive(DRIVE_SPEED,  20,  20, 5.0);       //Move toward submersible - Total distance to submersible is 25.5

            //deliver_specimen(4.21);

            //TODO: make this a function - move to get new piece
            //encoderDrive(DRIVE_SPEED, 10, 10, 5.0);       //reverse
            //turn to left
            //move back
            //turn left
            //move forward
            //lift
            //close claw
            //TODO: make this a function - move to the submersible
            // reverse
            //turn right
            //forward
            //turn right
            //forward
            //deliver_specimen(10);
            //encoderDrive(TURN_SPEED,   12, -12, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout

        }

        /**
         * This function lifts the arm,
         * moves forward to the submersible,
         * lowers the arm
         * opens the claw
         * @param distance_to_submersible - how far to go forward once the lift goes up
         */
        /*
        public void deliver_specimen(double distance_to_submersible) {
            encoderLift(DRIVE_SPEED*.1, -0.01, 0.4);                          // Lift the arm
            sleep(600);   // optional pause after each move.

            // Note: Reverse movement is obtained by setting a negative distance (not speed)
            encoderDrive(DRIVE_SPEED,  distance_to_submersible,  distance_to_submersible, 5.0);     // Move the rest of the way to the submersible
            encoderLift(DRIVE_SPEED*.3, 0.01, 0.2);                         // Drop arm
            robot.claw.setPosition(0.5);                                                 //open claw
        }


         */

    /*
        public void encoderLift(double speed,
                                double inches,
                                double timeoutS) {

            int newLeftTarget;
            int newRightTarget;

            // Ensure that the OpMode is still active
            if (opModeIsActive()) {
                // Determine new target position, and pass to motor controller
                newLeftTarget = robot.LiftA.getCurrentPosition() + (int) (inches * LIFT_COUNTS_PER_INCH);
                newRightTarget = robot.LiftB.getCurrentPosition() + (int) (inches * LIFT_COUNTS_PER_INCH);

                robot.LiftA.setTargetPosition(newLeftTarget);
                robot.LiftB.setTargetPosition(newRightTarget);
                robot.LiftA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.LiftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // reset the timeout time and start motion.
                runtime.reset();
                robot.LiftA.setPower(Math.abs(speed));
                robot.LiftB.setPower(Math.abs(speed));


                // keep looping while we are still active, and there is time left, and both motors are running.
                // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
                // its target position, the motion will stop.  This is "safer" in the event that the robot will
                // always end the motion as soon as possible.
                // However, if you require that BOTH motors have finished their moves before the robot continues
                // onto the next step, use (isBusy() || isBusy()) in the loop test.
                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (robot.LiftA.isBusy() && robot.LiftB.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("Lift running to", " %7d :%7d", newLeftTarget, newRightTarget);
                    telemetry.addData("Currently at", " at %7d :%7d",
                            robot.LiftA.getCurrentPosition(), robot.LiftB.getCurrentPosition());
                    telemetry.update();

                }
            }
            sleep(600);   // optional pause after each move.

        }
*/
        /**
         *  Method to perform a relative move, based on encoder counts.
         *  Encoders are not reset as the move is based on the current position.
         *  Move will stop if any of three conditions occur:
         *  1) Move gets to the desired position
         *  2) Move runs out of time
         *  3) Driver stops the OpMode running.
         */
        public void encoderDrive(double speed,
                                 double leftInches, double rightInches,
                                 double timeoutS) {
            int newLeftTarget;
            int newRightTarget;

            // Ensure that the OpMode is still active
            if (opModeIsActive()) {

                // Determine new target position, and pass to motor controller
                newLeftTarget = robot.frontLeftChassis.getCurrentPosition() + (int) ((leftInches * COUNTS_PER_INCH) * CORRECTION);
                newRightTarget = robot.frontRightChassis.getCurrentPosition() + (int) ((rightInches * COUNTS_PER_INCH) * CORRECTION);
                robot.frontLeftChassis.setTargetPosition(newLeftTarget);
                robot.frontRightChassis.setTargetPosition(newRightTarget);
                robot.backLeftChassis.setTargetPosition(newLeftTarget - 12);
                robot.backRightChassis.setTargetPosition(newRightTarget - 12);

                robot.frontLeftChassis.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRightChassis.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeftChassis.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRightChassis.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // reset the timeout time and start motion.
                runtime.reset();
                robot.frontLeftChassis.setPower(speed);
                robot.frontRightChassis.setPower(Math.abs(speed));
                robot.backLeftChassis.setPower(speed);
                robot.backRightChassis.setPower(Math.abs(speed));

                // keep looping while we are still active, and there is time left, and both motors are running.
                // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
                // its target position, the motion will stop.  This is "safer" in the event that the robot will
                // always end the motion as soon as possible.
                // However, if you require that BOTH motors have finished their moves before the robot continues
                // onto the next step, use (isBusy() || isBusy()) in the loop test.
                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (robot.frontLeftChassis.isBusy() && robot.frontRightChassis.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("Running to", " %7d :%7d", newLeftTarget, newRightTarget);
                    telemetry.addData("Currently at", " at %7d :%7d",
                            robot.frontLeftChassis.getCurrentPosition(), robot.frontRightChassis.getCurrentPosition());
                    telemetry.update();
                }

                // Stop all motion;
                robot.frontLeftChassis.setPower(0);
                robot.frontRightChassis.setPower(0);
                robot.backLeftChassis.setPower(0);
                robot.backRightChassis.setPower(0);

                // Turn off RUN_TO_POSITION
                robot.frontLeftChassis.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRightChassis.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeftChassis.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRightChassis.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                //sleep(250);   // optional pause after each move.

            }
            }
            public void rightTurn(){
            encoderDrive(DRIVE_SPEED,-24,24,10000);
        }
            public void leftTurn(){
            encoderDrive(DRIVE_SPEED,24,-24,10000);
            }


}
