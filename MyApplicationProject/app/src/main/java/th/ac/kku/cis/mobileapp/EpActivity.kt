package th.ac.kku.cis.mobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_ep.*
import kotlinx.android.synthetic.main.activity_list_fic.*

class EpActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    lateinit var adapter: ToDoItemAdapter
    private var listViewItems: ListView? = null
    var toDoItemList: MutableList<AddFicModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ep)

        var name = getIntent().getStringExtra("name")
        button_AddEp.setOnClickListener {
            var i = Intent(this@EpActivity, AddEpActivity::class.java)
            i.putExtra("name",name)
            startActivity(i)

        }

        list.setOnItemClickListener{ parent, view, position, id ->
            val intent = Intent(this, ShowEpActivity::class.java)
            val showdetai = parent.getItemAtPosition(position) as AddFicModel
            intent.putExtra("epName",showdetai.EpName.toString())
            intent.putExtra("story",showdetai.Story.toString())
            startActivity(intent)
        }

        listViewItems = findViewById<View>(R.id.list) as ListView
        toDoItemList = mutableListOf<AddFicModel>()
        adapter = ToDoItemAdapter(this, toDoItemList!!)
        listViewItems!!.setAdapter(adapter)

        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.child("EpFic").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = dataSnapshot.children.iterator()

                var name = getIntent().getStringExtra("name")

                // Check if current database contains any collection
                if (items.hasNext()) {
                    while (items.hasNext()) {
                        val toDoListindex = items.next()
                        val map = toDoListindex.getValue() as HashMap<String, Any>

                        if (map.get("topicName") == name) {
                            // add data to object
                            val todoItem = AddFicModel.create()
                            todoItem.EpName = map.get("epName") as String?
                            todoItem.Story = map.get("story") as String?
//                            todoItem.Id = map.get("Id") as String?
                            toDoItemList!!.add(todoItem);
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

    }
}

