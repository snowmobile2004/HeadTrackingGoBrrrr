package io.github.nickacpt.headtrackinggobrrrr;

import io.github.nickacpt.headtrackinggobrrrr.server.TrackingDataServer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeadTrackingGoBrrrr implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("headtrackinggobrrrr");
    private static TrackingDataServer trackingDataServer;

    public static double[] trackingData = new double[6];

    private static double lastHeadTrackYaw = 0;
    private static double lastHeadTrackPitch = 0;

    public static void onReceivedHeadTrackData() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        double yaw = HeadTrackingHelper.getYaw();
        player.setYaw((float) (player.getYaw() + yaw - lastHeadTrackYaw));
        lastHeadTrackYaw = yaw;

        double pitch = HeadTrackingHelper.getPitch();
        player.setPitch((float) (player.getPitch() + pitch - lastHeadTrackPitch));
        lastHeadTrackPitch = pitch;

    }

    @Override
    public void onInitializeClient() {
        // Setup the tracking data server.
        trackingDataServer = new TrackingDataServer();
        // Start the tracking data server.
        trackingDataServer.start();

        // Register the event handler for when the client is about to close.
        ClientLifecycleEvents.CLIENT_STOPPING.register(this::onClientStopping);
    }

    private void onClientStopping(MinecraftClient minecraftClient) {
        // Stop the tracking data server.
        trackingDataServer.stop();
    }
}
