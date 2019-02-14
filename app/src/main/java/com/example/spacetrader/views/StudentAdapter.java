package com.example.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList = new ArrayList<>();

    private OnStudentClickListener listener;

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);

        return new StudentViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);

        Log.d("APP", "Binding: " + position + " " + studentList.get(position));

        holder.studentMajor.setText(student.get_major().toString());
        holder.studentName.setText(student.get_name());
        holder.studentClass.setText(student.get_classStanding().toString());

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void setStudentList(List<Student> students) {
        studentList = students;
        notifyDataSetChanged();
    }


    class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView studentName;
        private TextView studentMajor;
        private TextView studentClass;


        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.text_student_name);
            studentMajor = itemView.findViewById(R.id.text_student_major);
            studentClass = itemView.findViewById(R.id.text_student_class);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onStudentClicked(studentList.get(position));
                    }
                }
            });

        }
    }

    public interface OnStudentClickListener {
        void onStudentClicked(Student student);
    }

    public void setOnStudentClickListener(OnStudentClickListener listener) {
        this.listener = listener;
    }


}
