package juliorodrigues.musictheoryhelper.model;

import juliorodrigues.musictheoryhelper.model.error.InvalidNoteException;

/**
 *
 */

public abstract class ToneOperation {

    public final NaturalNote rootNote;
    public final Accident accident;
    public final ToneUnit toneUnit;
    public final int quantity;

    public ToneOperation(ToneOperationParameters parameters) {
        this.rootNote = parameters.rootNote;
        this.accident = parameters.accident;
        this.toneUnit = parameters.toneUnit;
        this.quantity = parameters.quantity;
    }

    public abstract Note result() throws InvalidNoteException;

    public static class ToneOperationParameters {
        public final NaturalNote rootNote;
        public final Accident accident;
        public final ToneUnit toneUnit;
        public final int quantity;

        public ToneOperationParameters(NaturalNote rootNote, Accident accident, ToneUnit toneUnit, int quantity) {
            this.rootNote = rootNote;
            this.accident = accident;
            this.toneUnit = toneUnit;
            this.quantity = quantity;
        }
    }
}
