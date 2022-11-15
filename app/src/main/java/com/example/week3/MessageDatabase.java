package com.example.week3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ChatMessage.class}, version = 2)
public abstract class MessageDatabase extends RoomDatabase {

    // methods for interacting with the database
    public abstract ChatMessageDAO cmDAO();

}