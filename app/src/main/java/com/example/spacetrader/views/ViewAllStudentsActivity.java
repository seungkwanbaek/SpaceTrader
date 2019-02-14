package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;
import edu.gatech.cs2340.lab3newcomponents.viewmodels.StudentListingViewModel;

public class ViewAllStudentsActivity extends AppCompatActivity {

    private static final int ADD_STUDENT_REQUEST = 3;

    private StudentListingViewModel  viewModel;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_students);

        FloatingActionButton add =  findViewById(R.id.button_add_student);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewAllStudentsActivity.this, EditStudentActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.student_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new StudentAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(StudentListingViewModel.class);

        adapter.setStudentList(viewModel.getStudents());

        Log.d("APP", viewModel.getStudents().toString());

        adapter.setOnStudentClickListener(new StudentAdapter.OnStudentClickListener() {
            @Override
            public void onStudentClicked(Student student) {
                Intent intent = new Intent(ViewAllStudentsActivity.this, EditStudentActivity.class);
                intent.putExtra(CourseDetailActivity.STUDENT_DATA, student);
                startActivityForResult(intent, ADD_STUDENT_REQUEST);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        adapter.setStudentList(viewModel.getStudents());

        if (requestCode == ADD_STUDENT_REQUEST) {
            adapter.setStudentList(viewModel.getStudents());
        }
    }
}
