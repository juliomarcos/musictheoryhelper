package juliorodrigues.musictheoryhelper.model;

import juliorodrigues.musictheoryhelper.model.error.InvalidNoteException;

/**
 *
 */

public class ToneSubtraction extends ToneOperation {

    public ToneSubtraction(ToneOperationParameters parameters) {
        super(parameters);
    }

    public Note result() throws InvalidNoteException{
        // convert to semi tone (C as 0)
        // sub
        // uncovert

        Note note = new Note(rootNote, accident);
        int noteInSemiTones = note.inSemiTones();
        noteInSemiTones -= toneUnit == ToneUnit.Tone ? (quantity * 2) : (quantity);
        return new Note(noteInSemiTones);
    }
}
