package com.example.test2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.test2.databinding.FragmentMainMenuCultureBinding

private lateinit var linkbtn: Button
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainMenuculture.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenuculture : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMainMenuCultureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainMenuCultureBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_menu_culture, container, false)
        // Inflate the layout for this fragment
        val binding = FragmentMainMenuCultureBinding.inflate(inflater, container, false)

        val link2023 : Button = binding.root.findViewById(R.id.link2023)
        val link2022 : Button = binding.root.findViewById((R.id.link2022))
        val link2021 : Button = binding.root.findViewById(R.id.link2021)
        val link2020: Button = binding.root.findViewById(R.id.link2020)
        val link2019: Button = binding.root.findViewById(R.id.link2019)

        link2023.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://cs.inje.ac.kr/%EA%B5%90%EC%9C%A1%EC%9D%B4%EC%88%98-%EC%B2%B4%EA%B3%84%EB%8F%84/?uid=4633&mod=document&pageid=1"
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        link2022.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://cs.inje.ac.kr/%EA%B5%90%EC%9C%A1%EC%9D%B4%EC%88%98-%EC%B2%B4%EA%B3%84%EB%8F%84/?uid=4116&mod=document&pageid=1"
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        link2021.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://cs.inje.ac.kr/%EA%B5%90%EC%9C%A1%EC%9D%B4%EC%88%98-%EC%B2%B4%EA%B3%84%EB%8F%84/?uid=2699&mod=document&pageid=1"
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        link2020.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://cs.inje.ac.kr/%EA%B5%90%EC%9C%A1%EC%9D%B4%EC%88%98-%EC%B2%B4%EA%B3%84%EB%8F%84/?uid=1635&mod=document&pageid=1"
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        link2019.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://cs.inje.ac.kr/%EA%B5%90%EC%9C%A1%EC%9D%B4%EC%88%98-%EC%B2%B4%EA%B3%84%EB%8F%84/?uid=93&mod=document&pageid=1"
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainMenuSearch.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainMenuculture().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}