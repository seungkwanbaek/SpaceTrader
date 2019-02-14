package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.ClassStanding;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;
import edu.gatech.cs2340.lab3newcomponents.viewmodels.EditAddStudentViewModel;

public class EditStudentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditAddStudentViewModel viewModel;

    /* ************************
        Widgets we will need for binding and getting information
     */
    private TextView idField;
    private EditText nameField;
    private Spinner majorSpinner;

    private Spinner classSpinner;

    /* ************************
       Keeping track of spinner changes.  Not really used right now, probably don't need this.
     */
    private String _major = "NA";

    /* ***********************
       Data for student being edited.
     */
    private Student student;

    /* ***********************
       flag for whether this is a new student being added or an existing student being edited;
     */
    private boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TBD", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         * Grab the dialog widgets so we can get info for later
         */
        nameField = (EditText) findViewById(R.id.student_name_input);
        majorSpinner = (Spinner) findViewById(R.id.spinner);
        classSpinner = (Spinner) findViewById(R.id.spinner2);

        idField = (TextView) findViewById(R.id.student_id_field);
        Button button = findViewById(R.id.add_button);

        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Student.legalMajors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(adapter);

        /*
          Set up the adapter to display the allowable class standings in the spinner2
         */
        ArrayAdapter<ClassStanding> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Arrays.asList(ClassStanding.values()));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(adapter2);

        /*
           If a student has been passed in, this was an edit, if not, this is a new add
         */
        if (getIntent().hasExtra(CourseDetailActivity.STUDENT_DATA)) {
            student = (Student) getIntent().getSerializableExtra(CourseDetailActivity.STUDENT_DATA);
            majorSpinner.setSelection(Student.findPosition(student.get_major()));
            classSpinner.setSelection(student.get_classStanding().ordinal());

            editing = true;
            button.setText("Update");
            setTitle("Editing Existing Student");
        } else {
            // do I really need to add class b/c we added extra constructor in Student class
            student = new Student("Bob", "CS", ClassStanding.FRESHMAN);
            editing = false;
            button.setText("Add");
            setTitle("Adding New Student");
        }

        nameField.setText(student.get_name());
        idField.setText("" + student.getId());

        viewModel = ViewModelProviders.of(this).get(EditAddStudentViewModel.class);

    }

    /**
     * Button handler for the add new student button
     *
     * @param view the button
     */
    public void onAddPressed(View view) {
        Log.d("Edit", "Add/Update Student Pressed");


        student.setName(nameField.getText().toString());
        student.setMajor((String) majorSpinner.getSelectedItem());

        student.setClassStanding((ClassStanding) classSpinner.getSelectedItem() );

        Log.d("Edit", "Got new student data: " + student);

        if (!editing) {
            viewModel.addStudent(student);
        } else {
            viewModel.updateStudent(student);
        }

        finish();
    }

    /**
     * Button handler for cancel
     *
     * @param view the button pressed
     */
    public void onCancelPressed(View view) {
        Log.d("Edit", "Cancel Student");
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _major = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        _major = "NA";
    }
}
