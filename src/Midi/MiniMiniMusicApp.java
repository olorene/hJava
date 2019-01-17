package Midi;

import javax.sound.midi.*;

public class MiniMiniMusicApp {
    public static void main(String[] args) {
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        if (args.length < 2) {
            System.out.println("Не забудьте аргументы для инструмента и ноты");
            // Run with arguments ALT+SHIFT+F10, Right, E, Enter, Tab, enter your command line parameters, Enter
            // 102 30
            // 80 20
            // 40 70
        } else {
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            mini.play(instrument, note);
        }
    }
    public void play(int instrument, int note) {
        try {
            // Создаем и открываем синтезатор
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            // Создаем последовательность и дорожку (Аргументы черная магия)
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            ShortMessage first = new ShortMessage();
            // 192 - сообщение обб изменении инструмента; 1 - номер канала; instrument - замена на этот инструмент
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 1); // Tick - длительность
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, note ,100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, note, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);

            player.setSequence(seq);
            player.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
