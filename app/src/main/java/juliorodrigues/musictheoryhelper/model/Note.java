package juliorodrigues.musictheoryhelper.model;

import juliorodrigues.musictheoryhelper.error.NotImplementedExcepetion;
import juliorodrigues.musictheoryhelper.model.error.InvalidNoteException;

/**
 *
 */

public class Note {

    public final NaturalNote root;
    public final Accident accident;

    public Note(NaturalNote root, Accident accident) {
        this.root = root;
        this.accident = accident;
    }

    public Note(int noteInSemiTones) throws InvalidNoteException {
        noteInSemiTones %= 12;
        switch (noteInSemiTones) {
            case -1:
                root = NaturalNote.B;
                accident = Accident.None;
                break;
            case -2:
                root = NaturalNote.B;
                accident = Accident.Flat;
                break;
            case -3:
                root = NaturalNote.A;
                accident = Accident.None;
                break;
            case -4:
                root = NaturalNote.A;
                accident = Accident.Flat;
                break;
            case -5:
                root = NaturalNote.G;
                accident = Accident.None;
                break;
            case -6:
                root = NaturalNote.G;
                accident = Accident.Flat;
                break;
            case -7:
                root = NaturalNote.F;
                accident = Accident.None;
                break;
            case -8:
                root = NaturalNote.E;
                accident = Accident.Sharp;
                break;
            case -9:
                root = NaturalNote.E;
                accident = Accident.Flat;
                break;
            case -10:
                root = NaturalNote.D;
                accident = Accident.None;
                break;
            case -11:
                root = NaturalNote.D;
                accident = Accident.Flat;
                break;
            case 0:
                root = NaturalNote.C;
                accident = Accident.None;
                break;
            case 1:
                root = NaturalNote.C;
                accident = Accident.Sharp;
                break;
            case 2:
                root = NaturalNote.D;
                accident = Accident.None;
                break;
            case 3:
                root = NaturalNote.D;
                accident = Accident.Sharp;
                break;
            case 4:
                root = NaturalNote.E;
                accident = Accident.None;
                break;
            case 5:
                root = NaturalNote.F;
                accident = Accident.None;
                break;
            case 6:
                root = NaturalNote.F;
                accident = Accident.Sharp;
                break;
            case 7:
                root = NaturalNote.G;
                accident = Accident.None;
                break;
            case 8:
                root = NaturalNote.G;
                accident = Accident.Sharp;
                break;
            case 9:
                root = NaturalNote.A;
                accident = Accident.None;
                break;
            case 10:
                root = NaturalNote.A;
                accident = Accident.Sharp;
                break;
            case 11:
                root = NaturalNote.B;
                accident = Accident.None;
                break;
            default:
                throw new InvalidNoteException();
        }
    }

    /**
     * Gives preference to #.
     * @return
     */
    public String string() throws InvalidNoteException {
        StringBuilder sb = new StringBuilder();
        switch (root) {
            case C:
                sb.append("C");
                break;
            case D:
                sb.append("D");
                break;
            case E:
                sb.append("E");
                break;
            case F:
                sb.append("F");
                break;
            case G:
                sb.append("G");
                break;
            case A:
                sb.append("A");
                break;
            case B:
                sb.append("B");
                break;
        }
        switch (accident) {
            case Sharp:
                sb.append("#");
                break;
            case Flat:
                sb.append("b");
                break;
            case DoubleSharp:
                sb.append("##");
                break;
            case None:
                break;
            case DoubleFlat:
                sb.append("bb");
                break;
        }
        return sb.toString();
    }

    public String string(Accident accidentPreference) {
        throw new NotImplementedExcepetion();
    }

    public int inSemiTones() {
        int noteInSemiTones = 0;
        switch (root) {
            case C:
                noteInSemiTones = 0;
                break;
            case D:
                noteInSemiTones = 2;
                break;
            case E:
                noteInSemiTones = 4;
                break;
            case F:
                noteInSemiTones = 5;
                break;
            case G:
                noteInSemiTones = 7;
                break;
            case A:
                noteInSemiTones = 9;
                break;
            case B:
                noteInSemiTones = 11;
                break;
        }
        return noteInSemiTones;
    }
}
