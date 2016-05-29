package com.anja.task1.app.data;

/**
 * Created by Anna on 29.05.2016.
 */
public interface FBProfileDao {

    void saveProfile(FBProfile fbProfile);

    FBProfile loadProfile();
}
