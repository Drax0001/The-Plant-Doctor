package com.example.theplantdoctor

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class Chatbot : AppCompatActivity() {
    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var questionSubmited: EditText
    private lateinit var btnSubmit: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var mDbRef: DatabaseReference
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot)

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid
        mDbRef = FirebaseDatabase.getInstance().getReference()

        questionSubmited = findViewById(R.id.et_question)
        btnSubmit = findViewById(R.id.btn_submit_question)
//        val botResponse=findViewById<TextView>(R.id.tv_response)
        messageRecyclerView = findViewById(R.id.rv_chat_bubbles)
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)
        messageRecyclerView.layoutManager = LinearLayoutManager(this)
        messageRecyclerView.adapter = messageAdapter

        mDbRef.child("chatBot").child(senderUid!!).child("Query")
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()

                    for(postSnapshot in snapshot.children){
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        btnSubmit.setOnClickListener {
            val question=questionSubmited.text.toString().trim()
            val queryObject = Message(question, senderUid)

            if(question.isNotEmpty()){
                getResponse(question) { response ->
                    runOnUiThread {
                        mDbRef.child("chatBot").child(senderUid).child("Query").push()
                            .setValue(queryObject).addOnSuccessListener {
                                val responseObject = Message(response, senderUid)
                                mDbRef.child("chatBot").child(senderUid).child("Query").push()
                                    .setValue(responseObject)
                            }
                    }
                }
            }
                questionSubmited.setText("")
        }
    }

    fun getResponse(question: String, callback: (String) -> Unit){
        val apiKey="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"
        val url="https://api.openai.com/v1/engines/text-davinci-003/completions"

        val requestBody="""
            {
            "prompt": "$question",
            "max_tokens": 500,
            "temperature": 0
            }
        """.trimIndent()

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error","API failed",e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body=response.body?.string()
                if (body != null) {
                    Log.v("data",body)
                }
                else{
                    Log.v("data","empty")
                }
                val jsonObject= JSONObject(body)
                val jsonArray: JSONArray =jsonObject.getJSONArray("choices")
                val textResult=jsonArray.getJSONObject(0).getString("text")
                callback(textResult)
            }
        })
    }
}
