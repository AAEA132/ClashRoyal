package Model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    Clip clip;
    private static boolean isMute = false;
    private String status = "paused";
    private static SoundPlayer main, battle;

    public SoundPlayer(String filePath, int loop) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(loop);
    }

    public static void stop(String name) {
        SoundPlayer soundPlayer = SoundPlayer.getSoundByName(name);
        if (soundPlayer.status.equals("play"))
            soundPlayer.stop();
    }

    public static void play(String name) {
        SoundPlayer soundPlayer = SoundPlayer.getSoundByName(name);
        if (!soundPlayer.status.equals("play"))
            soundPlayer.play();
    }

    private void play() {
        status = "play";
        clip.start();
    }

    private void stop() {
        status = "paused";
        clip.stop();
    }

    public static SoundPlayer getSoundByName(String name) {

        if (isMute)
            return null;
        switch (name) {
            case "main":
                return main;
            case "battle":
                return battle;
        }
        return null;
    }

    public static void initializeGameSounds() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        main = new SoundPlayer("./src/main-music.wav",-1);
        main.play();
        battle = new SoundPlayer("./src/Music_2min_loop_battle_03(1).wav",-1);
        battle.stop();
    }

    public Clip getClip() {
        return clip;
    }
}
