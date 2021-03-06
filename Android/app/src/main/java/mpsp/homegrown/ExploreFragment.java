package mpsp.homegrown;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {

    public static ExploreFragment newInstance() {
        ExploreFragment fragment = new ExploreFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        //Getting buttons
        LinearLayout k1 = (LinearLayout) view.findViewById(R.id.kosarica1);
        LinearLayout k2 = (LinearLayout) view.findViewById(R.id.kosarica2);
        LinearLayout k3 = (LinearLayout) view.findViewById(R.id.kosarica3);
        LinearLayout k4 = (LinearLayout) view.findViewById(R.id.kosarica4);
        //LinearLayout k5 = (LinearLayout) view.findViewById(R.id.kosarica5);

        k1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start checkout activity
                Intent intent = new Intent(getActivity(), CheckOutActivity.class);
                intent.putExtra("idKosarice", 1);
                startActivity(intent);
            }
        });
        k2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start checkout activity
                Intent intent = new Intent(getActivity(), CheckOutActivity.class);
                intent.putExtra("idKosarice", 2);
                startActivity(intent);
            }
        });
        k3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start checkout activity
                Intent intent = new Intent(getActivity(), CheckOutActivity.class);
                intent.putExtra("idKosarice", 3);
                startActivity(intent);
            }
        });
        k4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start checkout activity
                Intent intent = new Intent(getActivity(), CheckOutActivity.class);
                intent.putExtra("idKosarice", 4);
                startActivity(intent);
            }
        });

        return view;
    }
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public ExploreFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ExploreFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ExploreFragment newInstance(String param1, String param2) {
//        ExploreFragment fragment = new ExploreFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_explore, container, false);
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
