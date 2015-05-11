package vs.com.essprototype;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class PersonalLeaveFragment extends Fragment {
    private FragmentActivity myContext;
    private Spinner spinner, spinner1, spinner2;
    private Button btn1, btn2,submit;
    private Calendar cal1,cal2;
    public PersonalLeaveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personal, container, false);
        getActivity().setTitle("On behalf Leave");
        btn1 = (Button) rootView.findViewById(R.id.date);
        btn2 = (Button) rootView.findViewById(R.id.date1);
        rootView.findViewById(R.id.date).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        rootView.findViewById(R.id.date1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePicker1();
            }
        });
        spinner = (Spinner) rootView.findViewById(R.id.spinner);
        spinner1 = (Spinner) rootView.findViewById(R.id.spinner1);
        spinner2 = (Spinner) rootView.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.selection_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.reason_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.session_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter1);
        // Inflate the layout for this fragment

        submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ListEmployee.class);
                startActivity(intent);
            }
        });


        return rootView;
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(myContext.getSupportFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;

            try {
                date1 = sdf.parse(year + "-" + monthOfYear + "-" + dayOfMonth);

            } catch (ParseException e) {
                e.printStackTrace();
            }

             cal1 = Calendar.getInstance();
            cal1.setTime(date1);

            if (validate()) {
                btn1.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }

        }
    };
    private void showDatePicker1() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate1);
        date.show(myContext.getSupportFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = null;
            try {

                date2 = sdf.parse(year + "-" + monthOfYear + "-" + dayOfMonth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            if (validate()) {
                btn2.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }

        }

    };

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
        getActivity().setTitle("Personal Leave");
    }
    private boolean validate(){
        if (cal1.after(cal2)) {
            System.out.println("Date1 is after Date2");
            return false;
        }
        if(cal1 != null){
            return true;
        }
        if (cal1.before(cal2)) {
            System.out.println("Date1 is before Date2");
            return true;
        }

        if (cal1.equals(cal2)) {
            System.out.println("Date1 is equal Date2");
            return true;
        }
        return false;
    }
}
