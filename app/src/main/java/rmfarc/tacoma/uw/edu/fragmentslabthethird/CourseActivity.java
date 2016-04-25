package rmfarc.tacoma.uw.edu.fragmentslabthethird;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CourseActivity extends AppCompatActivity implements CourseListFragment.OnCourseListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_viewing);

        if(savedInstanceState == null){
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.view_course_list_id, new CourseListFragment());
            // Commit the transaction
            transaction.commit();
        }
    }

    @Override
    public void onListFragmentInteraction(int position) {
        // Capture the student fragment from the activity layout
        CourseDetailFragment courseDetailFragment = (CourseDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.course_item_fragment);

        if (courseDetailFragment != null) {
            courseDetailFragment.updateCourseItemView(position);

        } else {
            courseDetailFragment = new CourseDetailFragment();
            Bundle args = new Bundle();
            args.putInt(CourseDetailFragment.ARG_POSITION, position);
            courseDetailFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.view_course_list_id, courseDetailFragment)
                    .addToBackStack(null);

            // Commit the transaction
            transaction.commit();

        }
    }
}
