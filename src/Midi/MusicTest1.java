package Midi;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.lang.reflect.Executable;

public class MusicTest1 {
    public void paly() {
        try {
        Sequencer sequencer = MidiSystem.getSequencer();
        System.out.println("Мы получили синтезатор");
        } catch (Exception e) {
            System.out.println("Неудача");
        }
    }

    public static void main(String[] args) {
        MusicTest1 mt = new MusicTest1();
        mt.paly();
    }
}
