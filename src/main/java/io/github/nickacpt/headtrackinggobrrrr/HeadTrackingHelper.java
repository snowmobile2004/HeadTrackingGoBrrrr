package io.github.nickacpt.headtrackinggobrrrr;

public class HeadTrackingHelper {
    // Head Tracking data.
    // X
    // Y
    // Z
    // Yaw
    // Pitch
    // Roll

    public static double getX() {
        return HeadTrackingGoBrrrr.trackingData[0];
    }

    public static double getY() {
        return HeadTrackingGoBrrrr.trackingData[1];
    }

    public static double getZ() {
        return HeadTrackingGoBrrrr.trackingData[2];
    }

    public static double getYaw() {
        return HeadTrackingGoBrrrr.trackingData[3];
    }

    public static double getPitch() {
        return HeadTrackingGoBrrrr.trackingData[4];
    }

    public static double getRoll() {
        return HeadTrackingGoBrrrr.trackingData[5];
    }
}
