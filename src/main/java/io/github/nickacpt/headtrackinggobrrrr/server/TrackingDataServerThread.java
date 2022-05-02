package io.github.nickacpt.headtrackinggobrrrr.server;

import io.github.nickacpt.headtrackinggobrrrr.HeadTrackingGoBrrrr;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TrackingDataServerThread extends Thread {
    private final TrackingDataServer server;

    public TrackingDataServerThread(TrackingDataServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (server.isRunning()) {
            try {
                byte[] data = new byte[48];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                server.getSocket().receive(packet);

                ByteBuffer wrapped = ByteBuffer.wrap(data).order(ByteOrder.nativeOrder());
                for (int i = 0; i < 6; i++) {
                    HeadTrackingGoBrrrr.trackingData[i] = Double.longBitsToDouble(wrapped.getLong((i * 8)));
                }

                HeadTrackingGoBrrrr.onReceivedHeadTrackData();

            } catch (Exception e) {
                HeadTrackingGoBrrrr.LOGGER.error("Failed to receive tracking data.", e);
            }
        }
    }
}
