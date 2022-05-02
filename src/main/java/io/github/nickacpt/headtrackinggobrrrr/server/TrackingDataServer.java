package io.github.nickacpt.headtrackinggobrrrr.server;

import io.github.nickacpt.headtrackinggobrrrr.HeadTrackingGoBrrrr;
import java.net.DatagramSocket;

/**
 * This class is an instance of a UDP server that receives tracking data from opentrack.
 */
public class TrackingDataServer {

    private DatagramSocket socket;
    private boolean running = false;

    public TrackingDataServer() {
        try {
            socket = new DatagramSocket(56867);
            HeadTrackingGoBrrrr.LOGGER.info("Tracking data server started at port " + socket.getLocalPort() + ".");
        } catch (Exception e) {
            HeadTrackingGoBrrrr.LOGGER.error("Failed to create socket for tracking data server.", e);
        }
    }

    /**
     * Starts the server.
     */
    public void start() {
        running = true;
        new TrackingDataServerThread(this).start();
    }

    /**
     * Stops the server.
     */
    public void stop() {
        running = false;
        socket.close();
    }

    public boolean isRunning() {
        return running;
    }

    public DatagramSocket getSocket() {
        return socket;
    }
}
