package Swing;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BeatBox {
    JPanel mainJpanel;
    ArrayList<JCheckBox> checkboxList;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame theFrame;

    String[] instrumentNames  = {"Bass Drum", "Closed Hi-Hat",
            "Open Hi-Hat","Acoustic Snare", "Crash Cymbal", "Hand Clap",
            "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga",
            "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
            "Open Hi Conga"};
    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

    public static void main(String[] args) {

    }

    public void buildGUI() {

    }

    public void setUpMidi() {

    }

    public void buildTrackAndStart() {

    }
    public class MyStartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    public class MyStopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    public class MyTempoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    public class MyDownTempoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public void makeTracks(int[] list) {

    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;

        return event;
    }
}
