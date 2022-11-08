package com.example.week3;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.week3.databinding.ActivityChatRoomBinding;
import com.example.week3.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {

    ChatRoomViewModel chatModel;

    ArrayList<ChatMessage> allMessages ;
    ActivityChatRoomBinding binding;
    private RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        allMessages = chatModel.messages.getValue();
        if(allMessages == null)
        {
            chatModel.messages.postValue( allMessages = new ArrayList<ChatMessage>() );
        }

        binding.recycleView.setLayoutManager( new LinearLayoutManager(this));

        //add the message typed in the edittext to the allMessages
        binding.sendButton.setOnClickListener( click -> {

            String message = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format( new Date());

            allMessages.add( new ChatMessage( message, currentDateandTime,true));
            myAdapter.notifyItemInserted(allMessages.size()-1);
            binding.textInput.setText("");

        });

        binding.receiveButton.setOnClickListener( click -> {

            String message = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format( new Date());

            allMessages.add( new ChatMessage( message, currentDateandTime,false));
            myAdapter.notifyItemInserted(allMessages.size()-1);
            binding.textInput.setText("");

        });

        binding.recycleView.setAdapter( myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View root;
                if(viewType == 0)
                    root = getLayoutInflater().inflate(R.layout.sent_message, parent, false);
                else
                    root = getLayoutInflater().inflate( R.layout.receive_message, parent, false);

                return new MyRowHolder( root );
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage object = allMessages.get(position);
                holder.messageText.setText(object.getMessage());
                holder.timeText.setText(object.getTimeSent());

            }

            @Override
            public int getItemCount() {

                return allMessages.size();
            }

            @Override
            public int getItemViewType(int position) {
                ChatMessage object = allMessages.get(position);

                if(object.getIsSentButton())
                    return 0; //0 represents send, text on the left
                else
                    return 1;
            }
        });
    }


    class MyRowHolder extends RecyclerView.ViewHolder{
        TextView messageText;
        TextView timeText;
        public MyRowHolder(@NonNull View itemView){
            super(itemView);
            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
        }

    }

}