package edu.ucsb.cs.cs185.frickenhamster.food.pref;

import android.app.*;
import android.os.*;
import android.support.v7.widget.*;
import android.view.*;
import edu.ucsb.cs.cs185.frickenhamster.food.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hamster
 * Date: 6/7/2015
 * Time: 7:49 PM
 */
public class PrefDialog extends DialogFragment
{
	
	private RecyclerView recyclerView;
	private PrefAdapter adapter;
	
	public PrefDialog()
	{

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View v = inflater.inflate(R.layout.pref_dialog, null);
		
		recyclerView = (RecyclerView) v.findViewById(R.id.pref_recycler_view);
		
		FoodApplication app = (FoodApplication) getActivity().getApplicationContext();
		adapter = new PrefAdapter(app.getFoodManager().getCuisineList());
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adapter);
		
		builder.setView(v);
		
		builder.setTitle(R.string.pref_title);
		
		AlertDialog dialog = builder.create();
		return dialog;
		
		//return super.onCreateDialog(savedInstanceState);
	}
}
