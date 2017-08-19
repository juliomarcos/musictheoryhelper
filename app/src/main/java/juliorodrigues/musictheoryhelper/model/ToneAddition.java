package juliorodrigues.musictheoryhelper.model;

import juliorodrigues.musictheoryhelper.model.error.InvalidNoteException;

/**
 *
 */

public class ToneAddition extends ToneOperation {

    public ToneAddition(ToneOperationParameters parameters) {
        super(parameters);
    }

    public Note result() throws InvalidNoteException {
        // convert to semi tone (C as 0)
        // add
        // uncovert

        Note note = new Note(rootNote, accident);
        int noteInSemiTones = note.inSemiTones();
        noteInSemiTones += toneUnit == ToneUnit.Tone ? (quantity * 2) : (quantity);
        return new Note(noteInSemiTones);
    }
}
