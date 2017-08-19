package juliorodrigues.musictheoryhelper.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import juliorodrigues.musictheoryhelper.R;
import juliorodrigues.musictheoryhelper.model.Accident;
import juliorodrigues.musictheoryhelper.model.NaturalNote;
import juliorodrigues.musictheoryhelper.model.Note;
import juliorodrigues.musictheoryhelper.model.Operator;
import juliorodrigues.musictheoryhelper.model.ToneAddition;
import juliorodrigues.musictheoryhelper.model.ToneOperation;
import juliorodrigues.musictheoryhelper.model.ToneSubtraction;
import juliorodrigues.musictheoryhelper.model.ToneUnit;
import juliorodrigues.musictheoryhelper.model.error.InvalidNoteException;

import static juliorodrigues.musictheoryhelper.model.ToneUnit.Tone;

public class ToneCalculatorActivity extends AppCompatActivity {

    private TextView cButton;
    private TextView dButton;
    private TextView eButton;
    private TextView fButton;
    private TextView gButton;
    private TextView aButton;
    private TextView bButton;

    private TextView addButton;
    private TextView subButton;

    private EditText quantityView;
    private TextView toneButton;
    private TextView semiToneButton;

    private TextView resultView;

    private ButtonRadioGroup<NaturalNote> notesButtonRadioGroup;
    private ButtonRadioGroup<Operator> operatorsButtonRadioGroup;
    private ButtonRadioGroup<ToneUnit> unitsButtonRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tone_calculator);

        // View Binding

        cButton = (TextView) findViewById(R.id.cButton);
        dButton = (TextView) findViewById(R.id.dButton);
        eButton = (TextView) findViewById(R.id.eButton);
        fButton = (TextView) findViewById(R.id.fButton);
        gButton = (TextView) findViewById(R.id.gButton);
        aButton = (TextView) findViewById(R.id.aButton);
        bButton = (TextView) findViewById(R.id.bButton);

        addButton = (TextView) findViewById(R.id.addButton);
        subButton = (TextView) findViewById(R.id.subButton);

        quantityView = (EditText) findViewById(R.id.quantityView);
        toneButton = (TextView) findViewById(R.id.toneButton);
        semiToneButton = (TextView) findViewById(R.id.semiToneButton);

        resultView = (TextView) findViewById(R.id.resultView);

        // Event Binding

        cButton.setOnClickListener(new SelectNoteListener(NaturalNote.C));
        dButton.setOnClickListener(new SelectNoteListener(NaturalNote.D));
        eButton.setOnClickListener(new SelectNoteListener(NaturalNote.E));
        fButton.setOnClickListener(new SelectNoteListener(NaturalNote.F));
        gButton.setOnClickListener(new SelectNoteListener(NaturalNote.G));
        aButton.setOnClickListener(new SelectNoteListener(NaturalNote.A));
        bButton.setOnClickListener(new SelectNoteListener(NaturalNote.B));

        addButton.setOnClickListener(new SelectOperatorListener(Operator.Add));
        subButton.setOnClickListener(new SelectOperatorListener(Operator.Sub));

        toneButton.setOnClickListener(new SelectUnitListener(Tone));
        semiToneButton.setOnClickListener(new SelectUnitListener(ToneUnit.SemiTone));

        // Radio Behavior

        final OnButtonRadioGroupPressedListener justCalculateAndShow = new OnButtonRadioGroupPressedListener() {
            @Override
            public void onButtonPressed() {
                calculateFinalNoteAndUpdateResultView();
            }
        };

        HashMap<NaturalNote, View> noteMap = new HashMap<>(7);
        noteMap.put(NaturalNote.C, cButton);
        noteMap.put(NaturalNote.D, dButton);
        noteMap.put(NaturalNote.E, eButton);
        noteMap.put(NaturalNote.F, fButton);
        noteMap.put(NaturalNote.G, gButton);
        noteMap.put(NaturalNote.A, aButton);
        noteMap.put(NaturalNote.B, bButton);
        notesButtonRadioGroup = new ButtonRadioGroup<>(noteMap, justCalculateAndShow);

        HashMap<Operator, View> operatorMap = new HashMap<>(2);
        operatorMap.put(Operator.Add, addButton);
        operatorMap.put(Operator.Sub, subButton);
        operatorsButtonRadioGroup = new ButtonRadioGroup<>(operatorMap, justCalculateAndShow);

        HashMap<ToneUnit, View> unitMap = new HashMap<>(2);
        unitMap.put(Tone, toneButton);
        unitMap.put(ToneUnit.SemiTone, semiToneButton);
        unitsButtonRadioGroup = new ButtonRadioGroup<>(unitMap, justCalculateAndShow);

        quantityView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateFinalNoteAndUpdateResultView();
            }
        });

        // Default Values

        notesButtonRadioGroup.select(NaturalNote.C);
        operatorsButtonRadioGroup.select(Operator.Add);
        unitsButtonRadioGroup.select(Tone);
    }

    public class SelectNoteListener implements View.OnClickListener {

        private final NaturalNote whichNote;

        public SelectNoteListener(NaturalNote whichNote) {
            this.whichNote = whichNote;
        }

        @Override
        public void onClick(View v) {
            notesButtonRadioGroup.select(whichNote);
        }
    }

    public class SelectOperatorListener implements View.OnClickListener {

        private final Operator whichOperator;

        public SelectOperatorListener(Operator whichOperator) {
            this.whichOperator = whichOperator;
        }

        @Override
        public void onClick(View v) {
            operatorsButtonRadioGroup.select(whichOperator);
        }
    }

    public class SelectUnitListener implements View.OnClickListener {

        private final ToneUnit whichUnit;

        public SelectUnitListener(ToneUnit whichUnit) {
            this.whichUnit = whichUnit;
        }

        @Override
        public void onClick(View v) {
            unitsButtonRadioGroup.select(whichUnit);
        }
    }

    class ButtonRadioGroup <T> {
        private final Map<T, View> association;
        private final OnButtonRadioGroupPressedListener listener;
        private View currentView;
        private T currentKey;

        ButtonRadioGroup(Map<T, View> association, OnButtonRadioGroupPressedListener listener) {
            this.association = association;
            this.listener = listener;
        }

        void select(T which) {
            for (Map.Entry<T, View> entry : association.entrySet()) {
                final View view = entry.getValue();
                view.setBackgroundColor(getColor(R.color.smokeWhite));
            }
            final View newSelection = association.get(which);
            newSelection.setBackground(getDrawable(R.drawable.card_button_selection_border));
            currentView = newSelection;
            currentKey = which;
            listener.onButtonPressed();
        }

        public T currentKey() {
            return currentKey;
        }
    }

    interface OnButtonRadioGroupPressedListener {
        void onButtonPressed();
    }

    private void calculateFinalNoteAndUpdateResultView() {
        try {
            int quantity = 0;
            final String rawQuantity = quantityView.getText().toString();
            if (rawQuantity.length() != 0) {
                quantity = Integer.parseInt(rawQuantity, 10);
            }
            final ToneOperation.ToneOperationParameters parameters = new ToneOperation.ToneOperationParameters(
                    notesButtonRadioGroup.currentKey(),
                    Accident.None,
                    unitsButtonRadioGroup.currentKey(),
                    quantity);

            ToneOperation toneOperation = operatorsButtonRadioGroup.currentKey() ==
                    Operator.Add ? new ToneAddition(parameters) :
                    new ToneSubtraction(parameters);

            Note result = toneOperation.result();
            resultView.setText(result.string());
        } catch (InvalidNoteException | NumberFormatException e) {
            resultView.setText("ERROR");
        }
    }
}
