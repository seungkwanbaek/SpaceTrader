package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.Course;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;
import edu.gatech.cs2340.lab3newcomponents.viewmodels.StudentListingViewModel;

/**
 * This view displays the list of all students in a specific course
 * <p>
 * The course is whatever was selected in the MainActivity with the click listener
 */

public class CourseDetailActivity extends AppCompatActivity {

    public static final String STUDENT_DATA = "STUDENT_DATA";
    private static final int EDIT_REQUEST = 5;

    private Course course;
    private StudentListingViewModel viewModel;

    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        if (getIntent().hasExtra(MainActivity.EXTRA_COURSE)) {
            course = (Course) getIntent().getSerializableExtra(MainActivity.EXTRA_COURSE);
        } else {
            Log.d("APP", "INTERNAL ERROR< NO COURSE PASSED");
        }

        setTitle("Students Registered for: " + course.getSchool() + " " + course.getNumber());

        RecyclerView recyclerView = findViewById(R.id.student_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new StudentAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(StudentListingViewModel.class);

        viewModel.setCurrentCourse(course);

        adapter.setStudentList(viewModel.getRegisteredStudents());

        adapter.setOnStudentClickListener(new StudentAdapter.OnStudentClickListener() {
            @Override
            public void onStudentClicked(Student student) {
                Intent intent = new Intent(CourseDetailActivity.this, EditStudentActivity.class);
                intent.putExtra(STUDENT_DATA, student);
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("APP", "Handling Activity return");
        adapter.setStudentList(viewModel.getRegisteredStudents());
        adapter.notifyDataSetChanged();
        Log.d("APP", "Activity Result list: " + viewModel.getStudents());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("APP", "Resuming Course Detail");
        adapter.setStudentList(course.getRegisteredStudents());
        adapter.notifyDataSetChanged();
        Log.d("APP", "Student List: " + course.getRegisteredStudents());
    }
}
