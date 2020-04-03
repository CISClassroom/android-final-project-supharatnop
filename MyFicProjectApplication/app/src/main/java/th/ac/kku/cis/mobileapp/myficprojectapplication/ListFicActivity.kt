package th.ac.kku.cis.mobileapp.myficprojectapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_ep.*
import kotlinx.android.synthetic.main.activity_add_fic.*
import kotlinx.android.synthetic.main.activity_list_fic.*
import kotlinx.android.synthetic.main.list_fic_layout.*

class ListFicActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    lateinit var adapter: ToDoAddNameFic
    private var listViewNamefic: ListView? = null
    var toDoItemList: MutableList<AddModelNameFic>? = null

    var mAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_fic)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        text_name_login1.text = user!!.email

        imageView1.setOnClickListener {
            mAuth!!.signOut()
            Toast.makeText(this,"Signed Out", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        button_addname.setOnClickListener {
            startActivity(Intent(this,AddFicActivity ::class.java))
        }

        listViewNamefic = findViewById<View>(R.id.listname_view) as ListView
        toDoItemList = mutableListOf<AddModelNameFic>()
        adapter = ToDoAddNameFic(this, toDoItemList!!)
        listViewNamefic!!.setAdapter(adapter)

        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.child("AddNameFic").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val items = dataSnapshot.children.iterator()

                // Check if current database contains any collection
                if (items.hasNext()) {
                    while (items.hasNext()) {
                        val toDoListindex = items.next()
                        val map = toDoListindex.getValue() as HashMap<String, Any>

                        // add data to object
                        val todoItem = AddModelNameFic.create()

                        todoItem.AddNameFic = map.get("addNameFic") as String?

                        toDoItemList!!.add(todoItem);
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        listname_view.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as AddModelNameFic
            //Toast.makeText(this,selectedItem,Toast.LENGTH_SHORT).show()
            val intent = Intent(this, EpActivity::class.java)

            intent.putExtra("name", selectedItem.AddNameFic)

            startActivity(intent)
        }

//        button1.setOnClickListener {
//            delete(AddModelNameFic)
//        }
    }
//    private fun delete(AddModelNameFic : AddNameFic ){
//        val mydatabase = FirebaseDatabase.getInstance().getReference("AddNameFic")
//        mydatabase.child(AddModelNameFic.AddNameFic).removeValue()
//    }

}

