import javax.swing.*;

public class Main {
    private static Frames frames = new Frames();

    public static void main(String args []) {
        frames.initialFrame();
        while (!frames.haveConnected()) {
        }

        frames.queryFrame();
    }
}
