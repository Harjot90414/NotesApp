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

class MainActivity : AppCompatActivity(),NotesInterface {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: RecyclerAdapterClass
    var arrayList = ArrayList<UserEntity>()
    lateinit var userDatabase: UserDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userDatabase = UserDatabase.getInstance(this)
        supportActionBar?.hide()
        adapter = RecyclerAdapterClass(arrayList, this)
        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rv.adapter = adapter
        getData()

        binding.btnFab.setOnClickListener {
            var dialog = Dialog(this)
            var dialogBinding = BtnNegativeDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialogBinding.tvUpdateText.setText("Add Note")
            dialogBinding.btnDelete1.visibility = View.GONE
            dialogBinding.btnUpdate.setText("Add")

            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.btnUpdate.setOnClickListener {
                if (dialogBinding.etItemName.text.toString().trim().isNullOrEmpty()) {

                } else if (dialogBinding.etEnterText.text.toString().trim().isNullOrEmpty()) {
                } else {
                    var userEntity = UserEntity()
                    userEntity.Title = dialogBinding.etItemName.text.toString()
                    userEntity.Text = dialogBinding.etEnterText.text.toString()

                    class saveDatabase : AsyncTask<Void, Void, Void>() {
                        override fun doInBackground(vararg p0: Void?): Void? {
                            userDatabase.userDao()
                                .insertData(userEntity)
                            return null
                        }

                        override fun onPostExecute(result: Void?) {


                        }
                    }
                    saveDatabase().execute()
                    getData()
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.show()
        }
    }


    fun getData() {
        arrayList.clear()
        class getUserData : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
               arrayList.addAll(
                   userDatabase.userDao().getData())
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                adapter.notifyDataSetChanged()
            }

        }
        getUserData().execute()
    }

    override fun click(userEntity: UserEntity) {
        var dialog = Dialog(this)
        var dialogBinding = BtnNegativeDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.tvUpdateText.setText("Update Note")
        dialogBinding.btnUpdate.setText("Update")
        dialogBinding.etItemName.setText(userEntity.Title)
        dialogBinding.etEnterText.setText(userEntity.Text)
        dialogBinding.btnUpdate.setOnClickListener {
            if (dialogBinding.etItemName.text.toString().trim().isNullOrEmpty()) {

            } else if (dialogBinding.etEnterText.text.toString().trim().isNullOrEmpty()) {
            } else {
                userEntity.Title = dialogBinding.etItemName.text.toString()
                userEntity.Text = dialogBinding.etEnterText.text.toString()
                dialog.dismiss()
                adapter.notifyDataSetChanged()
            }

        }
        dialog.show()
    }
}


