package com.harjot.notesapp

import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.harjot.notesapp.databinding.ActivityMainBinding
import com.harjot.notesapp.databinding.BtnNegativeDialogBinding

class MainActivity : AppCompatActivity() {
    lateinit var userDatabase: UserDatabase
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: RecyclerAdapterClass
    var arrayList = ArrayList<UserModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        userDatabase = UserDatabase.getInstance(this)


        adapter = RecyclerAdapterClass(arrayList, this)
        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rv.adapter = adapter

        binding.btnFab.setOnClickListener {
            showDialogView()
        }

    }

    fun showDialogView(position: Int = -1) {
        var dialog = Dialog(this)
        var dialogBinding = BtnNegativeDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        if (position == -1) {
            dialogBinding.tvUpdateText.setText("Add Note")
            dialogBinding.btnDelete1.visibility = View.GONE
            dialogBinding.btnUpdate.setText("Add")
        } else {
            dialogBinding.tvUpdateText.setText("Update Note")
            dialogBinding.btnUpdate.setText("Update")
            dialogBinding.etItemName.setText(arrayList[position].Title)
            dialogBinding.etEnterText.setText(arrayList[position].Text)
        }
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )



        dialogBinding.btnUpdate.setOnClickListener {
            if (dialogBinding.etItemName.text.toString().trim().isNullOrEmpty()) {
                dialogBinding.etItemName.error = "Enter Name"
            } else if (dialogBinding.etEnterText.text.toString().trim().isNullOrEmpty()) {
                dialogBinding.etEnterText.error = "Enter Address"
            } else {
                if (position > -1) {
                    arrayList[position] = UserModel(
                        dialogBinding.etItemName.text.toString(),
                        dialogBinding.etEnterText.text.toString()
                    )
                    class saveDatabase : AsyncTask<Void, Void, Void>() {
                        override fun doInBackground(vararg params: Void?): Void? {
                            userDatabase.userDao().insertData(
                                UserEntity(
                                    Title = dialogBinding.etItemName.text.toString(),
                                    Text = dialogBinding.etEnterText.text.toString()
                                )
                            )
                            return null
                        }

                        override fun onPostExecute(result: Void?) {
                            super.onPostExecute(result)
                        }
                    }
                    saveDatabase().execute()
//                    getData()
                } else {
                    arrayList.add(
                        UserModel(
                            dialogBinding.etItemName.text.toString(),
                            dialogBinding.etEnterText.text.toString()
                        )
                    )
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }

        dialogBinding.btnDelete1.setOnClickListener {
            arrayList.removeAt(position)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.show()
    }

//    fun getData() {
//        arrayList.clear()
//        class getUserData : AsyncTask<Void, Void, Void>() {
//            override fun doInBackground(vararg params: Void?): Void? {
//                arrayList.addAll(userDatabase.userDao().getData())
//                return null
//            }
//
//            override fun onPostExecute(result: Void?) {
//                super.onPostExecute(result)
//                adapter.notifyDataSetChanged()
//            }
//
//        }
//        getUserData().execute()
//    }
}

