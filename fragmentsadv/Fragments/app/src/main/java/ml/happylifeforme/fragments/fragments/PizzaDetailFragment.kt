package ml.happylifeforme.fragments.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_pizza_detail.*
import ml.happylifeforme.fragments.R
import ml.happylifeforme.fragments.data.Pizza


class PizzaDetailFragment : Fragment() {
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            // Get back arguments
            if (arguments != null) {
                position = arguments!!.getInt("position", 0)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the xml file for the fragment
        return inflater.inflate(R.layout.fragment_pizza_detail, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // update view
        tvTitle.text = Pizza.pizzaMenu[position]
        tvDetails.text = Pizza.pizzaDetails[position]
    }

}
