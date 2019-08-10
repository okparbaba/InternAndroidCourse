package ml.happylifeforme.fragments.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

import ml.happylifeforme.fragments.R
import ml.happylifeforme.fragments.data.Pizza


class PizzaMenuFragment : Fragment() {

    private lateinit var itemsAdapter: ArrayAdapter<String>
    private var listener: OnItemSelectedListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsAdapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, Pizza.pizzaMenu)
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the xml file for the fragment
        return inflater.inflate(R.layout.fragment_pizza_menu, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val lvItems = view.findViewById<ListView>(R.id.lvItems)
        lvItems.adapter = itemsAdapter

        lvItems.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // go to activity to load pizza details fragment
            listener!!.onPizzaItemSelected(position) // (3) Communicate with Activity using Listener
        }
    }

    //--OnItemSelectedListener listener;
    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        // context instanceof YourActivity
        if (context is OnItemSelectedListener)
            this.listener = context // = (YourActivity) context
        else {
            throw ClassCastException(context!!.toString() + " must implement PizzaMenuFragment.OnItemSelectedListener")
        }
    }

    // Define the events that the fragment will use to communicate
    interface OnItemSelectedListener {
        // This can be any number of events to be sent to the activity
        fun onPizzaItemSelected(position: Int)
    }
}
