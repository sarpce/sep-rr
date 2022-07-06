package utility;

/**
 * @author Isabel
 * replaces image paths with shorter strings
 */
public enum Images {
    /*
    public void rotate(ImageView imageView, int direction){
        imageView.setRotate(imageView.getRotate()+direction*90);
    }
    //TODO edit*/

    //cards
    CARD_BACK_CARD ("images/cards/CardBack.png"),
    //programmingCards
    AGAIN_CARD("images/cards/programmingCards/Again.png"),
    BACK_UP_CARD("images/cards/programmingCards/BackUp.png"),
    MOVE_ONE_CARD("images/cards/programmingCards/Move1.png"),
    MOVE_TWO_CARD("images/cards/programmingCards/Move2.png"),
    MOVE_THREE_CARD("images/cards/programmingCards/Move3.png"),
    POWER_UP_CARD("images/cards/programmingCards/PowerUp.png"),
    TURN_LEFT_CARD ("images/cards/programmingCards/TurnLeft.png"),
    TURN_RIGHT_CARD("images/cards/programmingCards/TurnRight.png"),
    U_TURN_CARD("images/cards/programmingCards/UTurn.png"),
    //damageCards
    public static String spamCard = "images/cards/damageCards/Spam.png";
    public static String trojanHorseCard = "images/cards/damageCards/TrojanHorse.png";
    public static String virusCard = "images/cards/damageCards/Virus.png";
    public static String wormCard = "images/cards/damageCards/Worm.png";
    //specialProgrammingCards
    public static String energyRoutineCard = "images/cards/specialProgrammingCards/EnergyRoutine.png";
    public static String repeatRoutineCard = "images/cards/specialProgrammingCards/RepeatRoutine.png";
    public static String sandboxRoutineCard = "images/cards/specialProgrammingCards/SandboxRoutine.png";
    public static String spamFolderCard = "images/cards/specialProgrammingCards/SpamFolder.png";
    public static String speedRoutineCard = "images/cards/specialProgrammingCards/SpeedRoutine.png";
    public static String weaselRoutineCard = "images/cards/specialProgrammingCards/WeaselRoutine.png";

    //board elements
    public static String antennaEastElement = "AntennaEast.png";
    public static String antennaNorthElement = "AntennaNorth.png";
    public static String antennaSouthElement = "AntennaSouth.png";
    public static String antennaWestElement = "AntennaWest.png";
    public static String beltBlueCrossElement = "BeltBlueCrossDown.png";
    public static String beltBlueCurveElement = "BeltBlueUpToLeft.png";
    public static String beltBlueElement = "BeltBlueUp.png";
    public static String beltGreenCurveElement = "BeltGreenUpToLeft.png";
    public static String beltGreenElement = "BeltGreenUp.png";
    public static String board2x8Element = "Board2x8.png";
    public static String checkPoint1Element = "CheckPoint1.png";
    public static String checkPoint2Element = "CheckPoint2.png";
    public static String checkPoint3Element = "CheckPoint3.png";
    public static String checkPoint4Element = "CheckPoint4.png";
    public static String energySpaceGreenElement = "EnergySpaceGreen.png";
    public static String gearClockwiseElement = "GearClockwise.png";
    public static String gearCounterclockwiseElement = "GearCounterclockwise.png";
    public static String laser1Element = "LaserUp.png";
    public static String pitElement = "Pit.png";
    public static String pushPanel24Element = "PushPanel24Top.png";
    public static String pushPanel135Element = "PushPanel135Top.png";
    public static String rebootElement = "Reboot.png";
    public static String startingPointElement = "StartingPoint.png";
    public static String wallElement = "WallTop.png";

    //racing courses
    public static String deathTrapMap = "images/racingCourses/DeathTrapMap.png";
    public static String dizzyHighwayMap = "images/racingCourses/DeathTrapMap.png";
    public static String extraCrispyMap = "images/racingCourses/DeathTrapMap.png";
    public static String lostBearingsMap = "images/racingCourses/DeathTrapMap.png";

    //robots
    public static String hammerBot = "images/robots/RobotPurpleHammer.png";
    public static String hulkBot = "images/robots/RobotRedHulkx90.png";
    public static String smashBot = "images/robots/RobotYellowSmash.png";
    public static String spinBot = "images/robots/RobotBlueSpin.png";
    public static String twonkyBot = "images/robots/RobotOrangeTwonky.png";
    public static String zoomBot = "images/robots/RobotGreenZoom.png";
    public String string;
    public Images(String string){
        this.string=string;
    }
    public String toString(){
        return this.string;
    }

    //others
    //TODO check importance of, increase and add other images to the class

}
