package com.example.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courseList = new ArrayList<>();

    private OnCourseClickListener listener;

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);

        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);

        holder.courseNumber.setText(course.getNumber());
        holder.courseName.setText(course.getName());
        holder.courseSchool.setText(course.getSchool().toString());


    }

    @Override
    public int getItemCount() {
        if (courseList == null) return 0;
        return courseList.size();
    }

    public void setCourseList(List<Course> courses) {
        courseList = courses;
        notifyDataSetChanged();
    }

    public Course getCourseAt(int position) {
        return courseList.get(position);
    }


    class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView courseSchool;
        private TextView courseName;
        private TextView courseNumber;


        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseSchool = itemView.findViewById(R.id.text_school);
            courseName = itemView.findViewById(R.id.text_course_name);
            courseNumber = itemView.findViewById(R.id.text_course_number);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onCourseClicked(courseList.get(position));
                    }
                }
            });

        }
    }

    public interface OnCourseClickListener {
        void onCourseClicked(Course course);
    }

    public void setOnCourseClickListener(OnCourseClickListener listener) {
        this.listener = listener;
    }

}
